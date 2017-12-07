package net.fnsco.trading.service.order.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.order.entity.TradeOrderByPayTypeDO;
public class TradeOrderByPayTypeProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "r_trade_order_by_pay_type";

    public String update(Map<String, Object> params) {
        TradeOrderByPayTypeDO tradeOrderByPayType = (TradeOrderByPayTypeDO) params.get("tradeOrderByPayType");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrderByPayType.getPayType())){
            SET("pay_type=#{tradeOrderByPayType.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getTradeDate())){
            SET("trade_date=#{tradeOrderByPayType.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getInnerCode())){
            SET("inner_code=#{tradeOrderByPayType.innerCode}");
        }
        if (tradeOrderByPayType.getTurnover() != null) {
            SET("turnover=#{tradeOrderByPayType.turnover}");
        }
        if (tradeOrderByPayType.getOrderNum() != null) {
            SET("order_num=#{tradeOrderByPayType.orderNum}");
        }
        if (tradeOrderByPayType.getOrderPrice() != null) {
            SET("order_price=#{tradeOrderByPayType.orderPrice}");
        }
        WHERE("id = #{tradeOrderByPayType.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderByPayTypeDO tradeOrderByPayType = (TradeOrderByPayTypeDO) params.get("tradeOrderByPayType");
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
        if (tradeOrderByPayType.getId() != null) {
            WHERE("id=#{tradeOrderByPayType.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getPayType())){
            WHERE("pay_type=#{tradeOrderByPayType.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getTradeDate())){
            WHERE("trade_date=#{tradeOrderByPayType.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getInnerCode())){
            WHERE("inner_code=#{tradeOrderByPayType.innerCode}");
        }
        if (tradeOrderByPayType.getTurnover() != null) {
            WHERE("turnover=#{tradeOrderByPayType.turnover}");
        }
        if (tradeOrderByPayType.getOrderNum() != null) {
            WHERE("order_num=#{tradeOrderByPayType.orderNum}");
        }
        if (tradeOrderByPayType.getOrderPrice() != null) {
            WHERE("order_price=#{tradeOrderByPayType.orderPrice}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderByPayTypeDO tradeOrderByPayType = (TradeOrderByPayTypeDO) params.get("tradeOrderByPayType");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrderByPayType.getId() != null) {
            WHERE("id=#{tradeOrderByPayType.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getPayType())){
            WHERE("pay_type=#{tradeOrderByPayType.payType}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getTradeDate())){
            WHERE("trade_date=#{tradeOrderByPayType.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayType.getInnerCode())){
            WHERE("inner_code=#{tradeOrderByPayType.innerCode}");
        }
        if (tradeOrderByPayType.getTurnover() != null) {
            WHERE("turnover=#{tradeOrderByPayType.turnover}");
        }
        if (tradeOrderByPayType.getOrderNum() != null) {
            WHERE("order_num=#{tradeOrderByPayType.orderNum}");
        }
        if (tradeOrderByPayType.getOrderPrice() != null) {
            WHERE("order_price=#{tradeOrderByPayType.orderPrice}");
        }
        }}.toString();
    }
}

