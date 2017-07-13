package net.fnsco.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;
import net.sf.json.JSONObject;

/**
 * @desc 推送消息详细内容实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月11日 下午5:23:23
 */

public class AppPushMsgInfoDTO extends DTO{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7892526271637632128L;
    
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
//   public static void main(String[] args) throws ParseException {
//    AppPushMsgInfoDTO app = new AppPushMsgInfoDTO();
//    app.setDetailURL("/dsds/detail");
//    app.setImageURL("/dsjd/image");
//    app.setMsgSubject("测试主题");
//    app.setMsgSubtitle("重大通知：quandhsjhjhjshdjshjhjh");
//    app.setMsgType("1");
//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String dateString = formatter.format(new Date());
//    app.setSendTime(formatter.parse(dateString));
//    System.out.println(app.toString());
//    JSONObject jsonObject = JSONObject.fromObject(app.toString());
//    AppPushMsgInfoDTO sds = (AppPushMsgInfoDTO) JSONObject.toBean(jsonObject, AppPushMsgInfoDTO.class);
//    System.out.println(sds);
//}
}
