package net.fnsco.trading.service.oilcard;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.oilcard.dao.OilCardApplyDAO;
import net.fnsco.trading.service.oilcard.entity.OilCardApplyDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OilCardApplyService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private OilCardApplyDAO oilCardApplyDAO;

 // 分页
 public ResultPageDTO<OilCardApplyDO> page(OilCardApplyDO oilCardApply, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询OilCardApplyService.page, oilCardApply=" + oilCardApply.toString());
     List<OilCardApplyDO> pageList = this.oilCardApplyDAO.pageList(oilCardApply, pageNum, pageSize);
     Integer count = this.oilCardApplyDAO.pageListCount(oilCardApply);
   ResultPageDTO<OilCardApplyDO> pager =  new ResultPageDTO<OilCardApplyDO>(count,pageList);
     return pager;
 }

 // 添加
 public OilCardApplyDO doAdd (OilCardApplyDO oilCardApply,int loginUserId) {
     logger.info("开始添加OilCardApplyService.add,oilCardApply=" + oilCardApply.toString());
     this.oilCardApplyDAO.insert(oilCardApply);
     return oilCardApply;
 }

 // 修改
 public Integer doUpdate (OilCardApplyDO oilCardApply,Integer loginUserId) {
     logger.info("开始修改OilCardApplyService.update,oilCardApply=" + oilCardApply.toString());
     int rows=this.oilCardApplyDAO.update(oilCardApply);
     return rows;
 }

 // 删除
 public Integer doDelete (OilCardApplyDO oilCardApply,Integer loginUserId) {
     logger.info("开始删除OilCardApplyService.delete,oilCardApply=" + oilCardApply.toString());
     int rows=this.oilCardApplyDAO.deleteById(oilCardApply.getId());
     return rows;
 }

 // 查询
 public OilCardApplyDO doQueryById (Integer id) {
     OilCardApplyDO obj = this.oilCardApplyDAO.getById(id);
     return obj;
 }
}