package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyShareHolderDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyShareHolderDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyShareHolderService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyShareHolderDAO dataCompanyShareHolderDAO;

 // 分页
 public ResultPageDTO<DataCompanyShareHolderDO> page(DataCompanyShareHolderDO dataCompanyShareHolder, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyShareHolderService.page, dataCompanyShareHolder=" + dataCompanyShareHolder.toString());
     List<DataCompanyShareHolderDO> pageList = this.dataCompanyShareHolderDAO.pageList(dataCompanyShareHolder, pageNum, pageSize);
     Integer count = this.dataCompanyShareHolderDAO.pageListCount(dataCompanyShareHolder);
   ResultPageDTO<DataCompanyShareHolderDO> pager =  new ResultPageDTO<DataCompanyShareHolderDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyShareHolderDO doAdd (DataCompanyShareHolderDO dataCompanyShareHolder,int loginUserId) {
     logger.info("开始添加DataCompanyShareHolderService.add,dataCompanyShareHolder=" + dataCompanyShareHolder.toString());
     this.dataCompanyShareHolderDAO.insert(dataCompanyShareHolder);
     return dataCompanyShareHolder;
 }

 // 修改
 public Integer doUpdate (DataCompanyShareHolderDO dataCompanyShareHolder,Integer loginUserId) {
     logger.info("开始修改DataCompanyShareHolderService.update,dataCompanyShareHolder=" + dataCompanyShareHolder.toString());
     int rows=this.dataCompanyShareHolderDAO.update(dataCompanyShareHolder);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyShareHolderDO dataCompanyShareHolder,Integer loginUserId) {
     logger.info("开始删除DataCompanyShareHolderService.delete,dataCompanyShareHolder=" + dataCompanyShareHolder.toString());
     int rows=this.dataCompanyShareHolderDAO.deleteById(dataCompanyShareHolder.getId());
     return rows;
 }

 // 查询
 public DataCompanyShareHolderDO doQueryById (Integer id) {
     DataCompanyShareHolderDO obj = this.dataCompanyShareHolderDAO.getById(id);
     return obj;
 }
}