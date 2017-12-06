package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * app获取验证码JO
 * 
 * @author hjt
 *
 */
public class GetValidateCodeJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="手机号码")
	private String mobile;// 手机号码
	@ApiModelProperty(value="设备号")
	private String deviceId;// 设备号
	@ApiModelProperty(value="验证码类型（1找回登录密码，2绑定银行卡）")
	private Integer type;// 验证码类型（1找回登录密码，2绑定银行卡）
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
