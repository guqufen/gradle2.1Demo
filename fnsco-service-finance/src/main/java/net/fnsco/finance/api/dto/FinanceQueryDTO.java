package net.fnsco.finance.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

public class FinanceQueryDTO extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer appUserId;//app用户登录id
	
	private String entityInnerCode;//实体商户号
	
	private String dates;//查询日期
	
	private Date startTime;//开始时间
	
	private Date endTime;//结束时间

	public Integer getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
