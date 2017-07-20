package net.fnsco.withhold.server.service.withhold;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.withhold.server.service.withhold.dao.WithholdInfoDAO;
import net.fnsco.withhold.server.service.withhold.entity.WithholdInfoDO;

@Service
public class WithholdInfoService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private WithholdInfoDAO withholdInfoDAO;

 // 分页
 public ResultPageDTO<WithholdInfoDO> page(WithholdInfoDO withholdInfo, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询WithholdInfoService.page, withholdInfo=" + withholdInfo.toString());
     List<WithholdInfoDO> pageList = this.withholdInfoDAO.pageList(withholdInfo, pageNum, pageSize);
     Integer count = this.withholdInfoDAO.pageListCount(withholdInfo);
   ResultPageDTO<WithholdInfoDO> pager =  new ResultPageDTO<WithholdInfoDO>(count,pageList);
     return pager;
 }

 // 添加
 public WithholdInfoDO doAdd (WithholdInfoDO withholdInfo,int loginUserId) {
     logger.info("开始添加WithholdInfoService.add,withholdInfo=" + withholdInfo.toString());
     this.withholdInfoDAO.insert(withholdInfo);
     return withholdInfo;
 }

 // 修改
 public Integer doUpdate (WithholdInfoDO withholdInfo,Integer loginUserId) {
     logger.info("开始修改WithholdInfoService.update,withholdInfo=" + withholdInfo.toString());
     int rows=this.withholdInfoDAO.update(withholdInfo);
     return rows;
 }

 // 删除
 public Integer doDelete (WithholdInfoDO withholdInfo,Integer loginUserId) {
     logger.info("开始删除WithholdInfoService.delete,withholdInfo=" + withholdInfo.toString());
     int rows=this.withholdInfoDAO.deleteById(withholdInfo.getId());
     return rows;
 }

 // 查询
 public WithholdInfoDO doQueryById (Integer id) {
     WithholdInfoDO obj = this.withholdInfoDAO.getById(id);
     return obj;
 }
}