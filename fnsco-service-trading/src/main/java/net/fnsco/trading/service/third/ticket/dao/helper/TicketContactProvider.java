package net.fnsco.trading.service.third.ticket.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.third.ticket.entity.TicketContactDO;
public class TicketContactProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "thr_ticket_contact";

    public String update(Map<String, Object> params) {
        TicketContactDO ticketContact = (TicketContactDO) params.get("ticketContact");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(ticketContact.getAppUserId())){
            SET("app_user_id=#{ticketContact.appUserId}");
        }
        if (StringUtils.isNotBlank(ticketContact.getName())){
            SET("name=#{ticketContact.name}");
        }
        if (StringUtils.isNotBlank(ticketContact.getMobile())){
            SET("mobile=#{ticketContact.mobile}");
        }
        if (StringUtils.isNotBlank(ticketContact.getCardType())){
            SET("card_type=#{ticketContact.cardType}");
        }
        if (StringUtils.isNotBlank(ticketContact.getCardNum())){
            SET("card_num=#{ticketContact.cardNum}");
        }
        if (StringUtils.isNotBlank(ticketContact.getTicketType())){
            SET("ticket_type=#{ticketContact.ticketType}");
        }
        WHERE("id = #{ticketContact.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TicketContactDO ticketContact = (TicketContactDO) params.get("ticketContact");
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
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (ticketContact.getId() != null) {
            WHERE("id=#{ticketContact.id}");
        }
        if (StringUtils.isNotBlank(ticketContact.getAppUserId())){
            WHERE("app_user_id=#{ticketContact.appUserId}");
        }
        if (StringUtils.isNotBlank(ticketContact.getName())){
            WHERE("name=#{ticketContact.name}");
        }
        if (StringUtils.isNotBlank(ticketContact.getMobile())){
            WHERE("mobile=#{ticketContact.mobile}");
        }
        if (StringUtils.isNotBlank(ticketContact.getCardType())){
            WHERE("card_type=#{ticketContact.cardType}");
        }
        if (StringUtils.isNotBlank(ticketContact.getCardNum())){
            WHERE("card_num=#{ticketContact.cardNum}");
        }
        if (StringUtils.isNotBlank(ticketContact.getTicketType())){
            WHERE("ticket_type=#{ticketContact.ticketType}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TicketContactDO ticketContact = (TicketContactDO) params.get("ticketContact");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (ticketContact.getId() != null) {
            WHERE("id=#{ticketContact.id}");
        }
        if (StringUtils.isNotBlank(ticketContact.getAppUserId())){
            WHERE("app_user_id=#{ticketContact.appUserId}");
        }
        if (StringUtils.isNotBlank(ticketContact.getName())){
            WHERE("name=#{ticketContact.name}");
        }
        if (StringUtils.isNotBlank(ticketContact.getMobile())){
            WHERE("mobile=#{ticketContact.mobile}");
        }
        if (StringUtils.isNotBlank(ticketContact.getCardType())){
            WHERE("card_type=#{ticketContact.cardType}");
        }
        if (StringUtils.isNotBlank(ticketContact.getCardNum())){
            WHERE("card_num=#{ticketContact.cardNum}");
        }
        if (StringUtils.isNotBlank(ticketContact.getTicketType())){
            WHERE("ticket_type=#{ticketContact.ticketType}");
        }
        }}.toString();
    }
}

