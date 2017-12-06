package net.fnsco.trading.service.account.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AppAccountBalanceDO {

    /**
     * id
     */
    private Integer id;

    /**
     * app用户ID
     */
    private Integer appUserId;

    /**
     * 账户余额
     */
    private BigDecimal fund;

    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", appUserId="+ appUserId + ", fund="+ fund + ", freezeAmount="+ freezeAmount + ", updateTime="+ updateTime + ", createTime="+ createTime + "]";
    }
}