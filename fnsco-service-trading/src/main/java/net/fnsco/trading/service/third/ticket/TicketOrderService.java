package net.fnsco.trading.service.third.ticket;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beust.jcommander.internal.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.account.AppAccountBalanceService;
import net.fnsco.trading.service.third.ticket.comm.TicketConstants;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderDAO;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderPassengerDAO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;
import net.fnsco.trading.service.third.ticket.util.TrainTicketsUtil;
import net.fnsco.trading.service.third.ticket.vo.OrderContactVO;
import net.fnsco.trading.service.third.ticket.vo.TrainOrderListVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class TicketOrderService extends BaseService {

    private Logger                   logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TicketOrderDAO           ticketOrderDAO;
    @Autowired
    private TicketOrderPassengerDAO  passengerDAO;
    @Autowired
    private AppAccountBalanceService appAccountBalanceService;

    // 分页
    public ResultPageDTO<TicketOrderDO> page(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketOrderService.page, ticketOrder=" + ticketOrder.toString());
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        Integer count = this.ticketOrderDAO.pageListCount(ticketOrder);
        ResultPageDTO<TicketOrderDO> pager = new ResultPageDTO<TicketOrderDO>(count, pageList);
        return pager;
    }

    @Transactional
    public void updateOrderStatus(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        for (TicketOrderDO order : pageList) {
            JSONObject obj = TrainTicketsUtil.getOrderStatus(order.getPayOrderNo());
            if (null != obj) {
                //0占座中1失败2成功4购买成功
                String result = obj.getString("result");
                if (result != null) {
                    JSONObject obj1 = JSONObject.fromObject(result);
                    String status = obj1.getString("status");
                    if ("0".equals(status)) {
                        order.setStatus(TicketConstants.OrderStateEnum.PROCESSING.getCode());
                    } else if ("1".equals(status)) {
                        order.setStatus(TicketConstants.OrderStateEnum.FAIL.getCode());
                    } else if ("2".equals(status)) {
                        order.setStatus(TicketConstants.OrderStateEnum.SIT_DOWN.getCode());
                    } else if ("4".equals(status)) {
                        String ordernumber = obj1.getString("ordernumber");
                        String passengers = obj1.getString("passengers");
                        JSONArray objArray2 = JSONArray.fromObject(passengers);
                        for (int i = 0; i < objArray2.size(); i++) {
                            JSONObject obj2 = objArray2.getJSONObject(i);
                            String ticketNo = obj2.getString("ticket_no");
                            String passengerid = obj2.getString("passengerid");
                            TicketOrderPassengerDO pass = passengerDAO.getByPassengerId(Integer.parseInt(passengerid));
                            pass.setTicketNo(ticketNo);
                            passengerDAO.update(pass);
                        }
                        order.setTrainOrderNumber(ordernumber);
                        //减去冻结金额
                        if (TicketConstants.OrderStateEnum.PAYING.getCode().equals(order.getStatus())) {
                            appAccountBalanceService.doUpdateFrozenAmount(order.getAppUserId(), order.getOrderAmount());
                            order.setStatus(TicketConstants.OrderStateEnum.SUCCESS.getCode());
                        }
                    }
                    ticketOrderDAO.update(ticketOrder);
                }
            }
        }
    }

    // 分页
    public ResultPageDTO<TrainOrderListVO> getOrderList(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketOrderService.page, ticketOrder=" + ticketOrder.toString());
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        List<TrainOrderListVO> resultList = Lists.newArrayList();
        for (TicketOrderDO order : pageList) {
            TrainOrderListVO vo = new TrainOrderListVO();
            vo.setFromStationCode(order.getFromStationCode());
            vo.setCreateTime(order.getCreateTime());
            vo.setFromStationName(order.getFromStationName());
            vo.setId(order.getId());
            vo.setOrderNo(order.getOrderNo());
            vo.setPayOrderNo(order.getPayOrderNo());
            vo.setStatus(order.getStatus());
            vo.setToStationCode(order.getToStationCode());
            vo.setToStationName(order.getToStationName());
            vo.setTrainCode(order.getTrainCode());
            vo.setTrainDate(order.getTrainDate());
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
        TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(ticketOrder.getAppUserId(), ticketOrder.getOrderNo());
        if (null == order) {
            return ResultDTO.fail("订单不存在");
        }
        if (TicketConstants.OrderStateEnum.SIT_DOWN.getCode().equals(order.getStatus())) {
            return ResultDTO.fail("订单状态不正常");
        }
        order.setStatus(TicketConstants.OrderStateEnum.PAYING.getCode());
        ticketOrderDAO.update(order);
        //冻结余额
        boolean payResult = appAccountBalanceService.doFrozenBalance(order.getAppUserId(), order.getOrderAmount());
        if (!payResult) {
            return ResultDTO.fail("账户余额不足，请充值");
        }
        JSONObject obj = TrainTicketsUtil.pay(order.getPayOrderNo());
        String error_code = obj.getString("error_code");
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if (!"0".equals(error_code)) {
            String reason = obj.getString("reason");
            appAccountBalanceService.doFrozenBalance(order.getAppUserId(), BigDecimal.ZERO.subtract(order.getOrderAmount()));
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
        TicketOrderDO order = this.ticketOrderDAO.getByUserIdOrderNo(ticketOrder.getAppUserId(), ticketOrder.getOrderNo());
        if (null == order) {
            return ResultDTO.fail("订单不存在");
        }
        if (TicketConstants.OrderStateEnum.SIT_DOWN.getCode().equals(order.getStatus())) {
            return ResultDTO.fail("订单状态不正常");
        }
        order.setStatus(TicketConstants.OrderStateEnum.CANCEL.getCode());
        ticketOrderDAO.update(order);
        JSONObject obj = TrainTicketsUtil.pay(order.getPayOrderNo());
        String error_code = obj.getString("error_code");
        if (!"0".equals(error_code)) {
            order.setStatus(TicketConstants.OrderStateEnum.SIT_DOWN.getCode());
            ticketOrderDAO.update(order);
            return ResultDTO.fail("取消支付失败,请稍后重试");
        }
        return ResultDTO.success();
    }
}