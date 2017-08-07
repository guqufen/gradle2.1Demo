package net.fnsco.api.doc.service.user.entity;

import java.util.Date;

public class UserLoginDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 登陆时间
     */
    private Date loginDate;

    /**
     * 连续登陆失败次数
     */
    private Integer loginFailureCount;

    /**
     * 
     */
    private Integer loginCount;

    /**
     * 登陆ip
     */
    private String loginIp;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登陆方式
     */
    private String loginType;

    /**
     * 
     */
    private String loginStatus;

    /**
     * 登陆验证码
     */
    private String authCode;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", loginDate="+ loginDate + ", loginFailureCount="+ loginFailureCount + ", loginCount="+ loginCount + ", loginIp="+ loginIp + ", userId="+ userId + ", loginType="+ loginType + ", loginStatus="+ loginStatus + ", authCode="+ authCode + "]";
    }
}