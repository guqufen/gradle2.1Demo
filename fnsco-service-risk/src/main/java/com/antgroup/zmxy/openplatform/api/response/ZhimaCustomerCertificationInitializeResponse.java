package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.customer.certification.initialize response.
 * 
 * @author auto create
 * @since 1.0, 2017-05-31 10:19:55
 */
public class ZhimaCustomerCertificationInitializeResponse extends ZhimaResponse {

	private static final long serialVersionUID = 5248166736381154621L;

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
