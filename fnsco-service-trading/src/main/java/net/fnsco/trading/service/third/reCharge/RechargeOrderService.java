package net.fnsco.trading.service.third.reCharge;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.trading.service.third.reCharge.dao.RechargeOrderDAO;
import net.fnsco.trading.service.third.reCharge.dto.RechargeOrderDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class RechargeOrderService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private RechargeOrderDAO rechargeOrderDAO;

 // 分页
 public ResultPageDTO<RechargeOrderDO> page(RechargeOrderDO rechargeOrder, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询RechargeOrderService.page, rechargeOrder=" + rechargeOrder.toString());
     List<RechargeOrderDO> pageList = this.rechargeOrderDAO.pageList(rechargeOrder, pageNum, pageSize);
     Integer count = this.rechargeOrderDAO.pageListCount(rechargeOrder);
   ResultPageDTO<RechargeOrderDO> pager =  new ResultPageDTO<RechargeOrderDO>(count,pageList);
     return pager;
 }

 // 添加
 public RechargeOrderDO doAdd (RechargeOrderDO rechargeOrder) {
     logger.info("开始添加RechargeOrderService.add,rechargeOrder=" + rechargeOrder.toString());
     rechargeOrder.setCreateTime(new Date());
     rechargeOrder.setUpdateTime(new Date());
     this.rechargeOrderDAO.insert(rechargeOrder);
     return rechargeOrder;
 }

 // 修改
 public Integer doUpdate (RechargeOrderDO rechargeOrder) {
     logger.info("开始修改RechargeOrderService.update,rechargeOrder=" + rechargeOrder.toString());
     rechargeOrder.setUpdateTime(new Date());
     int rows=this.rechargeOrderDAO.update(rechargeOrder);
     return rows;
 }

 // 删除
 public Integer doDelete (RechargeOrderDO rechargeOrder) {
     logger.info("开始删除RechargeOrderService.delete,rechargeOrder=" + rechargeOrder.toString());
     int rows=this.rechargeOrderDAO.deleteById(rechargeOrder.getId());
     return rows;
 }

 // 查询
 public RechargeOrderDO doQueryById (Integer id) {
     RechargeOrderDO obj = this.rechargeOrderDAO.getById(id);
     return obj;
 }
 
 public List<RechargeOrderDO> queryPhoneCharge(Date startDate){
	 return this.rechargeOrderDAO.queryPhoneCharge(startDate);
 }
 
 public RechargeOrderDO getByOrderNo(String orderNo){
	 return this.rechargeOrderDAO.getByOrderNo(orderNo);
 }
}