package net.fnsco.finance.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

public class FinanceDetailDTO  extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dates;//日期
	
	private String week;//星期
	
	private String ioTypeCode;//收支子类型
	
	private BigDecimal cash;//金额
	
	private String remark;//备注
	
	private String entityInnerCode;//实体商户编号

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getIoTypeCode() {
		return ioTypeCode;
	}

	public void setIoTypeCode(String ioTypeCode) {
		this.ioTypeCode = ioTypeCode;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}
	
}
