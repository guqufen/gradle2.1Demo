package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * app登录注册JO
 * 
 * @author hjt
 *
 */
public class RegisterJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="手机号码")
	private String mobile;// 手机号码
	@ApiModelProperty(value="密码")
	private String password;// 密码
	@ApiModelProperty(value="设备号")
	private String deviceId;// 设备号
	@ApiModelProperty(value="设备类型1:安卓/2: IOS")
	private Integer deviceType;// 设备类型1:安卓/2: IOS
	@ApiModelProperty(value="友盟设备号")
	private String deviceToken;// 友盟设备号
	@ApiModelProperty(value="支付密码")
	private String payPassword;//支付密码
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
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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
		deviceToken = deviceToken;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the payPassword
	 */
	public String getPayPassword() {
		return payPassword;
	}
	/**
	 * @param payPassword the payPassword to set
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}
