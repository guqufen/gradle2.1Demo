package net.fnsco.finance.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

public class FinanceDetailDTO  extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private String happenDate;//日期
	
	private String week;//星期
	
	private Integer type;//收支类型
	
	private String ioTypeCode;//收支子类型
	
	private String ioTypeName;//收支子类型名称
	
	private String cash;//金额字符串
	
	private BigDecimal cashDec;//金额
	
	private String remark;//备注
	
	private String shopInnerCode;//实体店铺编号
	
	private String shopName;//实体店铺名字
	
	private String icoUrl;//图片

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
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

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public BigDecimal getCashDec() {
		return cashDec;
	}

	public void setCashDec(BigDecimal cashDec) {
		this.cashDec = cashDec;
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
