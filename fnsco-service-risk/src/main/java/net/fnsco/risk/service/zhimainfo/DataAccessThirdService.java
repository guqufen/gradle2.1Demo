package net.fnsco.risk.service.zhimainfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.zhimainfo.dao.DataAccessThirdDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataAccessThirdDO;

@Service
public class DataAccessThirdService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataAccessThirdDAO dataAccessThirdDAO;

 // 分页
 public ResultPageDTO<DataAccessThirdDO> page(DataAccessThirdDO dataAccessThird, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataAccessThirdService.page, dataAccessThird=" + dataAccessThird.toString());
     List<DataAccessThirdDO> pageList = this.dataAccessThirdDAO.pageList(dataAccessThird, pageNum, pageSize);
     Integer count = this.dataAccessThirdDAO.pageListCount(dataAccessThird);
   ResultPageDTO<DataAccessThirdDO> pager =  new ResultPageDTO<DataAccessThirdDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataAccessThirdDO doAdd (DataAccessThirdDO dataAccessThird,int loginUserId) {
     logger.info("开始添加DataAccessThirdService.add,dataAccessThird=" + dataAccessThird.toString());
     this.dataAccessThirdDAO.insert(dataAccessThird);
     return dataAccessThird;
 }

 // 修改
 public Integer doUpdate (DataAccessThirdDO dataAccessThird,Integer loginUserId) {
     logger.info("开始修改DataAccessThirdService.update,dataAccessThird=" + dataAccessThird.toString());
     int rows=this.dataAccessThirdDAO.update(dataAccessThird);
     return rows;
 }

 // 删除
 public Integer doDelete (DataAccessThirdDO dataAccessThird,Integer loginUserId) {
     logger.info("开始删除DataAccessThirdService.delete,dataAccessThird=" + dataAccessThird.toString());
     int rows=this.dataAccessThirdDAO.deleteById(dataAccessThird.getId());
     return rows;
 }

 // 查询
 public DataAccessThirdDO doQueryById (Integer id) {
     DataAccessThirdDO obj = this.dataAccessThirdDAO.getById(id);
     return obj;
 }
}