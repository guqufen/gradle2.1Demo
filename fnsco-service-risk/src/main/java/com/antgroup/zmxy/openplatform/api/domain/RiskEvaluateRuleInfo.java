package com.antgroup.zmxy.openplatform.api.domain;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

/**
 * 风险评估规则配置信息单元
 *
 * @author auto create
 * @since 1.0, 2017-06-06 19:56:32
 */
public class RiskEvaluateRuleInfo extends ZhimaObject {

	private static final long serialVersionUID = 3489986778686616283L;

	/** 
	 * 规则是否可降级
	 */
	@ApiField("demote")
	private String demote;

	/** 
	 * 规则描述信息
	 */
	@ApiField("description")
	private String description;

	/** 
	 * 规则约束值
	 */
	@ApiField("expect_value")
	private String expectValue;

	/** 
	 * 规则对应逻辑符
	 */
	@ApiField("operator")
	private String operator;

	/** 
	 * 整数值，标示规则在策略中顺序
	 */
	@ApiField("order")
	private Long order;

	/** 
	 * 规则不通过时对应的风险编码
	 */
	@ApiField("risk_code")
	private String riskCode;

	/** 
	 * 规则编码
	 */
	@ApiField("rule_code")
	private String ruleCode;

	public void setDemote(String demote) {
		this.demote = demote;
	}
	public String getDemote( ) {
		return this.demote;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription( ) {
		return this.description;
	}

	public void setExpectValue(String expectValue) {
		this.expectValue = expectValue;
	}
	public String getExpectValue( ) {
		return this.expectValue;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperator( ) {
		return this.operator;
	}

	public void setOrder(Long order) {
		this.order = order;
	}
	public Long getOrder( ) {
		return this.order;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskCode( ) {
		return this.riskCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleCode( ) {
		return this.ruleCode;
	}

}
