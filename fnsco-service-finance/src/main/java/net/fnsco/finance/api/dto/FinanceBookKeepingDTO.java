package net.fnsco.finance.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class FinanceBookKeepingDTO extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer year;//记账年
	
	private Integer month;//记账月
	
	private	String totalSpending;//记账总支出
	
	private String totalRevenue;//记账总收入
	
	private String entityInnerCode;//实体商户号
	
	private String mercName;//商户名（必须40个字符）;
	
	private List<AppUserEntityDTO> appUserEntityDTOList;
	
	private List<FinanceEveryDayDTO> financeEveryDay;

	public String getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(String totalSpending) {
		this.totalSpending = totalSpending;
	}

	public String getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getMercName() {
		return mercName;
	}

	public void setMercName(String mercName) {
		this.mercName = mercName;
	}

	public List<AppUserEntityDTO> getAppUserEntityDTOList() {
		return appUserEntityDTOList;
	}

	public void setAppUserEntityDTOList(List<AppUserEntityDTO> appUserEntityDTOList) {
		this.appUserEntityDTOList = appUserEntityDTOList;
	}

	public List<FinanceEveryDayDTO> getFinanceEveryDay() {
		return financeEveryDay;
	}

	public void setFinanceEveryDay(List<FinanceEveryDayDTO> financeEveryDay) {
		this.financeEveryDay = financeEveryDay;
	}
	
}
