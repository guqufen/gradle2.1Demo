package net.fnsco.trading.service.withdraw;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.withdraw.dao.TradeWithdrawErrorDAO;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawErrorDO;

@Service
public class TradeWithdrawErrorService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private TradeWithdrawErrorDAO tradeWithdrawErrorDAO;

 // 分页
 public ResultPageDTO<TradeWithdrawErrorDO> page(TradeWithdrawErrorDO tradeWithdrawError, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询TradeWithdrawErrorService.page, tradeWithdrawError=" + tradeWithdrawError.toString());
     List<TradeWithdrawErrorDO> pageList = this.tradeWithdrawErrorDAO.pageList(tradeWithdrawError, pageNum, pageSize);
     Integer count = this.tradeWithdrawErrorDAO.pageListCount(tradeWithdrawError);
   ResultPageDTO<TradeWithdrawErrorDO> pager =  new ResultPageDTO<TradeWithdrawErrorDO>(count,pageList);
     return pager;
 }

 // 添加
 public TradeWithdrawErrorDO doAdd (TradeWithdrawErrorDO tradeWithdrawError,int loginUserId) {
     logger.info("开始添加TradeWithdrawErrorService.add,tradeWithdrawError=" + tradeWithdrawError.toString());
     this.tradeWithdrawErrorDAO.insert(tradeWithdrawError);
     return tradeWithdrawError;
 }

 // 修改
 public Integer doUpdate (TradeWithdrawErrorDO tradeWithdrawError,Integer loginUserId) {
     logger.info("开始修改TradeWithdrawErrorService.update,tradeWithdrawError=" + tradeWithdrawError.toString());
     int rows=this.tradeWithdrawErrorDAO.update(tradeWithdrawError);
     return rows;
 }

 // 删除
 public Integer doDelete (TradeWithdrawErrorDO tradeWithdrawError,Integer loginUserId) {
     logger.info("开始删除TradeWithdrawErrorService.delete,tradeWithdrawError=" + tradeWithdrawError.toString());
     int rows=this.tradeWithdrawErrorDAO.deleteById(tradeWithdrawError.getId());
     return rows;
 }

 // 查询
 public TradeWithdrawErrorDO doQueryById (Integer id) {
     TradeWithdrawErrorDO obj = this.tradeWithdrawErrorDAO.getById(id);
     return obj;
 }
}