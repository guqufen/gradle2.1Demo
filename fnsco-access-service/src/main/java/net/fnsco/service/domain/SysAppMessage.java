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

    private Date sendTime;

    private String contentJson;

    private Integer phoneType;

    private Integer sendType;

    private Integer pushType;

    private Integer status;

    private Integer modifyUserId;

    private Date modifyTime;

    private Integer msgType;

    private Integer busType;

    private String content;
    
    private String startSendTime;//开始推送时间 用于条件查询
    
    private String endSendTime;//借宿推送时间 用于条件查询
    
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContentJson() {
        return contentJson;
    }

    public void setContentJson(String contentJson) {
        this.contentJson = contentJson == null ? null : contentJson.trim();
    }

    public Integer getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(Integer phoneType) {
        this.phoneType = phoneType;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}