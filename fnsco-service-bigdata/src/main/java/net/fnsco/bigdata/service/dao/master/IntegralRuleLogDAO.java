package net.fnsco.bigdata.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.fnsco.bigdata.service.domain.IntegralRuleLog;

@Mapper
public interface IntegralRuleLogDAO {

	/**
	 * 条件查询积分日志数据列表
	 * @param integralRuleLog
	 * @return
	 */
	public List<IntegralRuleLog> queryListByCondition(IntegralRuleLog integralRuleLog);
	
	/**
	 * 通过实体商户号+积分规则代码/积分日期查询总积分
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public Integer querySumbyCondition(IntegralRuleLog integralRuleLog);
	
	/**
	 * 往商户积分表插入数据
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public void insert(IntegralRuleLog integralRuleLog);
	
	/**
	 * 通过实体商户号+积分规则代码/积分日期查询次数
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public Integer queryCountbyCondition(IntegralRuleLog integralRuleLog);
}
