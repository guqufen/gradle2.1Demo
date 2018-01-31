package net.fnsco.config;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.bigdata.api.dto.MercQueryDTO;
import net.fnsco.bigdata.api.merchant.MerchantCoreService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.trading.service.report.ReportStatService;
import net.fnsco.trading.service.third.reCharge.PrepaidRefillService;
import net.fnsco.trading.service.third.reCharge.RechargeOrderService;
import net.fnsco.trading.service.third.reCharge.entity.RechargeOrderDO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;

@EnableScheduling
public class TimerConfig {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TradeOrderDAO tradeOrderDAO;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ReportStatService reportStatService;
	@Autowired
	private RechargeOrderService rechargeOrderService;
	@Autowired
	private PrepaidRefillService prepaidRefillService;
	@Autowired
	private MerchantCoreService merchantCoreService;

	/**
	 * spring boot 定时任务
	 */
	// @Scheduled(cron = "0 * * * * ?")
	public void reportCurrentTime() {
		System.out.println("定时任务执行");
		reportStatService.createReportData(DateUtils.getDayStartTime(-3), DateUtils.getDayStartTime(0));
	}

	/**
	 * 每隔3秒定时查询中信银行扫一扫支付结果 时间取当前时间的前一天日期
	 */
	@Scheduled(cron = "*/3 * * * * ?")
	public void queryZxyhTrade() {

		// Date date = DateUtils.getStartDayTime(new Date());
		List<String> orderList = tradeOrderDAO.queryOnGoing(TradeConstants.ChannelTypeEnum.ZXYH_PAY.getCode(),
				DateUtils.getTimeYesterday(new Date()));
		for (String orderNo : orderList) {
			paymentService.passivePayResult(orderNo);
		}
	}

	/**
	 * 每隔3秒查询手机充值支付结果(渠道返回系统内部异常需要查询该笔交易结果) 时间取当前时间的前一天日期
	 */
	@Scheduled(cron = "*/3 * * * * ?")
	public void queryFlowCharge() {

		List<RechargeOrderDO> orderList = rechargeOrderService.queryPhoneCharge(DateUtils.getTimeYesterday(new Date()));// 查询
		for (RechargeOrderDO rechargeOrderDO : orderList) {

			// 话费充值
			if ("0".equals(rechargeOrderDO.getType())) {

				prepaidRefillService.orderSta(rechargeOrderDO);
				// 流量充值
			} else if ("1".equals(rechargeOrderDO.getType())) {

				prepaidRefillService.queryFlowResult(rechargeOrderDO);
			}

		}
	}

	/**
	 * buildReportDate:(报表统计生成)
	 *
	 * @param 设定文件
	 * @return void DOM对象
	 * @author tangliang
	 * @date 2017年12月14日 下午4:35:13
	 */
	@Scheduled(cron = "1 0 0 * * ?") // 每天凌晨00:00:01秒执行一次
	public void buildReportDate() {
		reportStatService.createReportData(DateUtils.getDayStartTime(-1), DateUtils.getDayStartTime(0));
	}

	/**
	 * 查询入件中信银行商户状态
	 * 
	 * @return void DOM对象
	 * @throws @since
	 *             CodingExample Ver 1.1
	 */
	// @Scheduled(cron="0 1 * * * ?") //每个小时的1秒执行，每60分钟执行一次
	@Scheduled(cron = "0 * * * * ?") // 每一分钟的0秒执行，每分钟执行一次
	public void updateMercStatus() {
		logger.error("查询中信商户是否入件成功...开始...");
		List<MercQueryDTO> list = this.merchantCoreService.getMercList();
		paymentService.queryAloneMchtInfoList(list);
		logger.error("查询中信商户是否入件成功...结束...");
	}
}
