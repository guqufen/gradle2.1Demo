package net.fnsco.trading.service.appUser;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.appUser.dao.AppUserDAO;
import net.fnsco.trading.service.appUser.entity.AppUserDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class AppUserService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AppUserDAO appUserDAO;

 // 分页
 public ResultPageDTO<AppUserDO> page(AppUserDO appUser, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppUserService.page, appUser=" + appUser.toString());
     List<AppUserDO> pageList = this.appUserDAO.pageList(appUser, pageNum, pageSize);
     Integer count = this.appUserDAO.pageListCount(appUser);
   ResultPageDTO<AppUserDO> pager =  new ResultPageDTO<AppUserDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppUserDO doAdd (AppUserDO appUser,int loginUserId) {
     logger.info("开始添加AppUserService.add,appUser=" + appUser.toString());
     this.appUserDAO.insert(appUser);
     return appUser;
 }

 // 修改
 public Integer doUpdate (AppUserDO appUser,Integer loginUserId) {
     logger.info("开始修改AppUserService.update,appUser=" + appUser.toString());
     int rows=this.appUserDAO.update(appUser);
     return rows;
 }

 // 删除
 public Integer doDelete (AppUserDO appUser,Integer loginUserId) {
     logger.info("开始删除AppUserService.delete,appUser=" + appUser.toString());
     int rows=this.appUserDAO.deleteById(appUser.getId());
     return rows;
 }

 // 查询
 public AppUserDO doQueryById (Integer id) {
     AppUserDO obj = this.appUserDAO.getById(id);
     return obj;
 }
}