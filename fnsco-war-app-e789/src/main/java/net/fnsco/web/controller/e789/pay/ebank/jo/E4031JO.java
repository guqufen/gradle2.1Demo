package net.fnsco.web.controller.e789.pay.ebank.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4031JO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "付款人帐号", example = "付款人帐号")
	private String OppAccNo;// 付款人帐号
	@ApiModelProperty(name = "手机号", example = "手机号")
	private String Mobile;// 手机号

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
