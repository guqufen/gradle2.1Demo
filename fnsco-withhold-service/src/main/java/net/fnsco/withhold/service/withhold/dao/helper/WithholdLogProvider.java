package net.fnsco.withhold.service.withhold.dao.helper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.withhold.service.withhold.entity.WithholdLogDO;
public class WithholdLogProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "w_withhold_log";

    public String update(Map<String, Object> params) {
        WithholdLogDO withholdLog = (WithholdLogDO) params.get("withholdLog");
        BEGIN();
        UPDATE(TABLE_NAME);
        if (withholdLog.getWithholdId() != null) {
            SET("withholdId=#{withholdLog.withholdId}");
        }
        if (withholdLog.getAmount() != null) {
            SET("amount=#{withholdLog.amount}");
        }
        if (withholdLog.getStatus() != null) {
            SET("status=#{withholdLog.status}");
        }
        if (StringUtils.isNotBlank(withholdLog.getFailReason())){
            SET("failReason=#{withholdLog.failReason}");
        }
        if (StringUtils.isNotBlank(withholdLog.getDebitDay())){
            SET("debitDay=#{withholdLog.debitDay}");
        }
        WHERE("id = #{withholdLog.id}");
        String sql = SQL();
        return sql;
    }

    public String pageList(Map<String, Object> params) {
        WithholdLogDO withholdLog = (WithholdLogDO) params.get("withholdLog");
        Integer pageNum = (Integer) params.get("pageNum");
        Integer pageSize = (Integer) params.get("pageSize");
        BEGIN();
        SELECT("*");
        FROM(TABLE_NAME);
        if (withholdLog.getId() != null) {
            WHERE("id=#{withholdLog.id}");
        }
        if (withholdLog.getWithholdId() != null) {
            WHERE("withholdId=#{withholdLog.withholdId}");
        }
        if (withholdLog.getAmount() != null) {
            WHERE("amount=#{withholdLog.amount}");
        }
        if (withholdLog.getStatus() != null) {
            WHERE("status=#{withholdLog.status}");
        }
        if (StringUtils.isNotBlank(withholdLog.getFailReason())){
            WHERE("failReason=#{withholdLog.failReason}");
        }
        if (StringUtils.isNotBlank(withholdLog.getDebitDay())){
            WHERE("debitDay=#{withholdLog.debitDay}");
        }
        ORDER_BY("id desc");
        String sql = SQL();
        int start = 0;
        int limit = 0;
        if (pageNum == null || pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 20;
        }
        start = (pageNum - 1) * pageSize;
        limit = pageSize;
        sql += " limit " + start + ", " + limit;
        return sql;
    }

    public String pageListCount(Map<String, Object> params) {
        WithholdLogDO withholdLog = (WithholdLogDO) params.get("withholdLog");
        BEGIN();
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (withholdLog.getId() != null) {
            WHERE("id=#{withholdLog.id}");
        }
        if (withholdLog.getWithholdId() != null) {
            WHERE("withholdId=#{withholdLog.withholdId}");
        }
        if (withholdLog.getAmount() != null) {
            WHERE("amount=#{withholdLog.amount}");
        }
        if (withholdLog.getStatus() != null) {
            WHERE("status=#{withholdLog.status}");
        }
        if (StringUtils.isNotBlank(withholdLog.getFailReason())){
            WHERE("failReason=#{withholdLog.failReason}");
        }
        if (StringUtils.isNotBlank(withholdLog.getDebitDay())){
            WHERE("debitDay=#{withholdLog.debitDay}");
        }
        String sql = SQL();
        return sql;
    }
}

