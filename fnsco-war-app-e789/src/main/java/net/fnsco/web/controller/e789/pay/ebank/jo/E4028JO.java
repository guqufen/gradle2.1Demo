package net.fnsco.web.controller.e789.pay.ebank.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4028JO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "付款人银行卡号", example = "付款人银行卡号")
	private String oppAccNo;// 付款人帐号
	/**
	 * APP登录用户ID
	 */
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
	private Integer userId;

	public String getOppAccNo() {
		return oppAccNo;
	}

	public void setOppAccNo(String oppAccNo) {
		this.oppAccNo = oppAccNo;
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

}
