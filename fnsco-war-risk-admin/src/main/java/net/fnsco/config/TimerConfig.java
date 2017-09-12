package net.fnsco.config;

import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class TimerConfig {

    /**
     * spring boot 定时任务
     * 2.扣款时间：早上 9点  下午 1点   下午5点 ，若第一次扣款没有成功，第二次继续请求，第二次没有成功，第三次继续请求。
     */
    //    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
    }

}
