package net.fnsco.order.service.act;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.order.service.act.dao.LoanApplyUserDAO;
import net.fnsco.order.service.act.entity.LoanApplyUserDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class LoanApplyUserService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private LoanApplyUserDAO loanApplyUserDAO;

 // 分页
 public ResultPageDTO<LoanApplyUserDO> page(LoanApplyUserDO loanApplyUser, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询LoanApplyUserService.page, loanApplyUser=" + loanApplyUser.toString());
     List<LoanApplyUserDO> pageList = this.loanApplyUserDAO.pageList(loanApplyUser, pageNum, pageSize);
     Integer count = this.loanApplyUserDAO.pageListCount(loanApplyUser);
   ResultPageDTO<LoanApplyUserDO> pager =  new ResultPageDTO<LoanApplyUserDO>(count,pageList);
     return pager;
 }

 // 添加
 public LoanApplyUserDO doAdd (LoanApplyUserDO loanApplyUser,int loginUserId) {
     logger.info("开始添加LoanApplyUserService.add,loanApplyUser=" + loanApplyUser.toString());
     this.loanApplyUserDAO.insert(loanApplyUser);
     return loanApplyUser;
 }

 // 修改
 public Integer doUpdate (LoanApplyUserDO loanApplyUser,Integer loginUserId) {
     logger.info("开始修改LoanApplyUserService.update,loanApplyUser=" + loanApplyUser.toString());
     int rows=this.loanApplyUserDAO.update(loanApplyUser);
     return rows;
 }

 // 删除
 public Integer doDelete (LoanApplyUserDO loanApplyUser,Integer loginUserId) {
     logger.info("开始删除LoanApplyUserService.delete,loanApplyUser=" + loanApplyUser.toString());
     int rows=this.loanApplyUserDAO.deleteById(loanApplyUser.getId());
     return rows;
 }

 // 查询
 public LoanApplyUserDO doQueryById (Integer id) {
     LoanApplyUserDO obj = this.loanApplyUserDAO.getById(id);
     return obj;
 }
}