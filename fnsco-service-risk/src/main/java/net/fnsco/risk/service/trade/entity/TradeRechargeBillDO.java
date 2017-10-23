package net.fnsco.risk.service.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradeRechargeBillDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 充值金额
     */
    private BigDecimal rxeAmount;

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

    public BigDecimal getRxeAmount() {
        return rxeAmount;
    }

    public void setRxeAmount(BigDecimal rxeAmount) {
        this.rxeAmount = rxeAmount;
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
        return "[id="+ id + ", rxeAmount="+ rxeAmount + ", remark="+ remark + ", createTime="+ createTime + "]";
    }
}