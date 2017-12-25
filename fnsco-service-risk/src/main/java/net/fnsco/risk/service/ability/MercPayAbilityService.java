package net.fnsco.risk.service.ability;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.risk.service.ability.dao.MercPayAbilityDAO;
import net.fnsco.risk.service.ability.entity.MercPayAbilityDO;
import net.fnsco.risk.service.report.dao.UserMercRelDAO;
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
	 * @param startTime
	 * @param endTime
	 */
	public void countRepaymentAbility(String startTime,String endTime) {
		/**
		 * 获取所有需要统计的实体商户商户号
		 */
		List<String> allEntityInnerCode = userMercRelDAO.getAllEntityInnerCode();
		if(CollectionUtils.isNotEmpty(allEntityInnerCode)) {
			String dateMonthStr = startTime.substring(0, 6);
			for (String entityInnerCode : allEntityInnerCode) {
				String totalAmount = tradeDataDAO.queryMerchantMouthSum("", "", entityInnerCode, "");
				if(!Strings.isNullOrEmpty(totalAmount)) {
					MercPayAbilityDO mercPayAbility = new MercPayAbilityDO();
					mercPayAbility.setEntityInnerCode(entityInnerCode);
					mercPayAbility.setMonth(dateMonthStr);
					mercPayAbility.setPayAbility(new BigDecimal(totalAmount));
					mercPayAbilityDAO.insert(mercPayAbility);
				}
			}
		}
		
	}
}