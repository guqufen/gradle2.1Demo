package net.fnsco.service.domain;

import java.util.Date;

public class AppUser {
    private Integer id;

    private String userName;

    private String mobile;

    private String password;

    private String realName;

    private String deviceId;

    private Integer gesState;

    private String gesPassword;

    private Integer gesTrail;

    private String payPassword;

    private Integer deviceType;

    private String deviceToken;

    private Integer passwordErrorNum;

    private Date passwordErrorDate;

    private String remark;

    private Date regTime;

    private Date lastLoginTime;

    private Date modifyTime;

    private Integer state;
    
    private Integer forcedLoginOut;
    
    /**
     * forcedLoginOut
     *
     * @return  the forcedLoginOut
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getForcedLoginOut() {
        return forcedLoginOut;
    }

    /**
     * forcedLoginOut
     *
     * @param   forcedLoginOut    the forcedLoginOut to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setForcedLoginOut(Integer forcedLoginOut) {
        this.forcedLoginOut = forcedLoginOut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Integer getGesState() {
        return gesState;
    }

    public void setGesState(Integer gesState) {
        this.gesState = gesState;
    }

    public String getGesPassword() {
        return gesPassword;
    }

    public void setGesPassword(String gesPassword) {
        this.gesPassword = gesPassword == null ? null : gesPassword.trim();
    }

    public Integer getGesTrail() {
        return gesTrail;
    }

    public void setGesTrail(Integer gesTrail) {
        this.gesTrail = gesTrail;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public Integer getPasswordErrorNum() {
        return passwordErrorNum;
    }

    public void setPasswordErrorNum(Integer passwordErrorNum) {
        this.passwordErrorNum = passwordErrorNum;
    }

    public Date getPasswordErrorDate() {
        return passwordErrorDate;
    }

    public void setPasswordErrorDate(Date passwordErrorDate) {
        this.passwordErrorDate = passwordErrorDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}