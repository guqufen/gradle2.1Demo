package net.fnsco.risk.service.trade;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.trade.dao.TradeRechargeBillDAO;
import net.fnsco.risk.service.trade.entity.TradeRechargeBillDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeRechargeBillService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeRechargeBillDAO tradeRechargeBillDAO;

 // 分页
 public ResultPageDTO<TradeRechargeBillDO> page(TradeRechargeBillDO tradeRechargeBill, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeRechargeBillService.page, tradeRechargeBill=" + tradeRechargeBill.toString());
     List<TradeRechargeBillDO> pageList = this.tradeRechargeBillDAO.pageList(tradeRechargeBill, pageNum, pageSize);
     Integer count = this.tradeRechargeBillDAO.pageListCount(tradeRechargeBill);
   ResultPageDTO<TradeRechargeBillDO> pager =  new ResultPageDTO<TradeRechargeBillDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeRechargeBillDO doAdd (TradeRechargeBillDO tradeRechargeBill,int loginUserId) {
     logger.info("开始添加TradeRechargeBillService.add,tradeRechargeBill=" + tradeRechargeBill.toString());
     this.tradeRechargeBillDAO.insert(tradeRechargeBill);
     return tradeRechargeBill;
 }

 // 修改
 public Integer doUpdate (TradeRechargeBillDO tradeRechargeBill,Integer loginUserId) {
     logger.info("开始修改TradeRechargeBillService.update,tradeRechargeBill=" + tradeRechargeBill.toString());
     int rows=this.tradeRechargeBillDAO.update(tradeRechargeBill);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeRechargeBillDO tradeRechargeBill,Integer loginUserId) {
     logger.info("开始删除TradeRechargeBillService.delete,tradeRechargeBill=" + tradeRechargeBill.toString());
     int rows=this.tradeRechargeBillDAO.deleteById(tradeRechargeBill.getId());
     return rows;
 }

 // 查询
 public TradeRechargeBillDO doQueryById (Integer id) {
     TradeRechargeBillDO obj = this.tradeRechargeBillDAO.getById(id);
     return obj;
 }
}