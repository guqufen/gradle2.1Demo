package net.fnsco.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.merchant.IntegralRuleService;
import net.fnsco.order.api.push.AppPushService;
import net.fnsco.order.api.trade.TradeReportService;
import net.fnsco.order.service.trade.TradeOrderService;

@EnableScheduling
public class TimerConfig {

    public Logger              logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppPushService     appPushService;
    @Autowired
    private TradeReportService tradeReportService;
    @Autowired
    private IntegralRuleService integralRuleService;
    @Autowired
    private TradeOrderService tradeOrderService;
    /**
     * spring boot 定时任务
     */
    //    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
    }

    /**
     * pushMagTimer:(这里用一句话描述这个方法的作用) 扫描待推送任务、合符条件执行推送任务
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @author tangliang
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "0 * * * * ?") //每一分钟的0秒执行，每分钟执行一次
    public void pushMagTimer() {
        appPushService.sendSystemMgs();
    }

    /**
     * pushFirstFailMsg:(这里用一句话描述这个方法的作用) 执行失败次数为1的任务
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "30 * * * * ?") //每一分钟的30秒执行，每分钟执行一次
    public void pushFirstFailMsg() {
        appPushService.sendFailMgs(1);
    }

    /**
     * pushSecondFailMsg:(这里用一句话描述这个方法的作用)执行失败次数为2的任务
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "0 1 * * * ?") //每个小时的1秒执行，每60分钟执行一次
    public void pushSecondFailMsg() {
        appPushService.sendFailMgs(2);
    }

    /**
     * 
     * pushThirdFailMsg:(这里用一句话描述这个方法的作用)执行失败次数为3的任务
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "0 0 0/2 * * ?") //每隔2个小时执行一次
    public void pushThirdFailMsg() {
        appPushService.sendFailMgs(3);
    }

    /**
     * buildTradeReportData:(这里用一句话描述这个方法的作用) 执行生成交易统计数据
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "1 0 0 * * ?") //每天凌晨00:00:01秒执行一次
    public void buildTradeReportData() {
        tradeReportService.buildTradeReportDaTa(DateUtils.getTimeByDayStr(-1), DateUtils.getTimeByDayStr(0));
    }
    
    /**
     * pushWeeklyData:(定时推送周报)    设定文件
     * @author    tangliang
     * @date      2017年8月31日 上午11:37:51
     * @return void    DOM对象
     */
    @Scheduled(cron = "0 0 8 ? * MON") //每周一上午八点推送周报
    public void pushWeeklyData() {
        appPushService.sendWeeklyDataMgs();
    }
    
    /**
     * pushMagTimer:(统计积分)
     *
     * @param      设定文件
     * @return void    DOM对象
     * @author tangliang
     * @date   2017年11月2日 上午9:38:37
     */
    @Scheduled(cron = "5 5 * * * ?") //每个小时的第五分钟第五秒执行，每小时执行一次
    //@Scheduled(cron = "0 */1 * * * ?")
    public void countMerchantEntityScores() {
        logger.error("定时统计积分开始");
    	integralRuleService.countTradeDataScores(DateUtils.getTimeByMinuteDate(-60), new Date());
    }
    /*
     * spring boot 定时更新订单
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void getOrderStatues() {
        tradeOrderService.updateOrderStatues("");
    }
    
    /**
     * spring boot 同步订单交易数据
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void syncOrderTradeData() {
        logger.error("同步分期付订单交易数据定时任务启动");
        tradeOrderService.syncOrderTradeData();
    }
}
