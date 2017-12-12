package net.fnsco.web.order;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.car.service.safe.OrderSafeService;
import net.fnsco.car.service.safe.entity.OrderSafeDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 保险订单管理
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月12日 上午9:38:20
 */
@Controller
@RequestMapping(value = "/web/order")
public class SafeOrderWebController extends BaseController {
	
	@Autowired
	private OrderSafeService orderSafeService;
	
	/**
	 * query:(分页查询)
	 *
	 * @param  @param orderBuy
	 * @param  @param currentPageNum
	 * @param  @param pageSize
	 * @param  @return    设定文件
	 * @return ResultPageDTO<OrderSafeDO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月12日 上午9:40:01
	 */
	@RequestMapping(value = "/safe", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "car:safe:list" })
	public ResultPageDTO<OrderSafeDO>  query(OrderSafeDO orderBuy ,Integer currentPageNum,Integer pageSize){
		logger.info("查询购车订单列表");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		ResultPageDTO<OrderSafeDO> result  = orderSafeService.page(orderBuy, currentPageNum, pageSize);
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
	@RequestMapping(value = "/updateSafeStatus", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "car:safe:update" })
	public ResultDTO<String>  updateStatus(OrderSafeDO orderBuy){
		logger.info("更新订单状态");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		orderSafeService.doUpdate(orderBuy, getUserId());
		return success();
	}
}
