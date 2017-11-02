package net.fnsco.finance.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.fnsco.core.base.DTO;
import net.fnsco.finance.service.domain.FinanceAccountBook;

public class FinanceEveryDayDTO extends DTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//编号

	private Integer Day;//记账日
	
	private String week;//记账星期
	
	private	BigDecimal spending;//记账支出
	
	private BigDecimal revenue;//记账收入
	
	private List<FinanceAccountBook> accountBook;//每项收支信息

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<FinanceAccountBook> getAccountBook() {
		return accountBook;
	}

	public void setAccountBook(List<FinanceAccountBook> accountBook) {
		this.accountBook = accountBook;
	}

}
