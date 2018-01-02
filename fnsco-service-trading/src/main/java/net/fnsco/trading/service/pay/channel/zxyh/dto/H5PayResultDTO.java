package net.fnsco.trading.service.pay.channel.zxyh.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class H5PayResultDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="应答码",example="应答码")
	private String respCode;// 应答码
	@ApiModelProperty(value="应答信息",example="应答信息")
	private String respMsg;// 应答信息
	@ApiModelProperty(value="商户订单哈珀",example="商户订单号")
	private String orderNo;// 商户订单号
	@ApiModelProperty(value="订单时间",example="订单时间")
	private String orderTime;// 订单时间
	@ApiModelProperty(value="支付跳转连接",example="支付跳转连接")
	private String mwebUrl;// 支付跳转链接,mwebUrl为拉起微信支付

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getMwebUrl() {
		return mwebUrl;
	}

	public void setMwebUrl(String mwebUrl) {
		this.mwebUrl = mwebUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
