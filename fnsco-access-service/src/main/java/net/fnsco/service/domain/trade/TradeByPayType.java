package net.fnsco.service.domain.trade;
/**
 * @desc 按照渠道统计交易营业额
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午9:55:43
 */
public class TradeByPayType {
    private Integer id;

    private String payType;

    private String tradeDate;

    private String innerCode;

    private Long turnover;

    private Integer orderNum;

    private Long orderPrice;
    
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
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

    public Long getTurnover() {
        return turnover;
    }

    public void setTurnover(Long turnover) {
        this.turnover = turnover;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }
}