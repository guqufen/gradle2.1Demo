package net.fnsco.trading.service.order.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.order.entity.TradeOrderByPayMediumDO;
public class TradeOrderByPayMediumProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "r_trade_order_by_pay_medium";

    public String update(Map<String, Object> params) {
        TradeOrderByPayMediumDO tradeOrderByPayMedium = (TradeOrderByPayMediumDO) params.get("tradeOrderByPayMedium");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getPayMedium())){
            SET("pay_medium=#{tradeOrderByPayMedium.payMedium}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getTradeDate())){
            SET("trade_date=#{tradeOrderByPayMedium.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getInnerCode())){
            SET("inner_code=#{tradeOrderByPayMedium.innerCode}");
        }
        if (tradeOrderByPayMedium.getTurnover() != null) {
            SET("turnover=#{tradeOrderByPayMedium.turnover}");
        }
        if (tradeOrderByPayMedium.getOrderNum() != null) {
            SET("order_num=#{tradeOrderByPayMedium.orderNum}");
        }
        if (tradeOrderByPayMedium.getOrderPrice() != null) {
            SET("order_price=#{tradeOrderByPayMedium.orderPrice}");
        }
        WHERE("id = #{tradeOrderByPayMedium.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderByPayMediumDO tradeOrderByPayMedium = (TradeOrderByPayMediumDO) params.get("tradeOrderByPayMedium");
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
        if (tradeOrderByPayMedium.getId() != null) {
            WHERE("id=#{tradeOrderByPayMedium.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getPayMedium())){
            WHERE("pay_medium=#{tradeOrderByPayMedium.payMedium}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getTradeDate())){
            WHERE("trade_date=#{tradeOrderByPayMedium.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getInnerCode())){
            WHERE("inner_code=#{tradeOrderByPayMedium.innerCode}");
        }
        if (tradeOrderByPayMedium.getTurnover() != null) {
            WHERE("turnover=#{tradeOrderByPayMedium.turnover}");
        }
        if (tradeOrderByPayMedium.getOrderNum() != null) {
            WHERE("order_num=#{tradeOrderByPayMedium.orderNum}");
        }
        if (tradeOrderByPayMedium.getOrderPrice() != null) {
            WHERE("order_price=#{tradeOrderByPayMedium.orderPrice}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderByPayMediumDO tradeOrderByPayMedium = (TradeOrderByPayMediumDO) params.get("tradeOrderByPayMedium");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrderByPayMedium.getId() != null) {
            WHERE("id=#{tradeOrderByPayMedium.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getPayMedium())){
            WHERE("pay_medium=#{tradeOrderByPayMedium.payMedium}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getTradeDate())){
            WHERE("trade_date=#{tradeOrderByPayMedium.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByPayMedium.getInnerCode())){
            WHERE("inner_code=#{tradeOrderByPayMedium.innerCode}");
        }
        if (tradeOrderByPayMedium.getTurnover() != null) {
            WHERE("turnover=#{tradeOrderByPayMedium.turnover}");
        }
        if (tradeOrderByPayMedium.getOrderNum() != null) {
            WHERE("order_num=#{tradeOrderByPayMedium.orderNum}");
        }
        if (tradeOrderByPayMedium.getOrderPrice() != null) {
            WHERE("order_price=#{tradeOrderByPayMedium.orderPrice}");
        }
        }}.toString();
    }
    
    /**
     * deleteByCondition:(批量删除数据)
     *
     * @param  @param params
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午2:36:08
     */
    public String deleteByCondition(Map<String, Object> params) {
    	TradeOrderByPayMediumDO tradeOrderByPayMedium = (TradeOrderByPayMediumDO) params.get("tradeOrderByPayMedium");
    	return new SQL() {{
    		DELETE_FROM(TABLE_NAME);
    		if (tradeOrderByPayMedium.getId() != null) {
                WHERE("id=#{tradeOrderByPayMedium.id}");
            }
            if (StringUtils.isNotBlank(tradeOrderByPayMedium.getPayMedium())){
                WHERE("pay_medium=#{tradeOrderByPayMedium.payMedium}");
            }
            if (StringUtils.isNotBlank(tradeOrderByPayMedium.getTradeDate())){
                WHERE("trade_date=#{tradeOrderByPayMedium.tradeDate}");
            }
            if (StringUtils.isNotBlank(tradeOrderByPayMedium.getInnerCode())){
                WHERE("inner_code=#{tradeOrderByPayMedium.innerCode}");
            }
            if (tradeOrderByPayMedium.getTurnover() != null) {
                WHERE("turnover=#{tradeOrderByPayMedium.turnover}");
            }
            if (tradeOrderByPayMedium.getOrderNum() != null) {
                WHERE("order_num=#{tradeOrderByPayMedium.orderNum}");
            }
            if (tradeOrderByPayMedium.getOrderPrice() != null) {
                WHERE("order_price=#{tradeOrderByPayMedium.orderPrice}");
            }
            if(StringUtils.isNotBlank(tradeOrderByPayMedium.getStartTradeDate())) {
            	WHERE("trade_date >= #{tradeOrderByPayMedium.startTradeDate}");
            }
            if(StringUtils.isNotBlank(tradeOrderByPayMedium.getEndTradeDate())) {
            	WHERE("trade_date < #{tradeOrderByPayMedium.endTradeDate}");
            }
    	}}.toString();
    }
}

