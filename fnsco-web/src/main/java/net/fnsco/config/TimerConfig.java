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
    
    @Autowired
    private AppPushService appPushService;
    
    /**
     * spring boot 定时任务
     */
//    @Scheduled(cron = "0 */5 * * * ?")
    public void reportCurrentTime() {
        System.out.println("定时任务执行");
    }
    
    /**
     * pushMagTimer:(这里用一句话描述这个方法的作用)
     *    设定文件
     * @return void    DOM对象
     * @throws 
     * @author tangliang
     * @since  CodingExample　Ver 1.1
     */
    @Scheduled(cron = "0 * * * * ?")//每一分钟的0秒执行，每分钟执行一次
    public void pushMagTimer(){
        appPushService.sendSystemMgs();
    }
}
