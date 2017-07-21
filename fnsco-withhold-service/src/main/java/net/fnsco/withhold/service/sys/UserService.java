package net.fnsco.withhold.service.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.withhold.service.sys.dao.UserDAO;
import net.fnsco.withhold.service.sys.entity.UserDO;

@Service
public class UserService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private UserDAO userDAO;

 // 分页
 public ResultPageDTO<UserDO> page(UserDO user, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询UserService.page, user=" + user.toString());
     List<UserDO> pageList = this.userDAO.pageList(user, pageNum, pageSize);
     Integer count = this.userDAO.pageListCount(user);
   ResultPageDTO<UserDO> pager =  new ResultPageDTO<UserDO>(count,pageList);
     return pager;
 }

 // 添加
 public UserDO doAdd (UserDO user,int loginUserId) {
     logger.info("开始添加UserService.add,user=" + user.toString());
     this.userDAO.insert(user);
     return user;
 }

 // 修改
 public Integer doUpdate (UserDO user,Integer loginUserId) {
     logger.info("开始修改UserService.update,user=" + user.toString());
     int rows=this.userDAO.update(user);
     return rows;
 }

 // 删除
 public Integer doDelete (UserDO user,Integer loginUserId) {
     logger.info("开始删除UserService.delete,user=" + user.toString());
     int rows=this.userDAO.deleteById(user.getId());
     return rows;
 }

 // 查询
 public UserDO doQueryById (Integer id) {
     UserDO obj = this.userDAO.getById(id);
     return obj;
 }
}