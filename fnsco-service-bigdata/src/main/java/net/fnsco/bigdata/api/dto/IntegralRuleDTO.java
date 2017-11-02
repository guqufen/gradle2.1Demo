package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.bigdata.service.domain.IntegralRule;
import net.fnsco.core.base.DTO;

/**
 * 用于返回
 * 
 * @author Administrator
 *
 */
public class IntegralRuleDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ruleName;// 规则名称

	private List<IntegralRule> ruleConditionList;// 规则条件

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public List<IntegralRule> getRuleConditionList() {
		return ruleConditionList;
	}

	public void setRuleConditionList(List<IntegralRule> ruleConditionList) {
		this.ruleConditionList = ruleConditionList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
