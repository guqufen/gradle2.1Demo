package net.fnsco.web.controller.e789.pay.ebank.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class E4033JO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "支付订单号", example = "支付订单号")
	private String orderNo;
	/**
	 * APP登录用户ID
	 */
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
	private Integer userId;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
