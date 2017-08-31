package net.fnsco.order.service.modules.push;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.dto.PushMsgInfoDTO;
import net.fnsco.order.api.push.AppPushService;
import net.fnsco.order.api.sysappmsg.SysAppMsgService;
import net.fnsco.order.api.sysappmsg.SysMsgAppFailService;
import net.fnsco.order.api.sysappmsg.SysMsgAppSuccService;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.SysAppMessage;
import net.fnsco.order.service.domain.SysMsgAppFail;
import net.fnsco.order.service.domain.SysMsgAppSucc;

/**
 * @desc 推送消息帮助类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月31日 下午2:32:28
 */
@Service
public class AppPushHelper extends BaseService {

    @Autowired
    private SysAppMsgService     sysAppMsgService;

    @Autowired
    private SysMsgAppSuccService sysMsgAppSuccService;

    @Autowired
    private SysMsgAppFailService sysMsgAppFailService;

    @Autowired
    private AppPushService       appPushService;

    /**
     * insertIntoDBSysAppMessage:(入库消息)
     * @param msgContent
     * @param msgSubject
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月31日 下午2:35:48
     * @return SysAppMessage    DOM对象
     */
    public SysAppMessage insertIntoDBSysAppMessage(String msgContent, String msgSubject) {
        SysAppMessage message = new SysAppMessage();
        message.setBusType(0);
        message.setModifyUserId(1);
        message.setMsgSubject("周报");
        message.setMsgSubTitle(msgContent);
        message.setMsgType(1);
        message.setPushType(1);
        message.setSendType(1);
        message.setSendTime(new Date());
        message.setModifyTime(new Date());
        message.setStatus(1);
        sysAppMsgService.insertSelective(message);
        return message;
    }

    /**
     * 
     * insertIntoDBSuccMsg:(入库成功消息)
     * @param userId
     * @param messageId
     * @param phoneType
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月31日 下午2:42:17
     * @return SysMsgAppSucc    DOM对象
     */
    public SysMsgAppSucc insertIntoDBSuccMsg(Integer userId, Integer messageId, Integer phoneType) {
        SysMsgAppSucc sysMsgAppSucc = new SysMsgAppSucc();
        sysMsgAppSucc.setSendTime(new Date());
        sysMsgAppSucc.setAppUserId(userId);
        sysMsgAppSucc.setMsgId(messageId);
        sysMsgAppSucc.setReadStatus(0);
        sysMsgAppSucc.setPhoneType(phoneType);
        sysMsgAppSuccService.insertSelective(sysMsgAppSucc);
        return sysMsgAppSucc;
    }

    /**
     * insertIntoDBFailMsg:(入库失败消息)
     * @param userId
     * @param messageId
     * @param phoneType
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月31日 下午2:46:19
     * @return SysMsgAppFail    DOM对象
     */
    public SysMsgAppFail insertIntoDBFailMsg(Integer userId, Integer messageId, Integer phoneType, Integer sendCount) {
        SysMsgAppFail sysMsgAppFail = new SysMsgAppFail();
        sysMsgAppFail.setAppUserId(userId);
        sysMsgAppFail.setMsgId(messageId);
        sysMsgAppFail.setSendCount(sendCount);
        sysMsgAppFail.setSendTime(new Date());
        sysMsgAppFail.setStatus(0);
        sysMsgAppFail.setPhoneType(phoneType);
        sysMsgAppFailService.insertSelective(sysMsgAppFail);
        return sysMsgAppFail;
    }

    /**
     * pushNewMessage:(发送新的业务通知信息)
     * @param appUser
     * @param message    设定文件
     * @author    tangliang
     * @date      2017年8月31日 下午2:58:16
     * @return void    DOM对象
     */
    public void pushNewMessage(AppUser appUser, SysAppMessage message) {

        Integer deviceType = appUser.getDeviceType();

        //分别推送安卓和IOS消息且保存发送结果
        if (deviceType == 1) {//安卓
            try {
                Integer androidStatus = appPushService.sendAndroidListcast(appUser.getDeviceToken(), message.getMsgSubTitle(), DateUtils.dateFormatToStr(message.getSendTime()),
                    message.getId().toString());
                if (androidStatus == 200) {
                    //成功
                    this.insertIntoDBSuccMsg(appUser.getId(), message.getId(), 1);
                    logger.info("安卓信息推送成功");
                } else {
                    //失败
                    this.insertIntoDBFailMsg(appUser.getId(), message.getId(), 1, 1);
                    logger.info("安卓信息推送失败");
                }
            } catch (Exception e) {
                logger.error("推送android消息异常" + e);
                e.printStackTrace();
            }
        } else if (deviceType == 2) {//ios
            ResultDTO<PushMsgInfoDTO> countInfo = sysAppMsgService.queryNewsCount(appUser.getId(), false, appUser.getDeviceType());
            int iosStatus;
            try {
                JSONObject alertContext = new JSONObject();
                alertContext.put("title", message.getMsgSubject());
                alertContext.put("subtitle", "");
                alertContext.put("body", message.getMsgSubTitle());
                iosStatus = appPushService.sendIOSUnicast(appUser.getDeviceToken(), alertContext, countInfo.getData().getUnReadCount() + 1, message.getId().toString());
                if (iosStatus == 200) {
                    //成功
                    this.insertIntoDBSuccMsg(appUser.getId(), message.getId(), 2);
                    logger.info("ios信息推送成功");
                } else {
                    //失败
                    this.insertIntoDBFailMsg(appUser.getId(), message.getId(), 2, 1);
                    logger.info("ios信息推送失败");
                }

            } catch (Exception e) {
                logger.error("推送ios消息异常" + e);
                e.printStackTrace();
            }
        }
    }
}
