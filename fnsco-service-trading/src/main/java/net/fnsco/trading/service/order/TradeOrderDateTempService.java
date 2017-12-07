package net.fnsco.trading.service.order;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.order.dao.TradeOrderDateTempDAO;
import net.fnsco.trading.service.order.entity.TradeOrderDateTempDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderDateTempService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeOrderDateTempDAO tradeOrderDateTempDAO;

 // 分页
 public ResultPageDTO<TradeOrderDateTempDO> page(TradeOrderDateTempDO tradeOrderDateTemp, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeOrderDateTempService.page, tradeOrderDateTemp=" + tradeOrderDateTemp.toString());
     List<TradeOrderDateTempDO> pageList = this.tradeOrderDateTempDAO.pageList(tradeOrderDateTemp, pageNum, pageSize);
     Integer count = this.tradeOrderDateTempDAO.pageListCount(tradeOrderDateTemp);
   ResultPageDTO<TradeOrderDateTempDO> pager =  new ResultPageDTO<TradeOrderDateTempDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeOrderDateTempDO doAdd (TradeOrderDateTempDO tradeOrderDateTemp,int loginUserId) {
     logger.info("开始添加TradeOrderDateTempService.add,tradeOrderDateTemp=" + tradeOrderDateTemp.toString());
     this.tradeOrderDateTempDAO.insert(tradeOrderDateTemp);
     return tradeOrderDateTemp;
 }

 // 修改
 public Integer doUpdate (TradeOrderDateTempDO tradeOrderDateTemp,Integer loginUserId) {
     logger.info("开始修改TradeOrderDateTempService.update,tradeOrderDateTemp=" + tradeOrderDateTemp.toString());
     int rows=this.tradeOrderDateTempDAO.update(tradeOrderDateTemp);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeOrderDateTempDO tradeOrderDateTemp,Integer loginUserId) {
     logger.info("开始删除TradeOrderDateTempService.delete,tradeOrderDateTemp=" + tradeOrderDateTemp.toString());
     int rows=this.tradeOrderDateTempDAO.deleteById(tradeOrderDateTemp.getId());
     return rows;
 }

 // 查询
 public TradeOrderDateTempDO doQueryById (Integer id) {
     TradeOrderDateTempDO obj = this.tradeOrderDateTempDAO.getById(id);
     return obj;
 }
}