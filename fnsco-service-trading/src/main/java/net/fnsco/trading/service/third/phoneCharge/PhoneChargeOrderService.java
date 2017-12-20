package net.fnsco.trading.service.third.phoneCharge;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.trading.service.third.phoneCharge.dao.PhoneChargeOrderDAO;
import net.fnsco.trading.service.third.phoneCharge.dto.PhoneChargeOrderDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class PhoneChargeOrderService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private PhoneChargeOrderDAO phoneChargeOrderDAO;

 // 分页
 public ResultPageDTO<PhoneChargeOrderDO> page(PhoneChargeOrderDO phoneChargeOrder, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询PhoneChargeOrderService.page, phoneChargeOrder=" + phoneChargeOrder.toString());
     List<PhoneChargeOrderDO> pageList = this.phoneChargeOrderDAO.pageList(phoneChargeOrder, pageNum, pageSize);
     Integer count = this.phoneChargeOrderDAO.pageListCount(phoneChargeOrder);
   ResultPageDTO<PhoneChargeOrderDO> pager =  new ResultPageDTO<PhoneChargeOrderDO>(count,pageList);
     return pager;
 }

 // 添加
 public PhoneChargeOrderDO doAdd (PhoneChargeOrderDO phoneChargeOrder,int loginUserId) {
     logger.info("开始添加PhoneChargeOrderService.add,phoneChargeOrder=" + phoneChargeOrder.toString());
     this.phoneChargeOrderDAO.insert(phoneChargeOrder);
     return phoneChargeOrder;
 }

 // 修改
 public Integer doUpdate (PhoneChargeOrderDO phoneChargeOrder,Integer loginUserId) {
     logger.info("开始修改PhoneChargeOrderService.update,phoneChargeOrder=" + phoneChargeOrder.toString());
     int rows=this.phoneChargeOrderDAO.update(phoneChargeOrder);
     return rows;
 }

 // 删除
 public Integer doDelete (PhoneChargeOrderDO phoneChargeOrder,Integer loginUserId) {
     logger.info("开始删除PhoneChargeOrderService.delete,phoneChargeOrder=" + phoneChargeOrder.toString());
     int rows=this.phoneChargeOrderDAO.deleteById(phoneChargeOrder.getId());
     return rows;
 }

 // 查询
 public PhoneChargeOrderDO doQueryById (Integer id) {
     PhoneChargeOrderDO obj = this.phoneChargeOrderDAO.getById(id);
     return obj;
 }
}