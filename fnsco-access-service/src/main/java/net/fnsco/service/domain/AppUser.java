package net.fnsco.service.domain;

import java.io.Serializable;
import java.util.Date;

public class AppUser implements Serializable{
    private Integer id;

    private String phonenum;

    private String password;

    private String realname;

    private Integer state;

    private Date registrationtime;

    private String deviceid;

    private Date logintime;

    private Date updatetime;

    private Integer gesstate;

    private String gespassword;

    private String paypassword;

    private Integer gestrail;

    private Integer devicetype;

    private String devicetoken;

    private Integer passworderror;

    private Date passworderrordate;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(Date registrationtime) {
        this.registrationtime = registrationtime;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getGesstate() {
        return gesstate;
    }

    public void setGesstate(Integer gesstate) {
        this.gesstate = gesstate;
    }

    public String getGespassword() {
        return gespassword;
    }

    public void setGespassword(String gespassword) {
        this.gespassword = gespassword == null ? null : gespassword.trim();
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword == null ? null : paypassword.trim();
    }

    public Integer getGestrail() {
        return gestrail;
    }

    public void setGestrail(Integer gestrail) {
        this.gestrail = gestrail;
    }

    public Integer getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(Integer devicetype) {
        this.devicetype = devicetype;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken == null ? null : devicetoken.trim();
    }

    public Integer getPassworderror() {
        return passworderror;
    }

    public void setPassworderror(Integer passworderror) {
        this.passworderror = passworderror;
    }

    public Date getPassworderrordate() {
        return passworderrordate;
    }

    public void setPassworderrordate(Date passworderrordate) {
        this.passworderrordate = passworderrordate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}