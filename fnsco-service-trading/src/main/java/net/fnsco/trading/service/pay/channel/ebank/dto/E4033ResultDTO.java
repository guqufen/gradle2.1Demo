package net.fnsco.trading.service.pay.channel.ebank.dto;

import net.fnsco.core.base.DTO;

public class E4033ResultDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String respCode;
	private String respMsg;
	private E4034RespDTO e4034RespDTO;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

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

	public E4034RespDTO getE4034RespDTO() {
		return e4034RespDTO;
	}

	public void setE4034RespDTO(E4034RespDTO e4034RespDTO) {
		this.e4034RespDTO = e4034RespDTO;
	}

	@Override
	public String toString() {
		return "E4033ResultDTO [success=" + success + ", respCode=" + respCode + ", respMsg=" + respMsg
				+ ", e4034RespDTO=" + e4034RespDTO + "]";
	}

}
