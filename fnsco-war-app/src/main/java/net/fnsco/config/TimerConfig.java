package net.fnsco.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.api.push.AppPushService;

@EnableScheduling
public class TimerConfig {
    
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    
     
    /**
     * spring boot 定时任务
     */
//    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        logger.info("定时任务执行");
    }
}
