package net.fnsco.risk.service.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradePayBillDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 交易金额
     */
    private BigDecimal txnAmount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 交易业务id
     */
    private Integer txnBusinnessId;

    /**
     * 交易业务类型
     */
    private String txnBussinessType;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
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

    public Integer getTxnBusinnessId() {
        return txnBusinnessId;
    }

    public void setTxnBusinnessId(Integer txnBusinnessId) {
        this.txnBusinnessId = txnBusinnessId;
    }

    public String getTxnBussinessType() {
        return txnBussinessType;
    }

    public void setTxnBussinessType(String txnBussinessType) {
        this.txnBussinessType = txnBussinessType;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", txnAmount="+ txnAmount + ", remark="+ remark + ", createTime="+ createTime + ", txnBusinnessId="+ txnBusinnessId + ", txnBussinessType="+ txnBussinessType + "]";
    }
}