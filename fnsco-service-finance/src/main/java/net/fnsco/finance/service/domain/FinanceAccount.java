package net.fnsco.finance.service.domain;

import java.util.Date;

public class FinanceAccount {
    private Integer id;

    private String accountId;

    private String shopInnerCode;

    private Date createTime;

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