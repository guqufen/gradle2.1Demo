package net.fnsco.withhold.service.trade;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.withhold.service.trade.dao.TradeDataDAO;
import net.fnsco.withhold.service.trade.entity.TradeDataDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeDataService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeDataDAO tradeDataDAO;

 // 分页
 public ResultPageDTO<TradeDataDO> page(TradeDataDO tradeData, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeDataService.page, tradeData=" + tradeData.toString());
     List<TradeDataDO> pageList = this.tradeDataDAO.pageList(tradeData, pageNum, pageSize);
     Integer count = this.tradeDataDAO.pageListCount(tradeData);
   ResultPageDTO<TradeDataDO> pager =  new ResultPageDTO<TradeDataDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeDataDO doAdd (TradeDataDO tradeData,int loginUserId) {
     logger.info("开始添加TradeDataService.add,tradeData=" + tradeData.toString());
     this.tradeDataDAO.insert(tradeData);
     return tradeData;
 }

 // 修改
 public Integer doUpdate (TradeDataDO tradeData,Integer loginUserId) {
     logger.info("开始修改TradeDataService.update,tradeData=" + tradeData.toString());
     int rows=this.tradeDataDAO.update(tradeData);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeDataDO tradeData,Integer loginUserId) {
     logger.info("开始删除TradeDataService.delete,tradeData=" + tradeData.toString());
     int rows=this.tradeDataDAO.deleteById(tradeData.getId());
     return rows;
 }

 // 查询
 public TradeDataDO doQueryById (Integer id) {
     TradeDataDO obj = this.tradeDataDAO.getById(id);
     return obj;
 }
}