package net.fnsco.car.service.safe;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.fnsco.car.service.safe.dao.OrderSafeDAO;
import net.fnsco.car.service.safe.entity.OrderSafeDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OrderSafeService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private OrderSafeDAO orderSafeDAO;

 // 分页
 public ResultPageDTO<OrderSafeDO> page(OrderSafeDO orderSafe, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询OrderSafeService.page, orderSafe=" + orderSafe.toString());
     List<OrderSafeDO> pageList = this.orderSafeDAO.pageList(orderSafe, pageNum, pageSize);
     Integer count = this.orderSafeDAO.pageListCount(orderSafe);
   ResultPageDTO<OrderSafeDO> pager =  new ResultPageDTO<OrderSafeDO>(count,pageList);
     return pager;
 }

 // 添加
 public OrderSafeDO doAdd (OrderSafeDO orderSafe,int loginUserId) {
     logger.info("开始添加OrderSafeService.add,orderSafe=" + orderSafe.toString());
     this.orderSafeDAO.insert(orderSafe);
     return orderSafe;
 }

 // 修改
 public Integer doUpdate (OrderSafeDO orderSafe,Integer loginUserId) {
     logger.info("开始修改OrderSafeService.update,orderSafe=" + orderSafe.toString());
     int rows=this.orderSafeDAO.update(orderSafe);
     return rows;
 }

 // 删除
 public Integer doDelete (OrderSafeDO orderSafe,Integer loginUserId) {
     logger.info("开始删除OrderSafeService.delete,orderSafe=" + orderSafe.toString());
     int rows=this.orderSafeDAO.deleteById(orderSafe.getId());
     return rows;
 }

 // 查询
 public OrderSafeDO doQueryById (Integer id) {
     OrderSafeDO obj = this.orderSafeDAO.getById(id);
     return obj;
 }
}