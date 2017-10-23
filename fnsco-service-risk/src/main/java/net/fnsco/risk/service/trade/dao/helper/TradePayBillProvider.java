package net.fnsco.risk.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.trade.entity.TradePayBillDO;
public class TradePayBillProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_trade_pay_bill";

    public String update(Map<String, Object> params) {
        TradePayBillDO tradePayBill = (TradePayBillDO) params.get("tradePayBill");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (tradePayBill.getTxnAmount() != null) {
            SET("txn_amount=#{tradePayBill.txnAmount}");
        }
        if (StringUtils.isNotBlank(tradePayBill.getRemark())){
            SET("remark=#{tradePayBill.remark}");
        }
        if (tradePayBill.getCreateTime() != null) {
            SET("create_time=#{tradePayBill.createTime}");
        }
        if (tradePayBill.getTxnBusinnessId() != null) {
            SET("txn_businness_id=#{tradePayBill.txnBusinnessId}");
        }
        if (StringUtils.isNotBlank(tradePayBill.getTxnBussinessType())){
            SET("txn_bussiness_type=#{tradePayBill.txnBussinessType}");
        }
        WHERE("id = #{tradePayBill.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradePayBillDO tradePayBill = (TradePayBillDO) params.get("tradePayBill");
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
        if (tradePayBill.getId() != null) {
            WHERE("id=#{tradePayBill.id}");
        }
        if (tradePayBill.getTxnAmount() != null) {
            WHERE("txn_amount=#{tradePayBill.txnAmount}");
        }
        if (StringUtils.isNotBlank(tradePayBill.getRemark())){
            WHERE("remark=#{tradePayBill.remark}");
        }
        if (tradePayBill.getCreateTime() != null) {
            WHERE("create_time=#{tradePayBill.createTime}");
        }
        if (tradePayBill.getTxnBusinnessId() != null) {
            WHERE("txn_businness_id=#{tradePayBill.txnBusinnessId}");
        }
        if (StringUtils.isNotBlank(tradePayBill.getTxnBussinessType())){
            WHERE("txn_bussiness_type=#{tradePayBill.txnBussinessType}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradePayBillDO tradePayBill = (TradePayBillDO) params.get("tradePayBill");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradePayBill.getId() != null) {
            WHERE("id=#{tradePayBill.id}");
        }
        if (tradePayBill.getTxnAmount() != null) {
            WHERE("txn_amount=#{tradePayBill.txnAmount}");
        }
        if (StringUtils.isNotBlank(tradePayBill.getRemark())){
            WHERE("remark=#{tradePayBill.remark}");
        }
        if (tradePayBill.getCreateTime() != null) {
            WHERE("create_time=#{tradePayBill.createTime}");
        }
        if (tradePayBill.getTxnBusinnessId() != null) {
            WHERE("txn_businness_id=#{tradePayBill.txnBusinnessId}");
        }
        if (StringUtils.isNotBlank(tradePayBill.getTxnBussinessType())){
            WHERE("txn_bussiness_type=#{tradePayBill.txnBussinessType}");
        }
        }}.toString();
    }
}

