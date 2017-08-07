package net.fnsco.withhold.service.sys;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.withhold.service.sys.dao.BankCodeDAO;
import net.fnsco.withhold.service.sys.entity.BankCodeDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class BankCodeService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private BankCodeDAO bankCodeDAO;

 // 分页
 public ResultPageDTO<BankCodeDO> page(BankCodeDO bankCode, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询BankCodeService.page, bankCode=" + bankCode.toString());
     List<BankCodeDO> pageList = this.bankCodeDAO.pageList(bankCode, pageNum, pageSize);
     Integer count = this.bankCodeDAO.pageListCount(bankCode);
   ResultPageDTO<BankCodeDO> pager =  new ResultPageDTO<BankCodeDO>(count,pageList);
     return pager;
 }

 // 添加
 public BankCodeDO doAdd (BankCodeDO bankCode,int loginUserId) {
     logger.info("开始添加BankCodeService.add,bankCode=" + bankCode.toString());
     this.bankCodeDAO.insert(bankCode);
     return bankCode;
 }

 // 修改
 public Integer doUpdate (BankCodeDO bankCode,Integer loginUserId) {
     logger.info("开始修改BankCodeService.update,bankCode=" + bankCode.toString());
     int rows=this.bankCodeDAO.update(bankCode);
     return rows;
 }

 // 删除
 public Integer doDelete (BankCodeDO bankCode,Integer loginUserId) {
     logger.info("开始删除BankCodeService.delete,bankCode=" + bankCode.toString());
     int rows=this.bankCodeDAO.deleteById(bankCode.getId());
     return rows;
 }

 // 查询
 public BankCodeDO doQueryById (Integer id) {
     BankCodeDO obj = this.bankCodeDAO.getById(id);
     return obj;
 }
}