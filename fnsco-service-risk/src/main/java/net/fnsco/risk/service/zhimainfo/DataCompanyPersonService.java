package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyPersonDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyPersonDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyPersonService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyPersonDAO dataCompanyPersonDAO;

 // 分页
 public ResultPageDTO<DataCompanyPersonDO> page(DataCompanyPersonDO dataCompanyPerson, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyPersonService.page, dataCompanyPerson=" + dataCompanyPerson.toString());
     List<DataCompanyPersonDO> pageList = this.dataCompanyPersonDAO.pageList(dataCompanyPerson, pageNum, pageSize);
     Integer count = this.dataCompanyPersonDAO.pageListCount(dataCompanyPerson);
   ResultPageDTO<DataCompanyPersonDO> pager =  new ResultPageDTO<DataCompanyPersonDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyPersonDO doAdd (DataCompanyPersonDO dataCompanyPerson,int loginUserId) {
     logger.info("开始添加DataCompanyPersonService.add,dataCompanyPerson=" + dataCompanyPerson.toString());
     this.dataCompanyPersonDAO.insert(dataCompanyPerson);
     return dataCompanyPerson;
 }

 // 修改
 public Integer doUpdate (DataCompanyPersonDO dataCompanyPerson,Integer loginUserId) {
     logger.info("开始修改DataCompanyPersonService.update,dataCompanyPerson=" + dataCompanyPerson.toString());
     int rows=this.dataCompanyPersonDAO.update(dataCompanyPerson);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyPersonDO dataCompanyPerson,Integer loginUserId) {
     logger.info("开始删除DataCompanyPersonService.delete,dataCompanyPerson=" + dataCompanyPerson.toString());
     int rows=this.dataCompanyPersonDAO.deleteById(dataCompanyPerson.getId());
     return rows;
 }

 // 查询
 public DataCompanyPersonDO doQueryById (Integer id) {
     DataCompanyPersonDO obj = this.dataCompanyPersonDAO.getById(id);
     return obj;
 }
}