package net.fnsco.withhold.service.withhold.entity;

import java.math.BigDecimal;

public class WithholdLogDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 代扣ID
     */
    private Integer withholdId;

    /**
     * 扣款金额
     */
    private BigDecimal amount;

    /**
     * 状态0扣款失败1扣款成功2补收
     */
    private Integer status;

    /**
     * 扣款失败原因
     */
    private String failReason;

    /**
     * 扣款日期
     */
    private String debitDay;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWithholdId() {
        return withholdId;
    }

    public void setWithholdId(Integer withholdId) {
        this.withholdId = withholdId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getDebitDay() {
        return debitDay;
    }

    public void setDebitDay(String debitDay) {
        this.debitDay = debitDay;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", withholdId="+ withholdId + ", amount="+ amount + ", status="+ status + ", failReason="+ failReason + ", debitDay="+ debitDay + "]";
    }
}