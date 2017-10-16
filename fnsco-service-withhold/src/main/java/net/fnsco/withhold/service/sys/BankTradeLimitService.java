package net.fnsco.withhold.service.sys;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.withhold.service.sys.dao.BankTradeLimitDAO;
import net.fnsco.withhold.service.sys.entity.BankTradeLimitDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class BankTradeLimitService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private BankTradeLimitDAO bankTradeLimitDAO;

 // 分页
 public ResultPageDTO<BankTradeLimitDO> page(BankTradeLimitDO bankTradeLimit, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询BankTradeLimitService.page, bankTradeLimit=" + bankTradeLimit.toString());
     List<BankTradeLimitDO> pageList = this.bankTradeLimitDAO.pageList(bankTradeLimit, pageNum, pageSize);
     Integer count = this.bankTradeLimitDAO.pageListCount(bankTradeLimit);
   ResultPageDTO<BankTradeLimitDO> pager =  new ResultPageDTO<BankTradeLimitDO>(count,pageList);
     return pager;
 }

 // 添加
 public BankTradeLimitDO doAdd (BankTradeLimitDO bankTradeLimit,int loginUserId) {
     logger.info("开始添加BankTradeLimitService.add,bankTradeLimit=" + bankTradeLimit.toString());
     this.bankTradeLimitDAO.insert(bankTradeLimit);
     return bankTradeLimit;
 }

 // 修改
 public Integer doUpdate (BankTradeLimitDO bankTradeLimit,Integer loginUserId) {
     logger.info("开始修改BankTradeLimitService.update,bankTradeLimit=" + bankTradeLimit.toString());
     int rows=this.bankTradeLimitDAO.update(bankTradeLimit);
     return rows;
 }

 // 删除
 public Integer doDelete (BankTradeLimitDO bankTradeLimit,Integer loginUserId) {
     logger.info("开始删除BankTradeLimitService.delete,bankTradeLimit=" + bankTradeLimit.toString());
     int rows=this.bankTradeLimitDAO.deleteById(bankTradeLimit.getId());
     return rows;
 }

 // 查询
 public BankTradeLimitDO doQueryById (Integer id) {
     BankTradeLimitDO obj = this.bankTradeLimitDAO.getById(id);
     return obj;
 }
}