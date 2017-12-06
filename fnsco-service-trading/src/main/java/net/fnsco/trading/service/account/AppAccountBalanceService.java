package net.fnsco.trading.service.account;

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
 public ResultPageDTO<AppAccountBalanceDO> page(AppAccountBalanceDO appAccountBalance, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppAccountBalanceService.page, appAccountBalance=" + appAccountBalance.toString());
     List<AppAccountBalanceDO> pageList = this.appAccountBalanceDAO.pageList(appAccountBalance, pageNum, pageSize);
     Integer count = this.appAccountBalanceDAO.pageListCount(appAccountBalance);
   ResultPageDTO<AppAccountBalanceDO> pager =  new ResultPageDTO<AppAccountBalanceDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppAccountBalanceDO doAdd (AppAccountBalanceDO appAccountBalance,int loginUserId) {
     logger.info("开始添加AppAccountBalanceService.add,appAccountBalance=" + appAccountBalance.toString());
     this.appAccountBalanceDAO.insert(appAccountBalance);
     return appAccountBalance;
 }

 // 修改
 public Integer doUpdate (AppAccountBalanceDO appAccountBalance,Integer loginUserId) {
     logger.info("开始修改AppAccountBalanceService.update,appAccountBalance=" + appAccountBalance.toString());
     int rows=this.appAccountBalanceDAO.update(appAccountBalance);
     return rows;
 }

 // 删除
 public Integer doDelete (AppAccountBalanceDO appAccountBalance,Integer loginUserId) {
     logger.info("开始删除AppAccountBalanceService.delete,appAccountBalance=" + appAccountBalance.toString());
     int rows=this.appAccountBalanceDAO.deleteById(appAccountBalance.getId());
     return rows;
 }

 // 查询
 public AppAccountBalanceDO doQueryById (Integer id) {
     AppAccountBalanceDO obj = this.appAccountBalanceDAO.getById(id);
     return obj;
 }
}