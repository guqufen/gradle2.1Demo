package net.fnsco.bigdata.api.merchant;

import java.util.List;

import net.fnsco.bigdata.service.domain.IntegralRule;

/**
 * 积分相关业务操作
 * @author Administrator
 *
 */
public interface IntegralRuleService {

	/**
	 * 条件查询所有数据
	 * 
	 * @param integralRule
	 * @return ：返回IntegralRule对象列表
	 */
	public List<IntegralRule> queryListByCondition(IntegralRule integralRule);

	/**
	 * 查询唯一的规则代码code
	 * 
	 * @return :返回code列表
	 */
	public List<String> queryDistinctName();
	
	/**
	 * 通过code查找当前积分信息
	 * @param code
	 * @return
	 */
	public IntegralRule queryIntegralByCode(String code);
}
