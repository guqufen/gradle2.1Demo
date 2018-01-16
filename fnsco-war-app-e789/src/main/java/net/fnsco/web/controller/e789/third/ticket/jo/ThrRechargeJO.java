package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class ThrRechargeJO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "订单号", example = "订单号")
	private String orderNo;// 订单号

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
