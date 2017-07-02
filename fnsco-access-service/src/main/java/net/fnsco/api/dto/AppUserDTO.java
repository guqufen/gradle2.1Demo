package net.fnsco.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

public class AppUserDTO extends DTO{
	  private Integer id;

	    private String userName;

	    private String mobile;

	    private String password;

	    private String realName;

	    private String deviceId;

	    private Integer gesState;

	    private String gesPassword;
	    private Integer gesTrail;
	    private String code;
	    private String oldPassword;
	    public String getOldPassword() {
			return oldPassword;
		}

		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
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
}
