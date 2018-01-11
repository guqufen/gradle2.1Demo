package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;

public class ValidateBankVO {
	@ApiModelProperty(value="描述" ,name="reason")
	private String reason;
	@ApiModelProperty(value="验证结果，1:匹配 2:不匹配" ,name="res")
    private String res;
//	@ApiModelProperty(value="描述" ,name="message")
//    private String message;
	@ApiModelProperty(value="返回码" ,name="error_code")
	private Integer error_code;
	/**
	 * reason
	 *
	 * @return  the reason
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getReason() {
		return reason;
	}
	/**
	 * reason
	 *
	 * @param   reason    the reason to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * jobid
	 *
	 * @return  the jobid
	 * @since   CodingExample Ver 1.0
	*/
	
	/**
	 * res
	 *
	 * @return  the res
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRes() {
		return res;
	}
	/**
	 * res
	 *
	 * @param   res    the res to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRes(String res) {
		this.res = res;
	}
	/**
	 * message
	 *
	 * @return  the message
	 * @since   CodingExample Ver 1.0
	*/
	
//	public String getMessage() {
//		return message;
//	}
//	/**
//	 * message
//	 *
//	 * @param   message    the message to set
//	 * @since   CodingExample Ver 1.0
//	 */
//	
//	public void setMessage(String message) {
//		this.message = message;
//	}
	/**
	 * error_code
	 *
	 * @return  the error_code
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getError_code() {
		return error_code;
	}
	/**
	 * error_code
	 *
	 * @param   error_code    the error_code to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}
	

}
