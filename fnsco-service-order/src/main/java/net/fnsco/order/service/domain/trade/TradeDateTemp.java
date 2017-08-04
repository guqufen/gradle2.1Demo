package net.fnsco.order.service.domain.trade;
/**
 * @desc 交易流水统计临时表
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午11:29:39
 */
public class TradeDateTemp {
    private Integer id;

    private String innerCode;

    private String amt;

    private String paySubType;

    private String timeStamp;
    
    private String tradeDate;

    private String tradeHoure;
    

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
     * tradeHoure
     *
     * @return  the tradeHoure
     * @since   CodingExample Ver 1.0
    */
    
    public String getTradeHoure() {
        return tradeHoure;
    }

    /**
     * tradeHoure
     *
     * @param   tradeHoure    the tradeHoure to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeHoure(String tradeHoure) {
        this.tradeHoure = tradeHoure;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt == null ? null : amt.trim();
    }

    public String getPaySubType() {
        return paySubType;
    }

    public void setPaySubType(String paySubType) {
        this.paySubType = paySubType == null ? null : paySubType.trim();
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp == null ? null : timeStamp.trim();
    }
}