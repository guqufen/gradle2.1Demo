package net.fnsco.order.controller.web.trade;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.order.api.dto.TradeDataDTO;
import net.fnsco.order.api.trade.TradeDataService;
import net.fnsco.order.service.domain.trade.TradeData;
/**
 * @desc 交易统计控制器
 * @author tangliang
 * @date 2017年6月28日 下午2:22:46
 */
@Controller
@RequestMapping(value = "/web/trade")
@Api(value = "/web", tags = { "交易统计控制器" })
public class TradeDataWebController extends BaseController {
	
    @Autowired
    private TradeDataService tradeDataService;
	/**
	 * 交易统计分页查询
	 * @param tradeDataDTO
	 * @param currentPageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/query",method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions(value = { "m:trade:list" })
	public ResultPageDTO<TradeData> query(TradeDataDTO tradeDataDTO,Integer currentPageNum,Integer pageSize){
	    
		return tradeDataService.queryTradeData(tradeDataDTO, currentPageNum, pageSize);
	}
	
}
