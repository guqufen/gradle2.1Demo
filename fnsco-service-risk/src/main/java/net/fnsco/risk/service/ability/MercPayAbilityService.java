package net.fnsco.risk.service.ability;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.ability.dao.MercPayAbilityDAO;
import net.fnsco.risk.service.ability.entity.MercPayAbilityDO;
import net.fnsco.risk.service.report.dao.UserMercRelDAO;
import net.fnsco.risk.service.sys.dao.IndustryDAO;
import net.fnsco.risk.service.sys.entity.IndustryDO;
import net.fnsco.risk.service.trade.dao.TradeDataDAO;

@Service
public class MercPayAbilityService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MercPayAbilityDAO mercPayAbilityDAO;
	@Autowired
	private UserMercRelDAO userMercRelDAO;
	@Autowired
	private TradeDataDAO tradeDataDAO;
	@Autowired
	private IndustryDAO industryDAO;

	// 分页
	public ResultPageDTO<MercPayAbilityDO> page(MercPayAbilityDO mercPayAbility, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询MercPayAbilityService.page, mercPayAbility=" + mercPayAbility.toString());
		List<MercPayAbilityDO> pageList = this.mercPayAbilityDAO.pageList(mercPayAbility, pageNum, pageSize);
		Integer count = this.mercPayAbilityDAO.pageListCount(mercPayAbility);
		ResultPageDTO<MercPayAbilityDO> pager = new ResultPageDTO<MercPayAbilityDO>(count, pageList);
		return pager;
	}

	// 添加
	public MercPayAbilityDO doAdd(MercPayAbilityDO mercPayAbility, int loginUserId) {
		logger.info("开始添加MercPayAbilityService.add,mercPayAbility=" + mercPayAbility.toString());
		this.mercPayAbilityDAO.insert(mercPayAbility);
		return mercPayAbility;
	}

	// 修改
	public Integer doUpdate(MercPayAbilityDO mercPayAbility, Integer loginUserId) {
		logger.info("开始修改MercPayAbilityService.update,mercPayAbility=" + mercPayAbility.toString());
		int rows = this.mercPayAbilityDAO.update(mercPayAbility);
		return rows;
	}

	// 删除
	public Integer doDelete(MercPayAbilityDO mercPayAbility, Integer loginUserId) {
		logger.info("开始删除MercPayAbilityService.delete,mercPayAbility=" + mercPayAbility.toString());
		int rows = this.mercPayAbilityDAO.deleteById(mercPayAbility.getId());
		return rows;
	}

	// 查询
	public MercPayAbilityDO doQueryById(Integer id) {
		MercPayAbilityDO obj = this.mercPayAbilityDAO.getById(id);
		return obj;
	}

	/**
	 * 计算各个商家还款能力，每个月计算一次
	 * 
	 * @param startTime 时间格式yyyyMMddHHmmss
	 * @param endTime 时间格式yyyyMMddHHmmss
	 */
	public void countRepaymentAbility(String startTime, String endTime) {
		/**
		 * 获取所有需要统计的实体商户商户号
		 */
		List<String> allEntityInnerCode = userMercRelDAO.getAllEntityInnerCode();
		if (CollectionUtils.isNotEmpty(allEntityInnerCode)) {
			String dateMonthStr = startTime.substring(0, 6);
			for (String entityInnerCode : allEntityInnerCode) {
				
				IndustryDO industryDO = industryDAO.getByEntityInnerCode(entityInnerCode);
				if (null != industryDO && !Strings.isNullOrEmpty(industryDO.getPosUsage()) &&!Strings.isNullOrEmpty(industryDO.getInterestRate()) ) {
					BigDecimal maxPrice = industryDO.getMaxPrice();
					if (null != maxPrice) {
						// 获取到商户实际交易流水
						String totalAmount = tradeDataDAO.queryMerchantMouthSum(startTime, endTime, entityInnerCode,
								maxPrice.toString());

						// 计算预估商户收入=商户实际交易流水/该商户的pos使用率
						if (!Strings.isNullOrEmpty(totalAmount)) {
							BigDecimal forecastIncome = new BigDecimal(totalAmount)
									.divide(new BigDecimal(industryDO.getPosUsage()));

							// 计算商户的还款能力=预估商户收入*净利率
							BigDecimal payAbility = forecastIncome
									.multiply(new BigDecimal(industryDO.getInterestRate()));
							
							saveDataToDB(entityInnerCode,dateMonthStr,payAbility);
						}
					}
				}
			}
		}

	}
	
	/**
	 * 保存入库
	 * @param mercPayAbility
	 */
	private void saveDataToDB(String entityInnerCode,String dateMonthStr,BigDecimal payAbility) {
		MercPayAbilityDO mercPayAbility = new MercPayAbilityDO();
		mercPayAbility.setEntityInnerCode(entityInnerCode);
		mercPayAbility.setMonth(dateMonthStr);
		int total = mercPayAbilityDAO.pageListCount(mercPayAbility);
		mercPayAbility.setPayAbility(payAbility);
		if(total > 0) {
			mercPayAbilityDAO.update(mercPayAbility);
		}else {
			mercPayAbilityDAO.insert(mercPayAbility);
		}
	}
}