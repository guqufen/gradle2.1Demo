package net.fnsco.finance.service.domain;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceAccountBook {
    private Integer id;

    private String accountId;

    private String cash;
    
    private BigDecimal cashDec;
    
    private Integer type;

    private String ioTypeCode;
    
    private String ioTypeName;

    private String remark;

    private Date createTime;

    private Date lastModefyTime;

    private String happenDate;
    
    private String icoUrl;
    
    private String icoUrlGray;
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
        this.ioTypeCode = ioTypeCode == null ? null : ioTypeCode.trim();
    }

    public String getIoTypeName() {
		return ioTypeName;
	}

	public void setIoTypeName(String ioTypeName) {
		this.ioTypeName = ioTypeName;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}

	public String getIcoUrlGray() {
		return icoUrlGray;
	}

	public void setIcoUrlGray(String icoUrlGray) {
		this.icoUrlGray = icoUrlGray;
	}
    
}