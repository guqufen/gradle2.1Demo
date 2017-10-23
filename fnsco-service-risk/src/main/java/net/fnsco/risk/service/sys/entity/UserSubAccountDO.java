package net.fnsco.risk.service.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserSubAccountDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 代理商ID
     */
    private Integer agentId;

    /**
     * 账户余额
     */
    private BigDecimal accountBalance;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", agentId="+ agentId + ", accountBalance="+ accountBalance + ", lastModifyTime="+ lastModifyTime + "]";
    }
}