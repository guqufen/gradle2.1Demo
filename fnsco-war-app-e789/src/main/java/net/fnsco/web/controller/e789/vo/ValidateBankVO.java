package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;

public class ValidateBankVO {
	@ApiModelProperty(value="本次查询流水号" ,name="jobid")
    private String jobid;
	@ApiModelProperty(value="姓名" ,name="realname")
    private String realname;
	@ApiModelProperty(value="银行卡卡号" ,name="bankcard")
    private String bankcard;
	@ApiModelProperty(value="身份证号码" ,name="idcard")
    private String idcard;
	@ApiModelProperty(value="预留手机号码" ,name="mobile")
    private String mobile;
	@ApiModelProperty(value="验证结果，1:匹配 2:不匹配" ,name="res")
    private String res;
	@ApiModelProperty(value="描述" ,name="message")
    private String message;
	/**
	 * jobid
	 *
	 * @return  the jobid
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getJobid() {
		return jobid;
	}
	/**
	 * jobid
	 *
	 * @param   jobid    the jobid to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	/**
	 * realname
	 *
	 * @return  the realname
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRealname() {
		return realname;
	}
	/**
	 * realname
	 *
	 * @param   realname    the realname to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * bankcard
	 *
	 * @return  the bankcard
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBankcard() {
		return bankcard;
	}
	/**
	 * bankcard
	 *
	 * @param   bankcard    the bankcard to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}
	/**
	 * idcard
	 *
	 * @return  the idcard
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getIdcard() {
		return idcard;
	}
	/**
	 * idcard
	 *
	 * @param   idcard    the idcard to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * mobile
	 *
	 * @return  the mobile
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMobile() {
		return mobile;
	}
	/**
	 * mobile
	 *
	 * @param   mobile    the mobile to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	
	public String getMessage() {
		return message;
	}
	/**
	 * message
	 *
	 * @param   message    the message to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMessage(String message) {
		this.message = message;
	}
	

}
