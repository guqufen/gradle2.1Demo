package net.fnsco.trading.service.order.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.order.entity.TradeOrderExtDO;
public class TradeOrderExtProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_trade_order_ext";

    public String update(Map<String, Object> params) {
        TradeOrderExtDO tradeOrderExt = (TradeOrderExtDO) params.get("tradeOrderExt");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrderExt.getOrderNo())){
            SET("order_no=#{tradeOrderExt.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getName())){
            SET("name=#{tradeOrderExt.name}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getMobile())){
            SET("mobile=#{tradeOrderExt.mobile}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getCard())){
            SET("card=#{tradeOrderExt.card}");
        }
        if (tradeOrderExt.getCreateTime() != null) {
            SET("create_time=#{tradeOrderExt.createTime}");
        }
        WHERE("id = #{tradeOrderExt.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderExtDO tradeOrderExt = (TradeOrderExtDO) params.get("tradeOrderExt");
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
        if (tradeOrderExt.getId() != null) {
            WHERE("id=#{tradeOrderExt.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getOrderNo())){
            WHERE("order_no=#{tradeOrderExt.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getName())){
            WHERE("name=#{tradeOrderExt.name}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getMobile())){
            WHERE("mobile=#{tradeOrderExt.mobile}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getCard())){
            WHERE("card=#{tradeOrderExt.card}");
        }
        if (tradeOrderExt.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrderExt.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderExtDO tradeOrderExt = (TradeOrderExtDO) params.get("tradeOrderExt");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrderExt.getId() != null) {
            WHERE("id=#{tradeOrderExt.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getOrderNo())){
            WHERE("order_no=#{tradeOrderExt.orderNo}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getName())){
            WHERE("name=#{tradeOrderExt.name}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getMobile())){
            WHERE("mobile=#{tradeOrderExt.mobile}");
        }
        if (StringUtils.isNotBlank(tradeOrderExt.getCard())){
            WHERE("card=#{tradeOrderExt.card}");
        }
        if (tradeOrderExt.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrderExt.createTime}");
        }
        }}.toString();
    }
}

