package net.fnsco.trading.service.account;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.account.dao.AppAccountBalanceDAO;
import net.fnsco.trading.service.account.entity.AppAccountBalanceDO;

@Service
public class AppAccountBalanceService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppAccountBalanceDAO appAccountBalanceDAO;

	// 分页
	public ResultPageDTO<AppAccountBalanceDO> page(AppAccountBalanceDO appAccountBalance, Integer pageNum,
			Integer pageSize) {
		logger.info("开始分页查询AppAccountBalanceService.page, appAccountBalance=" + appAccountBalance.toString());
		List<AppAccountBalanceDO> pageList = this.appAccountBalanceDAO.pageList(appAccountBalance, pageNum, pageSize);
		Integer count = this.appAccountBalanceDAO.pageListCount(appAccountBalance);
		ResultPageDTO<AppAccountBalanceDO> pager = new ResultPageDTO<AppAccountBalanceDO>(count, pageList);
		return pager;
	}

	// 添加
	public AppAccountBalanceDO doAdd(AppAccountBalanceDO appAccountBalance, int loginUserId) {
		logger.info("开始添加AppAccountBalanceService.add,appAccountBalance=" + appAccountBalance.toString());
		this.appAccountBalanceDAO.insert(appAccountBalance);
		return appAccountBalance;
	}

	// 修改
	public Integer doUpdate(AppAccountBalanceDO appAccountBalance, Integer loginUserId) {
		logger.info("开始修改AppAccountBalanceService.update,appAccountBalance=" + appAccountBalance.toString());
		int rows = this.appAccountBalanceDAO.update(appAccountBalance);
		return rows;
	}

	// 删除
	public Integer doDelete(AppAccountBalanceDO appAccountBalance, Integer loginUserId) {
		logger.info("开始删除AppAccountBalanceService.delete,appAccountBalance=" + appAccountBalance.toString());
		int rows = this.appAccountBalanceDAO.deleteById(appAccountBalance.getId());
		return rows;
	}

	// 查询
	public AppAccountBalanceDO doQueryById(Integer id) {
		AppAccountBalanceDO obj = this.appAccountBalanceDAO.getById(id);
		return obj;
	}

	/**
	 * doQueryByAppUserId:(// 查询)
	 *
	 * @param @param
	 *            appUserId
	 * @param @return
	 *            设定文件
	 * @return AppAccountBalanceDO DOM对象
	 * @author tangliang
	 * @date 2017年12月7日 上午9:53:07
	 */
	public AppAccountBalanceDO doQueryByAppUserId(Integer appUserId) {
		AppAccountBalanceDO obj = this.appAccountBalanceDAO.getByAppUserId(appUserId);
		return obj;
	}
	
	/**
	 * doJudgeBalance:(查询用户余额是否足够，true：表示足够 false：表示不足够)
	 *
	 * @param  @param appUserId
	 * @param  @param fund
	 * @param  @return    设定文件
	 * @return boolean    DOM对象
	 * @author tangliang
	 * @date   2017年12月19日 上午9:53:06
	 */
	public boolean doJudgeBalance(Integer appUserId,BigDecimal fund) {
		int count = appAccountBalanceDAO.judgeBalance(fund, appUserId);
		if(count == 0) {
			return false;
		}
		
		return true;
	}
}