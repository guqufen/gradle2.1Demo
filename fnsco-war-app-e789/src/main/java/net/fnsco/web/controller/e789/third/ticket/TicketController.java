package net.fnsco.web.controller.e789.third.ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.google.common.base.Strings;

import ch.qos.logback.core.status.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.freamwork.comm.Md5Util;
import net.fnsco.order.api.appuser.AppUserService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.trading.constant.E789ApiConstant;
import net.fnsco.trading.service.third.ticket.TicketContactService;
import net.fnsco.trading.service.third.ticket.TicketOrderService;
import net.fnsco.trading.service.third.ticket.TicketService;
import net.fnsco.trading.service.third.ticket.TicketSiteService;
import net.fnsco.trading.service.third.ticket.comm.TicketConstants;
import net.fnsco.trading.service.third.ticket.dto.TicketOrderDTO;
import net.fnsco.trading.service.third.ticket.dto.ticketsAvailableDTO;
import net.fnsco.trading.service.third.ticket.entity.TicketContactDO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.third.ticket.entity.TicketSiteDO;
import net.fnsco.trading.service.third.ticket.vo.TrainOrderListVO;
import net.fnsco.web.controller.e789.jo.CommonJO;
import net.fnsco.web.controller.e789.third.ticket.jo.CancelOrderJO;
import net.fnsco.web.controller.e789.third.ticket.jo.DeletePassengerJO;
import net.fnsco.web.controller.e789.third.ticket.jo.PassengerJO;
import net.fnsco.web.controller.e789.third.ticket.jo.QueryOrderListJO;
import net.fnsco.web.controller.e789.third.ticket.jo.SiteJO;
import net.fnsco.web.controller.e789.third.ticket.jo.TicketOrderJO;
import net.fnsco.web.controller.e789.third.ticket.jo.TrainQueryJO;
import net.fnsco.web.controller.e789.third.ticket.vo.AddTicketOrderVO;
import net.fnsco.web.controller.e789.third.ticket.vo.PyCommonVO;
import net.fnsco.web.controller.e789.third.ticket.vo.SeatTypeVO;
import net.fnsco.web.controller.e789.third.ticket.vo.SiteVO;
import net.fnsco.web.controller.e789.third.ticket.vo.TetterVO;
import net.fnsco.web.controller.e789.third.ticket.vo.TrainVO;
import net.fnsco.web.controller.e789.vo.TradeDataVO;

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
    private TicketSiteService    ticketSiteService;
    @Autowired
    private TicketService        ticketService;
    @Autowired
    private TicketContactService ticketContactService;
    @Autowired
    private TicketOrderService   ticketOrderService;
    @Autowired
    private AppUserService       appUserService;

    /**
     * queryTradeDataDetail:(查询交易流水详情)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/importSite")
    @ApiOperation(value = "站点列表导入")
    public ResultDTO<List<SiteVO>> importSite() {
        ticketSiteService.importSite();
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
    @RequestMapping(value = "/querySiteList")
    @ApiOperation(value = "模糊查询站点列表")
    public ResultDTO<List<TetterVO>> querySiteList(@RequestBody SiteJO siteJO) {
        long timer = System.currentTimeMillis();
        List<TicketSiteDO> result = ticketSiteService.querySiteList(siteJO.getSiteName());
        logger.error("查询站点列表sql耗时" + (System.currentTimeMillis() - timer));
        PyCommonVO<SiteVO> resultVO = new PyCommonVO<SiteVO>();
        for (TicketSiteDO site : result) {
            SiteVO vo = new SiteVO();
            vo.setSiteCode(site.getCode());
            vo.setSiteName(site.getName());
            vo.setSitePyName(site.getPyName());
            resultVO.setList(vo);
        }
        List<TetterVO> resultList = Lists.newArrayList();
        TetterVO.setList(resultList, resultVO);
        return success(resultList);
    }

    /**
     * queryTradeDataList:(查询火车票详情)
     *
     * @param  @param tradeDataJO
     * @param  @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:03:41
     * {
          "buyDate": "2018-01-20",
          "endSite": "HNH",
          "startSite": "HGH",
          "trainCode": "K80"
        }
     * 
     * 
     */
    @RequestMapping(value = "/queryTicketList")
    @ApiOperation(value = "查询火车票列表")
    public ResultDTO<List<TrainVO>> queryTicketList(@RequestBody TrainQueryJO ticketJO) {
        List<TrainVO> resultList = Lists.newArrayList();
        List<ticketsAvailableDTO> ticketList = ticketService.queryTicketList(ticketJO.getStartSite(), ticketJO.getEndSite(), ticketJO.getBuyDate());
        for (ticketsAvailableDTO dto : ticketList) {
            if (!Strings.isNullOrEmpty(ticketJO.getTrainCode())) {
                if (!ticketJO.getTrainCode().equals(dto.getTrain_code())) {
                    continue;
                }
            }
            TrainVO vo = new TrainVO();
            vo.setDuration(dto.getRun_time());
            vo.setEndTime(dto.getArrive_time());
            vo.setFromStationCode(dto.getFrom_station_code());
            vo.setFromStationName(dto.getFrom_station_name());
            //vo.setPrice(price);
            vo.setStartTime(dto.getStart_time());
            //vo.setTicketType(ticketType);
            vo.setToStationCode(dto.getTo_station_code());
            vo.setToStationName(dto.getTo_station_name());
            vo.setTrainCode(dto.getTrain_code());
            vo.setTrainDate(ticketJO.getBuyDate());
            List<SeatTypeVO> seatTypeList = getSeatType(dto);
            vo.setSeatTypeList(seatTypeList);
            vo.setCanBuyNow(dto.getCan_buy_now());
            resultList.add(vo);
        }
        return success(resultList);
    }

    private List<SeatTypeVO> getSeatType(ticketsAvailableDTO dto) {
        // 坐席 F:动卧(新增),9:商务座,P:特等座,M:一等座,O（大写字母O，不是数字0）:二等座,6:高级软卧, 4:软卧,3:硬卧,2:软座,1:硬座。
        List<SeatTypeVO> seatTypeList = Lists.newArrayList();
        SeatTypeVO seatVO1 = new SeatTypeVO();
        seatVO1.setSeatCode(TicketConstants.SeatTypeEnum.MOBILE_SLEEPER.getCode());
        seatVO1.setSeatName(TicketConstants.SeatTypeEnum.MOBILE_SLEEPER.getName() + "(上)");
        seatVO1.setPrice(dto.getDw_price());
        seatVO1.setNum(dto.getDw_num());
        if (!"0".equals(dto.getDw_price())) {
            seatTypeList.add(seatVO1);
        }
        SeatTypeVO seatVO110 = new SeatTypeVO();
        seatVO110.setSeatCode(TicketConstants.SeatTypeEnum.MOBILE_SLEEPER.getCode());
        seatVO110.setSeatName(TicketConstants.SeatTypeEnum.MOBILE_SLEEPER.getName() + "(下)");
        seatVO110.setPrice(dto.getDwx_price());
        seatVO110.setNum(dto.getDw_num());
        if (!"0".equals(dto.getDwx_price())) {
            seatTypeList.add(seatVO110);
        }

        SeatTypeVO seatVO2 = new SeatTypeVO();
        seatVO2.setSeatCode(TicketConstants.SeatTypeEnum.BUSINESS.getCode());
        seatVO2.setSeatName(TicketConstants.SeatTypeEnum.BUSINESS.getName());
        seatVO2.setPrice(dto.getSwz_price());
        seatVO2.setNum(dto.getSwz_num());
        if (!"0".equals(dto.getSwz_price())) {
            seatTypeList.add(seatVO2);
        }

        SeatTypeVO seatVO3 = new SeatTypeVO();
        seatVO3.setSeatCode(TicketConstants.SeatTypeEnum.SPECIAL.getCode());
        seatVO3.setSeatName(TicketConstants.SeatTypeEnum.SPECIAL.getName());
        seatVO3.setPrice(dto.getTdz_price());
        seatVO3.setNum(dto.getSwz_num());
        if (!"0".equals(dto.getTdz_price())) {
            seatTypeList.add(seatVO3);
        }

        SeatTypeVO seatVO4 = new SeatTypeVO();
        seatVO4.setSeatCode(TicketConstants.SeatTypeEnum.FIRST_CLASS.getCode());
        seatVO4.setSeatName(TicketConstants.SeatTypeEnum.FIRST_CLASS.getName());
        seatVO4.setPrice(dto.getYdz_price());
        seatVO4.setNum(dto.getYdz_num());
        if (!"0".equals(dto.getYdz_price())) {
            seatTypeList.add(seatVO4);
        }

        SeatTypeVO seatVO5 = new SeatTypeVO();
        seatVO5.setSeatCode(TicketConstants.SeatTypeEnum.SECOND_CLASS.getCode());
        seatVO5.setSeatName(TicketConstants.SeatTypeEnum.SECOND_CLASS.getName());
        seatVO5.setPrice(dto.getEdz_price());
        seatVO5.setNum(dto.getEdz_num());
        if (!"0".equals(dto.getEdz_price())) {
            seatTypeList.add(seatVO5);
        }

        SeatTypeVO seatVO6 = new SeatTypeVO();
        seatVO6.setSeatCode(TicketConstants.SeatTypeEnum.ADVANCED_SOFT.getCode());
        seatVO6.setSeatName(TicketConstants.SeatTypeEnum.ADVANCED_SOFT.getName() + "(下)");
        seatVO6.setPrice(dto.getGjrw_price());
        seatVO6.setNum(dto.getGjrw_num());
        if (!"0".equals(dto.getGjrw_price())) {
            seatTypeList.add(seatVO6);
        }

        SeatTypeVO seatVO611 = new SeatTypeVO();
        seatVO611.setSeatCode(TicketConstants.SeatTypeEnum.ADVANCED_SOFT.getCode());
        seatVO611.setSeatName(TicketConstants.SeatTypeEnum.ADVANCED_SOFT.getName() + "(上)");
        seatVO611.setPrice(dto.getGjrws_price());
        seatVO611.setNum(dto.getGjrw_num());
        if (!"0".equals(dto.getGjrws_price())) {
            seatTypeList.add(seatVO611);
        }

        SeatTypeVO seatVO7 = new SeatTypeVO();
        seatVO7.setSeatCode(TicketConstants.SeatTypeEnum.SOFT.getCode());
        seatVO7.setSeatName(TicketConstants.SeatTypeEnum.SOFT.getName() + "(上)");
        seatVO7.setPrice(dto.getRw_price());
        seatVO7.setNum(dto.getRw_num());
        if (!"0".equals(dto.getRw_price())) {
            seatTypeList.add(seatVO7);
        }

        SeatTypeVO seatVO711 = new SeatTypeVO();
        seatVO711.setSeatCode(TicketConstants.SeatTypeEnum.SOFT.getCode());
        seatVO711.setSeatName(TicketConstants.SeatTypeEnum.SOFT.getName() + "(下)");
        seatVO711.setPrice(dto.getRwx_price());
        seatVO711.setNum(dto.getRw_num());
        if (!"0".equals(dto.getRwx_price())) {
            seatTypeList.add(seatVO711);
        }

        SeatTypeVO seatVO9 = new SeatTypeVO();
        seatVO9.setSeatCode(TicketConstants.SeatTypeEnum.HARD_SLEEPER.getCode());
        seatVO9.setSeatName(TicketConstants.SeatTypeEnum.HARD_SLEEPER.getName() + "(上)");
        seatVO9.setPrice(dto.getYw_price());
        seatVO9.setNum(dto.getYw_num());
        if (!"0".equals(dto.getYw_price())) {
            seatTypeList.add(seatVO9);
        }

        SeatTypeVO seatVO911 = new SeatTypeVO();
        seatVO911.setSeatCode(TicketConstants.SeatTypeEnum.HARD_SLEEPER.getCode());
        seatVO911.setSeatName(TicketConstants.SeatTypeEnum.HARD_SLEEPER.getName() + "(中)");
        seatVO911.setPrice(dto.getYwz_price());
        seatVO911.setNum(dto.getYw_num());
        if (!"0".equals(dto.getYwz_price())) {
            seatTypeList.add(seatVO911);
        }

        SeatTypeVO seatVO912 = new SeatTypeVO();
        seatVO912.setSeatCode(TicketConstants.SeatTypeEnum.HARD_SLEEPER.getCode());
        seatVO912.setSeatName(TicketConstants.SeatTypeEnum.HARD_SLEEPER.getName() + "(下)");
        seatVO912.setPrice(dto.getYwx_price());
        seatVO912.setNum(dto.getYw_num());
        if (!"0".equals(dto.getYwx_price())) {
            seatTypeList.add(seatVO912);
        }

        SeatTypeVO seatVO10 = new SeatTypeVO();
        seatVO10.setSeatCode(TicketConstants.SeatTypeEnum.SOFT_SEAT.getCode());
        seatVO10.setSeatName(TicketConstants.SeatTypeEnum.SOFT_SEAT.getName());
        seatVO10.setPrice(dto.getRz_price());
        seatVO10.setNum(dto.getRz_num());
        if (!"0".equals(dto.getRz_price())) {
            seatTypeList.add(seatVO10);
        }

        SeatTypeVO seatV11 = new SeatTypeVO();
        seatV11.setSeatCode(TicketConstants.SeatTypeEnum.HARD_SEAT.getCode());
        seatV11.setSeatName(TicketConstants.SeatTypeEnum.HARD_SEAT.getName());
        seatV11.setPrice(dto.getYz_price());
        seatV11.setNum(dto.getYz_num());
        if (!"0".equals(dto.getYz_price())) {
            seatTypeList.add(seatV11);
        }

        SeatTypeVO seatV12 = new SeatTypeVO();
        seatV12.setSeatCode(TicketConstants.SeatTypeEnum.NO_SEAT.getCode());
        seatV12.setSeatName(TicketConstants.SeatTypeEnum.NO_SEAT.getName());
        seatV12.setPrice(dto.getWz_price());
        seatV12.setNum(dto.getWz_num());
        if (!"0".equals(dto.getWz_price())) {
            seatTypeList.add(seatV12);
        }

        SeatTypeVO seatV13 = new SeatTypeVO();
        seatV13.setSeatCode(TicketConstants.SeatTypeEnum.OTHER_SEAT.getCode());
        seatV13.setSeatName(TicketConstants.SeatTypeEnum.OTHER_SEAT.getName());
        seatV13.setPrice(dto.getQtxb_price());
        seatV13.setNum(dto.getQtxb_num());
        if (!"0".equals(dto.getQtxb_price())) {
            seatTypeList.add(seatV13);
        }

        return seatTypeList;
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
    //@RequestMapping(value = "/queryTicketDetail")
    //@ApiOperation(value = "查询火车票详情")
    //public ResultDTO<TrainVO> queryTicketDetail(@RequestBody TicketJO ticketJO) {
    //
    //    return success();
    //}

    /**
     * queryTradeDataDetail:(添加乘客)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/addPassenger")
    @ApiOperation(value = "添加乘客")
    public ResultDTO<TicketContactDO> addPassenger(@RequestBody PassengerJO passengerJO) {
        TicketContactDO ticketContact = new TicketContactDO();
        ticketContact.setAppUserId(passengerJO.getUserId());
        ticketContact.setCardNum(passengerJO.getCardNum());
        ticketContact.setCardType(passengerJO.getCardType());
        ticketContact.setName(passengerJO.getName());
        ticketContact.setTicketType(passengerJO.getTicketType());
        ticketContactService.doAdd(ticketContact);
        return success(ticketContact);
    }
    /**
     * 删除乘客列表中的乘客
     * @param passenger
     * @return
     */
    @RequestMapping(value = "/deletePassenger")
    @ApiOperation(value = "删除乘客")
    public ResultDTO deletePassenger(@RequestBody DeletePassengerJO passenger) {
        TicketContactDO ticketContact = new TicketContactDO();
        ticketContact.setId(passenger.getPassengerId());
        ticketContactService.doDelete(ticketContact);
        return success();
    }

    /**
     * queryTradeDataDetail:(查询乘客信息列表)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/queryPassengerList")
    @ApiOperation(value = "查询我的乘客列表")
    public ResultDTO queryPassenger(@RequestBody CommonJO commonJO) {
        TicketContactDO ticketContact = new TicketContactDO();
        ticketContact.setAppUserId(commonJO.getUserId());
        ResultPageDTO<TicketContactDO> result = ticketContactService.page(ticketContact, 1, 15);
        return success(result);
    }

    /**
     * queryTradeDataDetail:(提交订单)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    /**
    {
        "fromStationCode": "HGH",
        "fromStationName": "杭州东",
        "passengerId": "6201",
        "price": "11",
        "seatCode": "1",
        "toStationCode": "HNH",
        "toStationName": "海宁",
        "trainCode": "K528",
        "trainDate": "2017-12-30",
        "userId": 10
      }
      */
    @RequestMapping(value = "/addOrder")
    @ApiOperation(value = "提交订单")
    public ResultDTO<AddTicketOrderVO> addOrder(@RequestBody TicketOrderJO ticketOrderJO) {
        if (Strings.isNullOrEmpty(ticketOrderJO.getPassengerId())) {
            return fail("乘客不能为空");
        }
        TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();
        ticketOrderDTO.setFromStationCode(ticketOrderJO.getFromStationCode());
        ticketOrderDTO.setFromStationName(ticketOrderJO.getFromStationName());
        ticketOrderDTO.setPassengerId(ticketOrderJO.getPassengerId());
        BigDecimal amountB = new BigDecimal(ticketOrderJO.getPrice());
        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
        ticketOrderDTO.setPrice(amountBs.toString());
        ticketOrderDTO.setSeatCode(ticketOrderJO.getSeatCode());
        ticketOrderDTO.setToStationCode(ticketOrderJO.getToStationCode());
        ticketOrderDTO.setToStationName(ticketOrderJO.getToStationName());
        ticketOrderDTO.setTrainCode(ticketOrderJO.getTrainCode());
        ticketOrderDTO.setTrainDate(ticketOrderJO.getTrainDate());
        ticketOrderDTO.setUserId(ticketOrderJO.getUserId());
        ticketOrderDTO.setArriveTime(ticketOrderJO.getArriveTime());
        ticketOrderDTO.setRunTime(ticketOrderJO.getRunTime());
        ticketOrderDTO.setStartTime(ticketOrderJO.getStartTime());
        ResultDTO<TicketOrderDO> result = ticketService.addOrder(ticketOrderDTO);
        if (!result.isSuccess()) {
            return fail(result.getCode(), result.getMessage());
        }
        TicketOrderDO TicketOrderDO = result.getData();
        if (Strings.isNullOrEmpty(TicketOrderDO.getPayOrderNo())) {
            return fail(TicketOrderDO.getRespMsg());
        }
        AddTicketOrderVO resultVO = new AddTicketOrderVO();
        resultVO.setOrderNo(TicketOrderDO.getOrderNo());
        return success(resultVO);
    }

    /**
     * queryTradeDataDetail:(查询待支付的订单列表)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/getPayOrderList")
    @ApiOperation(value = "查询待支付订单列表")
    public ResultDTO<List<TrainOrderListVO>> getPayOrderList(@RequestBody QueryOrderListJO queryOrderListJO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(queryOrderListJO.getOrderNo());
        ticketOrder.setAppUserId(queryOrderListJO.getUserId());
        Integer pageNum = 1;
        Integer pageSize = 40;
        final List<TicketOrderDO> userIdList = ticketOrderService.updateOrderStatus(ticketOrder, pageNum, pageSize);
        sendSms(userIdList);
        Integer[] statuses = { 0, 1, 2 };
        ticketOrder.setStatuses(statuses);
        ResultPageDTO<TrainOrderListVO> resultList = ticketOrderService.getOrderList(ticketOrder, pageNum, pageSize);
        return success(resultList);
    }

    /**
     * queryTradeDataDetail:(取消订单)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/cancelOrder")
    @ApiOperation(value = "取消订单")
    public ResultDTO cancelOrder(@RequestBody CancelOrderJO cancelOrderJO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(cancelOrderJO.getOrderNo());
        ticketOrder.setAppUserId(cancelOrderJO.getUserId());
        return ticketOrderService.cancelOrder(ticketOrder);
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
    public ResultDTO pay(@RequestBody CancelOrderJO payOrderJO) {
    	Integer payType = payOrderJO.getPayType();
    	if (payType==null) {
            return ResultDTO.fail("支付类型不能为空");
        }
    	if (Strings.isNullOrEmpty(payOrderJO.getPayPassword())) {
    		return ResultDTO.fail("支付密码不能为空");
    	}
    	String password = Md5Util.getInstance().md5(payOrderJO.getPayPassword());
    	// 根据id查询用户是否存在获取原密码
    	AppUser mAppUser = appUserService.selectAppUserById(payOrderJO.getUserId());
    	if (null == mAppUser) {
    		logger.error("火车票支付时出错，用户Id未找到相关信息，appUserId=" + payOrderJO.getUserId());
    		return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
    	}
    	// 查到的密码和原密码做比较
    	if (!password.equals(mAppUser.getPayPassword())) {
    		logger.error("支付密码错误，请核对后重新输入！！");
    		return ResultDTO.fail(E789ApiConstant.E_APP_PAY_PASSWORD_ERROR);
    	}
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(payOrderJO.getOrderNo());
        ticketOrder.setAppUserId(payOrderJO.getUserId());
        if(payType==1) {
        	return	ticketOrderService.payByZFB(ticketOrder);
        }else {
        	return ticketOrderService.pay(ticketOrder);
        }
    }

    /**
     * queryTradeDataDetail:(查询订单列表)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/getPayingOrderList")
    @ApiOperation(value = "查询支付是否成功的订单列表")
    public ResultDTO<List<TrainOrderListVO>> getPayingOrderList(@RequestBody QueryOrderListJO queryOrderListJO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(queryOrderListJO.getOrderNo());
        ticketOrder.setAppUserId(queryOrderListJO.getUserId());
        Integer pageNum = 1;
        Integer pageSize = 20;
        final List<TicketOrderDO> userIdList = ticketOrderService.updateOrderStatus(ticketOrder, pageNum, pageSize);
        sendSms(userIdList);
        Integer[] statuses = { 4 };
        ticketOrder.setStatuses(statuses);
        ResultPageDTO<TrainOrderListVO> resultList = ticketOrderService.getOrderList(ticketOrder, pageNum, pageSize);
        return success(resultList);
    }

    private void sendSms(final List<TicketOrderDO> userIdList) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (TicketOrderDO order : userIdList) {
                    //邱您已购买1月20日G1362次4车12B号，长沙南站13：25开 。【E789】
                    AppUser mAppUser = appUserService.selectAppUserById(order.getAppUserId());
                    String mobile = mAppUser.getMobile();
                    String name = mAppUser.getUserName();
                    String trainDate = order.getTrainDate();
                    String trainCode = order.getTrainCode();
                    String fromStationName = order.getFromStationName();
                    String startTime = order.getStartTime();
                    String desc = name + "您已购买" + DateUtils.strToChina(trainDate) + trainCode + "次车票" + "," + fromStationName + "站" + startTime + "开。";
                    try {
                        String callback = SmsUtil.e789TicketPay(mobile, name, desc);
                        JSONObject callbackJson = (JSONObject) JSONObject.parse(callback);
                        String resultCode = callbackJson.getString("code");
                        if ("0".equals(resultCode)) {
                            logger.warn("已发送至手机号" + mobile + "返回详情" + callback);
                        } else {
                            logger.error("未能够发送至手机" + mobile + "错误code" + resultCode + ";错误详情" + callback);
                        }
                    } catch (Exception e) {
                        logger.error("未能够发送至手机" + mobile + "错误原因:异常错误");
                        logger.error("短信发送异常 ", e);
                    }
                }
            }
        }).start();
    }

    /**
     * queryTradeDataDetail:(查询订单列表)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/getOrderList")
    @ApiOperation(value = "查询所有订单列表")
    public ResultDTO<List<TrainOrderListVO>> getOrderList(@RequestBody QueryOrderListJO queryOrderListJO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(queryOrderListJO.getOrderNo());
        ticketOrder.setAppUserId(queryOrderListJO.getUserId());
        String status = queryOrderListJO.getStatus();
        Integer[] statusIntes = null;
        if (!Strings.isNullOrEmpty(status)) {
            String[] statuses = status.split(",");
            statusIntes = new Integer[statuses.length];
            for (int i = 0; i < statuses.length; i++) {
                statusIntes[i] = Integer.parseInt(statuses[i]);
            }

        }

        Integer pageNum = 1;
        Integer pageSize = 40;
        final List<TicketOrderDO> userIdList = ticketOrderService.updateOrderStatus(ticketOrder, pageNum, pageSize);
        sendSms(userIdList);
        ticketOrder.setStatuses(statusIntes);
        ResultPageDTO<TrainOrderListVO> resultList = ticketOrderService.getOrderList(ticketOrder, pageNum, pageSize);
        return success(resultList);
    }

    /**
     * queryTradeDataDetail:(退票)
     *
     * @param  @param tradeDataDetailJO
     * @param  @return    设定文件
     * @return ResultDTO<TradeDataDetailVO>    DOM对象
     * @author sxfei
     * @date   2017年12月13日 下午4:07:33
     */
    @RequestMapping(value = "/refund")
    @ApiOperation(value = "退票")
    public ResultDTO refund(@RequestBody CancelOrderJO payOrderJO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(payOrderJO.getOrderNo());
        ticketOrder.setAppUserId(payOrderJO.getUserId());
        return ticketOrderService.refund(ticketOrder);
    }
}
