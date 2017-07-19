package net.fnsco.config;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.fnsco.api.appuser.AppUserService;
import net.fnsco.api.dto.AppPushMsgInfoDTO;
import net.fnsco.api.dto.PushMsgInfoDTO;
import net.fnsco.api.push.AppPushService;
import net.fnsco.api.sysappmsg.SysAppMsgService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.JsonPluginsUtil;
import net.fnsco.core.utils.OssUtil;
import net.fnsco.service.domain.AppUser;
import net.fnsco.service.domain.SysAppMessage;

@EnableScheduling
public class TimerConfig {
    
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SysAppMsgService sysAppMsgService;
    
    @Autowired
    private AppPushService appPushService;
    
    @Autowired
    private AppUserService appUserService;
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
        List<SysAppMessage> datas = sysAppMsgService.queryExecuteData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  sendTime = null;
        List<AppUser>  users = null;
        if(datas.size()> 0 ){
            users = appUserService.queryAllPushUser();
        }
//        for (SysAppMessage sysAppMessage : datas) {
//            sendTime = sdf.format(sysAppMessage.getSendTime());//定义传递给友盟的时间
//            try {
//                if(StringUtils.isNotEmpty(sysAppMessage.getContentJson())){
//                    AppPushMsgInfoDTO appMsgInfo = JsonPluginsUtil.jsonToBean(sysAppMessage.getContentJson(), AppPushMsgInfoDTO.class);
//                    if(StringUtils.isNotEmpty(appMsgInfo.getImageURL())){
//                        String path = appMsgInfo.getImageURL().substring(appMsgInfo.getImageURL().indexOf("^")+1);
//                        appMsgInfo.setImageURL(OssUtil.getForeverFileUrl(OssUtil.getHeadBucketName(), path));
//                    }
//                    sysAppMessage.setContentJson(appMsgInfo.toString());
//                }
//                //IOS
//                if(sysAppMessage.getPhoneType() == 2){
//                    for (AppUser appUser : users) {
//                        if(appUser.getDeviceType() == 2){
//                            ResultDTO<PushMsgInfoDTO> countInfo =  sysAppMsgService.queryNewsCount(appUser.getId(), false, appUser.getDeviceType());
//                            int iosStatus = appPushService.sendIOSUnicast(appUser.getDeviceToken(),  sysAppMessage.getContent(), countInfo.getData().getUnReadCount(),sysAppMessage.getContentJson());
//                            if (iosStatus == 200) {
//                                  logger.warn("ios信息推送成功");
//                                  sysAppMessage.setStatus(1);
//                              } else {
//                                  logger.warn("ios信息推送失败");
//                                  sysAppMessage.setStatus(0);
//                              }
//                            }
//                    }
//                   //  安卓                  
//                }else if(sysAppMessage.getPhoneType() == 1){
//                    int androidStatus = appPushService.sendAndroidBroadcast(sysAppMessage.getContent(), sendTime,sysAppMessage.getContentJson());
//                    if (androidStatus == 200) {
//                        logger.warn("安卓信息推送成功");
//                        sysAppMessage.setStatus(1);
//                    } else {
//                        logger.warn("安卓信息推送失败");
//                        sysAppMessage.setStatus(0);
//                    }
//                }
//                sysAppMessage.setContentJson(null);
//                sysAppMsgService.updateByPrimaryKeySelective(sysAppMessage);
//               
//            } catch (Exception e) {
//                logger.error("广播推送异常", e);
//                e.printStackTrace();
//                
//            }
//        }
    }
}
