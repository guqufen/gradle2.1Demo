package net.fnsco.order.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

/**
 * @desc 按照天来统计
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月1日 上午9:58:10
 */

public class TradeDayDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7780504242856353273L;
    /**
     * 交易日期
     */
    private String tradeDate;
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
