package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.scene.creditpay.riskeval.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-06-19 11:21:17
 */
public class ZhimaMerchantSceneCreditpayRiskevalQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 2117448338876946133L;

	/** 
	 * 0代表不在关注名单中，1代表在关注名单中
	 */
	@ApiField("in_watch_list")
	private String inWatchList;

	/** 
	 * 
	 */
	@ApiField("zm_score")
	private String zmScore;

	public void setInWatchList(String inWatchList) {
		this.inWatchList = inWatchList;
	}
	public String getInWatchList( ) {
		return this.inWatchList;
	}

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}
	public String getZmScore( ) {
		return this.zmScore;
	}

}
