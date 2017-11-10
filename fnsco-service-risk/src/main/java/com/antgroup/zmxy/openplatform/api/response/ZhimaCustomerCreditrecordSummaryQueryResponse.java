package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.customer.creditrecord.summary.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-10-20 14:52:52
 */
public class ZhimaCustomerCreditrecordSummaryQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 1584383893612755894L;

	/** 
	 * 信用足迹的次数计数
	 */
	@ApiField("creditrecord_count")
	private String creditrecordCount;

	public void setCreditrecordCount(String creditrecordCount) {
		this.creditrecordCount = creditrecordCount;
	}
	public String getCreditrecordCount( ) {
		return this.creditrecordCount;
	}

}
