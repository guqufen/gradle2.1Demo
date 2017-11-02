package net.fnsco.finance.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

public class QueryDetailDTO extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String entityInnerCode;//实体商户号
	
	private Integer type;//收支类型0.支出 1.收入
	
	private Integer ioTypeCode;//收支子类型
	
	private String financeDateStr;//查询内容日期
	
	private Date  startTime;//开始时间

	private Date  endTime;//结束时间
	
	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIoTypeCode() {
		return ioTypeCode;
	}

	public void setIoTypeCode(Integer ioTypeCode) {
		this.ioTypeCode = ioTypeCode;
	}

	public String getFinanceDateStr() {
		return financeDateStr;
	}

	public void setFinanceDateStr(String financeDateStr) {
		this.financeDateStr = financeDateStr;
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
