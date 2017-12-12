package net.fnsco.web.order;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.car.service.finance.OrderFinanceService;
import net.fnsco.car.service.finance.entity.OrderFinanceDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 理财订单管理
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月11日 下午5:54:13
 */
@Controller
@RequestMapping(value = "/web/order")
public class FinanceOrderWebController extends BaseController {
	
	@Autowired
	private OrderFinanceService orderFinanceService;
	
	@RequestMapping(value = "/finance", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "car:finance:list" })
	public ResultPageDTO<OrderFinanceDO>  query(OrderFinanceDO orderBuy ,Integer currentPageNum,Integer pageSize){
		logger.info("查询理财订单列表");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		ResultPageDTO<OrderFinanceDO> result  = orderFinanceService.page(orderBuy, currentPageNum, pageSize);
		return result;
	}
	
	/**
	 * updateStatus:(更新状态)
	 *
	 * @param  @param orderBuy
	 * @param  @return    设定文件
	 * @return ResultDTO<String>    DOM对象
	 * @author tangliang
	 * @date   2017年12月11日 下午4:42:47
	 */
	@RequestMapping(value = "/updateFinanceStatus", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "car:finance:update" })
	public ResultDTO<String>  updateStatus(OrderFinanceDO orderBuy){
		logger.info("更新订单状态");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		orderFinanceService.doUpdate(orderBuy, getUserId());
		return success();
	}
}
