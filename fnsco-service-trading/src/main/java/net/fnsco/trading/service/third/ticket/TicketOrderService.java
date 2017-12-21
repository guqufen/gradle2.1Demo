package net.fnsco.trading.service.third.ticket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderDAO;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderPassengerDAO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;
import net.fnsco.trading.service.third.ticket.vo.OrderContactVO;
import net.fnsco.trading.service.third.ticket.vo.TrainOrderListVO;

@Service
public class TicketOrderService extends BaseService {

    private Logger                  logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TicketOrderDAO          ticketOrderDAO;
    @Autowired
    private TicketOrderPassengerDAO passengerDAO;

    // 分页
    public ResultPageDTO<TicketOrderDO> page(TicketOrderDO ticketOrder, Integer pageNum, Integer pageSize) {
        logger.info("开始分页查询TicketOrderService.page, ticketOrder=" + ticketOrder.toString());
        List<TicketOrderDO> pageList = this.ticketOrderDAO.pageList(ticketOrder, pageNum, pageSize);
        Integer count = this.ticketOrderDAO.pageListCount(ticketOrder);
        ResultPageDTO<TicketOrderDO> pager = new ResultPageDTO<TicketOrderDO>(count, pageList);
        return pager;
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
                contact.setPrice(pass.getPrice());
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
}