package net.fnsco.trading.service.order.entity;

import java.math.BigDecimal;

public class TradeOrderDateTempDO {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 内部商户号
     */
    private String innerCode;

    /**
     * 金额，格式为“10001”分
     */
    private String amt;

    /**
     * 交易子类型00刷卡01微信02支付宝
     */
    private String paySubType;

    /**
     * 交易时间戳yyyyMMddHHmmss
     */
    private String timeStamp;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 交易时间点（以小时为区间）
     */
    private String tradeHoure;

    /**
     * 手续费用
     */
    private BigDecimal procedureFee;

    /**
     * 支付媒介00pos机01app02台码
     */
    private String payMedium;



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
        this.innerCode = innerCode;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getPaySubType() {
        return paySubType;
    }

    public void setPaySubType(String paySubType) {
        this.paySubType = paySubType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeHoure() {
        return tradeHoure;
    }

    public void setTradeHoure(String tradeHoure) {
        this.tradeHoure = tradeHoure;
    }

    public BigDecimal getProcedureFee() {
        return procedureFee;
    }

    public void setProcedureFee(BigDecimal procedureFee) {
        this.procedureFee = procedureFee;
    }

    /**
	 * payMedium
	 *
	 * @return  the payMedium
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getPayMedium() {
		return payMedium;
	}

	/**
	 * payMedium
	 *
	 * @param   payMedium    the payMedium to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPayMedium(String payMedium) {
		this.payMedium = payMedium;
	}

	@Override
    public String toString() {
        return "[id="+ id + ", innerCode="+ innerCode + ", amt="+ amt + ", paySubType="+ paySubType + ", timeStamp="+ timeStamp + ", tradeDate="+ tradeDate + ", tradeHoure="+ tradeHoure + ", procedureFee="+ procedureFee + ", payMedium="+ payMedium + "]";
    }
}