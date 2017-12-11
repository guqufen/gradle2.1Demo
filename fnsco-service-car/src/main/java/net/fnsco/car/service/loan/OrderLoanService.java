package net.fnsco.car.service.loan;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.car.service.carBrand.CarBrandService;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.car.service.customer.CustomerService;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.loan.dao.OrderLoanDAO;
import net.fnsco.car.service.loan.entity.OrderLoanDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class OrderLoanService extends BaseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderLoanDAO orderLoanDAO;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DicCityService dicCityService;
	@Autowired
	private CarBrandService carBrandService;

	// 分页
	public ResultPageDTO<OrderLoanDO> page(OrderLoanDO orderLoan, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询OrderLoanService.page, orderLoan=" + orderLoan.toString());
		List<OrderLoanDO> pageList = this.orderLoanDAO.pageList(orderLoan, pageNum, pageSize);
		for (OrderLoanDO orderLoanDO : pageList) {
			if (null != orderLoanDO.getCustomerId()) {
				CustomerDO customerDO = customerService.doQueryById(orderLoan.getCustomerId());
				if (null != customerDO) {
					orderLoanDO.setCustomerName(customerDO.getName());
					orderLoanDO.setCustomerPhone(customerDO.getMobile());
				}
			}

			if (null != orderLoanDO.getCityId()) {
				DicCityDO dicCityDO = dicCityService.doQueryById(orderLoanDO.getCityId());
				if (null != dicCityDO) {
					orderLoanDO.setCityName(dicCityDO.getName());
				}
			}

			if (null != orderLoanDO.getCarTypeId()) {
				CarBrandDO carBrandDO = carBrandService.doQueryById(orderLoanDO.getCarTypeId());
				if (null != carBrandDO) {
					orderLoanDO.setCarTypeName(carBrandDO.getName());
				}
			}
		}
		Integer count = this.orderLoanDAO.pageListCount(orderLoan);
		ResultPageDTO<OrderLoanDO> pager = new ResultPageDTO<OrderLoanDO>(count, pageList);
		return pager;
	}

	// 添加
	public OrderLoanDO doAdd(OrderLoanDO orderLoan, int loginUserId) {
		logger.info("开始添加OrderLoanService.add,orderLoan=" + orderLoan.toString());
		this.orderLoanDAO.insert(orderLoan);
		return orderLoan;
	}

	// 修改
	public Integer doUpdate(OrderLoanDO orderLoan, Integer loginUserId) {
		logger.info("开始修改OrderLoanService.update,orderLoan=" + orderLoan.toString());
		int rows = this.orderLoanDAO.update(orderLoan);
		return rows;
	}

	// 删除
	public Integer doDelete(OrderLoanDO orderLoan, Integer loginUserId) {
		logger.info("开始删除OrderLoanService.delete,orderLoan=" + orderLoan.toString());
		int rows = this.orderLoanDAO.deleteById(orderLoan.getId());
		return rows;
	}

	// 查询
	public OrderLoanDO doQueryById(Integer id) {
		OrderLoanDO obj = this.orderLoanDAO.getById(id);
		return obj;
	}

	public ResultDTO<Object> addJo(OrderLoanDO orderLoan, CustomerDO customer) {
		customer = customerService.addCustomer(customer);
		if (customer.getId() == null) {
			return ResultDTO.fail("客户信息新增失败");
		}
		orderLoan.setCustomerId(customer.getId());
		orderLoan.setCreateTime(new Date());
		orderLoan.setLastUpdateTime(new Date());
		orderLoan.setStatus(0);
		orderLoanDAO.insert(orderLoan);
		return ResultDTO.success();
	}
}