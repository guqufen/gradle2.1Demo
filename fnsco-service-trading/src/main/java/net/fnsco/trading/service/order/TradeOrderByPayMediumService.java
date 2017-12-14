package net.fnsco.trading.service.order;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.order.dao.TradeOrderByPayMediumDAO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayMediumDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderByPayMediumService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeOrderByPayMediumDAO tradeOrderByPayMediumDAO;

 // 分页
 public ResultPageDTO<TradeOrderByPayMediumDO> page(TradeOrderByPayMediumDO tradeOrderByPayMedium, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeOrderByPayMediumService.page, tradeOrderByPayMedium=" + tradeOrderByPayMedium.toString());
     List<TradeOrderByPayMediumDO> pageList = this.tradeOrderByPayMediumDAO.pageList(tradeOrderByPayMedium, pageNum, pageSize);
     Integer count = this.tradeOrderByPayMediumDAO.pageListCount(tradeOrderByPayMedium);
   ResultPageDTO<TradeOrderByPayMediumDO> pager =  new ResultPageDTO<TradeOrderByPayMediumDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeOrderByPayMediumDO doAdd (TradeOrderByPayMediumDO tradeOrderByPayMedium,int loginUserId) {
     logger.info("开始添加TradeOrderByPayMediumService.add,tradeOrderByPayMedium=" + tradeOrderByPayMedium.toString());
     this.tradeOrderByPayMediumDAO.insert(tradeOrderByPayMedium);
     return tradeOrderByPayMedium;
 }

 // 修改
 public Integer doUpdate (TradeOrderByPayMediumDO tradeOrderByPayMedium,Integer loginUserId) {
     logger.info("开始修改TradeOrderByPayMediumService.update,tradeOrderByPayMedium=" + tradeOrderByPayMedium.toString());
     int rows=this.tradeOrderByPayMediumDAO.update(tradeOrderByPayMedium);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeOrderByPayMediumDO tradeOrderByPayMedium,Integer loginUserId) {
     logger.info("开始删除TradeOrderByPayMediumService.delete,tradeOrderByPayMedium=" + tradeOrderByPayMedium.toString());
     int rows=this.tradeOrderByPayMediumDAO.deleteById(tradeOrderByPayMedium.getId());
     return rows;
 }

 // 查询
 public TradeOrderByPayMediumDO doQueryById (Integer id) {
     TradeOrderByPayMediumDO obj = this.tradeOrderByPayMediumDAO.getById(id);
     return obj;
 }
}