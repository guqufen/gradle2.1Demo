package net.fnsco.order.service.trade;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.DbUtil;
import net.fnsco.order.service.trade.dao.TradeOrderDAO;
import net.fnsco.order.service.trade.entity.TradeOrderDO;

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
 public TradeOrderDO doAdd (TradeOrderDO tradeOrder) {
     logger.info("开始添加TradeOrderService.add,tradeOrder=" + tradeOrder.toString());
     tradeOrder.setOrderId(DateUtils.getNowDateStr()+tradeOrder.getChannelMerId()+DbUtil.getRandomStr(3));
     tradeOrder.setOrderCeateTime(new Date());
     tradeOrder.setCreateTime(new Date());
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