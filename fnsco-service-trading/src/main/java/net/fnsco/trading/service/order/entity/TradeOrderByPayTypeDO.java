package net.fnsco.trading.service.order.entity;

import java.math.BigDecimal;

public class TradeOrderByPayTypeDO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 支付渠道微信或支付宝
     */
    private String payType;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * app用户ID
     */
    private String innerCode;

    /**
     * 营业额
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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
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



    @Override
    public String toString() {
        return "[id="+ id + ", payType="+ payType + ", tradeDate="+ tradeDate + ", innerCode="+ innerCode + ", turnover="+ turnover + ", orderNum="+ orderNum + ", orderPrice="+ orderPrice + "]";
    }
}