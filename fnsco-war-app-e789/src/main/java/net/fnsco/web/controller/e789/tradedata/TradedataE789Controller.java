package net.fnsco.web.controller.e789.tradedata;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.web.controller.e789.jo.TradeDataDetailJO;
import net.fnsco.web.controller.e789.jo.TradeDataJO;
import net.fnsco.web.controller.e789.vo.TradeDataDetailVO;
import net.fnsco.web.controller.e789.vo.TradeDataVO;

/**
 * @desc 流水查询控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 下午3:12:47
 */
@RestController
@RequestMapping(value = "/app2c/tradeData", method = RequestMethod.POST)
@Api(value = "/app2c/tradeData", tags = { "首页-流水相关功能接口" })
public class TradedataE789Controller extends BaseController {
	
	/**
	 * queryTradeDataList:(查询流水列表)
	 *
	 * @param  @param tradeDataJO
	 * @param  @return    设定文件
	 * @return ResultDTO<Object>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 下午4:03:41
	 */
	@RequestMapping(value = "/query")
	@ApiOperation(value = "首页-查询交易流水列表")
	public ResultDTO<TradeDataVO> queryTradeDataList(@RequestBody TradeDataJO tradeDataJO){
		return null;
	}
	
	
	/**
	 * queryTradeDataDetail:(查询交易流水详情)
	 *
	 * @param  @param tradeDataDetailJO
	 * @param  @return    设定文件
	 * @return ResultDTO<TradeDataDetailVO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 下午4:07:33
	 */
	@RequestMapping(value = "/queryDetail")
	@ApiOperation(value = "首页-流水详情-查询交易流水详情")
	public ResultDTO<TradeDataDetailVO> queryTradeDataDetail(@RequestBody TradeDataDetailJO tradeDataDetailJO){
		return null;
	}
	
	
}
