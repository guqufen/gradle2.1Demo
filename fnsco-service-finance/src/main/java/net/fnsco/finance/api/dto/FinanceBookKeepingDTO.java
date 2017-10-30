package net.fnsco.finance.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.fnsco.core.base.DTO;

public class FinanceBookKeepingDTO extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer year;//记账年
	
	private Integer month;//记账月
	
	private	BigDecimal totalSpending;//记账总支出
	
	private BigDecimal totalRevenue;//记账总收入
	
	private String entityInnerCode;//实体商户号
	
	private List<AppUserEntityDTO> appUserEntityDTOList;
	
	private List<FinanceEveryDayDTO> financeEveryDay;

	public BigDecimal getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(BigDecimal totalSpending) {
		this.totalSpending = totalSpending;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(BigDecimal totalRevenue) {
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
