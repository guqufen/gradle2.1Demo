package com.antgroup.zmxy.openplatform.api.response;

import java.util.List;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;
import com.antgroup.zmxy.openplatform.api.domain.RiskEvaluateRuleInfo;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.risk.evaluate.rule.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-06-29 14:03:10
 */
public class ZhimaCreditRiskEvaluateRuleQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 6743214414698961481L;

	/** 
	 * 对应规则配置信息列表
	 */
	@ApiListField("rule_infos")
	@ApiField("risk_evaluate_rule_info")
	private List<RiskEvaluateRuleInfo> ruleInfos;

	public void setRuleInfos(List<RiskEvaluateRuleInfo> ruleInfos) {
		this.ruleInfos = ruleInfos;
	}
	public List<RiskEvaluateRuleInfo> getRuleInfos( ) {
		return this.ruleInfos;
	}

}
