package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.modulescore.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-03 15:18:20
 */
public class ZhimaCreditModulescoreQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 6517985515586211395L;

	/** 
	 * 资产状况指数
	 */
	@ApiField("asset_score")
	private String assetScore;

	/** 
	 * 行为表现指数
	 */
	@ApiField("behavior_score")
	private String behaviorScore;

	/** 
	 * 芝麻信用对于每一次调用的唯一标示，可用于后期对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 个人综合指数
	 */
	@ApiField("composite_score")
	private String compositeScore;

	/** 
	 * 信用历史指数
	 */
	@ApiField("credit_history_score")
	private String creditHistoryScore;

	/** 
	 * 身份特质指数
	 */
	@ApiField("identity_score")
	private String identityScore;

	public void setAssetScore(String assetScore) {
		this.assetScore = assetScore;
	}
	public String getAssetScore( ) {
		return this.assetScore;
	}

	public void setBehaviorScore(String behaviorScore) {
		this.behaviorScore = behaviorScore;
	}
	public String getBehaviorScore( ) {
		return this.behaviorScore;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setCompositeScore(String compositeScore) {
		this.compositeScore = compositeScore;
	}
	public String getCompositeScore( ) {
		return this.compositeScore;
	}

	public void setCreditHistoryScore(String creditHistoryScore) {
		this.creditHistoryScore = creditHistoryScore;
	}
	public String getCreditHistoryScore( ) {
		return this.creditHistoryScore;
	}

	public void setIdentityScore(String identityScore) {
		this.identityScore = identityScore;
	}
	public String getIdentityScore( ) {
		return this.identityScore;
	}

}
