package net.fnsco.core.utils.dto;

public class MessageValidateDTO {
	public MessageValidateDTO(String code, long time) {
		this.code = code;
		this.time = time;
	}

	private String code;
	private long time;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
