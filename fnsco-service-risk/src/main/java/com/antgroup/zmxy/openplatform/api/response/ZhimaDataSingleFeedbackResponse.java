package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.data.single.feedback response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-06 13:16:35
 */
public class ZhimaDataSingleFeedbackResponse extends ZhimaResponse {

	private static final long serialVersionUID = 3645397575637549581L;

	/** 
	 * 
	 */
	@ApiField("biz_success")
	private Boolean bizSuccess;

	public void setBizSuccess(Boolean bizSuccess) {
		this.bizSuccess = bizSuccess;
	}
	public Boolean getBizSuccess( ) {
		return this.bizSuccess;
	}

}
