package net.fnsco.trading.service.third.ticket;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import net.fnsco.core.alipay.AlipayAppPayRequestParams;
import net.fnsco.core.alipay.AlipayClientUtil;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.core.utils.CodeUtil;
import net.fnsco.core.utils.DateUtils;
import net.fnsco.core.utils.SmsUtil;
import net.fnsco.trading.comm.TradeConstants;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.third.ticket.comm.TicketConstants;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderDAO;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderPassengerDAO;
import net.fnsco.trading.service.third.ticket.dto.Tickets;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;
import net.fnsco.trading.service.third.ticket.util.TrainTicketsUtil;
import net.fnsco.trading.service.third.ticket.vo.OrderContactVO;
import net.fnsco.trading.service.third.ticket.vo.TrainOrderListVO;
import net.fnsco.trading.service.withdraw.TradeWithdrawService;
import net.fnsco.trading.service.withdraw.entity.TradeWithdrawDO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class TicketOrderService extends BaseService {
	@Autowired
	private Environment           env;
    private Logger                   logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TicketOrderDAO           ticketOrderDAO;
    @Autowired
    private TicketOrderPassengerDAO  passengerDAO;
    @Autowired
    private AppAccountBalanceService appAccountBalanceService;
    @Autowired
    private TradeWithdrawService     tradeWithdrawService;
    
    private static final String RECHANGE_NOTIFY_URL = "trade/alipay/ticketPayNotify";//支付宝充值回调
    // 分页
    public ResultPageDTO<TicketOrderDO> page(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketOrderService.page, ticketOrder=" + ticketOrder.toString());
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        Integer count = this.ticketOrderDAO.pageListCount(ticketOrder);
        ResultPageDTO<TicketOrderDO> pager = new ResultPageDTO<TicketOrderDO>(count, pageList);
        return pager;
    }

    @Transactional
    public List<TicketOrderDO> updateOrderStatus(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        List<TicketOrderDO> userIdList = Lists.newArrayList();
        Integer[] statuses = { 1, 2, 4, 7 };
        ticketOrder.setStatuses(statuses);
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        for (TicketOrderDO order : pageList) {
            if (Strings.isNullOrEmpty(order.getPayOrderNo())) {
                continue;
            }
            JSONObject obj = TrainTicketsUtil.getOrderStatus(order.getPayOrderNo());
            if (null != obj) {
                //0未占座1占座中2已占座3占座失败4支付中5支付完成6取消订单7退票中8退票完成'
                String result = obj.getString("result");
                if (result != null) {
                    JSONObject obj1 = JSONObject.fromObject(result);
                    String status = obj1.getString("status");
                    if ("0".equals(status)) {
                        order.setStatus(TicketConstants.OrderStateEnum.PROCESSING.getCode());
                    } else if ("1".equals(status)) {
                        order.setStatus(TicketConstants.OrderStateEnum.FAIL.getCode());
                        order.setRespMsg(obj1.getString("msg"));
                        TradeWithdrawDO tradeWithdraw = tradeWithdrawService.doQueryByOriginalOrderNo(order.getOrderNo());
                        if (null != tradeWithdraw) {//为空表示未点击支付，不用做处理
                            tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.FAIL.getCode());
                            tradeWithdraw.setStatus(2);
                            tradeWithdrawService.doUpdate(tradeWithdraw);
                        }
                    } else if ("2".equals(status)) {
                        BigDecimal amountB = new BigDecimal(obj1.getString("orderamount"));
                        BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
                        order.setOrderAmount(amountBs);
                        order.setStatus(TicketConstants.OrderStateEnum.SIT_DOWN.getCode());
                        order.setRespMsg(obj1.getString("msg"));
                    } else if ("3".equals(status)) {//支付成功待出票；
                        if (TicketConstants.OrderStateEnum.SIT_DOWN.getCode().equals(order.getStatus())) {
                            String ordernumber = obj1.getString("ordernumber");
                            String passengers = obj1.getString("passengers");
                            //String orderamount = obj1.getString("orderamount");

                            String payTime = obj1.getString("pay_time");
                            JSONArray objArray2 = JSONArray.fromObject(passengers);
                            for (int i = 0; i < objArray2.size(); i++) {
                                JSONObject obj2 = objArray2.getJSONObject(i);
                                String ticketNo = obj2.getString("ticket_no");
                                String passengerid = obj2.getString("passengerid");
                                String cxin = obj2.getString("cxin");
                                TicketOrderPassengerDO pass = passengerDAO.getById(Integer.parseInt(passengerid));
                                pass.setTicketNo(ticketNo);
                                pass.setCxin(cxin);
                                pass.setLastModifyTime(new Date());
                                passengerDAO.update(pass);
                            }
                            order.setTrainOrderNumber(ordernumber);
                            order.setPayTime(DateUtils.toParseYmdhms(payTime));
                            //减去冻结金额
                            appAccountBalanceService.doUpdateFrozenAmount(order.getAppUserId(), order.getOrderAmount());
                            order.setStatus(TicketConstants.OrderStateEnum.SUCCESS.getCode());
                            //更新订单表
                            TradeWithdrawDO tradeWithdraw = tradeWithdrawService.doQueryByOriginalOrderNo(order.getOrderNo());
                            tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.SUCCESS.getCode());
                            tradeWithdraw.setStatus(3);
                            tradeWithdrawService.doUpdate(tradeWithdraw);
                        }
                    } else if ("4".equals(status)) {
                        String ordernumber = obj1.getString("ordernumber");
                        String passengers = obj1.getString("passengers");
                        //String orderamount = obj1.getString("orderamount");

                        String payTime = obj1.getString("pay_time");
                        JSONArray objArray2 = JSONArray.fromObject(passengers);
                        for (int i = 0; i < objArray2.size(); i++) {
                            JSONObject obj2 = objArray2.getJSONObject(i);
                            String ticketNo = obj2.getString("ticket_no");
                            String passengerid = obj2.getString("passengerid");
                            String cxin = obj2.getString("cxin");
                            TicketOrderPassengerDO pass = passengerDAO.getById(Integer.parseInt(passengerid));
                            pass.setTicketNo(ticketNo);
                            pass.setCxin(cxin);
                            pass.setLastModifyTime(new Date());
                            passengerDAO.update(pass);
                        }
                        order.setTrainOrderNumber(ordernumber);
                        order.setPayTime(DateUtils.toParseYmdhms(payTime));
                        //减去冻结金额
                        if (TicketConstants.OrderStateEnum.PAYING.getCode().equals(order.getStatus())) {
                            appAccountBalanceService.doUpdateFrozenAmount(order.getAppUserId(), order.getOrderAmount());
                            order.setStatus(TicketConstants.OrderStateEnum.SUCCESS.getCode());
                            //发送成功短信
                            userIdList.add(order);
                        }
                        
                        //更新订单表
                        TradeWithdrawDO tradeWithdraw = tradeWithdrawService.doQueryByOriginalOrderNo(order.getOrderNo());
                        tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.SUCCESS.getCode());
                        tradeWithdraw.setStatus(3);
                        tradeWithdrawService.doUpdate(tradeWithdraw);
                    } else if ("5".equals(status)) {//出票失败
                        if (!TicketConstants.OrderStateEnum.PAY_FAIL.getCode().equals(order.getStatus())) {
                            boolean flug = appAccountBalanceService.doUpdateFrozenAmount(order.getAppUserId(), order.getOrderAmount());
                            if (flug) {
                                appAccountBalanceService.updateFund(order.getAppUserId(), BigDecimal.ZERO.subtract(order.getOrderAmount()));
                            }
                        }
                        order.setStatus(TicketConstants.OrderStateEnum.PAY_FAIL.getCode());
                    } else if ("7".equals(status)) {//有乘客退票成功
                        String passengers = obj1.getString("passengers");
                        JSONArray objArray2 = JSONArray.fromObject(passengers);
                        BigDecimal totalAmount = BigDecimal.ZERO;
                        for (int j = 0; j < objArray2.size(); j++) {
                            JSONObject obj11 = objArray2.getJSONObject(j);
                            String refundTimeline = obj11.getString("refundTimeline");
                            JSONArray objArray3 = JSONArray.fromObject(refundTimeline);
                            for (int i = 0; i < objArray3.size(); i++) {
                                JSONObject obj2 = objArray3.getJSONObject(i);
                                JSONObject detail = (JSONObject) obj2.get("detail");
                                if (null != detail) {
                                    String returnsuccess = detail.getString("returnsuccess");
                                    String returnMoney = detail.getString("returnmoney");
                                    String returnfailmsg = detail.getString("returnfailmsg");
                                    String ticketNo = detail.getString("ticket_no");
                                    String passportseno = detail.getString("passportseno");
                                    TicketOrderPassengerDO pass = passengerDAO.getByTC(ticketNo, passportseno);
                                    pass.setReturnFailMsg(returnfailmsg);
                                    BigDecimal amountB = new BigDecimal(returnMoney);
                                    BigDecimal amountBs = amountB.multiply(new BigDecimal("100"));
                                    pass.setReturnMoney(amountBs);
                                    pass.setLastModifyTime(new Date());
                                    passengerDAO.update(pass);
                                    if ("true".equals(returnsuccess)) {
                                        totalAmount.add(amountBs);
                                        order.setStatus(TicketConstants.OrderStateEnum.REFUND.getCode());
                                    }
                                    //appAccountBalanceService.updateFund(order.getAppUserId(), BigDecimal.ZERO.subtract(amountBs));
                                }
                            }
                        }
                        if (TicketConstants.OrderStateEnum.REFUND.getCode().equals(order.getStatus())) {
                            //增加退款的钱
                            appAccountBalanceService.updateFund(order.getAppUserId(), BigDecimal.ZERO.subtract(totalAmount));
                        }
                        TradeWithdrawDO tradeWithdraw = tradeWithdrawService.getUndoByOrderNo(order.getOrderNo());
                        tradeWithdraw.setRespCode(TradeConstants.RespCodeEnum.SUCCESS.getCode());
                        tradeWithdraw.setStatus(3);
                        tradeWithdraw.setAmount(totalAmount);
                        tradeWithdrawService.doUpdate(tradeWithdraw);
                    } else if ("8".equals(status)) {//有乘客退票失败
                        order.setStatus(TicketConstants.OrderStateEnum.REFUND_FAIL.getCode());
                    }
                    order.setLastModifyTime(new Date());
                    ticketOrderDAO.update(order);
                }
            }
        }
        return userIdList;
    }

    // 分页
    public ResultPageDTO<TrainOrderListVO> getOrderList(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketOrderService.page, ticketOrder=" + ticketOrder.toString());
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        List<TrainOrderListVO> resultList = Lists.newArrayList();
        for (TicketOrderDO order : pageList) {
            TrainOrderListVO vo = new TrainOrderListVO();
            vo.setFromStationCode(order.getFromStationCode());
            vo.setCreateTime(DateUtils.dateFormatToStr(order.getCreateTime()));
            vo.setFromStationName(order.getFromStationName());
            vo.setId(order.getId());
            vo.setOrderNo(order.getOrderNo());
            vo.setPayOrderNo(order.getPayOrderNo());
            vo.setStatus(order.getStatus());
            vo.setStatusName(TicketConstants.OrderStateEnum.getTypeName(order.getStatus()));
            vo.setToStationCode(order.getToStationCode());
            vo.setToStationName(order.getToStationName());
            vo.setTrainCode(order.getTrainCode());
            vo.setTrainDate(order.getTrainDate());
            vo.setArriveTime(order.getArriveTime());
            vo.setStartTime(order.getStartTime());
            vo.setRunTime(order.getRunTime());
            vo.setRespMsg(order.getRespMsg());
            List<TicketOrderPassengerDO> passList = passengerDAO.getByOrderNo(order.getOrderNo());
            List<OrderContactVO> orderContactList = Lists.newArrayList();
            for (TicketOrderPassengerDO pass : passList) {
                OrderContactVO contact = new OrderContactVO();
                contact.setCardNum(pass.getCardNum());
                contact.setCardTypeId(pass.getCardTypeId());
                contact.setCardTypeName(pass.getCardTypeName());
                contact.setId(pass.getId());
                contact.setOrderNo(pass.getOrderNo());
                contact.setPassengerId(pass.getPassengerId());
                contact.setPassengerName(pass.getPassengerName());
                BigDecimal amountB = pass.getPrice();
                BigDecimal amountBs = amountB.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                contact.setPrice(amountBs);
                contact.setSeatName(pass.getSeatName());
                contact.setSeatCode(pass.getSeatCode());
                contact.setTicketType(pass.getTicketType());
                contact.setTicketTypeName(pass.getTicketTypeName());
                orderContactList.add(contact);
            }
            vo.setOrderContactList(orderContactList);
            resultList.add(vo);
        }
        Integer count = this.ticketOrderDAO.pageListCount(ticketOrder);
        ResultPageDTO<TrainOrderListVO> pager = new ResultPageDTO<TrainOrderListVO>(count, resultList);
        return pager;
    }

    // 添加
    public TicketOrderDO doAdd(TicketOrderDO ticketOrder, int loginUserId) {
        logger.info("开始添加TicketOrderService.add,ticketOrder=" + ticketOrder.toString());
        this.ticketOrderDAO.insert(ticketOrder);
        return ticketOrder;
    }

    // 修改
    public Integer doUpdate(TicketOrderDO ticketOrder, Integer loginUserId) {
        logger.info("开始修改TicketOrderService.update,ticketOrder=" + ticketOrder.toString());
        int rows = this.ticketOrderDAO.update(ticketOrder);
        return rows;
    }

    // 删除
    public Integer doDelete(TicketOrderDO ticketOrder, Integer loginUserId) {
        logger.info("开始删除TicketOrderService.delete,ticketOrder=" + ticketOrder.toString());
        int rows = this.ticketOrderDAO.deleteById(ticketOrder.getId());
        return rows;
    }

    // 查询
    public TicketOrderDO doQueryById(Integer id) {
        TicketOrderDO obj = this.ticketOrderDAO.getById(id);
        return obj;
    }
    /**
     * 支付宝火车票支付回调
     * @param ticketOrder
     * @return
     */
    @Transactional
    public ResultDTO payByZFBNotify(String orderNo) {
    	TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(orderNo);
    	order.setStatus(TicketConstants.OrderStateEnum.PAYING.getCode());
        order.setLastModifyTime(new Date());
        ticketOrderDAO.update(order);
        JSONObject obj = TrainTicketsUtil.pay(order.getPayOrderNo());
        String error_code = obj.getString("error_code");
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if (!"0".equals(error_code)) {
            String reason = obj.getString("reason");
            order.setStatus(TicketConstants.OrderStateEnum.SIT_DOWN.getCode());
            order.setLastModifyTime(new Date());
            ticketOrderDAO.update(order);
            return ResultDTO.fail(reason);
        }
        return ResultDTO.success();
    }
    /**
     * 
     * pay:(支付宝支付订单)
     *
     * @param ticketOrder
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Transactional
    public ResultDTO payByZFB(TicketOrderDO ticketOrder) {
    	String notifyUrl = env.getProperty("app.base.url")+RECHANGE_NOTIFY_URL;
        TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(ticketOrder.getOrderNo());
        if (null == order) {
            return ResultDTO.fail("订单不存在");
        }
        if (!TicketConstants.OrderStateEnum.SIT_DOWN.getCode().equals(order.getStatus())) {
            return ResultDTO.fail("订单状态不正常");
        }
        
        AlipayAppPayRequestParams requestParams = new AlipayAppPayRequestParams();
        requestParams.setBody("e789火车票购买");
        requestParams.setSubject("火车票购买");
        requestParams.setTotalAmount(String.format("%.2f", order.getOrderAmount()));
        requestParams.setOutTradeNo(order.getOrderNo());	
        String url= env.getProperty("app.base.url") + RECHANGE_NOTIFY_URL;
        requestParams.setNotifyUrl(url);
        String body =  AlipayClientUtil.createPayOrderParams(requestParams);
        return ResultDTO.success(body);
    }
    /**
     * 
     * pay:(支付订单)
     *
     * @param ticketOrder
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Transactional
    public ResultDTO pay(TicketOrderDO ticketOrder) {

        TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(ticketOrder.getOrderNo());
        if (null == order) {
            return ResultDTO.fail("订单不存在");
        }
        if (!TicketConstants.OrderStateEnum.SIT_DOWN.getCode().equals(order.getStatus())) {
            return ResultDTO.fail("订单状态不正常");
        }
        tradeWithdrawService.doAddForTicket(order);
        //冻结余额
        boolean payResult = appAccountBalanceService.doFrozenBalance(order.getAppUserId(), order.getOrderAmount());
        if (!payResult) {
            return ResultDTO.fail("账户余额不足，请充值");
        }
        order.setStatus(TicketConstants.OrderStateEnum.PAYING.getCode());
        order.setLastModifyTime(new Date());
        ticketOrderDAO.update(order);
        JSONObject obj = TrainTicketsUtil.pay(order.getPayOrderNo());
        String error_code = obj.getString("error_code");
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if (!"0".equals(error_code)) {
            String reason = obj.getString("reason");
            appAccountBalanceService.doFrozenBalance(order.getAppUserId(), BigDecimal.ZERO.subtract(order.getOrderAmount()));
            order.setStatus(TicketConstants.OrderStateEnum.SIT_DOWN.getCode());
            order.setLastModifyTime(new Date());
            ticketOrderDAO.update(order);
            return ResultDTO.fail(reason);
        }
        return ResultDTO.success();
    }

    /**
     * 
     * cancelOrder:(取消订单)
     *
     * @param ticketOrder
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Transactional
    public ResultDTO cancelOrder(TicketOrderDO ticketOrder) {
        TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(ticketOrder.getOrderNo());
        if (null == order) {
            return ResultDTO.fail("订单不存在");
        }
        if (!TicketConstants.OrderStateEnum.SIT_DOWN.getCode().equals(order.getStatus())) {
            return ResultDTO.fail("订单状态不正常");
        }
        order.setStatus(TicketConstants.OrderStateEnum.CANCEL.getCode());
        order.setLastModifyTime(new Date());
        ticketOrderDAO.update(order);
        JSONObject obj = TrainTicketsUtil.cancel(order.getPayOrderNo());
        String error_code = obj.getString("error_code");
        if ("217304".equals(error_code)) {
            return ResultDTO.success();
        }
        if (!"0".equals(error_code)) {
            order.setStatus(TicketConstants.OrderStateEnum.SIT_DOWN.getCode());
            order.setLastModifyTime(new Date());
            ticketOrderDAO.update(order);
            return ResultDTO.fail("取消支付失败,请稍后重试");
        }
        return ResultDTO.success();
    }

    /**
     * 
     * refund:(退票)
     *
     * @param ticketOrder
     * @return   ResultDTO    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @Transactional
    public ResultDTO refund(TicketOrderDO ticketOrder) {
        TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(ticketOrder.getOrderNo());
        if (null == order) {
            return ResultDTO.fail("订单不存在");
        }
        if (!TicketConstants.OrderStateEnum.SUCCESS.getCode().equals(order.getStatus())) {
            return ResultDTO.fail("订单状态不正常");
        }
        order.setStatus(TicketConstants.OrderStateEnum.REFUNDING.getCode());
        order.setLastModifyTime(new Date());
        ticketOrderDAO.update(order);

        List<Tickets> ticketList = Lists.newArrayList();
        List<TicketOrderPassengerDO> passList = passengerDAO.getByOrderNo(order.getOrderNo());
        for (TicketOrderPassengerDO pass : passList) {
            Tickets tickets = new Tickets();
            tickets.setPassengername(pass.getPassengerName());
            tickets.setPassportseno(pass.getCardNum());
            tickets.setPassporttypeseid(pass.getCardTypeId());
            tickets.setTicket_no(pass.getTicketNo());
            ticketList.add(tickets);
        }
        tradeWithdrawService.doRefundForTicket(order);
        Map map = Maps.newHashMap();
        map.put("orderid", order.getPayOrderNo());
        map.put("tickets", JSON.toJSONString(ticketList));
        JSONObject obj;
        try {
            obj = TrainTicketsUtil.refund(map);
            String error_code = obj.getString("error_code");
            //判断是否调用成功，只有error_code=0的时候表示返回成功
            if (!"0".equals(error_code)) {
                String reason = obj.getString("reason");
                logger.error("调用退票程序出错", reason);
                order.setStatus(TicketConstants.OrderStateEnum.SUCCESS.getCode());
                order.setRespMsg("调用退票程序出错" + reason);
                order.setLastModifyTime(new Date());
                ticketOrderDAO.update(order);
                return ResultDTO.fail(reason);
            } else {
                String msg = obj.getString("msg");
                order.setRespMsg(msg);
                order.setLastModifyTime(new Date());
                ticketOrderDAO.update(order);
            }
        } catch (UnsupportedEncodingException e) {
            order.setStatus(TicketConstants.OrderStateEnum.SUCCESS.getCode());
            order.setLastModifyTime(new Date());
            ticketOrderDAO.update(order);
            logger.error("调用退票程序出错", e);
            return ResultDTO.fail("调用退票程序异常");
        }
        return ResultDTO.success();
    }

}