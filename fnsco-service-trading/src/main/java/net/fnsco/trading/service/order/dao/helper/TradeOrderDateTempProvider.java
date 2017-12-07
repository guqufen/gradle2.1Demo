package net.fnsco.trading.service.order.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.order.entity.TradeOrderDateTempDO;
public class TradeOrderDateTempProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "r_trade_order_date_temp";

    public String update(Map<String, Object> params) {
        TradeOrderDateTempDO tradeOrderDateTemp = (TradeOrderDateTempDO) params.get("tradeOrderDateTemp");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getInnerCode())){
            SET("inner_code=#{tradeOrderDateTemp.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getAmt())){
            SET("amt=#{tradeOrderDateTemp.amt}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getPaySubType())){
            SET("pay_sub_type=#{tradeOrderDateTemp.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTimeStamp())){
            SET("time_stamp=#{tradeOrderDateTemp.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTradeDate())){
            SET("trade_date=#{tradeOrderDateTemp.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTradeHoure())){
            SET("trade_houre=#{tradeOrderDateTemp.tradeHoure}");
        }
        if (tradeOrderDateTemp.getProcedureFee() != null) {
            SET("procedure_fee=#{tradeOrderDateTemp.procedureFee}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTerminalCode())){
            SET("terminal_code=#{tradeOrderDateTemp.terminalCode}");
        }
        WHERE("id = #{tradeOrderDateTemp.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderDateTempDO tradeOrderDateTemp = (TradeOrderDateTempDO) params.get("tradeOrderDateTemp");
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
        if (tradeOrderDateTemp.getId() != null) {
            WHERE("id=#{tradeOrderDateTemp.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getInnerCode())){
            WHERE("inner_code=#{tradeOrderDateTemp.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getAmt())){
            WHERE("amt=#{tradeOrderDateTemp.amt}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getPaySubType())){
            WHERE("pay_sub_type=#{tradeOrderDateTemp.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTimeStamp())){
            WHERE("time_stamp=#{tradeOrderDateTemp.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTradeDate())){
            WHERE("trade_date=#{tradeOrderDateTemp.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTradeHoure())){
            WHERE("trade_houre=#{tradeOrderDateTemp.tradeHoure}");
        }
        if (tradeOrderDateTemp.getProcedureFee() != null) {
            WHERE("procedure_fee=#{tradeOrderDateTemp.procedureFee}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTerminalCode())){
            WHERE("terminal_code=#{tradeOrderDateTemp.terminalCode}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderDateTempDO tradeOrderDateTemp = (TradeOrderDateTempDO) params.get("tradeOrderDateTemp");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrderDateTemp.getId() != null) {
            WHERE("id=#{tradeOrderDateTemp.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getInnerCode())){
            WHERE("inner_code=#{tradeOrderDateTemp.innerCode}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getAmt())){
            WHERE("amt=#{tradeOrderDateTemp.amt}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getPaySubType())){
            WHERE("pay_sub_type=#{tradeOrderDateTemp.paySubType}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTimeStamp())){
            WHERE("time_stamp=#{tradeOrderDateTemp.timeStamp}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTradeDate())){
            WHERE("trade_date=#{tradeOrderDateTemp.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTradeHoure())){
            WHERE("trade_houre=#{tradeOrderDateTemp.tradeHoure}");
        }
        if (tradeOrderDateTemp.getProcedureFee() != null) {
            WHERE("procedure_fee=#{tradeOrderDateTemp.procedureFee}");
        }
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getTerminalCode())){
            WHERE("terminal_code=#{tradeOrderDateTemp.terminalCode}");
        }
        }}.toString();
    }
}

