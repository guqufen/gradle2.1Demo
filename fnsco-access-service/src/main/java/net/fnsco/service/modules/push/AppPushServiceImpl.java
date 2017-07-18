package net.fnsco.service.modules.push;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.fnsco.api.push.AppPushService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.push.AndroidNotification;
import net.fnsco.core.push.PushClient;
import net.fnsco.core.push.android.AndroidBroadcast;
import net.fnsco.core.push.android.AndroidListcast;
import net.fnsco.core.push.android.AndroidUnicast;
import net.fnsco.core.push.ios.IOSBroadcast;
import net.fnsco.core.push.ios.IOSListcast;
import net.fnsco.core.push.ios.IOSUnicast;

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
    
    /**
     * (non-Javadoc) 安卓推送--列播
     * @see net.fnsco.api.push.AppPushService#sendAndroidListcast(java.lang.String, java.lang.String, java.lang.String)
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
        listcast.setExtraField("contentJson", contentJson);
        listcast.setCustomField("");//标识系统通知还是交易流水通知
        int status = client.send(listcast);
        return status;

    }

    /**
     * (non-Javadoc) ios推送--列播
     * @see net.fnsco.api.push.AppPushService#sendIOSListcast(java.lang.String, java.lang.String, java.lang.String)
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
     * @see net.fnsco.api.push.AppPushService#sendAndroidBroadcast(java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendAndroidBroadcast(String content, String sendTime,String contentJson) throws Exception {

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
        broadcast.setExtraField("contentJson", contentJson);
        int status = client.send(broadcast);
        return status;
    }

    /**
     * (non-Javadoc) ios--广播
     * @see net.fnsco.api.push.AppPushService#sendIOSBroadcast(java.lang.String, java.lang.String)
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
     * @see net.fnsco.api.push.AppPushService#sendAndroidUnicast(java.lang.String, java.lang.String, java.lang.String)
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
        unicast.setExtraField("msgType", "1");//交易流水通知
        unicast.setExtraField("titleType", "系统通知");
        unicast.setExtraField("innerTermCode", innerTermCode);
        unicast.setTicker("【数钱吧】您有一条新消息");
        int status = client.send(unicast);
        return status;
    }

    /**
     * (non-Javadoc) ios -- 单播
     * @see net.fnsco.api.push.AppPushService#sendIOSUnicast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendIOSUnicast(String iosUnicastToken, String content,Integer badge,String contentJson) throws Exception {

        logger.warn("开始IOS单播推送");
        String appkey = this.env.getProperty("ios.appkey");
        String appMasterSecret = this.env.getProperty("ios.app.master.secret");
        IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
        
        unicast.setDeviceToken(iosUnicastToken);
        unicast.setAlert(content);
        unicast.setSound( "");
        unicast.setContentAvailable(1);
        unicast.setBadge(badge);//未读数量
        if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "test")) {
            unicast.setTestMode();// 测试模式
        } else if (StringUtils.equals(this.env.getProperty("youmeng.msg.mode"), "www")) {
            unicast.setProductionMode();// 正式模式
        }
        // Set customized fields
        unicast.setCustomizedField("msgType", "1");//交易流水通知
        unicast.setCustomizedField("titleType", "系统通知");
        unicast.setCustomizedField("contentJson", contentJson);
        int status = client.send(unicast);
        return status;
    }

}
