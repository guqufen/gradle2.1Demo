package net.fnsco.trading.service.third.ticket;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.CodeUtil;
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

    @Transactional
    public ResultDTO addOrder(TicketOrderDTO ticketOrderDTO) {
        TicketOrderDO ticketOrder = new TicketOrderDO();
        ticketOrder.setOrderNo(CodeUtil.generateOrderCode("T"));
        ticketOrder.setFromStationCode(ticketOrderDTO.getFromStationCode());
        ticketOrder.setFromStationName(ticketOrderDTO.getFromStationName());
        ticketOrder.setLastModifyTime(new Date());
        ticketOrder.setStatus(TicketConstants.OrderStateEnum.INIT.getCode());
        ticketOrder.setToStationCode(ticketOrderDTO.getToStationCode());
        ticketOrder.setToStationName(ticketOrderDTO.getFromStationName());
        ticketOrder.setTrainCode(ticketOrderDTO.getTrainCode());
        ticketOrder.setTrainDate(ticketOrderDTO.getTrainDate());
        ticketOrder.setCreateTime(new Date());
        ticketOrderDAO.insert(ticketOrder);
        String passengerId = ticketOrderDTO.getPassengerId();
        String[] passengerIds = passengerId.split(",");
        List<TicketOrderPassengerDO> passList = Lists.newArrayList();
        for (int i = 0; i < passengerIds.length; i++) {
            TicketContactDO contact = ticketContactDAO.getById(Integer.parseInt(passengerIds[i]));
            TicketOrderPassengerDO ticketOrderPassenger = new TicketOrderPassengerDO();
            ticketOrderPassenger.setOrderNo(ticketOrder.getOrderNo());
            ticketOrderPassenger.setPassengerId(contact.getId());
            ticketOrderPassenger.setPassengerName(contact.getName());
            ticketOrderPassenger.setCardNum(contact.getCardNum());
            ticketOrderPassenger.setCardTypeId(contact.getCardType());
            ticketOrderPassenger.setCardTypeName(TicketConstants.CardTypeEnum.getTypeName(contact.getCardType()));
            ticketOrderPassenger.setSeatCode(ticketOrderDTO.getSeatCode());
            ticketOrderPassenger.setSeatName(TicketConstants.SeatTypeEnum.getTypeName(ticketOrderDTO.getSeatCode()));
            ticketOrderPassenger.setPrice(new BigDecimal(ticketOrderDTO.getPrice()));
            ticketOrderPassenger.setTicketType(ticketOrderDTO.getTicketType());
            ticketOrderPassenger.setTicketTypeName(TicketConstants.TicketTypeEnum.getTypeName(ticketOrderDTO.getTicketType()));
            ticketOrderPassenger.setStatus(TicketConstants.OrderStateEnum.INIT.getCode());
            ticketOrderPassenger.setCreateTime(new Date());
            ticketOrderPassenger.setLastModifyTime(new Date());
            ticketOrderPassengerDAO.insert(ticketOrderPassenger);
            passList.add(ticketOrderPassenger);
        }
        List<PassengersDTO> passDtoList = Lists.newArrayList();
        TicketOrderSubmitDTO order = new TicketOrderSubmitDTO();
        order.setCheci(ticketOrder.getTrainCode());
        order.setFrom_station_code(ticketOrder.getFromStationCode());
        order.setFrom_station_name(ticketOrder.getFromStationName());
        order.setTo_station_code(ticketOrder.getToStationCode());
        order.setTo_station_name(ticketOrder.getToStationName());
        order.setTrain_date(ticketOrder.getTrainDate());
        order.setUser_orderid(ticketOrder.getOrderNo());
        for (TicketOrderPassengerDO pass : passList) {
            PassengersDTO passengers = new PassengersDTO();
            passengers.setPassengerid(String.valueOf(pass.getId()));
            passengers.setPassengersename(pass.getPassengerName());
            passengers.setPassportseno(pass.getCardNum());
            passengers.setPassporttypeseid(pass.getCardTypeId());
            passengers.setPassporttypeseidname(pass.getCardTypeName());
            passengers.setPiaotype(pass.getTicketType());
            passengers.setPiaotypename(pass.getTicketTypeName());
            passengers.setPrice(pass.getPrice().toString());
            passengers.setZwcode(pass.getSeatCode());
            passengers.setZwname(pass.getSeatName());
            passDtoList.add(passengers);
        }
        order.setPassengers(passDtoList);
        Map paramesMap = JSON.parseObject(JSON.toJSONString(order), Map.class);
        String orderId = "";
        try {
            orderId = TrainTicketsUtil.postIndent(paramesMap);
            ticketOrder = ticketOrderDAO.getById(ticketOrder.getId());
            if (Strings.isNullOrEmpty(orderId)) {
                ticketOrder.setStatus(TicketConstants.OrderStateEnum.FAIL.getCode());
            } else {
                ticketOrder.setPayOrderNo(orderId);
            }
            ticketOrder.setLastModifyTime(new Date());
            ticketOrderDAO.update(ticketOrder);
        } catch (Exception ex) {
            logger.error("火车票提交订单出错" + JSON.toJSONString(paramesMap), ex);
            return ResultDTO.fail("火车票提交订单出错");
        }
        return ResultDTO.success(orderId);
    }

}
