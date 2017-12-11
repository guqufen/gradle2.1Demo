package net.fnsco.web.order;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.car.service.buy.entity.OrderBuyDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 贷款订单管理
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月11日 下午4:49:53
 */
@Controller
@RequestMapping(value = "/web/order")
public class LoanOrderWebController extends BaseController {
	
	
	@RequestMapping(value = "/loan", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "car:loan:list" })
	public ResultPageDTO<OrderBuyDO>  query(OrderBuyDO orderBuy ,Integer currentPageNum,Integer pageSize){
		logger.info("查询购车订单列表");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		ResultPageDTO<OrderBuyDO> result  = orderBuyService.page(orderBuy, currentPageNum, pageSize);
		return result;
	}
}
