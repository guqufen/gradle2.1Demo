package net.fnsco.web.order;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.car.service.buy.OrderBuyService;
import net.fnsco.car.service.buy.entity.OrderBuyDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 购车订单管理
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月11日 下午3:11:56
 */
@Controller
@RequestMapping(value = "/web/order")
public class BuyOrderWebController extends BaseController{
	
	@Autowired
	private OrderBuyService orderBuyService;
	/**
	 * query:(表格分页数据)
	 *
	 * @param  @param orderBuy
	 * @param  @param currentPageNum
	 * @param  @param pageSize
	 * @param  @return    设定文件
	 * @return ResultDTO<Object>    DOM对象
	 * @author tangliang
	 * @date   2017年12月11日 下午3:15:44
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "car:buy:list" })
	public ResultPageDTO<OrderBuyDO>  query(OrderBuyDO orderBuy ,Integer currentPageNum,Integer pageSize){
		logger.info("查询购车订单列表");
		ResultPageDTO<OrderBuyDO> result  = orderBuyService.page(orderBuy, currentPageNum, pageSize);
		return result;
	}
}
