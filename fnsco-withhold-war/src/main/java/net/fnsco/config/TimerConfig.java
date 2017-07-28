package net.fnsco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.withhold.service.trade.WithholdService;

@EnableScheduling
public class TimerConfig {
    @Autowired
    private WithholdService withholdService;

    /**
     * spring boot 定时任务
     * 2.扣款时间：早上 9点  下午 1点   下午5点 ，若第一次扣款没有成功，第二次继续请求，第二次没有成功，第三次继续请求。
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
    }
    /**
     * 
     * collectPayment9:(第一次扣款)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void collectPayment9() {
        System.out.println("定时任务执行9");
        withholdService.collectPayment(0);
    }
/**
 * 
 * collectPayment13:(第二次扣款)
 *   void    返回Result对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
    @Scheduled(cron = "0 0 13 * * ?")
    public void collectPayment13() {
        System.out.println("定时任务执行13");
        withholdService.collectPayment(1);
    }
/**
 * 
 * collectPayment17:(第三次扣款)
 *   void    返回Result对象
 * @throws 
 * @since  CodingExample　Ver 1.1
 */
    @Scheduled(cron = "0 0 17 * * ?")
    public void collectPayment17() {
        System.out.println("定时任务执行17");
        withholdService.collectPayment(2);
    }
    /**
     * 
     * collectPaymentRemind12:(扣款三天前提醒)
     *   void    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void collectPaymentRemind12() {
        System.out.println("定时任务执行12");
        withholdService.collectPayment(1);
    }
}
