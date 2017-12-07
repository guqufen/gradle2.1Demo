package net.fnsco.web.controller.e789.stat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.order.dao.TradeOrderByDayDAO;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.jo.PayTypeTurnoverJO;
import net.fnsco.web.controller.e789.vo.EveryDayTurnoverVO;
import net.fnsco.web.controller.e789.vo.PayTypeTurnoverVO;
import net.fnsco.web.controller.e789.vo.TotalTurnoverVO;

/**
 * @desc 统计相关功能控制器
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:22:52
 */
@RestController
@RequestMapping(value = "/app2c/stat", method = RequestMethod.POST)
@Api(value = "/app2c/stat", tags = { "首页-统计相关功能接口" })
public class StatController extends BaseController {
	
	@Autowired
	private TradeOrderByDayDAO tradeOrderByDayDAO;
	@Autowired
	private TradeOrderDAO tradeOrderDAO;
	/**
	 * 
	 * getTotalTurnover:(这里用一句话描述这个方法的作用)
	 *
	 * @param  @param commonJO
	 * @param  @return    设定文件
	 * @return ResultDTO<TotalTurnoverVO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 上午10:30:10
	 */
	@RequestMapping(value = "/getTotalTurnover")
	@ApiOperation(value = "首页-统计-获取今日昨天本月交易额接口")
	public ResultDTO<TotalTurnoverVO> getTotalTurnover(@RequestBody CommonJO commonJO){
		
		if(null == commonJO.getUserId()) {
 			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
 		}
		
		TotalTurnoverVO resultVO =  new TotalTurnoverVO();
		String tradeToday = DateUtils.getDateStrByMonth(0,0);//yyyy-MM-dd
		String totalToday = tradeOrderDAO.queryTotalAmount(tradeToday, commonJO.getUserId());
		
		String tradeYesterday = DateUtils.getDateStrByMonth(0,0);//yyyy-MM-dd
		String  totalTesterday = tradeOrderByDayDAO.selectDayTurnover(tradeYesterday, commonJO.getUserId());
		resultVO.setYesterdayTurnover(totalTesterday);
		
		
		String tradeMonth = DateUtils.getNowDateMonthStr();//yyyy-MM
		
		return success(resultVO);
	}
	
	/**
	 * getTurnoverByPayType:(按照交易类型来获取交易数据)
	 *
	 * @param  @param payTypeTurnoverJO
	 * @param  @return    设定文件
	 * @return ResultDTO<PayTypeTurnoverVO>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 上午10:40:17
	 */
	@RequestMapping(value = "/getTurnoverByPayType")
	@ApiOperation(value = "首页-统计-按照交易类型来获取交易数据")
	public ResultDTO<PayTypeTurnoverVO>  getTurnoverByPayType(@RequestBody PayTypeTurnoverJO payTypeTurnoverJO){
		
		return null;
	}
	
	/**
	 * getEveryDayTurnover:(获取最近7天的订单量和销售额)
	 *
	 * @param  @param commonJO
	 * @param  @return    设定文件
	 * @return ResultDTO<Object>    DOM对象
	 * @author tangliang
	 * @date   2017年12月5日 上午10:50:59
	 */
	@RequestMapping(value = "/getEveryDayTurnover")
	@ApiOperation(value = "首页-统计-获取最近7天的订单量和销售额")
	public ResultDTO<List<EveryDayTurnoverVO>> getEveryDayTurnover(@RequestBody CommonJO commonJO){
		return null;
	}
}
