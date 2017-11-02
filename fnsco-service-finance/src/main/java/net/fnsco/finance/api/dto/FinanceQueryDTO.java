package net.fnsco.finance.api.dto;

import net.fnsco.core.base.DTO;

public class FinanceQueryDTO extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appUserId;//app用户登录id
	
	private String entityInnerCode;//实体商户号
	
	private String dates;//查询日期
	
	private String happenDate;//账单发生日期YYYY-MM-DD
	
	private String startTime;//开始时间
	
	private String endTime;//结束时间

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
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

	public String getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}
	
}
