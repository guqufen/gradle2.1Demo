package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyCaseInfoDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyCaseInfoDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyCaseInfoService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyCaseInfoDAO dataCompanyCaseInfoDAO;

 // 分页
 public ResultPageDTO<DataCompanyCaseInfoDO> page(DataCompanyCaseInfoDO dataCompanyCaseInfo, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyCaseInfoService.page, dataCompanyCaseInfo=" + dataCompanyCaseInfo.toString());
     List<DataCompanyCaseInfoDO> pageList = this.dataCompanyCaseInfoDAO.pageList(dataCompanyCaseInfo, pageNum, pageSize);
     Integer count = this.dataCompanyCaseInfoDAO.pageListCount(dataCompanyCaseInfo);
   ResultPageDTO<DataCompanyCaseInfoDO> pager =  new ResultPageDTO<DataCompanyCaseInfoDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyCaseInfoDO doAdd (DataCompanyCaseInfoDO dataCompanyCaseInfo,int loginUserId) {
     logger.info("开始添加DataCompanyCaseInfoService.add,dataCompanyCaseInfo=" + dataCompanyCaseInfo.toString());
     this.dataCompanyCaseInfoDAO.insert(dataCompanyCaseInfo);
     return dataCompanyCaseInfo;
 }

 // 修改
 public Integer doUpdate (DataCompanyCaseInfoDO dataCompanyCaseInfo,Integer loginUserId) {
     logger.info("开始修改DataCompanyCaseInfoService.update,dataCompanyCaseInfo=" + dataCompanyCaseInfo.toString());
     int rows=this.dataCompanyCaseInfoDAO.update(dataCompanyCaseInfo);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyCaseInfoDO dataCompanyCaseInfo,Integer loginUserId) {
     logger.info("开始删除DataCompanyCaseInfoService.delete,dataCompanyCaseInfo=" + dataCompanyCaseInfo.toString());
     int rows=this.dataCompanyCaseInfoDAO.deleteById(dataCompanyCaseInfo.getId());
     return rows;
 }

 // 查询
 public DataCompanyCaseInfoDO doQueryById (Integer id) {
     DataCompanyCaseInfoDO obj = this.dataCompanyCaseInfoDAO.getById(id);
     return obj;
 }
}