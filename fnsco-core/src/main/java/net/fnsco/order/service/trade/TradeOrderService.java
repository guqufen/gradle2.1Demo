package net.fnsco.order.service.trade;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.order.service.trade.dao.TradeOrderDAO;
import net.fnsco.order.service.trade.entity.TradeOrderDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeOrderDAO tradeOrderDAO;

 // 分页
 public ResultPageDTO<TradeOrderDO> page(TradeOrderDO tradeOrder, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeOrderService.page, tradeOrder=" + tradeOrder.toString());
     List<TradeOrderDO> pageList = this.tradeOrderDAO.pageList(tradeOrder, pageNum, pageSize);
     Integer count = this.tradeOrderDAO.pageListCount(tradeOrder);
   ResultPageDTO<TradeOrderDO> pager =  new ResultPageDTO<TradeOrderDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeOrderDO doAdd (TradeOrderDO tradeOrder,int loginUserId) {
     logger.info("开始添加TradeOrderService.add,tradeOrder=" + tradeOrder.toString());
     this.tradeOrderDAO.insert(tradeOrder);
     return tradeOrder;
 }

 // 修改
 public Integer doUpdate (TradeOrderDO tradeOrder,Integer loginUserId) {
     logger.info("开始修改TradeOrderService.update,tradeOrder=" + tradeOrder.toString());
     int rows=this.tradeOrderDAO.update(tradeOrder);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeOrderDO tradeOrder,Integer loginUserId) {
     logger.info("开始删除TradeOrderService.delete,tradeOrder=" + tradeOrder.toString());
     int rows=this.tradeOrderDAO.deleteById(tradeOrder.getId());
     return rows;
 }

 // 查询
 public TradeOrderDO doQueryById (Integer id) {
     TradeOrderDO obj = this.tradeOrderDAO.getById(id);
     return obj;
 }
}