package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyFrinvDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrinvDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyFrinvService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyFrinvDAO dataCompanyFrinvDAO;

 // 分页
 public ResultPageDTO<DataCompanyFrinvDO> page(DataCompanyFrinvDO dataCompanyFrinv, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyFrinvService.page, dataCompanyFrinv=" + dataCompanyFrinv.toString());
     List<DataCompanyFrinvDO> pageList = this.dataCompanyFrinvDAO.pageList(dataCompanyFrinv, pageNum, pageSize);
     Integer count = this.dataCompanyFrinvDAO.pageListCount(dataCompanyFrinv);
   ResultPageDTO<DataCompanyFrinvDO> pager =  new ResultPageDTO<DataCompanyFrinvDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyFrinvDO doAdd (DataCompanyFrinvDO dataCompanyFrinv,int loginUserId) {
     logger.info("开始添加DataCompanyFrinvService.add,dataCompanyFrinv=" + dataCompanyFrinv.toString());
     this.dataCompanyFrinvDAO.insert(dataCompanyFrinv);
     return dataCompanyFrinv;
 }

 // 修改
 public Integer doUpdate (DataCompanyFrinvDO dataCompanyFrinv,Integer loginUserId) {
     logger.info("开始修改DataCompanyFrinvService.update,dataCompanyFrinv=" + dataCompanyFrinv.toString());
     int rows=this.dataCompanyFrinvDAO.update(dataCompanyFrinv);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyFrinvDO dataCompanyFrinv,Integer loginUserId) {
     logger.info("开始删除DataCompanyFrinvService.delete,dataCompanyFrinv=" + dataCompanyFrinv.toString());
     int rows=this.dataCompanyFrinvDAO.deleteById(dataCompanyFrinv.getId());
     return rows;
 }

 // 查询
 public DataCompanyFrinvDO doQueryById (Integer id) {
     DataCompanyFrinvDO obj = this.dataCompanyFrinvDAO.getById(id);
     return obj;
 }
}