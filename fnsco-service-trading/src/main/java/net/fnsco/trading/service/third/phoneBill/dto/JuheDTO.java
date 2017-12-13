package net.fnsco.trading.service.third.phoneBill.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

/**
 * 手机充值
 * 
 * @author Administrator
 *
 */
public class JuheDTO<T> extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "返回码", example = "返回码")
	private Integer error_code;
	@ApiModelProperty(value = "返回说明", example = "返回说明")
	private String reason;
	@ApiModelProperty(value = "返回结果集", example = "返回结果集")
	private T result;

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
