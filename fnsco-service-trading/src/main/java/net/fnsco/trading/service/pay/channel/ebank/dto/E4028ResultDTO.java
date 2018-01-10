package net.fnsco.trading.service.pay.channel.ebank.dto;

import net.fnsco.core.base.DTO;

public class E4028ResultDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String respCode;
	private String respMsg;
	private E4028RespDTO e4028RespDTO;

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

	public E4028RespDTO getE4028RespDTO() {
		return e4028RespDTO;
	}

	public void setE4028RespDTO(E4028RespDTO e4028RespDTO) {
		this.e4028RespDTO = e4028RespDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
