package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.customer.ep.certification.initialize response.
 * 
 * @author auto create
 * @since 1.0, 2017-07-27 20:20:02
 */
public class ZhimaCustomerEpCertificationInitializeResponse extends ZhimaResponse {

	private static final long serialVersionUID = 3285751782215397427L;

	/** 
	 * 本次认证的唯一标识，商户需要记录，biz_no有效期为23小时
	 */
	@ApiField("biz_no")
	private String bizNo;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

}
