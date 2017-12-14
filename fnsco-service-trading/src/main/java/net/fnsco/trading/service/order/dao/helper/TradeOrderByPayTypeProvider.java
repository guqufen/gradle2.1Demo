package net.fnsco.trading.service.order.dao.helper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    /**
     * deleteByCondition:(条件删除数据)
     *
     * @param  @param params
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午1:56:12
     */
    public String deleteByCondition(Map<String, Object> params) {
    	TradeOrderByPayTypeDO tradeOrderByPayType = (TradeOrderByPayTypeDO) params.get("tradeOrderByPayType");
    	return new SQL() {{
    		DELETE_FROM(TABLE_NAME);
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
            if(StringUtils.isNotBlank(tradeOrderByPayType.getStartTradeDate())) {
            	WHERE("trade_date >= #{tradeOrderByPayType.startTradeDate}");
            }
            if(StringUtils.isNotBlank(tradeOrderByPayType.getEndTradeDate())) {
            	WHERE("trade_date < #{tradeOrderByPayType.endTradeDate}");
            }
    	}}.toString();
    }
    
    /**
     * insertBatch:(批量插入)
     *
     * @param  @param params
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午3:43:54
     */
    public String insertBatch(Map<String, Object> params) {
    	List<TradeOrderByPayTypeDO> lists = (List<TradeOrderByPayTypeDO>) params.get("list");
    	StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO r_trade_order_by_pay_type ");  
        sb.append("(id,pay_type,trade_date,inner_code,turnover,order_num,order_price)");  
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].id},#'{'list[{0}].payType},#'{'list[{0}].tradeDate},#'{'list[{0}].innerCode},#'{'list[{0}].turnover},#'{'list[{0}].orderNum},#'{'list[{0}].orderPrice})");  
        for (int i = 0; i < lists.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < lists.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }
}

