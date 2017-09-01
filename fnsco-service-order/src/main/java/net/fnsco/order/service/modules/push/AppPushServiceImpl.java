package net.fnsco.order.service.modules.push;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.push.AndroidNotification;
import net.fnsco.core.push.PushClient;
import net.fnsco.core.push.android.AndroidBroadcast;
import net.fnsco.core.push.android.AndroidListcast;
import net.fnsco.core.push.android.AndroidUnicast;
import net.fnsco.core.push.ios.IOSBroadcast;
import net.fnsco.core.push.ios.IOSListcast;
import net.fnsco.core.push.ios.IOSUnicast;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.PushMsgInfoDTO;
import net.fnsco.order.api.push.AppPushService;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.api.sysappmsg.SysMsgAppFailService;
import net.fnsco.order.api.sysappmsg.SysMsgAppSuccService;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.trade.TradeByDayDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.SysAppMessage;
import net.fnsco.order.service.domain.SysMsgAppFail;
import net.fnsco.order.service.domain.SysMsgAppSucc;

/**
 * @desc 友盟推送实现
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 上午10:49:26
 */
@Service
public class AppPushServiceImpl extends BaseService implements AppPushService {
    
    @Autowired
    private Environment env;
     
    private PushClient client = new PushClient();
    
    @Autowired
    private SysAppMsgService sysAppMsgService;
    
    @Autowired
    private AppUserService appUserService;
    
    @Autowired
    private SysMsgAppSuccService sysMsgAppSuccService;
    
    @Autowired
    private SysMsgAppFailService sysMsgAppFailService;
    
    @Autowired
    private AppUserDao appuUserDao;
    
    @Autowired
    private TradeByDayDao  tradeByDayDao;
    
    @Autowired
    private AppPushHelper appPushHelper;
    
    /**
     * (non-Javadoc) 安卓推送--列播
     * @see net.fnsco.order.api.push.AppPushService#sendAndroidListcast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendAndroidListcast(String param_and, String content, String sendTime,String contentJson) throws Exception {

        logger.warn("开始安卓列播推送");
        String appkey = this.env.getProperty("ad.appkey");
        String appMasterSecret = this.env.getProperty("ad.app.master.secret");
        AndroidListcast listcast = new AndroidListcast(appkey,appMasterSecret);
        listcast.setDeviceToken(param_and);
        listcast.setTicker("【数钱吧】您有一条新消息");
        listcast.setTitle("数钱吧");
        listcast.setText(content);
        listcast.goAppAfterOpen();
        listcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);//通知
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            listcast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            listcast.setProductionMode();// 正式模式
        }
        listcast.setDescription("列播通知-Android");
        listcast.setExtraField("msgType", "1");//系统通知
        listcast.setExtraField("sendTime", sendTime);//推送时间
        listcast.setExtraField("titleType", "系统消息");
        listcast.setExtraField("msgId", contentJson);
        listcast.setCustomField("");//通知
        int status = client.send(listcast);
        return status;

    }

    /**
     * (non-Javadoc) ios推送--列播
     * @see net.fnsco.order.api.push.AppPushService#sendIOSListcast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendIOSListcast(String param_ios, String content, String sendTime) throws Exception {

        logger.warn("开始IOS列播推送");
        String appkey = this.env.getProperty("ios.appkey");
        String appMasterSecret = this.env.getProperty("ios.app.master.secret");
        IOSListcast listcast = new IOSListcast(appkey,appMasterSecret);
        listcast.setDeviceToken(param_ios);
        listcast.setAlert(content);//内容
        listcast.setBadge( 1);
        listcast.setSound( "default");
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            listcast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            listcast.setProductionMode();// 正式模式
        }
        listcast.setCustomizedField("msgType", "1");//系统通知
        listcast.setCustomizedField("sendTime", sendTime);//推送时间
        listcast.setCustomizedField("titleType", "系统消息");
        int status = client.send(listcast);
        return status;
    }

    /**
     * (non-Javadoc) 安卓--广播
     * @see net.fnsco.order.api.push.AppPushService#sendAndroidBroadcast(java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendAndroidBroadcast(String content, String sendTime,String ids) throws Exception {

        logger.warn("开始安卓广播推送");
        String appkey = this.env.getProperty("ad.appkey");
        String appMasterSecret = this.env.getProperty("ad.app.master.secret");
        AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
        broadcast.setTicker("【数钱吧】您有一条新消息");
        broadcast.setTitle("数钱吧");
        broadcast.setText(content);
        broadcast.goAppAfterOpen();
        broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            broadcast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            broadcast.setProductionMode();// 正式模式
        }
        broadcast.setExtraField("msgType", "1");//系统通知
        broadcast.setExtraField("sendTime", sendTime);
        broadcast.setExtraField("titleType", "系统消息");
        broadcast.setExtraField("msgId", ids);
        int status = client.send(broadcast);
        return status;
    }

    /**
     * (non-Javadoc) ios--广播
     * @see net.fnsco.order.api.push.AppPushService#sendIOSBroadcast(java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendIOSBroadcast(String content, String sendTime) throws Exception {

        logger.warn("开始IOS广播推送");
        String appkey = this.env.getProperty("ios.appkey");
        String appMasterSecret = this.env.getProperty("ios.app.master.secret");
        IOSBroadcast broadcast = new IOSBroadcast(appkey,appMasterSecret);
        broadcast.setAlert(content);
        broadcast.setBadge( 1);//角标消息数量
        broadcast.setSound( "");
        broadcast.setContentAvailable(1);//后台运行
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            broadcast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            broadcast.setProductionMode();// 正式模式
        }
        broadcast.setCustomizedField("msgType", "1");//系统通知
        broadcast.setCustomizedField("sendTime", sendTime);//系统通知
        broadcast.setCustomizedField("titleType", "系统消息");//系统通知
        int status = client.send(broadcast);
        return status;

    }

    /**
     * (non-Javadoc) andorid -- 单播
     * @see net.fnsco.order.api.push.AppPushService#sendAndroidUnicast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendAndroidUnicast(String adrUnicastToken, String innerTermCode, String content) throws Exception {

        logger.warn("开始安卓单播推送");
        String appkey = this.env.getProperty("ad.appkey");
        String appMasterSecret = this.env.getProperty("ad.app.master.secret");
        AndroidUnicast unicast = new AndroidUnicast(appkey,appMasterSecret);
        unicast.setDeviceToken(adrUnicastToken);
        unicast.setTitle("浙付通");
        unicast.setText(content);
        unicast.goAppAfterOpen();
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            unicast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            unicast.setProductionMode();// 正式模式
        }
        // Set customized fields
        unicast.setExtraField("msgType", "1");//通知
        unicast.setExtraField("titleType", "系统通知");
        unicast.setExtraField("innerTermCode", innerTermCode);
        unicast.setTicker("【数钱吧】您有一条新消息");
        int status = client.send(unicast);
        return status;
    }

    /**
     * (non-Javadoc) ios -- 单播
     * @see net.fnsco.order.api.push.AppPushService#sendIOSUnicast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendIOSUnicast(String iosUnicastToken, JSONObject content,Integer badge,String ids) throws Exception {

        logger.warn("开始IOS单播推送");
        String appkey = this.env.getProperty("ios.appkey");
        String appMasterSecret = this.env.getProperty("ios.app.master.secret");
        IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
        
        unicast.setDeviceToken(iosUnicastToken);
//        unicast.setAlert(content);
        unicast.setPredefinedKeyValue("alert", content);
        unicast.setSound( "");
        unicast.setContentAvailable(1);
        unicast.setBadge(badge);//未读数量
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            unicast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            unicast.setProductionMode();// 正式模式
        }
        // Set customized fields
        unicast.setCustomizedField("msgType", "1");//通知
        unicast.setCustomizedField("titleType", "系统通知");
        unicast.setCustomizedField("msgId", ids);
        int status = client.send(unicast);
        return status;
    }
    
    /**
     * (non-Javadoc)  发送待发送消息
     * @see net.fnsco.order.api.push.AppPushService#sendSystemMgs()
     * @auth tangliang
     * @date 2017年7月19日 下午3:08:33
     */
    @Override
    public void sendSystemMgs() {
        
        List<SysAppMessage> datas = sysAppMsgService.queryExecuteData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String  sendTime = null;
        List<AppUser>  users = null;
        if(datas.size() == 0 ){
           return;
        }
        users = appUserService.queryAllPushUser();
        for (SysAppMessage sysAppMessage : datas) {
            sendTime = sdf.format(sysAppMessage.getSendTime());//定义传递给友盟的时间
            try {
                //  安卓                  
                int androidStatus = sendAndroidBroadcast(sysAppMessage.getMsgSubTitle(), sendTime,sysAppMessage.getId().toString());
                if (androidStatus == 200) {
                    logger.warn("安卓信息推送成功");
                } else {
                    logger.warn("安卓信息推送失败");
                }
                JSONObject alertContext = new JSONObject();
                alertContext.put("title", sysAppMessage.getMsgSubject());
                alertContext.put("subtitle", "");
                alertContext.put("body", sysAppMessage.getMsgSubTitle());
                //IOS
                for (AppUser appUser : users) {
                    //成功
                    SysMsgAppSucc sysMsgAppSucc = new SysMsgAppSucc();
                    sysMsgAppSucc.setSendTime(new Date());
                    sysMsgAppSucc.setAppUserId(appUser.getId());
                    sysMsgAppSucc.setMsgId(sysAppMessage.getId());
                    sysMsgAppSucc.setReadStatus(0);
                    //失败情况
                    SysMsgAppFail sysMsgAppFail = new SysMsgAppFail();
                    sysMsgAppFail.setAppUserId(appUser.getId());
                    sysMsgAppFail.setMsgId(sysAppMessage.getId());
                    sysMsgAppFail.setSendCount(1);
                    sysMsgAppFail.setSendTime(new Date());
                    sysMsgAppFail.setStatus(0);
                    if(appUser.getDeviceType() == 2){
                        ResultDTO<PushMsgInfoDTO> countInfo =  sysAppMsgService.queryNewsCount(appUser.getId(), false, appUser.getDeviceType());
                        int iosStatus = sendIOSUnicast(appUser.getDeviceToken(),  alertContext, countInfo.getData().getUnReadCount()+1,sysAppMessage.getId().toString());
                        
                        if (iosStatus == 200) {
                            sysMsgAppSucc.setPhoneType(2);
                            sysMsgAppSuccService.insertSelective(sysMsgAppSucc);
                            logger.warn("ios信息推送成功");
                          } else {
                              sysMsgAppFail.setPhoneType(2);
                              sysMsgAppFailService.insertSelective(sysMsgAppFail);
                              logger.warn("ios信息推送失败");
                          }
                     }else if(appUser.getDeviceType() == 1){
                            if(androidStatus == 200){
                                sysMsgAppSucc.setPhoneType(1);
                                sysMsgAppSuccService.insertSelective(sysMsgAppSucc);
                            }else{
                                sysMsgAppFail.setPhoneType(1);
                                sysMsgAppFailService.insertSelective(sysMsgAppFail);
                            }
                            
                        }
                    
                }
                
                sysAppMessage.setStatus(1);//已发送状态
                sysAppMsgService.updateByPrimaryKeySelective(sysAppMessage);
               
            } catch (Exception e) {
                logger.error("广播推送异常", e);
                e.printStackTrace();
                
            }
        }
        
    }
    /**
     * 
     * (non-Javadoc)
     * @see net.fnsco.api.push.AppPushService#sendFirstFailMgs()第一次发送失败消息
     * @auth tangliang
     * @date 2017年7月20日 下午1:29:57
     */
    @Override
    public void sendFailMgs(Integer frequencyNum) {
        
        if(frequencyNum == null || !(frequencyNum > 0 && 4 > frequencyNum)){
            logger.error("非法参数!拒绝执行任务");
            return;
        }
        
        SysMsgAppFail record = new SysMsgAppFail();
        switch (frequencyNum) {
            case 1:
                record.setSendTime(DateUtils.getTimeByMinuteDate(-5));//前两分钟数据
                record.setSendCount(1);
                break;
            case 2:
                record.setSendTime(DateUtils.getTimeByMinuteDate(-2*60-5));//前两小时数据
                record.setSendCount(2);
                break;
            case 3:
                record.setSendTime(DateUtils.getTimeByMinuteDate(-5*60-5-2*60));//前五个小时数据
                record.setSendCount(3);
            default:
                return;
        }
        
        record.setStatus(0);
        List<SysMsgAppFail> datas = sysMsgAppFailService.queryFailMsg(record);
        for (SysMsgAppFail sysMsgAppFail : datas) {
            if(null == sysMsgAppFail.getAppUserId()){
                continue;
            }
            AppUser appuser = appUserService.selectAppUserById(sysMsgAppFail.getAppUserId());
            //不在线不推送
            if(null == appuser.getDeviceType()){
                continue;
            }
            //在线
            SysAppMessage message = sysAppMsgService.selectByPrimaryKey(sysMsgAppFail.getMsgId());
            //成功
            SysMsgAppSucc msgAppSucc = new SysMsgAppSucc();
            msgAppSucc.setSendTime(new Date());
            msgAppSucc.setAppUserId(appuser.getId());
            msgAppSucc.setMsgId(message.getId());
            msgAppSucc.setReadStatus(0);
            
            if(appuser.getDeviceType() == 1){//android
                try {
                    Integer androidStatus  = sendAndroidListcast(appuser.getDeviceToken(),message.getMsgSubTitle(),DateUtils.dateFormatToStr(message.getSendTime()),message.getId().toString());
                    sysMsgAppFail.setPhoneType(1);
                    if(androidStatus == 200){
                        msgAppSucc.setPhoneType(1);
                        sysMsgAppFail.setStatus(1);
                        sysMsgAppSuccService.insertSelective(msgAppSucc);
                        logger.info("安卓信息推送成功");
                    }else{
                        sysMsgAppFail.setSendCount(sysMsgAppFail.getSendCount()+1);
                        logger.info("安卓信息推送失败");
                    }
                } catch (Exception e) {
                    logger.error("第"+frequencyNum+"次重试推送android消息异常"+e);
                    e.printStackTrace();
                }
                
            }else if(appuser.getDeviceType() == 2){//IOS
                ResultDTO<PushMsgInfoDTO> countInfo =  sysAppMsgService.queryNewsCount(appuser.getId(), false, appuser.getDeviceType());
                int iosStatus;
                try {
                    JSONObject alertContext = new JSONObject();
                    alertContext.put("title", message.getMsgSubject());
                    alertContext.put("subtitle", "");
                    alertContext.put("body", message.getMsgSubTitle());
                    iosStatus = sendIOSUnicast(appuser.getDeviceToken(),  alertContext, countInfo.getData().getUnReadCount()+1,message.getId().toString());
                    sysMsgAppFail.setPhoneType(2);
                    if (iosStatus == 200) {
                        msgAppSucc.setPhoneType(2);
                        sysMsgAppFail.setStatus(1);
                        sysMsgAppSuccService.insertSelective(msgAppSucc);
                        logger.warn("ios信息推送成功");
                      } else {
                          sysMsgAppFail.setSendCount(sysMsgAppFail.getSendCount()+1);
                          logger.warn("ios信息推送失败");
                      }
                    
                } catch (Exception e) {
                    logger.error("第"+frequencyNum+"次重试推送ios消息异常"+e);
                    e.printStackTrace();
                }
            }
            sysMsgAppFailService.updateByPrimaryKeySelective(sysMsgAppFail);
        }
    }
    
    /**
     * (non-Javadoc)推送周报数据、推送给所有店主角色在线且本周之前有过交易数据的用户
     * @see net.fnsco.order.api.push.AppPushService#sendWeeklyDataMgs()
     * @author tangliang
     * @date 2017年8月31日 上午11:43:37
     */
    @Transactional
    @Override
    public void sendWeeklyDataMgs() {
        //查询所有是店主角色且在线的APP用户
        List<AppUser> appUsers = appuUserDao.selectAllInlineByRoleId(ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
        String lastSunday = DateUtils.getSundayStr(-1);
        
        String msgContent = "【周报】上周的周报已经生成,轻轻一点即可查看详情";
        SysAppMessage message = appPushHelper.insertIntoDBSysAppMessage(msgContent, "周报",0);
        if(message.getId() == null){
            logger.error("入库信息实体异常");
            return;
        }
        
        for (AppUser appUser : appUsers) {
            String minDate = tradeByDayDao.selectMinTradeDateByUserId(appUser.getId(), ConstantEnum.AuthorTypeEnum.SHOPOWNER.getCode());
            //如果上周日之前没有产生过交易数据，不推送
            if(Strings.isNullOrEmpty(minDate) && minDate.compareTo(lastSunday) >= 0){
                continue;
            } 
            
            Integer deviceType = appUser.getDeviceType();
            //不在线用户不推送
            if(null == deviceType ||deviceType == 0){
                continue;
            }
            //分别推送安卓和IOS消息且保存发送结果
            appPushHelper.pushNewMessage(appUser, message,true);
        }
    }
    
    /**
     * (non-Javadoc)自定义扩张参数的安卓列播
     * @see net.fnsco.order.api.push.AppPushService#sendAndroidListcast(java.lang.String, java.lang.String, java.util.Map)
     * @author tangliang
     * @date 2017年9月1日 上午9:47:33
     */
    @Override
    public Integer sendAndroidListcast(String param_and, String content, Map<String, String> extraField) throws Exception {
        
        logger.warn("开始安卓列播推送");
        String appkey = this.env.getProperty("ad.appkey");
        String appMasterSecret = this.env.getProperty("ad.app.master.secret");
        AndroidListcast listcast = new AndroidListcast(appkey,appMasterSecret);
        listcast.setDeviceToken(param_and);
        listcast.setTicker("【数钱吧】您有一条新消息");
        listcast.setTitle("数钱吧");
        listcast.setText(content);
        listcast.goAppAfterOpen();
        listcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);//通知
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            listcast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            listcast.setProductionMode();// 正式模式
        }
        listcast.setDescription("列播通知-Android");
        /**
         * 遍历扩张参数、放入listcast中
         */
        for (Entry<String, String> entry : extraField.entrySet()) {
            listcast.setExtraField(entry.getKey(), entry.getValue());
        }
        listcast.setCustomField("");//通知
        int status = client.send(listcast);
        return status;
        
    }
    /**
     * (non-Javadoc)自定义扩张参数的ios单播
     * @see net.fnsco.order.api.push.AppPushService#sendIOSUnicast(java.lang.String, org.json.JSONObject, java.lang.Integer, java.util.Map)
     * @author tangliang
     * @date 2017年9月1日 上午9:47:48
     */
    @Override
    public Integer sendIOSUnicast(String iosUnicastToken, JSONObject content, Integer badge, Map<String, String> extraField) throws Exception {
        
        logger.warn("开始IOS单播推送");
        String appkey = this.env.getProperty("ios.appkey");
        String appMasterSecret = this.env.getProperty("ios.app.master.secret");
        IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
        
        unicast.setDeviceToken(iosUnicastToken);
        unicast.setPredefinedKeyValue("alert", content);
        unicast.setSound( "");
        unicast.setContentAvailable(1);
        unicast.setBadge(badge);//未读数量
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            unicast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            unicast.setProductionMode();// 正式模式
        }
        /**
         * 遍历扩张参数、放入unicast中
         */
        for (Entry<String, String> entry : extraField.entrySet()) {
            unicast.setCustomizedField(entry.getKey(), entry.getValue());
        }
        int status = client.send(unicast);
        return status;
        
    }
    
}
