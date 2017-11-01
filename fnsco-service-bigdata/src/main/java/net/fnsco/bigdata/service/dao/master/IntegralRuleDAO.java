package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.IntegralRule;

@Mapper
public interface IntegralRuleDAO {
	
	/**
	 * 条件查询所有数据
	 * @param integralRule
	 * @return ：返回IntegralRule对象列表
	 */
	public List<IntegralRule> queryListByCondition (IntegralRule integralRule);

	/**
	 * 查询唯一的规则代码code
	 * @return :返回code列表
	 */
	public List<String> queryDistinctName();
	
	/**
	 * 通过code查找当前积分信息
	 * @param code
	 * @return
	 */
	public IntegralRule queryIntegralByCode(@Param("code")String code);
}
