package net.fnsco.car.service.buy;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.car.service.agent.dao.AgentDAO;
import net.fnsco.car.service.agent.entity.AgentDO;
import net.fnsco.car.service.buy.dao.OrderBuyDAO;
import net.fnsco.car.service.buy.entity.OrderBuyDO;
import net.fnsco.car.service.carBrand.CarBrandService;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.car.service.city.DicCityService;
import net.fnsco.car.service.city.entity.DicCityDO;
import net.fnsco.car.service.customer.CustomerService;
import net.fnsco.car.service.customer.entity.CustomerDO;
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
	@Autowired
	private DicCityService dicCityService;
	@Autowired
	private CarBrandService carBrandService;
	@Autowired
	private AgentDAO agentDAO;

	// 分页
	public ResultPageDTO<OrderBuyDO> page(OrderBuyDO orderBuy, Integer pageNum, Integer pageSize) {
		logger.info("开始分页查询OrderBuyService.page, orderBuy=" + orderBuy.toString());
		List<OrderBuyDO> pageList = this.orderBuyDAO.pageList(orderBuy, pageNum, pageSize);
		for (OrderBuyDO orderBuyDO : pageList) {
			if (null != orderBuyDO.getCustomerId()) {
				CustomerDO customerDO = customerService.doQueryById(orderBuyDO.getCustomerId());
				if (null != customerDO) {
					orderBuyDO.setCustomerName(customerDO.getName());
					orderBuyDO.setCustomerPhone(customerDO.getMobile());
				}
			}

			if (null != orderBuyDO.getCityId()) {
				DicCityDO dicCityDO = dicCityService.doQueryById(orderBuyDO.getCityId());
				if (null != dicCityDO) {
					orderBuyDO.setCityName(dicCityDO.getName());
				}
			}

			if (null != orderBuyDO.getCarTypeId()) {

				CarBrandDO carBrandDO = carBrandService.doQueryById(orderBuyDO.getCarTypeId());
				if (null != carBrandDO) {
					orderBuyDO.setCarTypeName(carBrandDO.getName());
				}
			}
			
			if(null != orderBuyDO.getCarSubTypeId()) {
				CarBrandDO carBrandDO = carBrandService.doQueryById(orderBuyDO.getCarSubTypeId());
				if(null != carBrandDO) {
					orderBuyDO.setCarTypeName(orderBuyDO.getCarTypeName()+"&"+carBrandDO.getName());
				}
			}
			
			if(null != orderBuyDO.getSuggestCode()) {
				AgentDO agentDO = agentDAO.getBySuggestCode(orderBuyDO.getSuggestCode());
				if(null != agentDO) {
					orderBuyDO.setAgentName(agentDO.getName());
				}
			}
			
		}
		Integer count = this.orderBuyDAO.pageListCount(orderBuy);
		ResultPageDTO<OrderBuyDO> pager = new ResultPageDTO<OrderBuyDO>(count, pageList);
		return pager;
	}

	// 添加
	public OrderBuyDO doAdd(OrderBuyDO orderBuy, int loginUserId) {
		logger.info("开始添加OrderBuyService.add,orderBuy=" + orderBuy.toString());
		this.orderBuyDAO.insert(orderBuy);
		return orderBuy;
	}

	// 修改
	public Integer doUpdate(OrderBuyDO orderBuy, Integer loginUserId) {
		logger.info("开始修改OrderBuyService.update,orderBuy=" + orderBuy.toString());
		int rows = this.orderBuyDAO.update(orderBuy);
		return rows;
	}

	// 删除
	public Integer doDelete(OrderBuyDO orderBuy, Integer loginUserId) {
		logger.info("开始删除OrderBuyService.delete,orderBuy=" + orderBuy.toString());
		int rows = this.orderBuyDAO.deleteById(orderBuy.getId());
		return rows;
	}

	// 查询
	public OrderBuyDO doQueryById(Integer id) {
		OrderBuyDO obj = this.orderBuyDAO.getById(id);
		return obj;
	}

	@Transactional
	public ResultDTO<Object> addJo(OrderBuyDO orderBuy, CustomerDO customer) {
		customer = customerService.addCustomer(customer);
		orderBuy.setCustomerId(customer.getId());
		orderBuy.setCreateTime(new Date());
		orderBuy.setLastUpdateTime(new Date());
		orderBuy.setStatus(0);
		orderBuyDAO.insert(orderBuy);
		return ResultDTO.success();
	}
}