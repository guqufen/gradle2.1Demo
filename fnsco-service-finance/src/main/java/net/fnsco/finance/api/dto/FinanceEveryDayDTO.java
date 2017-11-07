package net.fnsco.finance.api.dto;

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
	
	private	String spending;//记账支出
	
	private String revenue;//记账收入
	
	private	String spendingStr;//记账支出字符串
	
	private String revenueStr;//记账收入字符串
	
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

	public String getSpendingStr() {
		return spendingStr;
	}

	public void setSpendingStr(String spendingStr) {
		this.spendingStr = spendingStr;
	}

	public String getRevenueStr() {
		return revenueStr;
	}

	public void setRevenueStr(String revenueStr) {
		this.revenueStr = revenueStr;
	}


	public String getSpending() {
		return spending;
	}

	public void setSpending(String spending) {
		this.spending = spending;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public List<FinanceAccountBook> getAccountBook() {
		return accountBook;
	}

	public void setAccountBook(List<FinanceAccountBook> accountBook) {
		this.accountBook = accountBook;
	}

}
