package net.fnsco.bigdata.service.withdraw.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradeWithdrawDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 订单id
     */
    private String orderNo;

    /**
     * 原订单id
     */
    private String originalOrderNo;

    /**
     * 账号ID
     */
    private Integer appUserId;

    /**
     * 交易金额(分)
     */
    private BigDecimal amount;

    /**
     * 手续费(分)
     */
    private BigDecimal fee;

    /**
     * 清算金额（扣除手续费后的交易金额）
     */
    private BigDecimal settleMoney;

    /**
     * 余额(分)
     */
    private BigDecimal fund;

    /**
     * 交易类型 1提现/2收入/3新人红包/4预约提现/5提现手续费
     */
    private Integer tradeType;

    /**
     * 状态 0未提现1提现中2失败3成功
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 应答码
     */
    private String respCode;

    /**
     * 应答信息
     */
    private String respMsg;

    /**
     * 预约提现日期yyyy-MM-dd
     */
    private String paymentDate;

    /**
     * 交易成功时间
     */
    private String succTime;

    /**
     * 后台通知地址
     */
    private String backUrl;

    /**
     * 账户类型0对私1对公
     */
    private String bankAccountType;

    /**
     * 开户账号
     */
    private String bankAccountNo;

    /**
     * 账户开户名
     */
    private String bankAccountName;

    /**
     * 开户人身份证号
     */
    private String bankAccountCardId;

    /**
     * 支行名称
     */
    private String bankSubBankName;

    /**
     * 开户行
     */
    private String bankOpenBank;

    /**
     * 开户行行号
     */
    private String bankOpenBankNum;

    /**
     * 开户手机号
     */
    private String bankAccountPhone;

    /**
     * 渠道商户号
     */
    private String channelMerId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOriginalOrderNo() {
        return originalOrderNo;
    }

    public void setOriginalOrderNo(String originalOrderNo) {
        this.originalOrderNo = originalOrderNo;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSuccTime() {
        return succTime;
    }

    public void setSuccTime(String succTime) {
        this.succTime = succTime;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountCardId() {
        return bankAccountCardId;
    }

    public void setBankAccountCardId(String bankAccountCardId) {
        this.bankAccountCardId = bankAccountCardId;
    }

    public String getBankSubBankName() {
        return bankSubBankName;
    }

    public void setBankSubBankName(String bankSubBankName) {
        this.bankSubBankName = bankSubBankName;
    }

    public String getBankOpenBank() {
        return bankOpenBank;
    }

    public void setBankOpenBank(String bankOpenBank) {
        this.bankOpenBank = bankOpenBank;
    }

    public String getBankOpenBankNum() {
        return bankOpenBankNum;
    }

    public void setBankOpenBankNum(String bankOpenBankNum) {
        this.bankOpenBankNum = bankOpenBankNum;
    }

    public String getBankAccountPhone() {
        return bankAccountPhone;
    }

    public void setBankAccountPhone(String bankAccountPhone) {
        this.bankAccountPhone = bankAccountPhone;
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", orderNo="+ orderNo + ", originalOrderNo="+ originalOrderNo + ", appUserId="+ appUserId + ", amount="+ amount + ", fee="+ fee + ", settleMoney="+ settleMoney + ", fund="+ fund + ", tradeType="+ tradeType + ", status="+ status + ", createTime="+ createTime + ", updateTime="+ updateTime + ", respCode="+ respCode + ", respMsg="+ respMsg + ", paymentDate="+ paymentDate + ", succTime="+ succTime + ", backUrl="+ backUrl + ", bankAccountType="+ bankAccountType + ", bankAccountNo="+ bankAccountNo + ", bankAccountName="+ bankAccountName + ", bankAccountCardId="+ bankAccountCardId + ", bankSubBankName="+ bankSubBankName + ", bankOpenBank="+ bankOpenBank + ", bankOpenBankNum="+ bankOpenBankNum + ", bankAccountPhone="+ bankAccountPhone + ", channelMerId="+ channelMerId + "]";
    }
}