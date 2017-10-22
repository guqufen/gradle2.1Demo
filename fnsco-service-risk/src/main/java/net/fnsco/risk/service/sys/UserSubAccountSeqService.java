package net.fnsco.risk.service.sys;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.sys.dao.UserSubAccountSeqDAO;
import net.fnsco.risk.service.sys.entity.UserSubAccountSeqDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class UserSubAccountSeqService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private UserSubAccountSeqDAO userSubAccountSeqDAO;

 // 分页
 public ResultPageDTO<UserSubAccountSeqDO> page(UserSubAccountSeqDO userSubAccountSeq, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询UserSubAccountSeqService.page, userSubAccountSeq=" + userSubAccountSeq.toString());
     List<UserSubAccountSeqDO> pageList = this.userSubAccountSeqDAO.pageList(userSubAccountSeq, pageNum, pageSize);
     Integer count = this.userSubAccountSeqDAO.pageListCount(userSubAccountSeq);
   ResultPageDTO<UserSubAccountSeqDO> pager =  new ResultPageDTO<UserSubAccountSeqDO>(count,pageList);
     return pager;
 }

 // 添加
 public UserSubAccountSeqDO doAdd (UserSubAccountSeqDO userSubAccountSeq,int loginUserId) {
     logger.info("开始添加UserSubAccountSeqService.add,userSubAccountSeq=" + userSubAccountSeq.toString());
     this.userSubAccountSeqDAO.insert(userSubAccountSeq);
     return userSubAccountSeq;
 }

 // 修改
 public Integer doUpdate (UserSubAccountSeqDO userSubAccountSeq,Integer loginUserId) {
     logger.info("开始修改UserSubAccountSeqService.update,userSubAccountSeq=" + userSubAccountSeq.toString());
     int rows=this.userSubAccountSeqDAO.update(userSubAccountSeq);
     return rows;
 }

 // 删除
 public Integer doDelete (UserSubAccountSeqDO userSubAccountSeq,Integer loginUserId) {
     logger.info("开始删除UserSubAccountSeqService.delete,userSubAccountSeq=" + userSubAccountSeq.toString());
     int rows=this.userSubAccountSeqDAO.deleteById(userSubAccountSeq.getId());
     return rows;
 }

 // 查询
 public UserSubAccountSeqDO doQueryById (Integer id) {
     UserSubAccountSeqDO obj = this.userSubAccountSeqDAO.getById(id);
     return obj;
 }
}