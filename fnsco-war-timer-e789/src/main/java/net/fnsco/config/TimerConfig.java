package net.fnsco.config;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.core.utils.DateUtils;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.entity.TradeOrderDO;
import net.fnsco.trading.service.pay.channel.zxyh.PaymentService;
import net.fnsco.trading.service.report.ReportStatService;

@EnableScheduling
public class TimerConfig {

    public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TradeOrderDAO tradeOrderDAO;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReportStatService reportStatService;

    /**
     * spring boot 定时任务
     */
//        @Scheduled(cron = "0 * * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
        reportStatService.createReportData(DateUtils.getDayStartTime(-3), DateUtils.getDayStartTime(0));
    }
    
    /**
     * 每隔3秒定时查询中信银行扫一扫支付结果
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void queryZxyhTrade(){

    	Date date = DateUtils.getStartDayTime(new Date());
    	List<TradeOrderDO> tradeList= tradeOrderDAO.queryOnGoing("05", DateUtils.getStartDayTime(new Date()));
    	for (TradeOrderDO tradeDO : tradeList) {
    		paymentService.PassivePayResult(tradeDO);
		}
    }
    
    /**
     * 每隔3秒查询手机充值支付结果(渠道返回系统内部异常需要查询该笔交易结果)
     */
    public void queryFlowCharge(){
    	TradeOrderDO tradeOrderDO = new TradeOrderDO();
    	tradeOrderDO.setChannelType("80");//80-表示余额查询
    	tradeOrderDO.setTxnSubType(12);//12-表示购买话费
//    	tradeOrderDO.set
    }
    
    /**
     * buildReportDate:(报表统计生成)
     *
     * @param      设定文件
     * @return void    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午4:35:13
     */
    @Scheduled(cron = "1 0 0 * * ?") //每天凌晨00:00:01秒执行一次
    public void buildReportDate() {
    	reportStatService.createReportData(DateUtils.getDayStartTime(-1), DateUtils.getDayStartTime(0));
    }
}
