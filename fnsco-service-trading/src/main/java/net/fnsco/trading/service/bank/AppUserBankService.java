package net.fnsco.trading.service.bank;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.bank.dao.AppUserBankDAO;
import net.fnsco.trading.service.bank.entity.AppUserBankDO;

@Service
public class AppUserBankService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AppUserBankDAO appUserBankDAO;

 // 分页
 public ResultPageDTO<AppUserBankDO> page(AppUserBankDO appUserBank, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppUserBankService.page, appUserBank=" + appUserBank.toString());
     List<AppUserBankDO> pageList = this.appUserBankDAO.pageList(appUserBank, pageNum, pageSize);
     Integer count = this.appUserBankDAO.pageListCount(appUserBank);
   ResultPageDTO<AppUserBankDO> pager =  new ResultPageDTO<AppUserBankDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppUserBankDO doAdd (AppUserBankDO appUserBank,int loginUserId) {
     logger.info("开始添加AppUserBankService.add,appUserBank=" + appUserBank.toString());
     this.appUserBankDAO.insert(appUserBank);
     return appUserBank;
 }

 // 修改
 public Integer doUpdate (AppUserBankDO appUserBank,Integer loginUserId) {
     logger.info("开始修改AppUserBankService.update,appUserBank=" + appUserBank.toString());
     int rows=this.appUserBankDAO.update(appUserBank);
     return rows;
 }

 // 删除
 public Integer doDelete (AppUserBankDO appUserBank,Integer loginUserId) {
     logger.info("开始删除AppUserBankService.delete,appUserBank=" + appUserBank.toString());
     int rows=this.appUserBankDAO.deleteById(appUserBank.getId());
     return rows;
 }

 // 查询
 public AppUserBankDO doQueryById (Integer id) {
     AppUserBankDO obj = this.appUserBankDAO.getById(id);
     return obj;
 }

public Integer unBindBankCard(String userID, Integer bankId) {
	int rows=this.appUserBankDAO.unBindBankCard(userID,bankId);
    return rows;
	
}
}