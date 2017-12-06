package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class PassiveVO extends VO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "应答码",example="应答码")
	private String respCode;
	@ApiModelProperty(value = "应答信息",example="应答信息")
	private String respMsg;
	@ApiModelProperty(value = "商户订单号",example="商户订单号")
	private String orderId;
	@ApiModelProperty(value = "交易起始时间",example="交易起始时间")
	private String begTime;
	@ApiModelProperty(value = "交易金额",example="交易金额")
	private String amt;
	@ApiModelProperty(value = "交易完成时间",example="交易完成时间")
	private String endTime;
	@ApiModelProperty(value = "实收金额",example="实收金额")
	private String reciAmt;
	@ApiModelProperty(value = "优惠金额",example="优惠金额")
	private String preAmt;

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBegTime() {
		return begTime;
	}

	public void setBegTime(String begTime) {
		this.begTime = begTime;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getReciAmt() {
		return reciAmt;
	}

	public void setReciAmt(String reciAmt) {
		this.reciAmt = reciAmt;
	}

	public String getPreAmt() {
		return preAmt;
	}

	public void setPreAmt(String preAmt) {
		this.preAmt = preAmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
