package net.fnsco.web.controller.e789.pay.ebank.jo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class EbankAcctJO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "付款人银行卡号", example = "付款人银行卡号")
	private String OppAccNo;// 付款人帐号
	@ApiModelProperty(name = "银行卡号预留手机号", example = "银行卡号预留手机号")
	private String Mobile;// 手机号
	@ApiModelProperty(name = "交易金额", example = "交易金额")
	private BigDecimal amount;// 交易金额

	public String getOppAccNo() {
		return OppAccNo;
	}

	public void setOppAccNo(String oppAccNo) {
		OppAccNo = oppAccNo;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
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

}
