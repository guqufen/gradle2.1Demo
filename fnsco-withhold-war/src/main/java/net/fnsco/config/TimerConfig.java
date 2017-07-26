package net.fnsco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.withhold.service.trade.TradeDataService;

@EnableScheduling
public class TimerConfig {
    @Autowired
    private TradeDataService tradeDataService;

    /**
     * spring boot 定时任务
     * 2.扣款时间：早上 9点  下午 1点   下午5点 ，若第一次扣款没有成功，第二次继续请求，第二次没有成功，第三次继续请求。
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void collectPayment9() {
        System.out.println("定时任务执行");
        tradeDataService.collectPayment(0);
    }

    @Scheduled(cron = "0 0 13 * * ?")
    public void collectPayment13() {
        System.out.println("定时任务执行");
        tradeDataService.collectPayment(1);
    }

    @Scheduled(cron = "0 0 17 * * ?")
    public void collectPayment17() {
        System.out.println("定时任务执行");
        tradeDataService.collectPayment(2);
    }
}
