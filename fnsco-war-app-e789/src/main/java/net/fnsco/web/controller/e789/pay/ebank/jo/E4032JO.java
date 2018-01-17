package net.fnsco.web.controller.e789.pay.ebank.jo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4032JO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "付款人银行卡号", example = "付款人银行卡号")
	private String oppAccNo;// 付款人帐号
	@ApiModelProperty(name = "交易金额", example = "交易金额")
	private BigDecimal amount;// 交易金额
	/**
	 * APP登录用户ID
	 */
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
	private Integer userId;
	@ApiModelProperty(value = "支付密码", example = "支付密码")
	private String payPassword;

	public String getOppAccNo() {
		return oppAccNo;
	}

	public void setOppAccNo(String oppAccNo) {
		this.oppAccNo = oppAccNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}
