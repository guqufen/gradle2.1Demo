package net.fnsco.withhold.service.trade.entity;

import java.math.BigDecimal;

public class TradeDataDO {

    /**
     * 
     */
    private Integer    id;

    /**
     * 代扣ID
     */
    private Integer    withholdId;

    /**
     * 扣款金额
     */
    private BigDecimal txnAmt;

    /**
     * 状态0扣款失败1扣款成功2补收
     */
    private Integer    status;

    /**
     * 扣款失败原因
     */
    private String     failReason;

    /**
     * 姓名
     */
    private String     userName;

    /**
     * 手机号
     */
    private String     mobile;

    /**
     * 银行卡号
     */
    private String     bankCard;

    /**
     * 开户支行名
     */
    private String     subBankName;

    /**
     * 爱农银行编号
     */
    private String     anBankId;

    /**
     * 账户类型00：对公 01：对私
     */
    private String     accountType;

    /**
     * 交易类型
     */
    private String     txnType;

    /**
     * 交易子类型
     */
    private String     txnSubType;

    /**
     * 产品类型
     */
    private String     bizType;

    /**
     * 接入类型
     */
    private String     accessType;

    /**
     * 接收交易金额的商户号
     */
    private String     merId;

    /**
     * 订单id
     */
    private String     orderSn;

    /**
     * 订单发送时间YYYYMMDDhhmmss
     */
    private String     txnTime;

    /**
     * 交易币种
     */
    private String     currency;

    /**
     * 后台通知地址
     */
    private String     backUrl;

    /**
     * 支付方式0401代付
     */
    private String     payType;

    /**
     * 商品标题
     */
    private String     subject;

    /**
     * 商品描述
     */
    private String     body;

    /**
     * 银行卡验证信息及身份信息
     */
    private String     customerInfo;

    /**
     * 用途
     */
    private String     purpose;

    /**
     * 应答码
     */
    private String     respCode;

    /**
     * 应答信息
     */
    private String     respMsg;

    /**
     * 证件类型01：身份证02：军官证03：护照04：回乡证05：台胞证06：警官证07：士兵证99：其它证件
    
     */
    private String     certifType;

    /**
     * 证件号码
     */
    private String     certifyId;
    /**
     * 账号类型：01：借记卡03：存折04：公司账号
     */
    private String     accType;
    /**
     * 扣款次数
     */
    private Integer payTimes;
    
    /**
     * payTimes
     *
     * @return  the payTimes
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getPayTimes() {
        return payTimes;
    }

    /**
     * payTimes
     *
     * @param   payTimes    the payTimes to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPayTimes(Integer payTimes) {
        this.payTimes = payTimes;
    }

    /**
     * accType
     *
     * @return  the accType
     * @since   CodingExample Ver 1.0
    */
    
    public String getAccType() {
        return accType;
    }

    /**
     * accType
     *
     * @param   accType    the accType to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAccType(String accType) {
        this.accType = accType;
    }

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

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
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

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
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

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public String getCertifType() {
        return certifType;
    }

    public void setCertifType(String certifType) {
        this.certifType = certifType;
    }

    public String getCertifyId() {
        return certifyId;
    }

    public void setCertifyId(String certifyId) {
        this.certifyId = certifyId;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", withholdId=" + withholdId + ", txnAmt=" + txnAmt + ", status=" + status + ", failReason=" + failReason + ", userName=" + userName + ", mobile=" + mobile + ", bankCard="
               + bankCard + ", subBankName=" + subBankName + ", anBankId=" + anBankId + ", accountType=" + accountType + ", txnType=" + txnType + ", txnSubType=" + txnSubType + ", bizType=" + bizType
               + ", accessType=" + accessType + ", merId=" + merId + ", orderSn=" + orderSn + ", txnTime=" + txnTime + ", currency=" + currency + ", backUrl=" + backUrl + ", payType=" + payType
               + ", subject=" + subject + ", body=" + body + ", customerInfo=" + customerInfo + ", purpose=" + purpose + ", respCode=" + respCode + ", respMsg=" + respMsg + ", certifType="
               + certifType + ", certifyId=" + certifyId + "]";
    }
}