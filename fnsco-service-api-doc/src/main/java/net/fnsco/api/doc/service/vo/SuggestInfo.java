package net.fnsco.api.doc.service.vo;

import java.util.Date;

/**
 * 
		* <p>Title: 反馈信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年9月16日下午1:45:47</p>
 */
public class SuggestInfo {
    /** 记录id*/
    private Long   suggestId;

    /** 用户id*/
    private Long   userId;

    /** 用户邮箱*/
    private String email;

    /** 用户昵称*/
    private String nickName;

    /** 标题*/
    private String title;

    /** 内容 */
    private String content;

    /** 提出时间 */
    private Date   createDate;

    /** 状态  SuggestStatus*/
    private String status;

    /** 类型 SuggestType*/
    private String type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getSuggestId() {
        return suggestId;
    }

    public void setSuggestId(Long suggestId) {
        this.suggestId = suggestId;
    }

    /**
     * status
     *
     * @return  the status
     * @since   CodingExample Ver 1.0
    */

    public String getStatus() {
        return status;
    }

    /**
     * status
     *
     * @param   status    the status to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * type
     *
     * @return  the type
     * @since   CodingExample Ver 1.0
    */

    public String getType() {
        return type;
    }

    /**
     * type
     *
     * @param   type    the type to set
     * @since   CodingExample Ver 1.0
     */

    public void setType(String type) {
        this.type = type;
    }

}