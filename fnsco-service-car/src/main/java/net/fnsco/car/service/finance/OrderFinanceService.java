package net.fnsco.car.service.finance;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.car.customer.dao.CustomerDAO;
import net.fnsco.car.customer.entity.CustomerDO;
import net.fnsco.car.service.finance.dao.OrderFinanceDAO;
import net.fnsco.car.service.finance.entity.OrderFinanceDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OrderFinanceService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private OrderFinanceDAO orderFinanceDAO;
 @Autowired
 private CustomerDAO customerDAO;
 //保存理财申请信息
 public void saveFinance() {
	 CustomerDO customerDO =  new CustomerDO();
	 OrderFinanceDO orderFinance = new OrderFinanceDO();
	 customerDO.setName(null);
	 customerDO.setMobile(null);
	 customerDO.setCreateTime(new Date());
	 this.customerDAO.insert(customerDO);
	 orderFinance.setCustomerId(customerDO.getId());
	 orderFinance.setCreateTime(new Date());
	 orderFinance.setLastUpdateTime(new Date());
	 this.orderFinanceDAO.insert(orderFinance);
 }

 // 分页
 public ResultPageDTO<OrderFinanceDO> page(OrderFinanceDO orderFinance, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询OrderFinanceService.page, orderFinance=" + orderFinance.toString());
     List<OrderFinanceDO> pageList = this.orderFinanceDAO.pageList(orderFinance, pageNum, pageSize);
     Integer count = this.orderFinanceDAO.pageListCount(orderFinance);
   ResultPageDTO<OrderFinanceDO> pager =  new ResultPageDTO<OrderFinanceDO>(count,pageList);
     return pager;
 }

 // 添加
 public OrderFinanceDO doAdd (OrderFinanceDO orderFinance,int loginUserId) {
     logger.info("开始添加OrderFinanceService.add,orderFinance=" + orderFinance.toString());
     this.orderFinanceDAO.insert(orderFinance);
     return orderFinance;
 }

 // 修改
 public Integer doUpdate (OrderFinanceDO orderFinance,Integer loginUserId) {
     logger.info("开始修改OrderFinanceService.update,orderFinance=" + orderFinance.toString());
     int rows=this.orderFinanceDAO.update(orderFinance);
     return rows;
 }

 // 删除
 public Integer doDelete (OrderFinanceDO orderFinance,Integer loginUserId) {
     logger.info("开始删除OrderFinanceService.delete,orderFinance=" + orderFinance.toString());
     int rows=this.orderFinanceDAO.deleteById(orderFinance.getId());
     return rows;
 }

 // 查询
 public OrderFinanceDO doQueryById (Integer id) {
     OrderFinanceDO obj = this.orderFinanceDAO.getById(id);
     return obj;
 }
}