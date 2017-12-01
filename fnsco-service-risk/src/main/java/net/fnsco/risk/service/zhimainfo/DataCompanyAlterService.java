package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyAlterDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyAlterDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyAlterService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyAlterDAO dataCompanyAlterDAO;

 // 分页
 public ResultPageDTO<DataCompanyAlterDO> page(DataCompanyAlterDO dataCompanyAlter, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyAlterService.page, dataCompanyAlter=" + dataCompanyAlter.toString());
     List<DataCompanyAlterDO> pageList = this.dataCompanyAlterDAO.pageList(dataCompanyAlter, pageNum, pageSize);
     Integer count = this.dataCompanyAlterDAO.pageListCount(dataCompanyAlter);
   ResultPageDTO<DataCompanyAlterDO> pager =  new ResultPageDTO<DataCompanyAlterDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyAlterDO doAdd (DataCompanyAlterDO dataCompanyAlter,int loginUserId) {
     logger.info("开始添加DataCompanyAlterService.add,dataCompanyAlter=" + dataCompanyAlter.toString());
     this.dataCompanyAlterDAO.insert(dataCompanyAlter);
     return dataCompanyAlter;
 }

 // 修改
 public Integer doUpdate (DataCompanyAlterDO dataCompanyAlter,Integer loginUserId) {
     logger.info("开始修改DataCompanyAlterService.update,dataCompanyAlter=" + dataCompanyAlter.toString());
     int rows=this.dataCompanyAlterDAO.update(dataCompanyAlter);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyAlterDO dataCompanyAlter,Integer loginUserId) {
     logger.info("开始删除DataCompanyAlterService.delete,dataCompanyAlter=" + dataCompanyAlter.toString());
     int rows=this.dataCompanyAlterDAO.deleteById(dataCompanyAlter.getId());
     return rows;
 }

 // 查询
 public DataCompanyAlterDO doQueryById (Integer id) {
     DataCompanyAlterDO obj = this.dataCompanyAlterDAO.getById(id);
     return obj;
 }
}