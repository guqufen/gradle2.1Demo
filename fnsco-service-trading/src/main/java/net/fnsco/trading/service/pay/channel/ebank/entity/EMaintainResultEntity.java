package net.fnsco.trading.service.pay.channel.ebank.entity;

public class EMaintainResultEntity {

	// 是否成功
	private boolean success;
	// 返回码
	private String respCode;
	// 返回信息
	private String respMsg;

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

}
