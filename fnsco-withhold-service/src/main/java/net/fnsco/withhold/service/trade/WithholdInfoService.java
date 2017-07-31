package net.fnsco.withhold.service.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.withhold.service.sys.UserService;
import net.fnsco.withhold.service.sys.dao.BankCodeDAO;
import net.fnsco.withhold.service.sys.entity.BankCodeDO;
import net.fnsco.withhold.service.sys.entity.UserDO;
import net.fnsco.withhold.service.trade.dao.WithholdInfoDAO;
import net.fnsco.withhold.service.trade.entity.WithholdInfoDO;

@Service
public class WithholdInfoService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WithholdInfoDAO withholdInfoDAO;
	@Autowired
	private BankCodeDAO bankCodeDAO;
	@Autowired
	private UserService userService;

	// 分页
	public ResultPageDTO<WithholdInfoDO> page(WithholdInfoDO withholdInfo, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询WithholdInfoService.page, withholdInfo=" + withholdInfo.toString());
		List<WithholdInfoDO> pageList = this.withholdInfoDAO.pageList(withholdInfo, pageNum, pageSize);
		// 根据最后修改人ID查询姓名
		List<WithholdInfoDO> pageListNew = new ArrayList<>();
		for (WithholdInfoDO withholdInfoDO : pageList) {
			if (null == withholdInfoDO.getModifyUserId()) {
				withholdInfoDO.setModifyUserName("--");
			} else {
				UserDO userDO = userService.doQueryById(withholdInfoDO.getModifyUserId());
				if (null == userDO) {
					withholdInfoDO.setModifyUserName("--");
				} else {
					withholdInfoDO.setModifyUserName(userDO.getName());
				}
			}

			//银行卡号处理
			String bankCard = withholdInfoDO.getBankCard();
			withholdInfoDO.setBankCard(bankCard.substring(0, 4)+"****"+bankCard.substring(bankCard.length()-4));
			
			//提交时间处理
			pageListNew.add(withholdInfoDO);
		}
		Integer count = this.withholdInfoDAO.pageListCount(withholdInfo);
		ResultPageDTO<WithholdInfoDO> pager = new ResultPageDTO<WithholdInfoDO>(count, pageListNew);
		return pager;
	}

	// 添加
	public WithholdInfoDO doAdd(WithholdInfoDO withholdInfo, int loginUserId) {
		logger.info("开始添加WithholdInfoService.add,withholdInfo=" + withholdInfo.toString());
		Date now = new Date();
		withholdInfo.setModifyUserId(loginUserId);
		withholdInfo.setModifyTime(now);
		withholdInfo.setCertifType("01");// 证件类型
		withholdInfo.setAmountTotal(new BigDecimal(0.00));
		withholdInfo.setAccountType("01");
		withholdInfo.setStatus(1);
		withholdInfo.setCertifType("01");// 设置身份证
		withholdInfo.setAccType("01");// 帐号类型
		withholdInfo.setFailTotal(0);
		// 计算扣款开始、结束日期
		Calendar calender = Calendar.getInstance();
		if (now.getDate() < Integer.valueOf(withholdInfo.getDebitDay())
				|| (now.getDate() == Integer.valueOf(withholdInfo.getDebitDay())
						&& (now.getHours() == 8 && now.getMinutes() <= 30 || now.getHours() <= 7))) {
			withholdInfo.setStartDate(DateUtils.getDateStrByInput(calender.get(Calendar.YEAR), now.getMonth(),
					Integer.valueOf(withholdInfo.getDebitDay())));
		} else {
			withholdInfo.setStartDate(DateUtils.getDateStrByInput(calender.get(Calendar.YEAR), now.getMonth() + 1,
					Integer.valueOf(withholdInfo.getDebitDay())));
		}

		withholdInfo.setEndDate(DateUtils.getDateStrByStrAdd(withholdInfo.getStartDate(), withholdInfo.getTotal() - 1));
		// 设置爱农编号
		if (StringUtils.isEmpty(withholdInfo.getBankCard()) || withholdInfo.getBankCard().length() < 6) {
			return null;
		}
		BankCodeDO bankCodeDO = bankCodeDAO.getByCardNum(withholdInfo.getBankCard(),
				withholdInfo.getBankCard().length());
		if (null == bankCodeDO || StringUtils.isEmpty(bankCodeDO.getCode())) {
			return null;
		}
		withholdInfo.setAnBankId(bankCodeDO.getCode());
		this.withholdInfoDAO.insert(withholdInfo);
		return withholdInfo;
	}

	// 修改
	public Integer doUpdate(WithholdInfoDO withholdInfo, Integer loginUserId) {
		logger.info("开始修改WithholdInfoService.update,withholdInfo=" + withholdInfo.toString());
		int rows = this.withholdInfoDAO.update(withholdInfo);
		return rows;
	}

	// 删除
	public Integer doDelete(WithholdInfoDO withholdInfo, Integer loginUserId) {
		logger.info("开始删除WithholdInfoService.delete,withholdInfo=" + withholdInfo.toString());
		int rows = this.withholdInfoDAO.deleteById(withholdInfo.getId());
		return rows;
	}

	// 查询
	public WithholdInfoDO doQueryById(Integer id) {
		WithholdInfoDO obj = this.withholdInfoDAO.getById(id);
		return obj;
	}
}