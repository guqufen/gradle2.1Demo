package net.fnsco.trading.service.third.reCharge.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class ChargeResultDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "应答码", example = "应答码")
	private String respCode;
	@ApiModelProperty(value = "应答信息", example = "应答信息")
	private String respMsg;
	@ApiModelProperty(value = "订单号", example = "订单号")
	private String orderNo;

	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
