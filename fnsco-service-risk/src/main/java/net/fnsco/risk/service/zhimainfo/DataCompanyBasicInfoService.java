package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyBasicInfoDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyBasicInfoDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyBasicInfoService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyBasicInfoDAO dataCompanyBasicInfoDAO;

 // 分页
 public ResultPageDTO<DataCompanyBasicInfoDO> page(DataCompanyBasicInfoDO dataCompanyBasicInfo, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyBasicInfoService.page, dataCompanyBasicInfo=" + dataCompanyBasicInfo.toString());
     List<DataCompanyBasicInfoDO> pageList = this.dataCompanyBasicInfoDAO.pageList(dataCompanyBasicInfo, pageNum, pageSize);
     Integer count = this.dataCompanyBasicInfoDAO.pageListCount(dataCompanyBasicInfo);
   ResultPageDTO<DataCompanyBasicInfoDO> pager =  new ResultPageDTO<DataCompanyBasicInfoDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyBasicInfoDO doAdd (DataCompanyBasicInfoDO dataCompanyBasicInfo,int loginUserId) {
     logger.info("开始添加DataCompanyBasicInfoService.add,dataCompanyBasicInfo=" + dataCompanyBasicInfo.toString());
     this.dataCompanyBasicInfoDAO.insert(dataCompanyBasicInfo);
     return dataCompanyBasicInfo;
 }

 // 修改
 public Integer doUpdate (DataCompanyBasicInfoDO dataCompanyBasicInfo,Integer loginUserId) {
     logger.info("开始修改DataCompanyBasicInfoService.update,dataCompanyBasicInfo=" + dataCompanyBasicInfo.toString());
     int rows=this.dataCompanyBasicInfoDAO.update(dataCompanyBasicInfo);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyBasicInfoDO dataCompanyBasicInfo,Integer loginUserId) {
     logger.info("开始删除DataCompanyBasicInfoService.delete,dataCompanyBasicInfo=" + dataCompanyBasicInfo.toString());
     int rows=this.dataCompanyBasicInfoDAO.deleteById(dataCompanyBasicInfo.getId());
     return rows;
 }

 // 查询
 public DataCompanyBasicInfoDO doQueryById (Integer id) {
     DataCompanyBasicInfoDO obj = this.dataCompanyBasicInfoDAO.getById(id);
     return obj;
 }
}