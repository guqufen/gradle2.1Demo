package net.fnsco.car.service.loan;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.car.service.customer.CustomerService;
import net.fnsco.car.service.customer.entity.CustomerDO;
import net.fnsco.car.service.file.OrderFileService;
import net.fnsco.car.service.file.entity.OrderFileDO;
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
	private OrderFileService orderFileService;

	// 分页
	public ResultPageDTO<OrderLoanDO> page(OrderLoanDO orderLoan, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询OrderLoanService.page, orderLoan=" + orderLoan.toString());
		List<OrderLoanDO> pageList = this.orderLoanDAO.pageList(orderLoan, pageNum, pageSize);
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

	public ResultDTO<Object> addJo(OrderLoanDO orderLoan, CustomerDO customer, String fileIds) {

		customer = customerService.addCustomer(customer);
		if (customer.getId() == null) {
			return ResultDTO.fail("客户信息新增失败");
		}
		orderLoan.setCustomerId(customer.getId());
		orderLoan.setCreateTime(new Date());
		orderLoan.setLastUpdateTime(new Date());
		orderLoan.setStatus(0);
		orderLoanDAO.insert(orderLoan);
		if (orderLoan.getId() == null) {
			return ResultDTO.fail();
		}
		// 更新文件信息
		if (!StringUtils.isEmpty(fileIds)) {
			OrderFileDO orderFile = new OrderFileDO();
			String[] ids = fileIds.split(",");
			for (String id : ids) {
				orderFile.setId(Integer.parseInt(id));
				orderFile.setOrderNo(orderLoan.getId().toString());
				orderFileService.doUpdate(orderFile, 0);
			}
		}
		return ResultDTO.success();
	}
}