package net.fnsco.web.order;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.fnsco.car.service.loan.OrderLoanService;
import net.fnsco.car.service.loan.entity.OrderLoanDO;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.freamwork.business.WebUserDTO;

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
	
	@Autowired
	private OrderLoanService orderLoanService;
	/**
	 * query:(分页查询)
	 *
	 * @param  @param orderBuy
	 * @param  @param currentPageNum
	 * @param  @param pageSize
	 * @param  @return    设定文件
	 * @return ResultPageDTO<OrderLoanDO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月11日 下午4:52:57
	 */
	@RequestMapping(value = "/loan", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "car:loan:list" })
	public ResultPageDTO<OrderLoanDO>  query(OrderLoanDO orderBuy ,Integer currentPageNum,Integer pageSize){
		logger.info("查询购车订单列表");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		
		WebUserDTO adminUser = (WebUserDTO) getSessionUser();
		 if(null != adminUser && null != adminUser.getType() && adminUser.getType() == 2) {
			 orderBuy.setSysUserId(adminUser.getId());
		 }
		 
		ResultPageDTO<OrderLoanDO> result  = orderLoanService.page(orderBuy, currentPageNum, pageSize);
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
	@RequestMapping(value = "/updateLoanStatus", method = RequestMethod.POST)
	@ResponseBody
	@RequiresPermissions(value = { "car:loan:update" })
	public ResultDTO<String>  updateStatus(OrderLoanDO orderBuy){
		logger.info("更新订单状态");
		if(-1 ==orderBuy.getStatus()) {
			orderBuy.setStatus(null);
		}
		orderLoanService.doUpdate(orderBuy, getUserId());
		return success();
	}
}
