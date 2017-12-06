package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 中信银行微信/支付宝被扫支付交易结果查询JO
 * 
 * @author Administrator
 *
 */
public class PassiveResultQueryJO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "原支付订单号(用于交易状态查询)",example="原支付订单号")
	private String orderNo;// 原支付商户订单号(原交易返回，此处便于交易状态查询交易)

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
