package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.kkcredit.acscore.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-18 13:35:59
 */
public class ZhimaCreditKkcreditAcscoreQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 5824658885762633489L;

	/** 
	 * 用户的芝麻卡卡信用分。分值范围[393,784]。如果用户数据不足，无法评分时，返回数值0。
	 */
	@ApiField("kkdac_score")
	private Long kkdacScore;

	public void setKkdacScore(Long kkdacScore) {
		this.kkdacScore = kkdacScore;
	}
	public Long getKkdacScore( ) {
		return this.kkdacScore;
	}

}
