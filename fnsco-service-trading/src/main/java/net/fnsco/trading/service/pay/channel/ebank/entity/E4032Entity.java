package net.fnsco.trading.service.pay.channel.ebank.entity;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class E4032Entity {

	@ApiModelProperty(name = "应答码", example = "应答码")
	private String respCode;
	@ApiModelProperty(name = "应答信息", example = "应答信息")
	private String respMsg;
	@ApiModelProperty(name = "批次凭证号", example = "批次凭证号")
	private String orderNo;
	@ApiModelProperty(name = "交易金额", example = "交易金额")
	private BigDecimal amount;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
