package net.fnsco.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
public class TimerConfig {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * spring boot 定时任务
     */
    //    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
    }

}
