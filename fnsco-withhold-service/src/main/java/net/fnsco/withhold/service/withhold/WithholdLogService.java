package net.fnsco.withhold.service.withhold;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.withhold.service.withhold.dao.WithholdLogDAO;
import net.fnsco.withhold.service.withhold.entity.WithholdLogDO;

@Service
public class WithholdLogService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private WithholdLogDAO withholdLogDAO;

 // 分页
 public ResultPageDTO<WithholdLogDO> page(WithholdLogDO withholdLog, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询WithholdLogService.page, withholdLog=" + withholdLog.toString());
     List<WithholdLogDO> pageList = this.withholdLogDAO.pageList(withholdLog, pageNum, pageSize);
     Integer count = this.withholdLogDAO.pageListCount(withholdLog);
   ResultPageDTO<WithholdLogDO> pager =  new ResultPageDTO<WithholdLogDO>(count,pageList);
     return pager;
 }

 // 添加
 public WithholdLogDO doAdd (WithholdLogDO withholdLog,int loginUserId) {
     logger.info("开始添加WithholdLogService.add,withholdLog=" + withholdLog.toString());
     this.withholdLogDAO.insert(withholdLog);
     return withholdLog;
 }

 // 修改
 public Integer doUpdate (WithholdLogDO withholdLog,Integer loginUserId) {
     logger.info("开始修改WithholdLogService.update,withholdLog=" + withholdLog.toString());
     int rows=this.withholdLogDAO.update(withholdLog);
     return rows;
 }

 // 删除
 public Integer doDelete (WithholdLogDO withholdLog,Integer loginUserId) {
     logger.info("开始删除WithholdLogService.delete,withholdLog=" + withholdLog.toString());
     int rows=this.withholdLogDAO.deleteById(withholdLog.getId());
     return rows;
 }

 // 查询
 public WithholdLogDO doQueryById (Integer id) {
     WithholdLogDO obj = this.withholdLogDAO.getById(id);
     return obj;
 }
}