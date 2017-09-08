package net.fnsco.order.api.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import net.fnsco.core.base.DTO;
import net.sf.json.JSONObject;

/**
 * @desc 推送消息详细内容实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月11日 下午5:23:23
 */

public class AppPushMsgInfoDTO extends DTO implements Comparable<AppPushMsgInfoDTO>{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7892526271637632128L;
    
    private Integer id;
    /**
     * 消息主题
     */
    private String msgSubject;
    
    /**
     * 图片地址
     */
    private String imageURL;
    
    /**
     * 推送类型0内推1强推
     */
    private String msgType = "1";
    
    /**
     * 推送时间
     */
    private Date sendTime;
    
    /**
     * 推送时间字符串
     */
    private String sendTimeStr;
    /**
     * 消息详情链接
     */
    private String detailURL;
    
    /**
     * 副标题
     */
    private String msgSubtitle;
    
    /**
     * 周报开始日期
     */
    
    private String weeklyStartDate;
    /**
     * 周报结束日期
     */
    private String weeklyEndDate;
    
    /**
     * weeklyStartDate
     *
     * @return  the weeklyStartDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getWeeklyStartDate() {
        return weeklyStartDate;
    }

    /**
     * weeklyStartDate
     *
     * @param   weeklyStartDate    the weeklyStartDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setWeeklyStartDate(String weeklyStartDate) {
        this.weeklyStartDate = weeklyStartDate;
    }

    /**
     * weeklyEndDate
     *
     * @return  the weeklyEndDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getWeeklyEndDate() {
        return weeklyEndDate;
    }

    /**
     * weeklyEndDate
     *
     * @param   weeklyEndDate    the weeklyEndDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setWeeklyEndDate(String weeklyEndDate) {
        this.weeklyEndDate = weeklyEndDate;
    }

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * msgSubject
     *
     * @return  the msgSubject
     * @since   CodingExample Ver 1.0
    */
    
    public String getMsgSubject() {
        return msgSubject;
    }

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
     * msgSubject
     *
     * @param   msgSubject    the msgSubject to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMsgSubject(String msgSubject) {
        this.msgSubject = msgSubject;
    }

    /**
     * imageURL
     *
     * @return  the imageURL
     * @since   CodingExample Ver 1.0
    */
    
    public String getImageURL() {
        return imageURL;
    }

    /**
     * imageURL
     *
     * @param   imageURL    the imageURL to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * msgType
     *
     * @return  the msgType
     * @since   CodingExample Ver 1.0
    */
    
    public String getMsgType() {
        return msgType;
    }

    /**
     * msgType
     *
     * @param   msgType    the msgType to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * sendTime
     *
     * @return  the sendTime
     * @since   CodingExample Ver 1.0
    */
    
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * sendTime
     *
     * @param   sendTime    the sendTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * detailURL
     *
     * @return  the detailURL
     * @since   CodingExample Ver 1.0
    */
    
    public String getDetailURL() {
        return detailURL;
    }

    /**
     * detailURL
     *
     * @param   detailURL    the detailURL to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }

    /**
     * msgSubtitle
     *
     * @return  the msgSubtitle
     * @since   CodingExample Ver 1.0
    */
    
    public String getMsgSubtitle() {
        return msgSubtitle;
    }

    /**
     * msgSubtitle
     *
     * @param   msgSubtitle    the msgSubtitle to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMsgSubtitle(String msgSubtitle) {
        this.msgSubtitle = msgSubtitle;
    }
    /**
     * (non-Javadoc) 重写
     * @see java.lang.Object#toString()
     * @auth tangliang
     * @date 2017年7月11日 下午5:39:58
     */
    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
    
    @Override
    public int hashCode() {
        return super.hashCode()<<1;
    }
    
    /**
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     * @auth tangliang
     * @date 2017年7月13日 下午3:16:23
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof AppPushMsgInfoDTO){
            AppPushMsgInfoDTO apm = (AppPushMsgInfoDTO) obj;
            if(StringUtils.isNoneEmpty(apm.getSendTimeStr()) && apm.getSendTimeStr().equals(sendTimeStr)){
                return true;
            }
        }
        return super.equals(obj);
        
    }
    /**
     * (non-Javadoc)为消息排序，主要根据发送时间字段
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * @auth tangliang
     * @date 2017年7月13日 下午3:18:17
     */
    @Override
    public int compareTo(AppPushMsgInfoDTO o) {
        if(StringUtils.isNoneEmpty(o.getSendTimeStr()) && StringUtils.isNoneEmpty(this.sendTimeStr)){
            return o.getSendTimeStr().compareTo(this.sendTimeStr);
        }
        return 0;
    }
}
