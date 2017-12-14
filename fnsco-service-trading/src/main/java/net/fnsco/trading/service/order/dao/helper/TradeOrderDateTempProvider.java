package net.fnsco.trading.service.order.dao.helper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getPayMedium())){
            SET("pay_medium=#{tradeOrderDateTemp.payMedium}");
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
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getPayMedium())){
            WHERE("pay_medium=#{tradeOrderDateTemp.payMedium}");
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
        if (StringUtils.isNotBlank(tradeOrderDateTemp.getPayMedium())){
            WHERE("pay_medium=#{tradeOrderDateTemp.payMedium}");
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
     * @date   2017年12月14日 上午9:47:47
     */
    public String insertBatch(Map<String, Object> params) {
    	List<TradeOrderDateTempDO> lists = (List<TradeOrderDateTempDO>) params.get("list");
    	StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO r_trade_order_date_temp ");  
        sb.append("(id,inner_code,amt,pay_sub_type,time_stamp,trade_date,trade_houre,procedure_fee,pay_medium)");  
        sb.append("VALUES ");  
        MessageFormat mf = new MessageFormat("(null,#{list[{0}].innerCode},#{{list[{0}].amt},#{{list[{0}].paySubType},#{{list[{0}].timeStamp},#{{list[{0}].tradeDate},#{{list[{0}].tradeHoure},#{{list[{0}].procedureFee},#{{list[{0}].payMedium})");  
        for (int i = 0; i < lists.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < lists.size() - 1) {  
                sb.append(",");  
            }  
        }  
        return sb.toString();  
    }
    
    /**
     * selectTradeDataByDay:(查询天数据)
     *
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午2:45:06
     */
    public String selectTradeDataByDay() {
    	 return new SQL() {{
    		 SELECT("SUM(amt) AS turnover,SUM(procedure_fee) AS procedureFee,inner_code AS innerCode,COUNT(*) AS orderNum,cast((SUM(amt) / COUNT(*)) AS DECIMAL (16, 2)) AS orderPrice,trade_date AS tradeDate");
    		 FROM(TABLE_NAME);
    		 GROUP_BY("inner_code,trade_date");
    	 }}.toString();
    }
    
    /**
     * selectTradeDataByPayType:(查询支付方式)
     *
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午3:15:27
     */
    public String selectTradeDataByPayType() {
    	
    	 return new SQL() {{
    		 SELECT("SUM(amt) AS turnover,inner_code AS innerCode,COUNT(*) AS orderNum,pay_sub_type AS payType,cast((SUM(amt) / COUNT(*)) AS DECIMAL (16, 2)) AS orderPrice,trade_date AS tradeDate");
    		 FROM(TABLE_NAME);
    		 GROUP_BY("inner_code,pay_sub_type,trade_date");
    	 }}.toString();
    }
    
    /**
     * selectTradeDataByPayMedium:(按照支付媒介方式统计)
     *
     * @param  @return    设定文件
     * @return String    DOM对象
     * @author tangliang
     * @date   2017年12月14日 下午3:51:57
     */
    public String selectTradeDataByPayMedium() {
    	return new SQL() {{
   		 SELECT("SUM(amt) AS turnover,inner_code AS innerCode,COUNT(*) AS orderNum,pay_medium AS payMedium,cast((SUM(amt) / COUNT(*)) AS DECIMAL (16, 2)) AS orderPrice,trade_date AS tradeDate");
   		 FROM(TABLE_NAME);
   		 GROUP_BY("inner_code,pay_medium,trade_date");
   	 }}.toString();
    }
}

