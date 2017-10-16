package net.fnsco.order.service.domain.trade;

import java.math.BigDecimal;
/**
 * @desc 按照终端号、天统计
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date     2017年10月13日 下午3:33:07
 */
public class TradeTerminalByDay {
    private Integer id;

    private String tradeDate;

    private String innerCode;

    private String terminalCode;

    private BigDecimal turnover;

    private Integer orderNum;

    private BigDecimal orderPrice;

    private BigDecimal procedureFee;
    
    private String startDate;//条件查询开始日期
    private String endDate;//条件查询结束日期
    
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getProcedureFee() {
        return procedureFee;
    }

    public void setProcedureFee(BigDecimal procedureFee) {
        this.procedureFee = procedureFee;
    }
}