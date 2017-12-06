package net.fnsco.web.controller.e789.pay.zxyh.jo;

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

	@ApiModelProperty(value = "内部商户号")
	private String innerCode;// 内部商户号
	@ApiModelProperty(value = "app用户ID")
	private String appUserId;// app用户ID
	@ApiModelProperty(value = "交易金额")
	private String amt;// 交易金额
	@ApiModelProperty(value = "授权码(付款码)")
	private String authId;// 授权码，扫码支付授权码

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
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
