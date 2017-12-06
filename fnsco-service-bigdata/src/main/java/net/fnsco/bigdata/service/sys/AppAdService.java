package net.fnsco.bigdata.service.sys;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.service.sys.dao.AppAdDAO;
import net.fnsco.bigdata.service.sys.entity.AppAdDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class AppAdService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private AppAdDAO appAdDAO;

 // 分页
 public ResultPageDTO<AppAdDO> page(AppAdDO appAd, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询AppAdService.page, appAd=" + appAd.toString());
     List<AppAdDO> pageList = this.appAdDAO.pageList(appAd, pageNum, pageSize);
     Integer count = this.appAdDAO.pageListCount(appAd);
   ResultPageDTO<AppAdDO> pager =  new ResultPageDTO<AppAdDO>(count,pageList);
     return pager;
 }

 // 添加
 public AppAdDO doAdd (AppAdDO appAd,int loginUserId) {
     logger.info("开始添加AppAdService.add,appAd=" + appAd.toString());
     this.appAdDAO.insert(appAd);
     return appAd;
 }

 // 修改
 public Integer doUpdate (AppAdDO appAd,Integer loginUserId) {
     logger.info("开始修改AppAdService.update,appAd=" + appAd.toString());
     int rows=this.appAdDAO.update(appAd);
     return rows;
 }

 // 删除
 public Integer doDelete (AppAdDO appAd,Integer loginUserId) {
     logger.info("开始删除AppAdService.delete,appAd=" + appAd.toString());
     int rows=this.appAdDAO.deleteById(appAd.getId());
     return rows;
 }

 // 查询
 public AppAdDO doQueryById (Integer id) {
     AppAdDO obj = this.appAdDAO.getById(id);
     return obj;
 }
}