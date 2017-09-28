package net.fnsco.bigdata.service.domain.trade;

import java.math.BigDecimal;

public class TradeStatistics {
	private Integer id;
	/**
	 * 交易日期
	 */
	private String tradeDate;
	/**
	 * 内部商务号
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
	 * 结算商户号
	 */
	private String merId;
	/**
	 * 商户名
	 */
	private String merName;
	/**
	 * 内部终端号
	 */
	private String terminalCode;
	/**
	 * 支付开始时间
	 */
	private String startTime;
	/**
	 * 支付结束时间
	 */
	private String endTime;

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

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
