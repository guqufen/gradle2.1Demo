package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.data.batch.feedback response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-06 13:16:12
 */
public class ZhimaDataBatchFeedbackResponse extends ZhimaResponse {

	private static final long serialVersionUID = 4631831252466697998L;

	/** 
	 * 
	 */
	@ApiField("biz_success")
	private String bizSuccess;

	public void setBizSuccess(String bizSuccess) {
		this.bizSuccess = bizSuccess;
	}
	public String getBizSuccess( ) {
		return this.bizSuccess;
	}

}
