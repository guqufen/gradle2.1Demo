package net.fnsco.bigdata.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.api.merchant.IntegralRuleService;
import net.fnsco.bigdata.service.dao.master.IntegralRuleDAO;
import net.fnsco.bigdata.service.domain.IntegralRule;

@Service
public class IntegralRuleServiceImpl implements IntegralRuleService{

	@Autowired
	private IntegralRuleDAO integralRuleDAO;

	@Override
	public List<IntegralRule> queryListByCondition(IntegralRule integralRule) {
		// TODO Auto-generated method stub
		return integralRuleDAO.queryListByCondition(integralRule);
	}

	@Override
	public List<String> queryDistinctName() {
		// TODO Auto-generated method stub
		return integralRuleDAO.queryDistinctName();
	}

	/**
	 * 通过code查找当前积分信息
	 * @param code
	 * @return
	 */
	public IntegralRule queryIntegralByCode(String code){
		return integralRuleDAO.queryIntegralByCode(code);
	}
}
