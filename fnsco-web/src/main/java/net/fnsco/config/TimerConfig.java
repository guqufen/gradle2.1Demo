package net.fnsco.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.api.push.AppPushService;
import net.fnsco.api.sysappmsg.SysAppMsgService;
import net.fnsco.service.domain.SysAppMessage;

@EnableScheduling
public class TimerConfig {
    
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SysAppMsgService sysAppMsgService;
    
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
    @Scheduled(cron = "0 * * * * ?")
    public void pushMagTimer(){
        List<SysAppMessage> datas = sysAppMsgService.queryExecuteData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  sendTime = null;
        for (SysAppMessage sysAppMessage : datas) {
            sendTime = sdf.format(sysAppMessage.getSendTime());//定义传递给友盟的时间
            try {
                if(sysAppMessage.getPhoneType() == 2){
                    
                    
                    int iosStatus = appPushService.sendIOSBroadcast(sysAppMessage.getContent(), sendTime);
                    if (iosStatus == 200) {
                        logger.warn("ios信息推送成功");
                        sysAppMessage.setStatus(1);
                    } else {
                        logger.warn("ios信息推送失败");
                        sysAppMessage.setStatus(0);
                    }
                }else if(sysAppMessage.getPhoneType() == 1){
                    int androidStatus = appPushService.sendAndroidBroadcast(sysAppMessage.getContent(), sendTime);
                    if (androidStatus == 200) {
                        logger.warn("安卓信息推送成功");
                        sysAppMessage.setStatus(1);
                    } else {
                        logger.warn("安卓信息推送失败");
                        sysAppMessage.setStatus(0);
                    }
                }
                sysAppMsgService.updateByPrimaryKeySelective(sysAppMessage);
               
            } catch (Exception e) {
                logger.error("广播推送异常", e);
                e.printStackTrace();
                
            }
            
        }
    }
}
