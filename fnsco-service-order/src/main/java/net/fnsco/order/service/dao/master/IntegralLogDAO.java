package net.fnsco.order.service.dao.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.fnsco.order.service.domain.IntegralLog;

@Mapper
public interface IntegralLogDAO {

	/**
	 * 条件查询积分日志数据列表
	 * @param integralRuleLog
	 * @return
	 */
	public List<IntegralLog> queryListByCondition(IntegralLog integralRuleLog);
	
	/**
	 * 通过实体商户号+积分规则代码/积分日期查询总积分
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public Integer querySumbyCondition(IntegralLog integralRuleLog);
	
	/**
	 * 往商户积分表插入数据
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public int insert(IntegralLog integralRuleLog);
	
	/**
	 * 通过实体商户号+积分规则代码/积分日期查询次数
	 * @param integralRuleLog
	 * @return :总积分
	 */
	public Integer queryCountbyCondition(IntegralLog integralRuleLog);
}
