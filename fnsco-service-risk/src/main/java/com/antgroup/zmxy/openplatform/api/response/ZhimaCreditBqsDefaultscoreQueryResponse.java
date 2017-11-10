package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.bqs.defaultscore.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-07 14:55:48
 */
public class ZhimaCreditBqsDefaultscoreQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 3248815423813158799L;

	/** 
	 * 
	 */
	@ApiField("gongzhai_score")
	private Long gongzhaiScore;

	/** 
	 * 
	 */
	@ApiField("passed")
	private Boolean passed;

	/** 
	 * 
	 */
	@ApiField("qizha_score")
	private Long qizhaScore;

	/** 
	 * 
	 */
	@ApiField("wending_score")
	private Long wendingScore;

	/** 
	 * 
	 */
	@ApiField("zizhi_score")
	private Long zizhiScore;

	public void setGongzhaiScore(Long gongzhaiScore) {
		this.gongzhaiScore = gongzhaiScore;
	}
	public Long getGongzhaiScore( ) {
		return this.gongzhaiScore;
	}

	public void setPassed(Boolean passed) {
		this.passed = passed;
	}
	public Boolean getPassed( ) {
		return this.passed;
	}

	public void setQizhaScore(Long qizhaScore) {
		this.qizhaScore = qizhaScore;
	}
	public Long getQizhaScore( ) {
		return this.qizhaScore;
	}

	public void setWendingScore(Long wendingScore) {
		this.wendingScore = wendingScore;
	}
	public Long getWendingScore( ) {
		return this.wendingScore;
	}

	public void setZizhiScore(Long zizhiScore) {
		this.zizhiScore = zizhiScore;
	}
	public Long getZizhiScore( ) {
		return this.zizhiScore;
	}

}
