package net.fnsco.finance.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

public class FinanceEveryDayDTO extends DTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer Day;//记账日
	
	private String week;//记账星期
	
	private	BigDecimal spending;//记账支出
	
	private BigDecimal revenue;//记账收入

	private Integer type;//收支类型0.支出 1.收入
	
	private String ioTypeCode;//收支子类型
	
	private BigDecimal amount;//各项收支金额

	public Integer getDay() {
		return Day;
	}

	public void setDay(Integer day) {
		Day = day;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public BigDecimal getSpending() {
		return spending;
	}

	public void setSpending(BigDecimal spending) {
		this.spending = spending;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIoTypeCode() {
		return ioTypeCode;
	}

	public void setIoTypeCode(String ioTypeCode) {
		this.ioTypeCode = ioTypeCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
