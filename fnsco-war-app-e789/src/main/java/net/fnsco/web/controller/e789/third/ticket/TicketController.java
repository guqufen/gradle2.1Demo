package net.fnsco.web.controller.e789.third.ticket;

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
import net.fnsco.trading.service.order.TradeOrderService;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.jo.TradeDataDetailJO;
import net.fnsco.web.controller.e789.third.ticket.jo.SiteJO;
import net.fnsco.web.controller.e789.third.ticket.jo.TicketJO;
import net.fnsco.web.controller.e789.third.ticket.jo.TicketOrderJO;
import net.fnsco.web.controller.e789.third.ticket.jo.TrainQueryJO;
import net.fnsco.web.controller.e789.third.ticket.vo.PassengerVO;
import net.fnsco.web.controller.e789.third.ticket.vo.SiteVO;
import net.fnsco.web.controller.e789.third.ticket.vo.TicketOrderVO;
import net.fnsco.web.controller.e789.third.ticket.vo.TrainVO;

/**
 * @desc 火车票功能
 * @author   sxfei
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月13日 下午3:12:47
 */
@RestController
@RequestMapping(value = "/app2c/ticket", method = RequestMethod.POST)
@Api(value = "/app2c/ticket", tags = { "账户-火车票功能相关接口" })
public class TicketController extends BaseController {

    @Autowired
    private TradeOrderService tradeOrderService;

    /**
     * queryTradeDataDetail:(查询交易流水详情)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/querySiteList")
    @ApiOperation(value = "模糊查询站点列表")
    public ResultDTO<List<SiteVO>> querySiteList(@RequestBody SiteJO siteJO) {

        return success();
    }

    /**
     * queryTradeDataList:(查询火车票详情)
     *
     * @param  @param tradeDataJO
     * @param  @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:03:41
     */
    @RequestMapping(value = "/queryTicketList")
    @ApiOperation(value = "查询火车票列表")
    public ResultDTO<List<TrainVO>> queryTicketList(@RequestBody TrainQueryJO ticketJO) {

        return success();
    }

    /**
     * queryTradeDataDetail:(查询交易流水详情)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/queryTicketDetail")
    @ApiOperation(value = "查询火车票详情")
    public ResultDTO<TrainVO> queryTicketDetail(@RequestBody TicketJO ticketJO) {

        return success();
    }

    /**
     * queryTradeDataDetail:(查询交易流水详情)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/addPassenger")
    @ApiOperation(value = "添加乘客")
    public ResultDTO addPassenger(@RequestBody TradeDataDetailJO tradeDataDetailJO) {

        return success();
    }

    /**
     * queryTradeDataDetail:(查询交易流水详情)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/queryPassengerList")
    @ApiOperation(value = "查询乘客")
    public ResultDTO<PassengerVO> queryPassenger(@RequestBody CommonJO commonJO) {

        return success();
    }

    /**
     * queryTradeDataDetail:(查询交易流水详情)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/addOrder")
    @ApiOperation(value = "提交订单")
    public ResultDTO<TicketOrderVO> addOrder(@RequestBody TicketOrderJO ticketOrderJO) {

        return success();
    }

    /**
     * queryTradeDataDetail:(确认支付)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/pay")
    @ApiOperation(value = "确认支付")
    public ResultDTO pay(@RequestBody TicketOrderJO ticketOrderJO) {

        return success();
    }
}
