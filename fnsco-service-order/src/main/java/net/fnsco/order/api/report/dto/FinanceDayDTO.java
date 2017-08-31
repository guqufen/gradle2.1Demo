package net.fnsco.order.api.report.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月30日 上午10:33:48
 */

public class FinanceDayDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 7486355374045146302L;
    /**
     * 交易时间
     */
    private String tradeDate;
    
    /**
     * 交易额
     */
    private String turnover;
    
    /**
     * 手续费
     */
    private String procedureFee;
    
    /**
     * 结算金额
     */
    private String settlementAmount;
    
    /**
     * 订单数量
     */
    private Integer orderNum;
    
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
     * turnover
     *
     * @return  the turnover
     * @since   CodingExample Ver 1.0
    */
    
    public String getTurnover() {
        return turnover;
    }

    /**
     * turnover
     *
     * @param   turnover    the turnover to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    /**
     * procedureFee
     *
     * @return  the procedureFee
     * @since   CodingExample Ver 1.0
    */
    
    public String getProcedureFee() {
        return procedureFee;
    }

    /**
     * procedureFee
     *
     * @param   procedureFee    the procedureFee to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setProcedureFee(String procedureFee) {
        this.procedureFee = procedureFee;
    }

    /**
     * settlementAmount
     *
     * @return  the settlementAmount
     * @since   CodingExample Ver 1.0
    */
    
    public String getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * settlementAmount
     *
     * @param   settlementAmount    the settlementAmount to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setSettlementAmount(String settlementAmount) {
        this.settlementAmount = settlementAmount;
    }
    
}
