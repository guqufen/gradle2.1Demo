package net.fnsco.order.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.bigdata.service.dao.master.MerchantEntityCoreRefDao;
import net.fnsco.bigdata.service.dao.master.trade.TradeDataDAO;
import net.fnsco.bigdata.service.domain.MerchantEntityCoreRef;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.BaseService;
import net.fnsco.order.api.merchant.IntegralRuleLogService;
import net.fnsco.order.api.merchant.IntegralRuleService;
import net.fnsco.order.service.dao.master.IntegralRuleDAO;
import net.fnsco.order.service.domain.IntegralRule;
import net.fnsco.order.service.domain.IntegralRuleLog;

@Service
public class IntegralRuleServiceImpl extends BaseService implements IntegralRuleService{

	@Autowired
	private IntegralRuleDAO integralRuleDAO;
	
	@Autowired
	private TradeDataDAO    tradeDataDAO;
	
	@Autowired
	private IntegralRuleLogService integralRuleLogService;
	
	@Autowired
	private MerchantEntityCoreRefDao merchantEntityCoreRefDao;
	
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
	
	/** 统计交易流水的积分信息
	 * (non-Javadoc)
	 * @see net.fnsco.bigdata.api.merchant.IntegralRuleService#countMerchantEntityScores(java.util.Date, java.util.Date)
	 * @author tangliang
	 * @date 2017年11月2日 上午9:52:08
	 */
	@Override
	public void countTradeDataScores(Date startTime, Date endTime) {
		
		TradeData record = new TradeData();
		record.setStartCreateTime(startTime);
		record.setEndCreateTime(endTime);
		List<TradeData> datas = tradeDataDAO.queryTempByCondition(record);
		for (TradeData tradeData : datas) {
			String amt = tradeData.getAmt();
			if(Strings.isNullOrEmpty(amt)) {
				logger.error("id为"+tradeData.getId()+"流水的交易金额为空!不计算积分");
				continue;
			}
			
			long amtLong = Long.valueOf(amt);
			long firstLevel = 1000l;
			long secLevel = 50000l;
			
			/**
			 * 查询实体商户code
			 */
			if(Strings.isNullOrEmpty(tradeData.getInnerCode())) {
				logger.error("id为"+tradeData.getId()+"innerCode为空");
				continue;
			}
			
			logger.info("id为"+tradeData.getId()+"流水交易金额为"+tradeData.getAmt()+"统计积分");
			MerchantEntityCoreRef mcr = merchantEntityCoreRefDao.selectByInnerCodeLimit1(tradeData.getInnerCode());
			if(amtLong > firstLevel && amtLong <secLevel) {
				integralRuleLogService.insert(mcr.getEntityInnerCode(), IntegralRuleLog.IntegralTypeEnum.CODE_POS01.getCode());
			}else if(amtLong >= secLevel) {
				integralRuleLogService.insert(mcr.getEntityInnerCode(), IntegralRuleLog.IntegralTypeEnum.CODE_POS02.getCode());
			}
		}
	}
}
