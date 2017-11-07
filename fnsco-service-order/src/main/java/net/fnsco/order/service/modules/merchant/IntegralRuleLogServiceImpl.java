package net.fnsco.order.service.modules.merchant;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.service.dao.master.MerchantEntityDao;
import net.fnsco.bigdata.service.domain.MerchantEntity;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ConstantEnum.IntegralTypeEnum;
import net.fnsco.order.api.merchant.IntegralRuleLogService;
import net.fnsco.order.service.dao.master.IntegralRuleDAO;
import net.fnsco.order.service.dao.master.IntegralRuleLogDAO;
import net.fnsco.order.service.domain.IntegralRule;
import net.fnsco.order.service.domain.IntegralRuleLog;


@Service
public class IntegralRuleLogServiceImpl extends BaseService implements IntegralRuleLogService{

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
		insert(entityInnerCode, ruleCode, null);

	}

	/**
	 * 查询积分,根据实体商户号查询邀请新商家成功的积分信息
	 * 
	 * @param integralRuleLog
	 * @return
	 */
	public List<IntegralRuleLog> queryListByEntityInnerCode(String entityInnerCode) {
		IntegralRuleLog integralRuleLog2 = new IntegralRuleLog();
		integralRuleLog2.setEntityInnerCode(entityInnerCode);// 设置实体商户号
		integralRuleLog2.setRuleCode("004");// 设置规则代码
		return integralRuleLogDAO.queryListByCondition(integralRuleLog2);
	}

	// 特殊处理，针对有针对分享链接(传入描述)
	@Transactional
	public void insert(String entityInnerCode, String ruleCode, String description) {

		// 判断如果参数有一个为空
		if (StringUtils.isBlank(entityInnerCode) || StringUtils.isBlank(ruleCode)) {
			logger.error("入参为空：entityInnerCode=[" + entityInnerCode + "],ruleCode=[" + ruleCode + "]");
			return;
		}

		IntegralRuleLog integralRuleLog = dealInsertData(entityInnerCode, ruleCode, description);
		// 返回为空，则不用更新
		if (null == integralRuleLog) {
			return;
		}
		integralRuleLogDAO.insert(integralRuleLog);// 存入数据库

		// 更新实体商户表里面的积分scores字段
		// 先通过entityCode查找到更新前的scores，然后再将scores加上本次积分，接着更新积分
		MerchantEntity merchantEntity = merchantEntityDao.selectByEntityInnerCode(integralRuleLog.getEntityInnerCode());
		if (null != merchantEntity) {
			merchantEntity.setLastModefyTimer(new Date());
			merchantEntityDao.updateByEntityInnerCode(merchantEntity);// 通过实体商户号更新
		}
	}

	// 处理插日志表公共部分，比如判断
	public IntegralRuleLog dealInsertData(String entityInnerCode, String ruleCode, String description) {

		IntegralRuleLog integralRuleLog = new IntegralRuleLog();
		integralRuleLog.setEntityInnerCode(entityInnerCode);
		integralRuleLog.setRuleCode(ruleCode);

		// 通过code查找积分数值
		IntegralRule integralRule = integralRuleDAO.queryIntegralByCode(ruleCode);
		if(null == integralRule){
			return null;
		}

		// 通过code查找积分类型
		String type = IntegralTypeEnum.getTypeByCode(integralRule.getCode());// 通过code查找类型，封顶/计次/其他
		if(null == type){
			return null;
		}

		if ("1" == type) {// type=1表示封顶,code为001,002,007时

			// 通过积分日期和code去查找
			IntegralRuleLog integralRuleLog2 = new IntegralRuleLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			integralRuleLog2.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期

			Integer integralSum = integralRuleLogDAO.querySumbyCondition(integralRuleLog2);

			// 判不为空，不然后面直接加减会报错
			if (integralSum != null) {
				if ("007".equals(integralRuleLog.getRuleCode())) {// code=007表示记账，封顶10分
					if ((integralSum + integralRule.getIntegral()) > 10) {// pos收银，封顶100分
						return null;
					}
				} else {
					if ((integralSum + integralRule.getIntegral()) > 100) {// pos收银，封顶100分
						return null;
					}
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
				return null;
			}
		} else if ("3" == type) {// type=3表示所有前3次，code为006

			// 通过积分日期和code去查找
			IntegralRuleLog integralRuleLog2 = new IntegralRuleLog();
			integralRuleLog2.setEntityInnerCode(integralRuleLog.getEntityInnerCode());// 设置实体商户号
			integralRuleLog2.setRuleCode(integralRuleLog.getRuleCode());// 设置规则代码
			Integer integralCount = integralRuleLogDAO.queryCountbyCondition(integralRuleLog2);

			if (integralCount >= 3) {
				return null;
			}
		}

		// 将数据存入积分日志表
		integralRuleLog.setRuleCode(integralRule.getCode());// 规则积分代码
		integralRuleLog.setIntegral(integralRule.getIntegral());// 设置积分
		integralRuleLog.setIntegralDate(DateUtils.getDateStrYYYYMMDD(new Date()));// 设置积分日期
		// 如果传入描述是否为空
		if (StringUtils.isNotBlank(description)) {
			integralRuleLog.setDescription(description);// 不为空，则取其值
		} else {
			integralRuleLog.setDescription(integralRule.getDescription());// 设置积分描述
		}
		integralRuleLog.setCreateTime(new Date());// 创建时间

		return integralRuleLog;
	}
}

