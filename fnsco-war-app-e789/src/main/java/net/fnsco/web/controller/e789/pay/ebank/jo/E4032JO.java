package net.fnsco.web.controller.e789.pay.ebank.jo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4032JO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name="银行卡号",example="银行卡号")
	private String acctNo;// 银行卡号
	@ApiModelProperty(name="交易金额",example="交易金额")
	private BigDecimal amount;// 交易金额

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
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
