package net.fnsco.trading.service.order;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.order.dao.TradeOrderByDayDAO;
import net.fnsco.trading.service.order.entity.TradeOrderByDayDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderByDayService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeOrderByDayDAO tradeOrderByDayDAO;

 // 分页
 public ResultPageDTO<TradeOrderByDayDO> page(TradeOrderByDayDO tradeOrderByDay, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeOrderByDayService.page, tradeOrderByDay=" + tradeOrderByDay.toString());
     List<TradeOrderByDayDO> pageList = this.tradeOrderByDayDAO.pageList(tradeOrderByDay, pageNum, pageSize);
     Integer count = this.tradeOrderByDayDAO.pageListCount(tradeOrderByDay);
   ResultPageDTO<TradeOrderByDayDO> pager =  new ResultPageDTO<TradeOrderByDayDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeOrderByDayDO doAdd (TradeOrderByDayDO tradeOrderByDay,int loginUserId) {
     logger.info("开始添加TradeOrderByDayService.add,tradeOrderByDay=" + tradeOrderByDay.toString());
     this.tradeOrderByDayDAO.insert(tradeOrderByDay);
     return tradeOrderByDay;
 }

 // 修改
 public Integer doUpdate (TradeOrderByDayDO tradeOrderByDay,Integer loginUserId) {
     logger.info("开始修改TradeOrderByDayService.update,tradeOrderByDay=" + tradeOrderByDay.toString());
     int rows=this.tradeOrderByDayDAO.update(tradeOrderByDay);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeOrderByDayDO tradeOrderByDay,Integer loginUserId) {
     logger.info("开始删除TradeOrderByDayService.delete,tradeOrderByDay=" + tradeOrderByDay.toString());
     int rows=this.tradeOrderByDayDAO.deleteById(tradeOrderByDay.getId());
     return rows;
 }

 // 查询
 public TradeOrderByDayDO doQueryById (Integer id) {
     TradeOrderByDayDO obj = this.tradeOrderByDayDAO.getById(id);
     return obj;
 }
}