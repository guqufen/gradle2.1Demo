package net.fnsco.order.api.merchant;

import java.util.Date;
import java.util.List;

import net.fnsco.order.service.domain.IntegralRule;

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
	
	/**
	 * countMerchantEntityScores:(统计积分)
	 *
	 * @param      设定文件
	 * @return void    DOM对象
	 * @author tangliang
	 * @date   2017年11月2日 上午9:45:30
	 */
	void countTradeDataScores(Date startTime,Date endTime);
}
