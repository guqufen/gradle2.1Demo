/**
 * 
 */
package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @author
 * 验证码和验证码产生时间
 */
public class SmsCodeDTO extends DTO{
	public SmsCodeDTO(String code,long time) {
		this.code =code;
		this.time=time;
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
