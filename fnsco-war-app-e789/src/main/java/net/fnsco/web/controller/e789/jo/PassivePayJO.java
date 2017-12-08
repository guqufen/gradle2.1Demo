package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 中信银行微信/支付宝被扫支付交易JO
 * 
 * @author Administrator
 *
 */
public class PassivePayJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "app用户ID", example = "app用户ID")
	private Integer userId;// app用户ID
	@ApiModelProperty(value = "交易金额", example = "交易金额")
	private String amt;// 交易金额
	@ApiModelProperty(value = "授权码(付款码)", example = "授权码(付款码)")
	private String authId;// 授权码，扫码支付授权码

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthId() {
		return authId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
