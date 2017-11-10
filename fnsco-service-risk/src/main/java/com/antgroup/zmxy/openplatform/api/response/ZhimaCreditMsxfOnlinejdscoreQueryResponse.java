package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.msxf.onlinejdscore.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-22 17:29:40
 */
public class ZhimaCreditMsxfOnlinejdscoreQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 1848259784789118667L;

	/** 
	 * 模型预测成好用户（0）的概率值，取值范围 [0, 1]。
	 */
	@ApiField("model_score")
	private String modelScore;

	/** 
	 * 预测结果值 {0, 1}，其中 0 表示好用户。
	 */
	@ApiField("predict_result")
	private Long predictResult;

	/** 
	 * 预测成 predict_result 的概率值，取值范围 [0.5, 1]
	 */
	@ApiField("predict_score")
	private String predictScore;

	public void setModelScore(String modelScore) {
		this.modelScore = modelScore;
	}
	public String getModelScore( ) {
		return this.modelScore;
	}

	public void setPredictResult(Long predictResult) {
		this.predictResult = predictResult;
	}
	public Long getPredictResult( ) {
		return this.predictResult;
	}

	public void setPredictScore(String predictScore) {
		this.predictScore = predictScore;
	}
	public String getPredictScore( ) {
		return this.predictScore;
	}

}
