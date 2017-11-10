package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.scene.creditpay.bizriskeval.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-07-20 22:17:50
 */
public class ZhimaMerchantSceneCreditpayBizriskevalQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 4885755656886362182L;

	/** 
	 * 芝麻分
	 */
	@ApiField("zm_score")
	private String zmScore;

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}
	public String getZmScore( ) {
		return this.zmScore;
	}

}
