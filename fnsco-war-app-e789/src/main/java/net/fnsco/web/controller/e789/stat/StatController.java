package net.fnsco.web.controller.e789.stat;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.trading.service.order.dao.TradeOrderByDayDAO;
import net.fnsco.trading.service.order.dao.TradeOrderByPayMediumDAO;
import net.fnsco.trading.service.order.dao.TradeOrderByPayTypeDAO;
import net.fnsco.trading.service.order.dao.TradeOrderDAO;
import net.fnsco.trading.service.order.dto.OrderDayDTO;
import net.fnsco.trading.service.order.dto.OrderPayTypeDTO;
import net.fnsco.trading.service.order.entity.TradeOrderByPayMediumDO;
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
	@Autowired
	private TradeOrderByPayTypeDAO tradeOrderByPayTypeDAO;
	@Autowired
	private TradeOrderByPayMediumDAO tradeOrderByPayMediumDAO;
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
		
		/**
		 * 今日
		 */
		TotalTurnoverVO resultVO =  new TotalTurnoverVO();
		String tradeToday = DateUtils.getDateStrByMonth(0,0);//yyyy-MM-dd
		String totalToday = tradeOrderDAO.queryTotalAmount(tradeToday, commonJO.getUserId());
		resultVO.setTodayTurnover(formatRMBNumber(totalToday));
		
		/**
		 * 昨天
		 */
		String tradeYesterday = DateUtils.getDateStrByMonth(0,-1);//yyyy-MM-dd
		String  totalTesterday = tradeOrderByDayDAO.selectDayTurnover(tradeYesterday, commonJO.getUserId());
		resultVO.setYesterdayTurnover(formatRMBNumber(totalTesterday));
		
		/**
		 * 本月
		 */
		String startTradeDate = DateUtils.getMouthStartTime(0);//yyyy-MM
		String thisMonth =  tradeOrderByDayDAO.selectTotalTurnover(startTradeDate, tradeYesterday, commonJO.getUserId());
		String thisMouthTru = new BigDecimal(totalToday).add(new BigDecimal(thisMonth)).toString();
		resultVO.setThisMonthTurnover(formatRMBNumber(thisMouthTru));
		
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
		
		if(null == payTypeTurnoverJO.getUserId()) {
			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
		}
		
		formatInputDate(payTypeTurnoverJO);
		PayTypeTurnoverVO payTypeTurnoverVO = new PayTypeTurnoverVO();
		List<OrderPayTypeDTO> datas = tradeOrderByPayTypeDAO.selectByCondition(payTypeTurnoverJO.getStartDate(), payTypeTurnoverJO.getEndDate(), payTypeTurnoverJO.getUserId());
		Integer orderNum = 0;
		BigDecimal turnover =  new BigDecimal(0);
		if(null != datas) {
			for (OrderPayTypeDTO orderPayTypeDTO : datas) {
				//扫码
				if("01".equals(orderPayTypeDTO.getPayType()) ||"02".equals(orderPayTypeDTO.getPayType())) {
					orderNum += orderPayTypeDTO.getOrderNum();
					if(null != orderPayTypeDTO.getTurnover()) {
						turnover = turnover.add(orderPayTypeDTO.getTurnover());
					}
				}else if("03".equals(orderPayTypeDTO.getPayType())) {
					payTypeTurnoverVO.setFenshanfu(formatRMBNumber(orderPayTypeDTO.getTurnover().toString()));
					payTypeTurnoverVO.setFenshanfuNum(orderPayTypeDTO.getOrderNum());
				}
			}
		}
		payTypeTurnoverVO.setqRTurnover(formatRMBNumber(turnover.toString()));
		payTypeTurnoverVO.setqRNum(orderNum);
		/**
		 * 台码需要单独查询
		 */
		TradeOrderByPayMediumDO tradeOrderByPayMedium = new TradeOrderByPayMediumDO();
		tradeOrderByPayMedium.setUserId(payTypeTurnoverJO.getUserId());
		tradeOrderByPayMedium.setPayMedium("02");
		tradeOrderByPayMedium.setStartTradeDate(payTypeTurnoverJO.getStartDate());
		tradeOrderByPayMedium.setEndTradeDate(DateUtils.getDateStrByStr(payTypeTurnoverJO.getEndDate(),1));
		List<OrderPayTypeDTO> taikaData = tradeOrderByPayMediumDAO.countSUMTurnover(tradeOrderByPayMedium);
		if(null != taikaData && taikaData.size() == 1) {
			payTypeTurnoverVO.setTaiKTurnover(formatRMBNumber(taikaData.get(0).getTurnover().toString()));
			payTypeTurnoverVO.setTaiKNum(taikaData.get(0).getOrderNum());
		}
		
		return success(payTypeTurnoverVO);
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
		if(null == commonJO.getUserId()) {
			return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
		}
		
		String startTradeDate = DateUtils.getDateStrByMonth(0,-8);//yyyy-MM-dd;
		String endTradeDate = DateUtils.getDateStrByMonth(0,-1);
		List<OrderDayDTO> datas = tradeOrderByDayDAO.selectTurnoverByCondition(startTradeDate, endTradeDate, commonJO.getUserId());
		List<EveryDayTurnoverVO> resultData = Lists.newArrayList();
		for (OrderDayDTO orderDayDTO : datas) {
			EveryDayTurnoverVO vo = new EveryDayTurnoverVO();
			vo.setOrderNum(orderDayDTO.getOrderNumber());
			vo.setTurnover(formatRMBNumber(orderDayDTO.getTurnover()));
			vo.setTurnoverDate(orderDayDTO.getTradeDate());
			resultData.add(vo);
		}
		return success(resultData);
	}
	
	/**
	 * formatRMBNumber:(改为保留两位小数 RMB显示)
	 *
	 * @param  @param str
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @author tangliang
	 * @date   2017年12月7日 下午6:09:41
	 */
	private String formatRMBNumber(String str) {
		if(Strings.isNullOrEmpty(str)) {
			return String.format("%.2f", 0);
		}else {
			return String.format("%.2f", new BigDecimal(str).divide(new BigDecimal(100)).doubleValue());
		}
	}
	
	/**
     * formatInputDate:(这里用一句话描述这个方法的作用)格式化输入日期参数
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return TradeReportDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    private PayTypeTurnoverJO formatInputDate(PayTypeTurnoverJO tradeReportDTO) {
    	
    	if (!Strings.isNullOrEmpty(tradeReportDTO.getStartDate())) {
            tradeReportDTO.setStartDate(DateUtils.formatDateStrInput(tradeReportDTO.getStartDate()));
        }
        if (!Strings.isNullOrEmpty(tradeReportDTO.getEndDate())) {
            tradeReportDTO.setEndDate(DateUtils.formatDateStrInput(tradeReportDTO.getEndDate()));
        }
        //如果没有传递时间和商家，则默认上周和全部商户数据
        if (Strings.isNullOrEmpty(tradeReportDTO.getStartDate()) || Strings.isNullOrEmpty(tradeReportDTO.getEndDate())) {
            tradeReportDTO.setStartDate(DateUtils.getDateStrByMonth(0,-1));
            tradeReportDTO.setEndDate(DateUtils.getDateStrByMonth(0,-8));
        }
        return tradeReportDTO;
    }
}
