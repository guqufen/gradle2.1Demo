package net.fnsco.service.modules.push;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.fnsco.api.push.AppPushService;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.push.PushClient;
import net.fnsco.core.push.android.AndroidBroadcast;
import net.fnsco.core.push.ios.IOSBroadcast;
import net.fnsco.core.push.AndroidNotification;

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
     * (non-Javadoc)
     * @see net.fnsco.api.push.AppPushService#sendAndroidListcast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendAndroidListcast(String param_and, String content, String sendTime) throws Exception {

        // TODO Auto-generated method stub
        return null;

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.push.AppPushService#sendIOSListcast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendIOSListcast(String param_ios, String content, String sendTime) throws Exception {

        // TODO Auto-generated method stub
        return null;

    }

    /**
     * (non-Javadoc) 安卓--广播
     * @see net.fnsco.api.push.AppPushService#sendAndroidBroadcast(java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public Integer sendAndroidBroadcast(String content, String sendTime) throws Exception {

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
        broadcast.setBadge( 0);
        broadcast.setSound( "default");
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
     * (non-Javadoc)
     * @see net.fnsco.api.push.AppPushService#sendAndroidUnicast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public void sendAndroidUnicast(String adrUnicastToken, String innerTermCode, String content) throws Exception {

        // TODO Auto-generated method stub

    }

    /**
     * (non-Javadoc)
     * @see net.fnsco.api.push.AppPushService#sendIOSUnicast(java.lang.String, java.lang.String, java.lang.String)
     * @auth tangliang
     * @date 2017年7月12日 上午10:49:26
     */
    @Override
    public void sendIOSUnicast(String iosUnicastToken, String innerTermCode, String content) throws Exception {

        // TODO Auto-generated method stub

    }

}
