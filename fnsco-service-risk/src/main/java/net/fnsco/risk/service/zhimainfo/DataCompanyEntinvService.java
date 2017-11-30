package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyEntinvDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyEntinvDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyEntinvService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyEntinvDAO dataCompanyEntinvDAO;

 // 分页
 public ResultPageDTO<DataCompanyEntinvDO> page(DataCompanyEntinvDO dataCompanyEntinv, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyEntinvService.page, dataCompanyEntinv=" + dataCompanyEntinv.toString());
     List<DataCompanyEntinvDO> pageList = this.dataCompanyEntinvDAO.pageList(dataCompanyEntinv, pageNum, pageSize);
     Integer count = this.dataCompanyEntinvDAO.pageListCount(dataCompanyEntinv);
   ResultPageDTO<DataCompanyEntinvDO> pager =  new ResultPageDTO<DataCompanyEntinvDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyEntinvDO doAdd (DataCompanyEntinvDO dataCompanyEntinv,int loginUserId) {
     logger.info("开始添加DataCompanyEntinvService.add,dataCompanyEntinv=" + dataCompanyEntinv.toString());
     this.dataCompanyEntinvDAO.insert(dataCompanyEntinv);
     return dataCompanyEntinv;
 }

 // 修改
 public Integer doUpdate (DataCompanyEntinvDO dataCompanyEntinv,Integer loginUserId) {
     logger.info("开始修改DataCompanyEntinvService.update,dataCompanyEntinv=" + dataCompanyEntinv.toString());
     int rows=this.dataCompanyEntinvDAO.update(dataCompanyEntinv);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyEntinvDO dataCompanyEntinv,Integer loginUserId) {
     logger.info("开始删除DataCompanyEntinvService.delete,dataCompanyEntinv=" + dataCompanyEntinv.toString());
     int rows=this.dataCompanyEntinvDAO.deleteById(dataCompanyEntinv.getId());
     return rows;
 }

 // 查询
 public DataCompanyEntinvDO doQueryById (Integer id) {
     DataCompanyEntinvDO obj = this.dataCompanyEntinvDAO.getById(id);
     return obj;
 }
}