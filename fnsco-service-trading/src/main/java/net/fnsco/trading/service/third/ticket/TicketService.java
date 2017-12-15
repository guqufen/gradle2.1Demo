package net.fnsco.trading.service.third.ticket;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.trading.service.third.ticket.comm.TicketConstants;
import net.fnsco.trading.service.third.ticket.dao.TicketContactDAO;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderDAO;
import net.fnsco.trading.service.third.ticket.dao.TicketOrderPassengerDAO;
import net.fnsco.trading.service.third.ticket.dto.PassengersDTO;
import net.fnsco.trading.service.third.ticket.dto.TicketOrderDTO;
import net.fnsco.trading.service.third.ticket.dto.TicketOrderSubmitDTO;
import net.fnsco.trading.service.third.ticket.dto.ticketsAvailableDTO;
import net.fnsco.trading.service.third.ticket.entity.TicketContactDO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderDO;
import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;
import net.fnsco.trading.service.third.ticket.util.TrainTicketsUtil;

@Service
public class TicketService extends BaseService {
    @Autowired
    private TicketOrderDAO          ticketOrderDAO;
    @Autowired
    private TicketOrderPassengerDAO ticketOrderPassengerDAO;
    @Autowired
    private TicketContactDAO        ticketContactDAO;

    public List<ticketsAvailableDTO> queryTicketList(String startSite, String endSite, String buyDate) {
        List<ticketsAvailableDTO> resultList = Lists.newArrayList();
        if (Strings.isNullOrEmpty(startSite)) {
            return resultList;
        }
        if (Strings.isNullOrEmpty(endSite)) {
            return resultList;
        }
        if (Strings.isNullOrEmpty(buyDate)) {
            return resultList;
        }
        //Map data = getTicketsAvailable(buyDate, startSite, endSite);
        String data = TrainTicketsUtil.getTicketsAvailable("2017-12-26", "HGH", "VNP");
        if (Strings.isNullOrEmpty(data)) {
            return resultList;
        }
        resultList = JSON.parseArray(data, ticketsAvailableDTO.class);
        return resultList;
    }

    public void addOrder(TicketOrderDTO ticketOrderDTO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrderDAO.insert(ticketOrder);
        String passengerId = ticketOrderDTO.getPassengerId();
        String[] passengerIds = passengerId.split(",");
        for (int i = 0; i < passengerIds.length; i++) {
            TicketContactDO contact = ticketContactDAO.getById(Integer.parseInt(passengerIds[i]));
            TicketOrderPassengerDO ticketOrderPassenger = new TicketOrderPassengerDO();
            ticketOrderPassenger.setOrderNo(ticketOrder.getOrderNo());
            ticketOrderPassenger.setPassengerId(contact.getId());
            ticketOrderPassenger.setPassengerName(contact.getName());
            ticketOrderPassenger.setCardNum(contact.getCardNum());
            ticketOrderPassenger.setCardTypeId(contact.getCardType());
            ticketOrderPassenger.setCardTypeName(TicketConstants.CardTypeEnum.getTypeName(contact.getCardType()));
            ticketOrderPassenger.setSeatCode(ticketOrderDTO.getSeatType());
            ticketOrderPassenger.setSeatName(TicketConstants.SeatTypeEnum.getTypeName(ticketOrderDTO.getSeatType()));
            ticketOrderPassenger.setPrice(new BigDecimal(ticketOrderDTO.getPrice()));
            ticketOrderPassenger.setTicketType(ticketOrderDTO.getTicketType());
            ticketOrderPassenger.setTicketTypeName(TicketConstants.TicketTypeEnum.getTypeName(ticketOrderDTO.getTicketType()));
            ticketOrderPassenger.setStatus(TicketConstants.OrderStateEnum.INIT.getCode());
            ticketOrderPassenger.setCreateTime(new Date());
            ticketOrderPassenger.setLastModifyTime(new Date());
            ticketOrderPassengerDAO.insert(ticketOrderPassenger);
        }
        TicketOrderSubmitDTO order = new TicketOrderSubmitDTO();
        PassengersDTO passengers = new PassengersDTO();
        order.setPassengers(passengers);
        Map paramesMap = JSON.parseObject(JSON.toJSONString(order), Map.class);
        try {
            String orderId = TrainTicketsUtil.postIndent(paramesMap);
        } catch (Exception ex) {
            logger.error("火车票提交订单出错" + JSON.toJSONString(paramesMap), ex);
        }
    }
}
