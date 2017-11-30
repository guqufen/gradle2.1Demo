package net.fnsco.risk.service.zhimainfo;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.zhimainfo.dao.DataCompanyFrPositionDAO;
import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrPositionDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class DataCompanyFrPositionService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private DataCompanyFrPositionDAO dataCompanyFrPositionDAO;

 // 分页
 public ResultPageDTO<DataCompanyFrPositionDO> page(DataCompanyFrPositionDO dataCompanyFrPosition, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询DataCompanyFrPositionService.page, dataCompanyFrPosition=" + dataCompanyFrPosition.toString());
     List<DataCompanyFrPositionDO> pageList = this.dataCompanyFrPositionDAO.pageList(dataCompanyFrPosition, pageNum, pageSize);
     Integer count = this.dataCompanyFrPositionDAO.pageListCount(dataCompanyFrPosition);
   ResultPageDTO<DataCompanyFrPositionDO> pager =  new ResultPageDTO<DataCompanyFrPositionDO>(count,pageList);
     return pager;
 }

 // 添加
 public DataCompanyFrPositionDO doAdd (DataCompanyFrPositionDO dataCompanyFrPosition,int loginUserId) {
     logger.info("开始添加DataCompanyFrPositionService.add,dataCompanyFrPosition=" + dataCompanyFrPosition.toString());
     this.dataCompanyFrPositionDAO.insert(dataCompanyFrPosition);
     return dataCompanyFrPosition;
 }

 // 修改
 public Integer doUpdate (DataCompanyFrPositionDO dataCompanyFrPosition,Integer loginUserId) {
     logger.info("开始修改DataCompanyFrPositionService.update,dataCompanyFrPosition=" + dataCompanyFrPosition.toString());
     int rows=this.dataCompanyFrPositionDAO.update(dataCompanyFrPosition);
     return rows;
 }

 // 删除
 public Integer doDelete (DataCompanyFrPositionDO dataCompanyFrPosition,Integer loginUserId) {
     logger.info("开始删除DataCompanyFrPositionService.delete,dataCompanyFrPosition=" + dataCompanyFrPosition.toString());
     int rows=this.dataCompanyFrPositionDAO.deleteById(dataCompanyFrPosition.getId());
     return rows;
 }

 // 查询
 public DataCompanyFrPositionDO doQueryById (Integer id) {
     DataCompanyFrPositionDO obj = this.dataCompanyFrPositionDAO.getById(id);
     return obj;
 }
}