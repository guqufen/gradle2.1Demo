package net.fnsco.order.service.modules.merchant;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ConstantEnum.IntegralTypeEnum;
import net.fnsco.order.api.merchant.IntegralLogService;
import net.fnsco.order.service.dao.master.IntegralRuleDAO;
import net.fnsco.order.service.dao.master.IntegralLogDAO;
import net.fnsco.order.service.domain.IntegralRule;
import net.fnsco.order.service.domain.IntegralLog;


@Service
public class IntegralLogServiceImpl extends BaseService implements IntegralLogService{

	@Autowired
	private IntegralLogDAO integralLogDAO;
	@Autowired
	private IntegralRuleDAO integralRuleDAO;
	@Autowired
	private MerchantEntityDao merchantEntityDao;

	@Override
	public List<IntegralLog> queryListByCondition(IntegralLog integralRuleLog) {

		return integralLogDAO.queryListByCondition(integralRuleLog);
	}

	@Override
	public Integer querySumbyCondition(IntegralLog integralRuleLog) {

		return integralLogDAO.querySumbyCondition(integralRuleLog);
	}

	/**
	 * 将数据插入日志表中，先要判断封顶/计次数，然后去查找是否已经超过，没有超过则记录
	 * 主要数据：entityInnerCode实体商户号，ruleCode积分规则代码
	 */
	@Override
	public int insert(String entityInnerCode, String ruleCode) {
		int result = insert(entityInnerCode, ruleCode, null);
		return result;
	}

	/**
	 * 查询积分,根据实体商户号查询邀请新商家成功的积分信息
	 * 
	 * @param integralRuleLog
	 * @return
	 */
	public List<IntegralLog> queryListByEntityInnerCode(String entityInnerCode) {
		IntegralLog integralRuleLog2 = new IntegralLog();
		integralRuleLog2.setEntityInnerCode(entityInnerCode);// 设置实体商户号
		integralRuleLog2.setRuleCode("004");// 设置规则代码
		return integralLogDAO.queryListByCondition(integralRuleLog2);
	}

	// 特殊处理，针对有针对分享链接(传入描述)
	@Transactional
	public int insert(String entityInnerCode, String ruleCode, String description) {

		// 判断如果参数有一个为空
		if (Strings.isNullOrEmpty(entityInnerCode) || Strings.isNullOrEmpty(ruleCode)) {
			logger.error("入参为空：entityInnerCode=[" + entityInnerCode + "],ruleCode=[" + ruleCode + "]");
			return -1;
		}

		IntegralLog integralRuleLog = dealInsertData(entityInnerCode, ruleCode, description);
		// 返回为空，则不用更新
		if (null == integralRuleLog) {
			return 1;
		}
		int result = integralLogDAO.insert(integralRuleLog);// 存入数据库

		// 更新实体商户表里面的积分scores字段
		// 先通过entityCode查找到更新前的scores，然后再将scores加上本次积分，接着更新积分
		MerchantEntity merchantEntity = merchantEntityDao.selectByEntityInnerCode(integralRuleLog.getEntityInnerCode());
		if (null != merchantEntity) {
			logger.info("更新实体商户积分entityInnerCode=[" + entityInnerCode + "],原积分scores=[" + merchantEntity.getScores() + "]，本次累计积分integral=["+integralRuleLog.getIntegral()+"]");
			merchantEntity.setLastModefyTimer(new Date());
			merchantEntity.setScores(new BigDecimal(integralRuleLog.getIntegral()));//设置商户积分，便于后续相加
			merchantEntityDao.updateByEntityInnerCode(merchantEntity);// 通过实体商户号更新
		}

		return result;
	}

	// 处理插日志表公共部分，比如判断
	public IntegralLog dealInsertData(String entityInnerCode, String ruleCode, String description) {

		IntegralLog integralRuleLog = new IntegralLog();
		integralRuleLog.setEntityInnerCode(entityInnerCode);
		integralRuleLog.setRuleCode(ruleCode);

		// 通过code查找积分数值
		IntegralRule integralRule = integralRuleDAO.queryIntegralByCode(ruleCode);
		if(null == integralRule){
			logger.error("未通过积分规则代码找到相关数据ruleCode=["+integralRule.getCode()+"]，请核查");
			return null;
		}

		// 通过code查找积分类型
		String type = IntegralTypeEnum.getTypeByCode(integralRule.getCode());// 通过code查找类型，封顶/计次/其他
		if(null == type){
			logger.error("未找到及分类型(封顶/计次/其他)ruleCode=["+integralRule.getCode()+"]，请核查");
			return null;
		}

		if ("1" == type) {// type=1表示封顶,code为001,002,007时

			// 通过积分日期和code去查找
			IntegralLog integralRuleLog2 = new IntegralLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			integralRuleLog2.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期

			Integer integralSum = integralLogDAO.querySumbyCondition(integralRuleLog2);

			// 判不为空，不然后面直接加减会报错
			if (integralSum != null) {
				if ("007".equals(integralRuleLog.getRuleCode())) {// code=007表示记账，封顶10分
					if (integralSum  >= 10) {
						logger.error("本次积分为封顶10分，目前积分scores=["+integralSum+"]，已达到封顶条件，此次不计分");
						return null;
					}
				} else {
					if (integralSum  >= 100) {// pos收银，封顶100分
						logger.error("本次积分为封顶100分，目前积分scores=["+integralSum+"]，已达到封顶条件，此次不计分");
						return null;
					}
				}
			}
		} else if ("2" == type) {// type=2表示每日第一次，code为003

			// 通过积分日期和code去查找
			IntegralLog integralRuleLog2 = new IntegralLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			integralRuleLog2.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期
			Integer integralCount = integralLogDAO.queryCountbyCondition(integralRuleLog2);

			if (integralCount > 0) {
				logger.error("本次积分为当日第一次积分有效，当天已经积过分，此次不计分");
				return null;
			}
		} else if ("3" == type) {// type=3表示所有前3次，code为006

			// 通过积分日期和code去查找
			IntegralLog integralRuleLog2 = new IntegralLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			Integer integralCount = integralLogDAO.queryCountbyCondition(integralRuleLog2);

			if (integralCount >= 3) {
				logger.error("本次积分为所有前3次有效，已达到条件，此次不计分");
				return null;
			}
		}

		// 将数据存入积分日志表
		integralRuleLog.setRuleCode(integralRule.getCode());// 规则积分代码
		integralRuleLog.setIntegral(integralRule.getIntegral());// 设置积分
		integralRuleLog.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期
		// 如果传入描述是否为空
		if ( !Strings.isNullOrEmpty(description) ) {
			integralRuleLog.setDescription(description);// 不为空，则取其值
		} else {
			integralRuleLog.setDescription(integralRule.getDescription());// 设置积分描述
		}
		integralRuleLog.setCreateTime(new Date());// 创建时间

		return integralRuleLog;
	}
}

