package net.fnsco.service.domain;

import java.util.Date;
/**
 * @desc APP消息推送信息实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月11日 下午3:40:25
 *
 */
public class SysAppMessage {
    
    private Integer id;

    private Integer msgType;

    private Integer busType;

    private String msgSubject;

    private String imageUrl;

    private String detailUrl;

    private String msgSubTitle;

    private Integer pushType;

    private Integer sendType;

    private Date sendTime;

    private Integer status;

    private Integer modifyUserId;

    private Date modifyTime;
    
    private String startSendTime;//开始推送时间 用于条件查询
    
    private String endSendTime;//借宿推送时间 用于条件查询
    
    private String sendTimeStr;//推送时间字符串
    
    private String modifyUser;
    
    /**
     * sendTimeStr
     *
     * @return  the sendTimeStr
     * @since   CodingExample Ver 1.0
    */
    
    public String getSendTimeStr() {
        return sendTimeStr;
    }

    /**
     * sendTimeStr
     *
     * @param   sendTimeStr    the sendTimeStr to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setSendTimeStr(String sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    /**
     * modifyUser
     *
     * @return  the modifyUser
     * @since   CodingExample Ver 1.0
    */
    
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * modifyUser
     *
     * @param   modifyUser    the modifyUser to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * startSendTime
     *
     * @return  the startSendTime
     * @since   CodingExample Ver 1.0
    */
    
    public String getStartSendTime() {
        return startSendTime;
    }

    /**
     * startSendTime
     *
     * @param   startSendTime    the startSendTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setStartSendTime(String startSendTime) {
        this.startSendTime = startSendTime;
    }

    /**
     * endSendTime
     *
     * @return  the endSendTime
     * @since   CodingExample Ver 1.0
    */
    
    public String getEndSendTime() {
        return endSendTime;
    }

    /**
     * endSendTime
     *
     * @param   endSendTime    the endSendTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setEndSendTime(String endSendTime) {
        this.endSendTime = endSendTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public String getMsgSubject() {
        return msgSubject;
    }

    public void setMsgSubject(String msgSubject) {
        this.msgSubject = msgSubject == null ? null : msgSubject.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }

    public String getMsgSubTitle() {
        return msgSubTitle;
    }

    public void setMsgSubTitle(String msgSubTitle) {
        this.msgSubTitle = msgSubTitle == null ? null : msgSubTitle.trim();
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
}