package net.fnsco.api.doc.service.project.entity;

import java.util.Date;

public class EmailDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 邮件模板名称
     */
    private String emailName;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 通知角色邮箱
     */
    private String roleType;

    /**
     * 其他通知邮箱
     */
    private String otherSubject;

    /**
     * 内容
     */
    private String content;

    /**
     * 
     */
    private Date createDate;

    /**
     * 
     */
    private Date modifyDate;

    /**
     * 
     */
    private Integer userId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getOtherSubject() {
        return otherSubject;
    }

    public void setOtherSubject(String otherSubject) {
        this.otherSubject = otherSubject;
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", emailName="+ emailName + ", subject="+ subject + ", roleType="+ roleType + ", otherSubject="+ otherSubject + ", content="+ content + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", userId="+ userId + "]";
    }
}