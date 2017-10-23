package net.fnsco.risk.service.sys;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.sys.dao.UserSubAccountDAO;
import net.fnsco.risk.service.sys.entity.UserSubAccountDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class UserSubAccountService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private UserSubAccountDAO userSubAccountDAO;

 // 分页
 public ResultPageDTO<UserSubAccountDO> page(UserSubAccountDO userSubAccount, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询UserSubAccountService.page, userSubAccount=" + userSubAccount.toString());
     List<UserSubAccountDO> pageList = this.userSubAccountDAO.pageList(userSubAccount, pageNum, pageSize);
     Integer count = this.userSubAccountDAO.pageListCount(userSubAccount);
   ResultPageDTO<UserSubAccountDO> pager =  new ResultPageDTO<UserSubAccountDO>(count,pageList);
     return pager;
 }

 // 添加
 public UserSubAccountDO doAdd (UserSubAccountDO userSubAccount,int loginUserId) {
     logger.info("开始添加UserSubAccountService.add,userSubAccount=" + userSubAccount.toString());
     this.userSubAccountDAO.insert(userSubAccount);
     return userSubAccount;
 }

 // 修改
 public Integer doUpdate (UserSubAccountDO userSubAccount,Integer loginUserId) {
     logger.info("开始修改UserSubAccountService.update,userSubAccount=" + userSubAccount.toString());
     int rows=this.userSubAccountDAO.update(userSubAccount);
     return rows;
 }

 // 删除
 public Integer doDelete (UserSubAccountDO userSubAccount,Integer loginUserId) {
     logger.info("开始删除UserSubAccountService.delete,userSubAccount=" + userSubAccount.toString());
     int rows=this.userSubAccountDAO.deleteById(userSubAccount.getId());
     return rows;
 }

 // 查询
 public UserSubAccountDO doQueryById (Integer id) {
     UserSubAccountDO obj = this.userSubAccountDAO.getById(id);
     return obj;
 }
}