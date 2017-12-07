package net.fnsco.trading.service.order;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.order.dao.TradeOrderByPayTypeDAO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayTypeDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeOrderByPayTypeService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeOrderByPayTypeDAO tradeOrderByPayTypeDAO;

 // 分页
 public ResultPageDTO<TradeOrderByPayTypeDO> page(TradeOrderByPayTypeDO tradeOrderByPayType, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeOrderByPayTypeService.page, tradeOrderByPayType=" + tradeOrderByPayType.toString());
     List<TradeOrderByPayTypeDO> pageList = this.tradeOrderByPayTypeDAO.pageList(tradeOrderByPayType, pageNum, pageSize);
     Integer count = this.tradeOrderByPayTypeDAO.pageListCount(tradeOrderByPayType);
   ResultPageDTO<TradeOrderByPayTypeDO> pager =  new ResultPageDTO<TradeOrderByPayTypeDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeOrderByPayTypeDO doAdd (TradeOrderByPayTypeDO tradeOrderByPayType,int loginUserId) {
     logger.info("开始添加TradeOrderByPayTypeService.add,tradeOrderByPayType=" + tradeOrderByPayType.toString());
     this.tradeOrderByPayTypeDAO.insert(tradeOrderByPayType);
     return tradeOrderByPayType;
 }

 // 修改
 public Integer doUpdate (TradeOrderByPayTypeDO tradeOrderByPayType,Integer loginUserId) {
     logger.info("开始修改TradeOrderByPayTypeService.update,tradeOrderByPayType=" + tradeOrderByPayType.toString());
     int rows=this.tradeOrderByPayTypeDAO.update(tradeOrderByPayType);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeOrderByPayTypeDO tradeOrderByPayType,Integer loginUserId) {
     logger.info("开始删除TradeOrderByPayTypeService.delete,tradeOrderByPayType=" + tradeOrderByPayType.toString());
     int rows=this.tradeOrderByPayTypeDAO.deleteById(tradeOrderByPayType.getId());
     return rows;
 }

 // 查询
 public TradeOrderByPayTypeDO doQueryById (Integer id) {
     TradeOrderByPayTypeDO obj = this.tradeOrderByPayTypeDAO.getById(id);
     return obj;
 }
}