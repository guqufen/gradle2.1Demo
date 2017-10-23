package net.fnsco.risk.service.trade;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.risk.service.trade.dao.TradePayBillDAO;
import net.fnsco.risk.service.trade.entity.TradePayBillDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradePayBillService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradePayBillDAO tradePayBillDAO;

 // 分页
 public ResultPageDTO<TradePayBillDO> page(TradePayBillDO tradePayBill, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradePayBillService.page, tradePayBill=" + tradePayBill.toString());
     List<TradePayBillDO> pageList = this.tradePayBillDAO.pageList(tradePayBill, pageNum, pageSize);
     Integer count = this.tradePayBillDAO.pageListCount(tradePayBill);
   ResultPageDTO<TradePayBillDO> pager =  new ResultPageDTO<TradePayBillDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradePayBillDO doAdd (TradePayBillDO tradePayBill,int loginUserId) {
     logger.info("开始添加TradePayBillService.add,tradePayBill=" + tradePayBill.toString());
     this.tradePayBillDAO.insert(tradePayBill);
     return tradePayBill;
 }

 // 修改
 public Integer doUpdate (TradePayBillDO tradePayBill,Integer loginUserId) {
     logger.info("开始修改TradePayBillService.update,tradePayBill=" + tradePayBill.toString());
     int rows=this.tradePayBillDAO.update(tradePayBill);
     return rows;
 }

 // 删除
 public Integer doDelete (TradePayBillDO tradePayBill,Integer loginUserId) {
     logger.info("开始删除TradePayBillService.delete,tradePayBill=" + tradePayBill.toString());
     int rows=this.tradePayBillDAO.deleteById(tradePayBill.getId());
     return rows;
 }

 // 查询
 public TradePayBillDO doQueryById (Integer id) {
     TradePayBillDO obj = this.tradePayBillDAO.getById(id);
     return obj;
 }
}