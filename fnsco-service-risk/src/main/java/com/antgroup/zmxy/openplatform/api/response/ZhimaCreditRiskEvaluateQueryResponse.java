package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.risk.evaluate.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-09-21 13:15:15
 */
public class ZhimaCreditRiskEvaluateQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 6374778649661785573L;

	/** 
	 * 评估结果：true 通过，false 不通过
	 */
	@ApiField("accessible")
	private Boolean accessible;

	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 拓展字段，存储明细信息，当前未开放
	 */
	@ApiField("details")
	private String details;

	/** 
	 * 额度等级[A/B/C...]
	 */
	@ApiField("limit_level")
	private String limitLevel;

	/** 
	 * 如果风险评估不通过，则输出对应不通过原因编码，具体编码请查看产品接口文档；
	 */
	@ApiField("risk_code")
	private String riskCode;

	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}
	public Boolean getAccessible( ) {
		return this.accessible;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	public String getDetails( ) {
		return this.details;
	}

	public void setLimitLevel(String limitLevel) {
		this.limitLevel = limitLevel;
	}
	public String getLimitLevel( ) {
		return this.limitLevel;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskCode( ) {
		return this.riskCode;
	}

}
