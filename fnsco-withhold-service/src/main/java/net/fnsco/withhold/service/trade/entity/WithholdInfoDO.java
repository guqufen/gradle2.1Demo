package net.fnsco.withhold.service.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WithholdInfoDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 身份证号
     */
    private String cardNum;

    /**
     * 扣款日
     */
    private String debitDay;

    /**
     * 扣款金额/次
     */
    private BigDecimal amount;

    /**
     * 扣款总额
     */
    private BigDecimal amountTotal;

    /**
     * 银行卡号
     */
    private String bankCard;

    /**
     * 状态0终止1进行2已完成
     */
    private Integer status;

    /**
     * 最后提交人
     */
    private Integer modifyUserId;

    /**
     * 最后提交时间
     */
    private Date modifyTime;

    /**
     * 扣款次数
     */
    private Integer total;

    /**
     * 开户支行名
     */
    private String subBankName;

    /**
     * 爱农银行编号
     */
    private String anBankId;

    /**
     * 账户类型00：对公 01：对私
     */
    private String accountType;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getDebitDay() {
        return debitDay;
    }

    public void setDebitDay(String debitDay) {
        this.debitDay = debitDay;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    public String getAnBankId() {
        return anBankId;
    }

    public void setAnBankId(String anBankId) {
        this.anBankId = anBankId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", userName="+ userName + ", mobile="+ mobile + ", cardNum="+ cardNum + ", debitDay="+ debitDay + ", amount="+ amount + ", amountTotal="+ amountTotal + ", bankCard="+ bankCard + ", status="+ status + ", modifyUserId="+ modifyUserId + ", modifyTime="+ modifyTime + ", total="+ total + ", subBankName="+ subBankName + ", anBankId="+ anBankId + ", accountType="+ accountType + "]";
    }
}