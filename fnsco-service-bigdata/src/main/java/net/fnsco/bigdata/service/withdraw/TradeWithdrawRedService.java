package net.fnsco.bigdata.service.withdraw;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.service.withdraw.dao.TradeWithdrawRedDAO;
import net.fnsco.bigdata.service.withdraw.entity.TradeWithdrawRedDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class TradeWithdrawRedService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeWithdrawRedDAO tradeWithdrawRedDAO;

 // 分页
 public ResultPageDTO<TradeWithdrawRedDO> page(TradeWithdrawRedDO tradeWithdrawRed, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeWithdrawRedService.page, tradeWithdrawRed=" + tradeWithdrawRed.toString());
     List<TradeWithdrawRedDO> pageList = this.tradeWithdrawRedDAO.pageList(tradeWithdrawRed, pageNum, pageSize);
     Integer count = this.tradeWithdrawRedDAO.pageListCount(tradeWithdrawRed);
   ResultPageDTO<TradeWithdrawRedDO> pager =  new ResultPageDTO<TradeWithdrawRedDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeWithdrawRedDO doAdd (TradeWithdrawRedDO tradeWithdrawRed,int loginUserId) {
     logger.info("开始添加TradeWithdrawRedService.add,tradeWithdrawRed=" + tradeWithdrawRed.toString());
     this.tradeWithdrawRedDAO.insert(tradeWithdrawRed);
     return tradeWithdrawRed;
 }

 // 修改
 public Integer doUpdate (TradeWithdrawRedDO tradeWithdrawRed,Integer loginUserId) {
     logger.info("开始修改TradeWithdrawRedService.update,tradeWithdrawRed=" + tradeWithdrawRed.toString());
     int rows=this.tradeWithdrawRedDAO.update(tradeWithdrawRed);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeWithdrawRedDO tradeWithdrawRed,Integer loginUserId) {
     logger.info("开始删除TradeWithdrawRedService.delete,tradeWithdrawRed=" + tradeWithdrawRed.toString());
     int rows=this.tradeWithdrawRedDAO.deleteById(tradeWithdrawRed.getId());
     return rows;
 }

 // 查询
 public TradeWithdrawRedDO doQueryById (Integer id) {
     TradeWithdrawRedDO obj = this.tradeWithdrawRedDAO.getById(id);
     return obj;
 }
}