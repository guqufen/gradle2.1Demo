package net.fnsco.web.withdraw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;

/**
 * 
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2018年1月4日 下午4:22:53
 */
@Controller
@RequestMapping(value = "/web/e789/withdraw")
public class TradeWithdrawController extends BaseController{
	
	@Autowired
	private TradeWithdrawService tradeWithdrawService;
	/**
	 * 
	 * @param tradeWithdraw
	 * @param currentPageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseBody
	public ResultPageDTO<TradeWithdrawDO> query(TradeWithdrawDO tradeWithdraw, Integer currentPageNum,Integer pageSize) {
		return tradeWithdrawService.page(tradeWithdraw, currentPageNum, pageSize);
	}
}
