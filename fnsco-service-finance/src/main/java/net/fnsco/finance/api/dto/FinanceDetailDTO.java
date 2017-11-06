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
	
	private Integer type;//收支类型
	
	private String ioTypeCode;//收支子类型
	
	private String ioTypeName;//收支子类型名称
	
	private BigDecimal cash;//金额
	
	private String remark;//备注
	
	private String shopInnerCode;//实体店铺编号
	
	private String shopName;//实体店铺名字
	
	private String icoUrl;//图片

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

	public String getIoTypeName() {
		return ioTypeName;
	}

	public void setIoTypeName(String ioTypeName) {
		this.ioTypeName = ioTypeName;
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

	public String getShopInnerCode() {
		return shopInnerCode;
	}

	public void setShopInnerCode(String shopInnerCode) {
		this.shopInnerCode = shopInnerCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}
	
}
