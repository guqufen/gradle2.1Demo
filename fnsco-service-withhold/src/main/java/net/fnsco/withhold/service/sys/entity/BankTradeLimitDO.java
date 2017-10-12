package net.fnsco.withhold.service.sys.entity;


public class BankTradeLimitDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 银行代码
     */
    private String bankCode;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 单笔限额（万）
     */
    private String tradeTimesLimit;

    /**
     * 日限额（万）
     */
    private String tradeDayLimit;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTradeTimesLimit() {
        return tradeTimesLimit;
    }

    public void setTradeTimesLimit(String tradeTimesLimit) {
        this.tradeTimesLimit = tradeTimesLimit;
    }

    public String getTradeDayLimit() {
        return tradeDayLimit;
    }

    public void setTradeDayLimit(String tradeDayLimit) {
        this.tradeDayLimit = tradeDayLimit;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", bankCode="+ bankCode + ", bankName="+ bankName + ", tradeTimesLimit="+ tradeTimesLimit + ", tradeDayLimit="+ tradeDayLimit + "]";
    }
}