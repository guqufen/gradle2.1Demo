package net.fnsco.risk.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.trade.entity.TradeRechargeBillDO;
public class TradeRechargeBillProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_trade_recharge_bill";

    public String update(Map<String, Object> params) {
        TradeRechargeBillDO tradeRechargeBill = (TradeRechargeBillDO) params.get("tradeRechargeBill");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (tradeRechargeBill.getRxeAmount() != null) {
            SET("rxe_amount=#{tradeRechargeBill.rxeAmount}");
        }
        if (StringUtils.isNotBlank(tradeRechargeBill.getRemark())){
            SET("remark=#{tradeRechargeBill.remark}");
        }
        if (tradeRechargeBill.getCreateTime() != null) {
            SET("create_time=#{tradeRechargeBill.createTime}");
        }
        WHERE("id = #{tradeRechargeBill.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeRechargeBillDO tradeRechargeBill = (TradeRechargeBillDO) params.get("tradeRechargeBill");
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
        if (tradeRechargeBill.getId() != null) {
            WHERE("id=#{tradeRechargeBill.id}");
        }
        if (tradeRechargeBill.getRxeAmount() != null) {
            WHERE("rxe_amount=#{tradeRechargeBill.rxeAmount}");
        }
        if (StringUtils.isNotBlank(tradeRechargeBill.getRemark())){
            WHERE("remark=#{tradeRechargeBill.remark}");
        }
        if (tradeRechargeBill.getCreateTime() != null) {
            WHERE("create_time=#{tradeRechargeBill.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeRechargeBillDO tradeRechargeBill = (TradeRechargeBillDO) params.get("tradeRechargeBill");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeRechargeBill.getId() != null) {
            WHERE("id=#{tradeRechargeBill.id}");
        }
        if (tradeRechargeBill.getRxeAmount() != null) {
            WHERE("rxe_amount=#{tradeRechargeBill.rxeAmount}");
        }
        if (StringUtils.isNotBlank(tradeRechargeBill.getRemark())){
            WHERE("remark=#{tradeRechargeBill.remark}");
        }
        if (tradeRechargeBill.getCreateTime() != null) {
            WHERE("create_time=#{tradeRechargeBill.createTime}");
        }
        }}.toString();
    }
}

