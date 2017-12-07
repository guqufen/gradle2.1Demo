package net.fnsco.trading.service.appUser.entity;

import java.util.Date;

public class AppUserDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号（可作为登陆名）
     */
    private String mobile;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 身份证号
     */
    private String idCardNumber;

    /**
     * 用户名称
     */
    private String realName;

    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 是否开启手势密码0：停用 1：开启
     */
    private Integer gesState;

    /**
     * 手势密码
     */
    private String gesPassword;

    /**
     * 是否显示手势轨迹 0:停用 1：启用
     */
    private Integer gesTrail;

    /**
     * 支付密码(md5)
     */
    private String payPassword;

    /**
     * 设备类型 1:安卓/2: IOS
     */
    private Integer deviceType;

    /**
     * 友盟设备号
     */
    private String deviceToken;

    /**
     * 提现密码连续错误次数
     */
    private Integer passwordErrorNum;

    /**
     * 提现密码错误时间
     */
    private Date passwordErrorDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户注册时间
     */
    private Date regTime;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 用户状态0：注销1：正常
     */
    private Integer state;

    /**
     * 强制退出标志0不强制退出1强制退出
     */
    private Integer forcedLoginOut;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String headImagePath;

    /**
     * 邀请人实体商户id
     */
    private String inviteEntityInnnerCode;

    /**
     * 邀请状态积分状态0未获取积分1已获取积分
     */
    private Integer inviteStatus;



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
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
        this.gesPassword = gesPassword;
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
        this.payPassword = payPassword;
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
        this.deviceToken = deviceToken;
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
        this.remark = remark;
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

    public Integer getForcedLoginOut() {
        return forcedLoginOut;
    }

    public void setForcedLoginOut(Integer forcedLoginOut) {
        this.forcedLoginOut = forcedLoginOut;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeadImagePath() {
        return headImagePath;
    }

    public void setHeadImagePath(String headImagePath) {
        this.headImagePath = headImagePath;
    }

    public String getInviteEntityInnnerCode() {
        return inviteEntityInnnerCode;
    }

    public void setInviteEntityInnnerCode(String inviteEntityInnnerCode) {
        this.inviteEntityInnnerCode = inviteEntityInnnerCode;
    }

    public Integer getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Integer inviteStatus) {
        this.inviteStatus = inviteStatus;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", userName="+ userName + ", mobile="+ mobile + ", password="+ password + ", idCardNumber="+ idCardNumber + ", realName="+ realName + ", deviceId="+ deviceId + ", gesState="+ gesState + ", gesPassword="+ gesPassword + ", gesTrail="+ gesTrail + ", payPassword="+ payPassword + ", deviceType="+ deviceType + ", deviceToken="+ deviceToken + ", passwordErrorNum="+ passwordErrorNum + ", passwordErrorDate="+ passwordErrorDate + ", remark="+ remark + ", regTime="+ regTime + ", lastLoginTime="+ lastLoginTime + ", modifyTime="+ modifyTime + ", state="+ state + ", forcedLoginOut="+ forcedLoginOut + ", sex="+ sex + ", headImagePath="+ headImagePath + ", inviteEntityInnnerCode="+ inviteEntityInnnerCode + ", inviteStatus="+ inviteStatus + "]";
    }
}