package net.fnsco.web.controller.e789.pay.ebank.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4031JO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "付款人银行卡号", example = "付款人银行卡号")
	private String oppAccNo;// 付款人帐号
	@ApiModelProperty(name = "银行卡号预留手机号", example = "银行卡号预留手机号")
	private String mobile;// 手机号

	public String getOppAccNo() {
		return oppAccNo;
	}

	public void setOppAccNo(String oppAccNo) {
		this.oppAccNo = oppAccNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
