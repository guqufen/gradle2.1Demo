package net.fnsco.bigdata.service.sys;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.service.sys.dao.PayCategroryWxDAO;
import net.fnsco.bigdata.service.sys.entity.PayCategroryWxDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class PayCategroryWxService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private PayCategroryWxDAO payCategroryWxDAO;

 // 分页
 public ResultPageDTO<PayCategroryWxDO> page(PayCategroryWxDO payCategroryWx, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询PayCategroryWxService.page, payCategroryWx=" + payCategroryWx.toString());
     List<PayCategroryWxDO> pageList = this.payCategroryWxDAO.pageList(payCategroryWx, pageNum, pageSize);
     Integer count = this.payCategroryWxDAO.pageListCount(payCategroryWx);
   ResultPageDTO<PayCategroryWxDO> pager =  new ResultPageDTO<PayCategroryWxDO>(count,pageList);
     return pager;
 }

 // 添加
 public PayCategroryWxDO doAdd (PayCategroryWxDO payCategroryWx,int loginUserId) {
     logger.info("开始添加PayCategroryWxService.add,payCategroryWx=" + payCategroryWx.toString());
     this.payCategroryWxDAO.insert(payCategroryWx);
     return payCategroryWx;
 }

 // 修改
 public Integer doUpdate (PayCategroryWxDO payCategroryWx,Integer loginUserId) {
     logger.info("开始修改PayCategroryWxService.update,payCategroryWx=" + payCategroryWx.toString());
     int rows=this.payCategroryWxDAO.update(payCategroryWx);
     return rows;
 }

 // 删除
 public Integer doDelete (PayCategroryWxDO payCategroryWx,Integer loginUserId) {
     logger.info("开始删除PayCategroryWxService.delete,payCategroryWx=" + payCategroryWx.toString());
     int rows=this.payCategroryWxDAO.deleteById(payCategroryWx.getId());
     return rows;
 }

 // 查询
 public PayCategroryWxDO doQueryById (Integer id) {
     PayCategroryWxDO obj = this.payCategroryWxDAO.getById(id);
     return obj;
 }
}