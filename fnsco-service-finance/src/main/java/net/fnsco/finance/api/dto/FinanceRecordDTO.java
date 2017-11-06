package net.fnsco.finance.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import net.fnsco.core.base.DTO;

public class FinanceRecordDTO extends DTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String accountId;

    private BigDecimal cash;//金额

    private Integer type;//收支类型0.支出 1.收入

    private String ioTypeCode;//收支子类型

    private String remark;//备注

    private Date createTime;//创建时间

    private Date lastModefyTime;//最后修改时间
    
    private String happenDate;//账单发生日期YYYY-MM-DD

    private String lastModefyTimeStr;//最后修改时间字符串
    
    private String shopInnerCode; //实体店铺编号
    
    private String entityInnerCode;//实体商户号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModefyTime() {
		return lastModefyTime;
	}

	public void setLastModefyTime(Date lastModefyTime) {
		this.lastModefyTime = lastModefyTime;
	}

	public String getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}

	public String getLastModefyTimeStr() {
		return lastModefyTimeStr;
	}

	public void setLastModefyTimeStr(String lastModefyTimeStr) {
		this.lastModefyTimeStr = lastModefyTimeStr;
	}

	public String getShopInnerCode() {
		return shopInnerCode;
	}

	public void setShopInnerCode(String shopInnerCode) {
		this.shopInnerCode = shopInnerCode;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}
}
	