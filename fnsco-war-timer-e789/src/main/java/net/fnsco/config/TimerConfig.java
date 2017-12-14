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
     * 每隔3秒定时查询查询中信银行扫一扫支付结果
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void queryZxyhTrade(){

    	Date date = DateUtils.getStartDayTime(new Date());
    	List<TradeOrderDO> tradeList= tradeOrderDAO.queryOnGoing(DateUtils.getStartDayTime(new Date()));
    	for (TradeOrderDO tradeDO : tradeList) {
    		paymentService.PassivePayResult(tradeDO);
		}
    }
    
    @Scheduled(cron = "1 0 0 * * ?") //每天凌晨00:00:01秒执行一次
    public void buildReportDate() {
    	reportStatService.createReportData(DateUtils.getDayStartTime(-1), DateUtils.getDayStartTime(0));
    }
}
