package net.fnsco.finance.service.domain;

import java.util.Date;

public class FinanceAccount {
    private Integer id;

    private Integer accountId;

    private String shopInnerCode;

    private Date createTime;

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

    public String getShopInnerCode() {
        return shopInnerCode;
    }

    public void setShopInnerCode(String shopInnerCode) {
        this.shopInnerCode = shopInnerCode == null ? null : shopInnerCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}