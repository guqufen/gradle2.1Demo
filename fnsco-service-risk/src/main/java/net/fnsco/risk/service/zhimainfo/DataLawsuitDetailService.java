package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataLawsuitDetailDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDetailDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataLawsuitDetailService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataLawsuitDetailDAO dataLawsuitDetailDAO;

 // 分页
 public ResultPageDTO<DataLawsuitDetailDO> page(DataLawsuitDetailDO dataLawsuitDetail, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataLawsuitDetailService.page, dataLawsuitDetail=" + dataLawsuitDetail.toString());
     List<DataLawsuitDetailDO> pageList = this.dataLawsuitDetailDAO.pageList(dataLawsuitDetail, pageNum, pageSize);
     Integer count = this.dataLawsuitDetailDAO.pageListCount(dataLawsuitDetail);
   ResultPageDTO<DataLawsuitDetailDO> pager =  new ResultPageDTO<DataLawsuitDetailDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataLawsuitDetailDO doAdd (DataLawsuitDetailDO dataLawsuitDetail,int loginUserId) {
     logger.info("开始添加DataLawsuitDetailService.add,dataLawsuitDetail=" + dataLawsuitDetail.toString());
     this.dataLawsuitDetailDAO.insert(dataLawsuitDetail);
     return dataLawsuitDetail;
 }

 // 修改
 public Integer doUpdate (DataLawsuitDetailDO dataLawsuitDetail,Integer loginUserId) {
     logger.info("开始修改DataLawsuitDetailService.update,dataLawsuitDetail=" + dataLawsuitDetail.toString());
     int rows=this.dataLawsuitDetailDAO.update(dataLawsuitDetail);
     return rows;
 }

 // 删除
 public Integer doDelete (DataLawsuitDetailDO dataLawsuitDetail,Integer loginUserId) {
     logger.info("开始删除DataLawsuitDetailService.delete,dataLawsuitDetail=" + dataLawsuitDetail.toString());
     int rows=this.dataLawsuitDetailDAO.deleteById(dataLawsuitDetail.getId());
     return rows;
 }

 // 查询
 public DataLawsuitDetailDO doQueryById (Integer id) {
     DataLawsuitDetailDO obj = this.dataLawsuitDetailDAO.getById(id);
     return obj;
 }
}