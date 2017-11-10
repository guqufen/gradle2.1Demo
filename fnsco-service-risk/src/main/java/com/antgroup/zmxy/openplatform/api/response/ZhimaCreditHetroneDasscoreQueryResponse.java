package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.hetrone.dasscore.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-07 14:52:06
 */
public class ZhimaCreditHetroneDasscoreQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8722319662665886435L;

	/** 
	 * 共创das分
	 */
	@ApiField("das_score")
	private Long dasScore;

	public void setDasScore(Long dasScore) {
		this.dasScore = dasScore;
	}
	public Long getDasScore( ) {
		return this.dasScore;
	}

}
