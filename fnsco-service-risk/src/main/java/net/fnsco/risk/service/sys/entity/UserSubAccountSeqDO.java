package net.fnsco.risk.service.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserSubAccountSeqDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
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



    @Override
    public String toString() {
        return "[id="+ id + ", accountBalance="+ accountBalance + ", remark="+ remark + ", createTime="+ createTime + "]";
    }
}