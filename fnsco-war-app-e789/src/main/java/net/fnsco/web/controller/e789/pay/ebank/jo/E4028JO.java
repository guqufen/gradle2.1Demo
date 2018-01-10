package net.fnsco.web.controller.e789.pay.ebank.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4028JO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "银行卡号", example = "银行卡号")
	private String acctNo;

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
