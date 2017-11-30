package net.fnsco.risk.service.zhimainfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.risk.service.zhimainfo.dao.DataLawsuitDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDO;

@Service
public class DataLawsuitService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataLawsuitDAO dataLawsuitDAO;

 // 分页
 public ResultPageDTO<DataLawsuitDO> page(DataLawsuitDO dataLawsuit, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataLawsuitService.page, dataLawsuit=" + dataLawsuit.toString());
     List<DataLawsuitDO> pageList = this.dataLawsuitDAO.pageList(dataLawsuit, pageNum, pageSize);
     Integer count = this.dataLawsuitDAO.pageListCount(dataLawsuit);
   ResultPageDTO<DataLawsuitDO> pager =  new ResultPageDTO<DataLawsuitDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataLawsuitDO doAdd (DataLawsuitDO dataLawsuit,int loginUserId) {
     logger.info("开始添加DataLawsuitService.add,dataLawsuit=" + dataLawsuit.toString());
     this.dataLawsuitDAO.insert(dataLawsuit);
     return dataLawsuit;
 }

 // 修改
 public Integer doUpdate (DataLawsuitDO dataLawsuit,Integer loginUserId) {
     logger.info("开始修改DataLawsuitService.update,dataLawsuit=" + dataLawsuit.toString());
     int rows=this.dataLawsuitDAO.update(dataLawsuit);
     return rows;
 }

 // 删除
 public Integer doDelete (DataLawsuitDO dataLawsuit,Integer loginUserId) {
     logger.info("开始删除DataLawsuitService.delete,dataLawsuit=" + dataLawsuit.toString());
     int rows=this.dataLawsuitDAO.deleteById(dataLawsuit.getId());
     return rows;
 }

 // 查询
 public DataLawsuitDO doQueryById (Integer id) {
     DataLawsuitDO obj = this.dataLawsuitDAO.getById(id);
     return obj;
 }
}