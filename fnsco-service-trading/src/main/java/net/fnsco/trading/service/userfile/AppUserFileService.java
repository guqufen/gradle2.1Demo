package net.fnsco.trading.service.userfile;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.userfile.dao.AppUserFileDAO;
import net.fnsco.trading.service.userfile.entity.AppUserFileDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class AppUserFileService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AppUserFileDAO appUserFileDAO;

 // 分页
 public ResultPageDTO<AppUserFileDO> page(AppUserFileDO appUserFile, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppUserFileService.page, appUserFile=" + appUserFile.toString());
     List<AppUserFileDO> pageList = this.appUserFileDAO.pageList(appUserFile, pageNum, pageSize);
     Integer count = this.appUserFileDAO.pageListCount(appUserFile);
   ResultPageDTO<AppUserFileDO> pager =  new ResultPageDTO<AppUserFileDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppUserFileDO doAdd (AppUserFileDO appUserFile) {
     logger.info("开始添加AppUserFileService.add,appUserFile=" + appUserFile.toString());
     this.appUserFileDAO.insert(appUserFile);
     return appUserFile;
 }

 // 修改
 public Integer doUpdate (AppUserFileDO appUserFile,Integer loginUserId) {
     logger.info("开始修改AppUserFileService.update,appUserFile=" + appUserFile.toString());
     int rows=this.appUserFileDAO.update(appUserFile);
     return rows;
 }

 // 删除
 public Integer doDelete (AppUserFileDO appUserFile,Integer loginUserId) {
     logger.info("开始删除AppUserFileService.delete,appUserFile=" + appUserFile.toString());
     int rows=this.appUserFileDAO.deleteById(appUserFile.getId());
     return rows;
 }

 // 查询
 public AppUserFileDO doQueryById (Integer id) {
     AppUserFileDO obj = this.appUserFileDAO.getById(id);
     return obj;
 }
 
 //查询
 public AppUserFileDO doQueryByUserId (Integer appUserId,String fileType) {
   AppUserFileDO obj = this.appUserFileDAO.getByUserId (appUserId,fileType);
   return obj;
 }
 //根据appuserid和正反面删除
 public Integer deleteByIdAndSide (Integer appUserId,String fileType) {
   int rows=this.appUserFileDAO.deleteByIdAndSide(appUserId,fileType);
   return rows;
}
}