package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.customer.certification.material.certify response.
 * 
 * @author auto create
 * @since 1.0, 2017-06-05 13:42:56
 */
public class ZhimaCustomerCertificationMaterialCertifyResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8494831274878727195L;

	/** 
	 * 本次认证的唯一标识
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 认证没有通过的原因
	 */
	@ApiField("failed_reason")
	private String failedReason;

	/** 
	 * 认证是否通过，通过为true，不通过为false
	 */
	@ApiField("passed")
	private String passed;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	public String getFailedReason( ) {
		return this.failedReason;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}
	public String getPassed( ) {
		return this.passed;
	}

}
