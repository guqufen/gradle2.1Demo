package net.fnsco.order.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月3日 下午5:27:21
 */

public class TradeHourDTO extends DTO{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -3344772367211768539L;
    /**
     * 交易日期
     */
    private String tradeDate;
    /**
     * 交易时间点
     */
    private String tradeHour;
    /**
     * 笔数
     */
    private Integer orderNum;
    /**
     * 营业额
     */
    private BigDecimal turnover;
    /**
     * tradeDate
     *
     * @return  the tradeDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getTradeDate() {
        return tradeDate;
    }
    /**
     * tradeDate
     *
     * @param   tradeDate    the tradeDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }
    /**
     * tradeHour
     *
     * @return  the tradeHour
     * @since   CodingExample Ver 1.0
    */
    
    public String getTradeHour() {
        return tradeHour;
    }
    /**
     * tradeHour
     *
     * @param   tradeHour    the tradeHour to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeHour(String tradeHour) {
        this.tradeHour = tradeHour;
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
    
    
}
