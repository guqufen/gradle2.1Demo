package net.fnsco.order.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.merchant.IntegralRuleLogService;
import net.fnsco.order.service.dao.master.IntegralRuleDAO;
import net.fnsco.order.service.dao.master.IntegralRuleLogDAO;
import net.fnsco.order.service.domain.IntegralRule;
import net.fnsco.order.service.domain.IntegralRuleLog;
import net.fnsco.order.service.domain.IntegralRuleLog.IntegralTypeEnum;

@Service
public class IntegralRuleLogServiceImpl implements IntegralRuleLogService {

	@Autowired
	private IntegralRuleLogDAO integralRuleLogDAO;
	@Autowired
	private IntegralRuleDAO integralRuleDAO;
	@Autowired
	private MerchantEntityDao merchantEntityDao;

	@Override
	public List<IntegralRuleLog> queryListByCondition(IntegralRuleLog integralRuleLog) {

		return integralRuleLogDAO.queryListByCondition(integralRuleLog);
	}

	@Override
	public Integer querySumbyCondition(IntegralRuleLog integralRuleLog) {

		return integralRuleLogDAO.querySumbyCondition(integralRuleLog);
	}

	/**
	 * 将数据插入日志表中，先要判断封顶/计次数，然后去查找是否已经超过，没有超过则记录
	 * 主要数据：entityInnerCode实体商户号，ruleCode积分规则代码
	 */
	@Override
	@Transactional
	public void insert(String entityInnerCode, String ruleCode) {

		IntegralRuleLog integralRuleLog = new IntegralRuleLog();
		integralRuleLog.setEntityInnerCode(entityInnerCode);
		integralRuleLog.setRuleCode(ruleCode);

		// 通过code查找积分数值
		IntegralRule integralRule = integralRuleDAO.queryIntegralByCode(ruleCode);
		String type = IntegralTypeEnum.getDataByCode(ruleCode);// 通过code查找类型，封顶/计次/其他

		if ("1" == type) {// type=1表示封顶10分,code为001,002,007时

			// 通过积分日期和code去查找
			IntegralRuleLog integralRuleLog2 = new IntegralRuleLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			integralRuleLog2.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期

			Integer integralSum = integralRuleLogDAO.querySumbyCondition(integralRuleLog2);

			// 判不为空，不然后面直接加减会报错
			if (integralSum != null) {
				if ((integralSum + integralRule.getIntegral()) > 10) {
					return;
				}
			}
		} else if ("2" == type) {// type=2表示每日第一次，code为003

			// 通过积分日期和code去查找
			IntegralRuleLog integralRuleLog2 = new IntegralRuleLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			integralRuleLog2.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期
			Integer integralCount = integralRuleLogDAO.queryCountbyCondition(integralRuleLog2);

			if (integralCount > 0) {
				return;
			}
		} else if ("3" == type) {// type=3表示所有前3次，code为006

			// 通过积分日期和code去查找
			IntegralRuleLog integralRuleLog2 = new IntegralRuleLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			Integer integralCount = integralRuleLogDAO.queryCountbyCondition(integralRuleLog2);

			if (integralCount >= 3) {
				return;
			}
		}

		// 将数据存入积分日志表
		integralRuleLog.setRuleCode(integralRule.getCode());// 规则积分代码
		integralRuleLog.setIntegral(integralRule.getIntegral());// 设置积分
		integralRuleLog.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期
		integralRuleLog.setDescription(integralRule.getDescription());// 设置积分描述
		integralRuleLog.setCreateTime(new Date());// 创建时间
		integralRuleLogDAO.insert(integralRuleLog);// 存入数据库

		// 更新实体商户表里面的积分scores字段
		// 先通过entityCode查找到更新前的scores，然后再将scores加上本次积分，接着更新积分
		MerchantEntity merchantEntity = merchantEntityDao.selectByEntityInnerCode(integralRuleLog.getEntityInnerCode());
		merchantEntity.setScores(merchantEntity.getScores() + integralRule.getIntegral().longValue());// 积分相加
		merchantEntity.setLastModefyTimer(new Date());
		merchantEntityDao.updateByEntityInnerCode(merchantEntity);// 通过实体商户号更新
	}
}
