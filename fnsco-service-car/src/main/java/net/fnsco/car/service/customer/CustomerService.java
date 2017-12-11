package net.fnsco.car.service.customer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.car.service.customer.dao.CustomerDAO;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class CustomerService extends BaseService {

 private Logger logger = LoggerFactory.getLogger(this.getClass());
 @Autowired
 private CustomerDAO customerDAO;

 // 分页
 public ResultPageDTO<CustomerDO> page(CustomerDO customer, Integer pageNum, Integer pageSize) {
     logger.info("开始分页查询CustomerService.page, customer=" + customer.toString());
     List<CustomerDO> pageList = this.customerDAO.pageList(customer, pageNum, pageSize);
     Integer count = this.customerDAO.pageListCount(customer);
   ResultPageDTO<CustomerDO> pager =  new ResultPageDTO<CustomerDO>(count,pageList);
     return pager;
 }

 // 添加
 public CustomerDO doAdd (CustomerDO customer,int loginUserId) {
     logger.info("开始添加CustomerService.add,customer=" + customer.toString());
     this.customerDAO.insert(customer);
     return customer;
 }

 // 修改
 public Integer doUpdate (CustomerDO customer,Integer loginUserId) {
     logger.info("开始修改CustomerService.update,customer=" + customer.toString());
     int rows=this.customerDAO.update(customer);
     return rows;
 }

 // 删除
 public Integer doDelete (CustomerDO customer,Integer loginUserId) {
     logger.info("开始删除CustomerService.delete,customer=" + customer.toString());
     int rows=this.customerDAO.deleteById(customer.getId());
     return rows;
 }

 // 查询
 public CustomerDO doQueryById (Integer id) {
     CustomerDO obj = this.customerDAO.getById(id);
     return obj;
 }
//客户添加
 public CustomerDO addCustomer(CustomerDO customer) {
	 customer.setCreateTime(new Date());
	 this.customerDAO.insert(customer);
	 return customer;
	
 }
}