package net.fnsco.order.service.trade.entity;

import java.util.Date;

public class TradeDataLklDO {

    /**
     * UUID
     */
    private String id;

    /**
     * 内部商户号
     */
    private String innerCode;

    /**
     * 交易类型1消费2撤销
     */
    private String txnType;

    /**
     * 交易子类型
     */
    private String txnSubType;

    /**
     * 交易币种
     */
    private String currency;

    /**
     * 支付超时时间YYYYMMDDhhmmss
     */
    private String payTimeOut;

    /**
     * 商品标题
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 银行卡验证信息及身份信息
     */
    private String customerInfo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 持卡人IP
     */
    private String customerIp;

    /**
     * 受理订单号
     */
    private String tn;

    /**
     * 应答码1001成功1002失败
     */
    private String respCode;

    /**
     * 应答信息
     */
    private String respMsg;

    /**
     * 清算金额
     */
    private String settleAmount;

    /**
     * 清算币种
     */
    private String settleCurrency;

    /**
     * 清算日期YYYYMMDDhhmmss
     */
    private String settleDate;

    /**
     * 交易成功时间
     */
    private String succTime;

    /**
     * 原商户订单号
     */
    private String orgMerOrderId;

    /**
     * 可退金额
     */
    private String canRefAmt;

    /**
     * 累计退款次数
     */
    private String refCnt;

    /**
     * 累计退款金额
     */
    private String refAmt;

    /**
     * 银行编号
     */
    private String bankId;

    /**
     * 对公对私标志
     */
    private String ppFlag;

    /**
     * 用途
     */
    private String purpose;

    /**
     * 借贷类型00 境内借记卡 ；01 境内贷记卡 ； 60 境外借记卡 ； 61 境外贷记卡
     */
    private String dcType;

    /**
     * 持卡人卡号
     */
    private String certifyId;

    /**
     * 通道号
     */
    private String msgDestId;

    /**
     * 刷卡人人名称
     */
    private String customerNm;

    /**
     * 刷卡人电话
     */
    private String phoneNo;

    /**
     * 商户微信公众号里边给用户分配的id
     */
    private String subOpenId;

    /**
     * 渠道类型来源00拉卡拉01浦发02爱农03法奈昇04聚惠分05中信银行90富友
     */
    private String channelType;

    /**
     * 金额，格式为“10001”分
     */
    private String amt;

    /**
     * 订单号（不唯一）
     */
    private String orderNo;

    /**
     * 订单时间戳
     */
    private String orderTime;

    /**
     * 订单信息 
     */
    private String orderInfo;

    /**
     * 支付方式00刷卡01二维码02分期付
     */
    private String payType;

    /**
     * 交易子类型00刷卡01微信02支付宝03聚惠分
     */
    private String paySubType;

    /**
     * 交易时间戳yyyyMMddHHmmss
     */
    private String timeStamp;

    /**
     * 交易详情（JSON串）
     */
    private String tradeDetail;

    /**
     * 结算商户号
     */
    private String merId;

    /**
     * 内部终端号，用于多商户对应同一个商户的情况
     */
    private String termId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 凭证号
     */
    private String traceNo;

    /**
     * 参考号
     */
    private String referNo;

    /**
     * 授权码
     */
    private String authCode;

    /**
     * 扫码交易的订单号
     */
    private String orderIdScan;

    /**
     * 来源00拉卡拉01导入02同步03法奈昇04浦发
     */
    private String source;

    /**
     * md5唯一标识
     */
    private String md5;

    /**
     * 交易传送时间
     */
    private String sendTime;

    /**
     * 交易创建时间
     */
    private Date createTime;

    /**
     * 交易状态0非正常交易（包括撤销交易和撤销原交易）1正常交易
     */
    private String status;

    /**
     * 支付媒介00pos机01app02台码
     */
    private String payMedium;

    /**
     * 渠道终端号
     */
    private String channelTermCode;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayTimeOut() {
        return payTimeOut;
    }

    public void setPayTimeOut(String payTimeOut) {
        this.payTimeOut = payTimeOut;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
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

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getSuccTime() {
        return succTime;
    }

    public void setSuccTime(String succTime) {
        this.succTime = succTime;
    }

    public String getOrgMerOrderId() {
        return orgMerOrderId;
    }

    public void setOrgMerOrderId(String orgMerOrderId) {
        this.orgMerOrderId = orgMerOrderId;
    }

    public String getCanRefAmt() {
        return canRefAmt;
    }

    public void setCanRefAmt(String canRefAmt) {
        this.canRefAmt = canRefAmt;
    }

    public String getRefCnt() {
        return refCnt;
    }

    public void setRefCnt(String refCnt) {
        this.refCnt = refCnt;
    }

    public String getRefAmt() {
        return refAmt;
    }

    public void setRefAmt(String refAmt) {
        this.refAmt = refAmt;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPpFlag() {
        return ppFlag;
    }

    public void setPpFlag(String ppFlag) {
        this.ppFlag = ppFlag;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public String getCertifyId() {
        return certifyId;
    }

    public void setCertifyId(String certifyId) {
        this.certifyId = certifyId;
    }

    public String getMsgDestId() {
        return msgDestId;
    }

    public void setMsgDestId(String msgDestId) {
        this.msgDestId = msgDestId;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSubOpenId() {
        return subOpenId;
    }

    public void setSubOpenId(String subOpenId) {
        this.subOpenId = subOpenId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPaySubType() {
        return paySubType;
    }

    public void setPaySubType(String paySubType) {
        this.paySubType = paySubType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTradeDetail() {
        return tradeDetail;
    }

    public void setTradeDetail(String tradeDetail) {
        this.tradeDetail = tradeDetail;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    public String getReferNo() {
        return referNo;
    }

    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getOrderIdScan() {
        return orderIdScan;
    }

    public void setOrderIdScan(String orderIdScan) {
        this.orderIdScan = orderIdScan;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayMedium() {
        return payMedium;
    }

    public void setPayMedium(String payMedium) {
        this.payMedium = payMedium;
    }

    public String getChannelTermCode() {
        return channelTermCode;
    }

    public void setChannelTermCode(String channelTermCode) {
        this.channelTermCode = channelTermCode;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", innerCode="+ innerCode + ", txnType="+ txnType + ", txnSubType="+ txnSubType + ", currency="+ currency + ", payTimeOut="+ payTimeOut + ", subject="+ subject + ", body="+ body + ", customerInfo="+ customerInfo + ", remark="+ remark + ", customerIp="+ customerIp + ", tn="+ tn + ", respCode="+ respCode + ", respMsg="+ respMsg + ", settleAmount="+ settleAmount + ", settleCurrency="+ settleCurrency + ", settleDate="+ settleDate + ", succTime="+ succTime + ", orgMerOrderId="+ orgMerOrderId + ", canRefAmt="+ canRefAmt + ", refCnt="+ refCnt + ", refAmt="+ refAmt + ", bankId="+ bankId + ", ppFlag="+ ppFlag + ", purpose="+ purpose + ", dcType="+ dcType + ", certifyId="+ certifyId + ", msgDestId="+ msgDestId + ", customerNm="+ customerNm + ", phoneNo="+ phoneNo + ", subOpenId="+ subOpenId + ", channelType="+ channelType + ", amt="+ amt + ", orderNo="+ orderNo + ", orderTime="+ orderTime + ", orderInfo="+ orderInfo + ", payType="+ payType + ", paySubType="+ paySubType + ", timeStamp="+ timeStamp + ", tradeDetail="+ tradeDetail + ", merId="+ merId + ", termId="+ termId + ", batchNo="+ batchNo + ", traceNo="+ traceNo + ", referNo="+ referNo + ", authCode="+ authCode + ", orderIdScan="+ orderIdScan + ", source="+ source + ", md5="+ md5 + ", sendTime="+ sendTime + ", createTime="+ createTime + ", status="+ status + ", payMedium="+ payMedium + ", channelTermCode="+ channelTermCode + "]";
    }
}