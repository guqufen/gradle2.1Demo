package net.fnsco.trading.service.order.dao.helper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.order.entity.TradeOrderByDayDO;
public class TradeOrderByDayProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "r_trade_order_by_day";

    public String update(Map<String, Object> params) {
        TradeOrderByDayDO tradeOrderByDay = (TradeOrderByDayDO) params.get("tradeOrderByDay");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(tradeOrderByDay.getTradeDate())){
            SET("trade_date=#{tradeOrderByDay.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByDay.getInnerCode())){
            SET("inner_code=#{tradeOrderByDay.innerCode}");
        }
        if (tradeOrderByDay.getTurnover() != null) {
            SET("turnover=#{tradeOrderByDay.turnover}");
        }
        if (tradeOrderByDay.getOrderNum() != null) {
            SET("order_num=#{tradeOrderByDay.orderNum}");
        }
        if (tradeOrderByDay.getOrderPrice() != null) {
            SET("order_price=#{tradeOrderByDay.orderPrice}");
        }
        if (tradeOrderByDay.getProcedureFee() != null) {
            SET("procedure_fee=#{tradeOrderByDay.procedureFee}");
        }
        if (tradeOrderByDay.getCreateTime() != null) {
            SET("create_time=#{tradeOrderByDay.createTime}");
        }
        WHERE("id = #{tradeOrderByDay.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        TradeOrderByDayDO tradeOrderByDay = (TradeOrderByDayDO) params.get("tradeOrderByDay");
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
        if (tradeOrderByDay.getId() != null) {
            WHERE("id=#{tradeOrderByDay.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderByDay.getTradeDate())){
            WHERE("trade_date=#{tradeOrderByDay.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByDay.getInnerCode())){
            WHERE("inner_code=#{tradeOrderByDay.innerCode}");
        }
        if (tradeOrderByDay.getTurnover() != null) {
            WHERE("turnover=#{tradeOrderByDay.turnover}");
        }
        if (tradeOrderByDay.getOrderNum() != null) {
            WHERE("order_num=#{tradeOrderByDay.orderNum}");
        }
        if (tradeOrderByDay.getOrderPrice() != null) {
            WHERE("order_price=#{tradeOrderByDay.orderPrice}");
        }
        if (tradeOrderByDay.getProcedureFee() != null) {
            WHERE("procedure_fee=#{tradeOrderByDay.procedureFee}");
        }
        if (tradeOrderByDay.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrderByDay.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        TradeOrderByDayDO tradeOrderByDay = (TradeOrderByDayDO) params.get("tradeOrderByDay");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (tradeOrderByDay.getId() != null) {
            WHERE("id=#{tradeOrderByDay.id}");
        }
        if (StringUtils.isNotBlank(tradeOrderByDay.getTradeDate())){
            WHERE("trade_date=#{tradeOrderByDay.tradeDate}");
        }
        if (StringUtils.isNotBlank(tradeOrderByDay.getInnerCode())){
            WHERE("inner_code=#{tradeOrderByDay.innerCode}");
        }
        if (tradeOrderByDay.getTurnover() != null) {
            WHERE("turnover=#{tradeOrderByDay.turnover}");
        }
        if (tradeOrderByDay.getOrderNum() != null) {
            WHERE("order_num=#{tradeOrderByDay.orderNum}");
        }
        if (tradeOrderByDay.getOrderPrice() != null) {
            WHERE("order_price=#{tradeOrderByDay.orderPrice}");
        }
        if (tradeOrderByDay.getProcedureFee() != null) {
            WHERE("procedure_fee=#{tradeOrderByDay.procedureFee}");
        }
        if (tradeOrderByDay.getCreateTime() != null) {
            WHERE("create_time=#{tradeOrderByDay.createTime}");
        }
        }}.toString();
    }
    
    /**
     * deleteByCondition:(按照条件删除数据)
     *
     * @param  @param params
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午1:38:00
     */
    public String deleteByCondition(Map<String, Object> params) {
    	
    	TradeOrderByDayDO tradeOrderByDay = (TradeOrderByDayDO) params.get("tradeOrderByDay");
    	 return new SQL() {{
    		 DELETE_FROM(TABLE_NAME);
    		 if (tradeOrderByDay.getId() != null) {
    	            WHERE("id=#{tradeOrderByDay.id}");
    	        }
    	        if (StringUtils.isNotBlank(tradeOrderByDay.getTradeDate())){
    	            WHERE("trade_date=#{tradeOrderByDay.tradeDate}");
    	        }
    	        if (StringUtils.isNotBlank(tradeOrderByDay.getInnerCode())){
    	            WHERE("inner_code=#{tradeOrderByDay.innerCode}");
    	        }
    	        if (tradeOrderByDay.getTurnover() != null) {
    	            WHERE("turnover=#{tradeOrderByDay.turnover}");
    	        }
    	        if (tradeOrderByDay.getOrderNum() != null) {
    	            WHERE("order_num=#{tradeOrderByDay.orderNum}");
    	        }
    	        if (tradeOrderByDay.getOrderPrice() != null) {
    	            WHERE("order_price=#{tradeOrderByDay.orderPrice}");
    	        }
    	        if (tradeOrderByDay.getProcedureFee() != null) {
    	            WHERE("procedure_fee=#{tradeOrderByDay.procedureFee}");
    	        }
    	        if (tradeOrderByDay.getCreateTime() != null) {
    	            WHERE("create_time=#{tradeOrderByDay.createTime}");
    	        }
    	        if(StringUtils.isNotBlank(tradeOrderByDay.getStartTradeDate())) {
    	        	WHERE("trade_date >= #{tradeOrderByDay.startTradeDate}");
    	        }
    	        if(StringUtils.isNotBlank(tradeOrderByDay.getEndTradeDate())) {
    	        	WHERE("trade_date < #{tradeOrderByDay.endTradeDate}");
    	        }
    	 }}.toString();
    }
    
    /**
     * insertBatch:(批量插入数据)
     *
     * @param  @param params
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午3:10:20
     */
    public String insertBatch(Map<String, Object> params) {
    	List<TradeOrderByDayDO> lists = (List<TradeOrderByDayDO>) params.get("list");
    	StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO r_trade_order_by_day ");  
        sb.append("(id,trade_date,inner_code,turnover,order_num,order_price,procedure_fee,create_time)");  
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(#{list[{0}].id},#{list[{0}].tradeDate},#{list[{0}].innerCode},#{list[{0}].turnover},#{list[{0}].orderNum},#{list[{0}].orderPrice},#{list[{0}].procedureFee},#{list[{0}].createTime})");  
        for (int i = 0; i < lists.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < lists.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }
}

