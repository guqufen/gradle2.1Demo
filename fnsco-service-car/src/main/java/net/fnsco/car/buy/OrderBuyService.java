package net.fnsco.car.buy;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.car.buy.dao.OrderBuyDAO;
import net.fnsco.car.buy.entity.OrderBuyDO;
import net.fnsco.car.customer.CustomerService;
import net.fnsco.car.customer.entity.CustomerDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OrderBuyService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private OrderBuyDAO orderBuyDAO;
 @Autowired
 private CustomerService customerService;

 // 分页
 public ResultPageDTO<OrderBuyDO> page(OrderBuyDO orderBuy, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询OrderBuyService.page, orderBuy=" + orderBuy.toString());
     List<OrderBuyDO> pageList = this.orderBuyDAO.pageList(orderBuy, pageNum, pageSize);
     Integer count = this.orderBuyDAO.pageListCount(orderBuy);
   ResultPageDTO<OrderBuyDO> pager =  new ResultPageDTO<OrderBuyDO>(count,pageList);
     return pager;
 }

 // 添加
 public OrderBuyDO doAdd (OrderBuyDO orderBuy,int loginUserId) {
     logger.info("开始添加OrderBuyService.add,orderBuy=" + orderBuy.toString());
     this.orderBuyDAO.insert(orderBuy);
     return orderBuy;
 }

 // 修改
 public Integer doUpdate (OrderBuyDO orderBuy,Integer loginUserId) {
     logger.info("开始修改OrderBuyService.update,orderBuy=" + orderBuy.toString());
     int rows=this.orderBuyDAO.update(orderBuy);
     return rows;
 }

 // 删除
 public Integer doDelete (OrderBuyDO orderBuy,Integer loginUserId) {
     logger.info("开始删除OrderBuyService.delete,orderBuy=" + orderBuy.toString());
     int rows=this.orderBuyDAO.deleteById(orderBuy.getId());
     return rows;
 }

 // 查询
 public OrderBuyDO doQueryById (Integer id) {
     OrderBuyDO obj = this.orderBuyDAO.getById(id);
     return obj;
 }

@Transactional
public ResultDTO<Object> addJo(OrderBuyDO orderBuy,CustomerDO customer) {
	customer = customerService.addCustomer(customer);
	if(customer.getId() == null){
		return ResultDTO.fail("客户信息新增失败");
	}
	orderBuy.setCustomerId(customer.getId());
	orderBuy.setCreateTime(new Date());
	orderBuy.setLastUpdateTime(new Date());
	orderBuy.setStatus(0);
	orderBuyDAO.insert(orderBuy);
	return ResultDTO.success();
}
}