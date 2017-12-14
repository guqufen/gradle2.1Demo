package net.fnsco.trading.service.third.ticket.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.third.ticket.entity.TicketSiteDO;
public class TicketSiteProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "thr_ticket_site";

    public String update(Map<String, Object> params) {
        TicketSiteDO ticketSite = (TicketSiteDO) params.get("ticketSite");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(ticketSite.getName())){
            SET("name=#{ticketSite.name}");
        }
        if (StringUtils.isNotBlank(ticketSite.getPyName())){
            SET("py_name=#{ticketSite.pyName}");
        }
        if (StringUtils.isNotBlank(ticketSite.getCode())){
            SET("code=#{ticketSite.code}");
        }
        if (ticketSite.getCreateTime() != null) {
            SET("create_time=#{ticketSite.createTime}");
        }
        if (ticketSite.getLastModifyTime() != null) {
            SET("last_modify_time=#{ticketSite.lastModifyTime}");
        }
        WHERE("id = #{ticketSite.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TicketSiteDO ticketSite = (TicketSiteDO) params.get("ticketSite");
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
        if (ticketSite.getId() != null) {
            WHERE("id=#{ticketSite.id}");
        }
        if (StringUtils.isNotBlank(ticketSite.getName())){
            WHERE("name=#{ticketSite.name}");
        }
        if (StringUtils.isNotBlank(ticketSite.getPyName())){
            WHERE("py_name=#{ticketSite.pyName}");
        }
        if (StringUtils.isNotBlank(ticketSite.getCode())){
            WHERE("code=#{ticketSite.code}");
        }
        if (ticketSite.getCreateTime() != null) {
            WHERE("create_time=#{ticketSite.createTime}");
        }
        if (ticketSite.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{ticketSite.lastModifyTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TicketSiteDO ticketSite = (TicketSiteDO) params.get("ticketSite");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (ticketSite.getId() != null) {
            WHERE("id=#{ticketSite.id}");
        }
        if (StringUtils.isNotBlank(ticketSite.getName())){
            WHERE("name=#{ticketSite.name}");
        }
        if (StringUtils.isNotBlank(ticketSite.getPyName())){
            WHERE("py_name=#{ticketSite.pyName}");
        }
        if (StringUtils.isNotBlank(ticketSite.getCode())){
            WHERE("code=#{ticketSite.code}");
        }
        if (ticketSite.getCreateTime() != null) {
            WHERE("create_time=#{ticketSite.createTime}");
        }
        if (ticketSite.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{ticketSite.lastModifyTime}");
        }
        }}.toString();
    }
}

