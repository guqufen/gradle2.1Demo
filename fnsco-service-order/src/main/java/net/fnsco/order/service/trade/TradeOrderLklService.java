package net.fnsco.order.service.trade;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.order.service.trade.dao.TradeOrderLklDAO;
import net.fnsco.order.service.trade.entity.TradeOrderLklDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderLklService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeOrderLklDAO tradeOrderLklDAO;

 // 分页
 public ResultPageDTO<TradeOrderLklDO> page(TradeOrderLklDO tradeOrderLkl, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeOrderLklService.page, tradeOrderLkl=" + tradeOrderLkl.toString());
     List<TradeOrderLklDO> pageList = this.tradeOrderLklDAO.pageList(tradeOrderLkl, pageNum, pageSize);
     Integer count = this.tradeOrderLklDAO.pageListCount(tradeOrderLkl);
   ResultPageDTO<TradeOrderLklDO> pager =  new ResultPageDTO<TradeOrderLklDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeOrderLklDO doAdd (TradeOrderLklDO tradeOrderLkl,int loginUserId) {
     logger.info("开始添加TradeOrderLklService.add,tradeOrderLkl=" + tradeOrderLkl.toString());
     this.tradeOrderLklDAO.insert(tradeOrderLkl);
     return tradeOrderLkl;
 }

 // 修改
 public Integer doUpdate (TradeOrderLklDO tradeOrderLkl,Integer loginUserId) {
     logger.info("开始修改TradeOrderLklService.update,tradeOrderLkl=" + tradeOrderLkl.toString());
     int rows=this.tradeOrderLklDAO.update(tradeOrderLkl);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeOrderLklDO tradeOrderLkl,Integer loginUserId) {
     logger.info("开始删除TradeOrderLklService.delete,tradeOrderLkl=" + tradeOrderLkl.toString());
     int rows=this.tradeOrderLklDAO.deleteById(tradeOrderLkl.getId());
     return rows;
 }

 // 查询
 public TradeOrderLklDO doQueryById (Integer id) {
     TradeOrderLklDO obj = this.tradeOrderLklDAO.getById(id);
     return obj;
 }
}