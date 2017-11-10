package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.scene.creditpay.signriskeval.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-07-20 22:17:31
 */
public class ZhimaMerchantSceneCreditpaySignriskevalQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8283247668969819477L;

	/** 
	 * openId
	 */
	@ApiField("open_id")
	private String openId;

	/** 
	 * 芝麻分
	 */
	@ApiField("zm_score")
	private String zmScore;

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId( ) {
		return this.openId;
	}

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}
	public String getZmScore( ) {
		return this.zmScore;
	}

}
