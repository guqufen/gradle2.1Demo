package net.fnsco.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc 周报数据结构
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月1日 上午9:47:47
 */

public class WeeklyDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 1244105432684095285L;
    /**
     * 商家innerCode
     */
    private String innerCode;
    /**
     * 查询开始时间
     */
    private String startDate;
    /**
     * 查询结束时间
     */
    private String endDate;
    /**
     * 总营业额
     */
    private BigDecimal turnover;
    /**
     * 订单数
     */
    private Integer orderNum;
    /**
     * 客单价
     */
    private BigDecimal orderPrice;
    /**
     * 支付渠道返回数据
     */
    private List<TradeTypeDTO> tradeTypeData;
    /**
     * 按照天统计数据
     */
    private List<TradeDayDTO> tradeDayData;
    /**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getInnerCode() {
        return innerCode;
    }
    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
    /**
     * startDate
     *
     * @return  the startDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getStartDate() {
        return startDate;
    }
    /**
     * startDate
     *
     * @param   startDate    the startDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * endDate
     *
     * @return  the endDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getEndDate() {
        return endDate;
    }
    /**
     * endDate
     *
     * @param   endDate    the endDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * turnover
     *
     * @return  the turnover
     * @since   CodingExample Ver 1.0
    */
    
    public BigDecimal getTurnover() {
        return turnover;
    }
    /**
     * turnover
     *
     * @param   turnover    the turnover to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }
    /**
     * orderNum
     *
     * @return  the orderNum
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getOrderNum() {
        return orderNum;
    }
    /**
     * orderNum
     *
     * @param   orderNum    the orderNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**
     * orderPrice
     *
     * @return  the orderPrice
     * @since   CodingExample Ver 1.0
    */
    
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
    /**
     * orderPrice
     *
     * @param   orderPrice    the orderPrice to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
    /**
     * tradeTypeData
     *
     * @return  the tradeTypeData
     * @since   CodingExample Ver 1.0
    */
    
    public List<TradeTypeDTO> getTradeTypeData() {
        return tradeTypeData;
    }
    /**
     * tradeTypeData
     *
     * @param   tradeTypeData    the tradeTypeData to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeTypeData(List<TradeTypeDTO> tradeTypeData) {
        this.tradeTypeData = tradeTypeData;
    }
    /**
     * tradeDayData
     *
     * @return  the tradeDayData
     * @since   CodingExample Ver 1.0
    */
    
    public List<TradeDayDTO> getTradeDayData() {
        return tradeDayData;
    }
    /**
     * tradeDayData
     *
     * @param   tradeDayData    the tradeDayData to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeDayData(List<TradeDayDTO> tradeDayData) {
        this.tradeDayData = tradeDayData;
    }
    
}
