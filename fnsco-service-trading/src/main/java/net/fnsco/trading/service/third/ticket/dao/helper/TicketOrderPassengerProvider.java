package net.fnsco.trading.service.third.ticket.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.third.ticket.entity.TicketOrderPassengerDO;

public class TicketOrderPassengerProvider {

    private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "thr_ticket_order_passenger";

    public String updateByTC(Map<String, Object> params) {
        TicketOrderPassengerDO ticketOrderPassenger = (TicketOrderPassengerDO) params.get("ticketOrderPassenger");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(ticketOrderPassenger.getOrderNo())) {
                    SET("order_no=#{ticketOrderPassenger.orderNo}");
                }
                if (ticketOrderPassenger.getPassengerId() != null) {
                    SET("passenger_id=#{ticketOrderPassenger.passengerId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getPassengerName())) {
                    SET("passenger_name=#{ticketOrderPassenger.passengerName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketType())) {
                    SET("ticket_type=#{ticketOrderPassenger.ticketType}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketTypeName())) {
                    SET("ticket_type_name=#{ticketOrderPassenger.ticketTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeId())) {
                    SET("card_type_id=#{ticketOrderPassenger.cardTypeId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeName())) {
                    SET("card_type_name=#{ticketOrderPassenger.cardTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardNum())) {
                    SET("card_num=#{ticketOrderPassenger.cardNum}");
                }
                if (ticketOrderPassenger.getPrice() != null) {
                    SET("price=#{ticketOrderPassenger.price}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatCode())) {
                    SET("seat_code=#{ticketOrderPassenger.seatCode}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatName())) {
                    SET("seat_name=#{ticketOrderPassenger.seatName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketNo())) {
                    SET("ticket_no=#{ticketOrderPassenger.ticketNo}");
                }

                if (ticketOrderPassenger.getCreateTime() != null) {
                    SET("create_time=#{ticketOrderPassenger.createTime}");
                }
                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }

                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }

                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }

                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }
                WHERE("id = #{ticketOrderPassenger.id}");
            }
        }.toString();
    }

    public String update(Map<String, Object> params) {
        TicketOrderPassengerDO ticketOrderPassenger = (TicketOrderPassengerDO) params.get("ticketOrderPassenger");
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (StringUtils.isNotBlank(ticketOrderPassenger.getOrderNo())) {
                    SET("order_no=#{ticketOrderPassenger.orderNo}");
                }
                if (ticketOrderPassenger.getPassengerId() != null) {
                    SET("passenger_id=#{ticketOrderPassenger.passengerId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getPassengerName())) {
                    SET("passenger_name=#{ticketOrderPassenger.passengerName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketType())) {
                    SET("ticket_type=#{ticketOrderPassenger.ticketType}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketTypeName())) {
                    SET("ticket_type_name=#{ticketOrderPassenger.ticketTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeId())) {
                    SET("card_type_id=#{ticketOrderPassenger.cardTypeId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeName())) {
                    SET("card_type_name=#{ticketOrderPassenger.cardTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardNum())) {
                    SET("card_num=#{ticketOrderPassenger.cardNum}");
                }
                if (ticketOrderPassenger.getPrice() != null) {
                    SET("price=#{ticketOrderPassenger.price}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatCode())) {
                    SET("seat_code=#{ticketOrderPassenger.seatCode}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatName())) {
                    SET("seat_name=#{ticketOrderPassenger.seatName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketNo())) {
                    SET("ticket_no=#{ticketOrderPassenger.ticketNo}");
                }

                if (ticketOrderPassenger.getCreateTime() != null) {
                    SET("create_time=#{ticketOrderPassenger.createTime}");
                }
                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }

                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }

                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }

                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    SET("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }
                WHERE("id = #{ticketOrderPassenger.id}");
            }
        }.toString();
    }

    public String pageList(Map<String, Object> params) {
        TicketOrderPassengerDO ticketOrderPassenger = (TicketOrderPassengerDO) params.get("ticketOrderPassenger");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (ticketOrderPassenger.getId() != null) {
                    WHERE("id=#{ticketOrderPassenger.id}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getOrderNo())) {
                    WHERE("order_no=#{ticketOrderPassenger.orderNo}");
                }
                if (ticketOrderPassenger.getPassengerId() != null) {
                    WHERE("passenger_id=#{ticketOrderPassenger.passengerId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getPassengerName())) {
                    WHERE("passenger_name=#{ticketOrderPassenger.passengerName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketType())) {
                    WHERE("ticket_type=#{ticketOrderPassenger.ticketType}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketTypeName())) {
                    WHERE("ticket_type_name=#{ticketOrderPassenger.ticketTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeId())) {
                    WHERE("card_type_id=#{ticketOrderPassenger.cardTypeId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeName())) {
                    WHERE("card_type_name=#{ticketOrderPassenger.cardTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardNum())) {
                    WHERE("card_num=#{ticketOrderPassenger.cardNum}");
                }
                if (ticketOrderPassenger.getPrice() != null) {
                    WHERE("price=#{ticketOrderPassenger.price}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatCode())) {
                    WHERE("seat_code=#{ticketOrderPassenger.seatCode}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatName())) {
                    WHERE("seat_name=#{ticketOrderPassenger.seatName}");
                }

                if (ticketOrderPassenger.getCreateTime() != null) {
                    WHERE("create_time=#{ticketOrderPassenger.createTime}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketNo())) {
                    SET("ticket_no=#{ticketOrderPassenger.ticketNo}");
                }
                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    WHERE("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }
                ORDER_BY("id desc limit " + start + ", " + limit);
            }
        }.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TicketOrderPassengerDO ticketOrderPassenger = (TicketOrderPassengerDO) params.get("ticketOrderPassenger");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (ticketOrderPassenger.getId() != null) {
                    WHERE("id=#{ticketOrderPassenger.id}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getOrderNo())) {
                    WHERE("order_no=#{ticketOrderPassenger.orderNo}");
                }
                if (ticketOrderPassenger.getPassengerId() != null) {
                    WHERE("passenger_id=#{ticketOrderPassenger.passengerId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getPassengerName())) {
                    WHERE("passenger_name=#{ticketOrderPassenger.passengerName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketType())) {
                    WHERE("ticket_type=#{ticketOrderPassenger.ticketType}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketTypeName())) {
                    WHERE("ticket_type_name=#{ticketOrderPassenger.ticketTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeId())) {
                    WHERE("card_type_id=#{ticketOrderPassenger.cardTypeId}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardTypeName())) {
                    WHERE("card_type_name=#{ticketOrderPassenger.cardTypeName}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getCardNum())) {
                    WHERE("card_num=#{ticketOrderPassenger.cardNum}");
                }
                if (ticketOrderPassenger.getPrice() != null) {
                    WHERE("price=#{ticketOrderPassenger.price}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatCode())) {
                    WHERE("seat_code=#{ticketOrderPassenger.seatCode}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getSeatName())) {
                    WHERE("seat_name=#{ticketOrderPassenger.seatName}");
                }
                if (ticketOrderPassenger.getCreateTime() != null) {
                    WHERE("create_time=#{ticketOrderPassenger.createTime}");
                }
                if (ticketOrderPassenger.getLastModifyTime() != null) {
                    WHERE("last_modify_time=#{ticketOrderPassenger.lastModifyTime}");
                }
                if (StringUtils.isNotBlank(ticketOrderPassenger.getTicketNo())) {
                    SET("ticket_no=#{ticketOrderPassenger.ticketNo}");
                }
            }
        }.toString();
    }
}
