package net.fnsco.withhold.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.withhold.service.trade.entity.WithholdLogDO;
public class WithholdLogProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "w_withhold_log";

    public String update(Map<String, Object> params) {
        WithholdLogDO withholdLog = (WithholdLogDO) params.get("withholdLog");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (withholdLog.getWithholdId() != null) {
            SET("withhold_id=#{withholdLog.withholdId}");
        }
        if (withholdLog.getAmount() != null) {
            SET("amount=#{withholdLog.amount}");
        }
        if (withholdLog.getStatus() != null) {
            SET("status=#{withholdLog.status}");
        }
        if (StringUtils.isNotBlank(withholdLog.getFailReason())){
            SET("fail_reason=#{withholdLog.failReason}");
        }
        if (StringUtils.isNotBlank(withholdLog.getDebitDay())){
            SET("debit_day=#{withholdLog.debitDay}");
        }
        WHERE("id = #{withholdLog.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        WithholdLogDO withholdLog = (WithholdLogDO) params.get("withholdLog");
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
        if (withholdLog.getId() != null) {
            WHERE("id=#{withholdLog.id}");
        }
        if (withholdLog.getWithholdId() != null) {
            WHERE("withhold_id=#{withholdLog.withholdId}");
        }
        if (withholdLog.getAmount() != null) {
            WHERE("amount=#{withholdLog.amount}");
        }
        if (withholdLog.getStatus() != null) {
            WHERE("status=#{withholdLog.status}");
        }
        if (StringUtils.isNotBlank(withholdLog.getFailReason())){
            WHERE("fail_reason=#{withholdLog.failReason}");
        }
        if (StringUtils.isNotBlank(withholdLog.getDebitDay())){
            WHERE("debit_day=#{withholdLog.debitDay}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        WithholdLogDO withholdLog = (WithholdLogDO) params.get("withholdLog");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (withholdLog.getId() != null) {
            WHERE("id=#{withholdLog.id}");
        }
        if (withholdLog.getWithholdId() != null) {
            WHERE("withhold_id=#{withholdLog.withholdId}");
        }
        if (withholdLog.getAmount() != null) {
            WHERE("amount=#{withholdLog.amount}");
        }
        if (withholdLog.getStatus() != null) {
            WHERE("status=#{withholdLog.status}");
        }
        if (StringUtils.isNotBlank(withholdLog.getFailReason())){
            WHERE("fail_reason=#{withholdLog.failReason}");
        }
        if (StringUtils.isNotBlank(withholdLog.getDebitDay())){
            WHERE("debit_day=#{withholdLog.debitDay}");
        }
        }}.toString();
    }
}

