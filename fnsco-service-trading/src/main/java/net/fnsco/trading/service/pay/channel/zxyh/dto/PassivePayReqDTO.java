package net.fnsco.trading.service.pay.channel.zxyh.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

/**
 * 中信银行微信/支付宝被扫支付交易入参
 * 
 * @author Administrator
 *
 */
public class PassivePayReqDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "app用户ID", example = "app用户ID")
	private Integer userId;// app用户ID
	@ApiModelProperty(value = "交易金额", example = "交易金额")
	private String amt;// 交易金额
	@ApiModelProperty(value = "授权码(付款码)", example = "授权码(付款码)")
	private String authId;// 授权码，扫码支付授权码
	@ApiModelProperty(value = "交易子类型01微信02支付宝", example = "交易子类型01微信02支付宝")
	private String paySubType;// 交易子类型01微信02支付宝

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

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getPaySubType() {
		return paySubType;
	}

	public void setPaySubType(String paySubType) {
		this.paySubType = paySubType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PassivePayReqDTO [userId=" + userId + ", amt=" + amt + ", paySubType=" + paySubType + "]";
	}

}
