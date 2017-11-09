package net.fnsco.order.api.merchant;

import java.util.List;

import net.fnsco.order.service.domain.IntegralLog;

public interface IntegralLogService {

	/**
	 * 条件查询积分日志数据列表
	 * 
	 * @param integralRuleLog
	 * @return
	 */
	public List<IntegralLog> queryListByCondition(IntegralLog integralRuleLog);
	/**
     * 条件查询积分日志数据列表
     * 
     * @param integralRuleLog
     * @return
     */
    public List<IntegralLog> queryListByEntityInnerCode(String entityInnerCode);

	/**
	 * 通过实体商户号+积分规则代码/积分日期查询总积分
	 * 
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public Integer querySumbyCondition(IntegralLog integralRuleLog);

	/**
	 * 往商户积分表插入数据
	 * 
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public int insert(String entityInnerCode, String ruleCode);
	
	/**
	 * 往商户积分表插入数据
	 * 
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public int insert(String entityInnerCode, String ruleCode, String description);
}
