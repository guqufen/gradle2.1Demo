package net.fnsco.finance.service.domain;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceAccountBook {
    private Integer id;

    private Integer accountId;

    private BigDecimal cash;

    private Integer type;

    private String ioTypeCode;

    private String remark;

    private Date createTime;

    private Date lastModefyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
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
        this.ioTypeCode = ioTypeCode == null ? null : ioTypeCode.trim();
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
}