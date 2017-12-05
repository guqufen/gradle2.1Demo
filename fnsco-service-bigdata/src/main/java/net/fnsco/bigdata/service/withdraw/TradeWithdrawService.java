package net.fnsco.bigdata.service.withdraw;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.service.withdraw.dao.TradeWithdrawDAO;
import net.fnsco.bigdata.service.withdraw.entity.TradeWithdrawDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeWithdrawService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeWithdrawDAO tradeWithdrawDAO;

 // 分页
 public ResultPageDTO<TradeWithdrawDO> page(TradeWithdrawDO tradeWithdraw, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeWithdrawService.page, tradeWithdraw=" + tradeWithdraw.toString());
     List<TradeWithdrawDO> pageList = this.tradeWithdrawDAO.pageList(tradeWithdraw, pageNum, pageSize);
     Integer count = this.tradeWithdrawDAO.pageListCount(tradeWithdraw);
   ResultPageDTO<TradeWithdrawDO> pager =  new ResultPageDTO<TradeWithdrawDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeWithdrawDO doAdd (TradeWithdrawDO tradeWithdraw,int loginUserId) {
     logger.info("开始添加TradeWithdrawService.add,tradeWithdraw=" + tradeWithdraw.toString());
     this.tradeWithdrawDAO.insert(tradeWithdraw);
     return tradeWithdraw;
 }

 // 修改
 public Integer doUpdate (TradeWithdrawDO tradeWithdraw,Integer loginUserId) {
     logger.info("开始修改TradeWithdrawService.update,tradeWithdraw=" + tradeWithdraw.toString());
     int rows=this.tradeWithdrawDAO.update(tradeWithdraw);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeWithdrawDO tradeWithdraw,Integer loginUserId) {
     logger.info("开始删除TradeWithdrawService.delete,tradeWithdraw=" + tradeWithdraw.toString());
     int rows=this.tradeWithdrawDAO.deleteById(tradeWithdraw.getId());
     return rows;
 }

 // 查询
 public TradeWithdrawDO doQueryById (Integer id) {
     TradeWithdrawDO obj = this.tradeWithdrawDAO.getById(id);
     return obj;
 }
}