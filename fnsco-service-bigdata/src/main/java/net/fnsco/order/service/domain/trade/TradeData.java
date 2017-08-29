package net.fnsco.order.service.domain.trade;

import java.util.Date;
import java.util.List;

public class TradeData {
    private String       id;

    private String       innerCode;

    private String       txnType;

    private String       txnSubType;

    private String       currency;

    private String       payTimeOut;

    private String       subject;

    private String       body;

    private String       customerInfo;

    private String       remark;

    private String       customerIp;

    private String       tn;

    private String       respCode;

    private String       respMsg;

    private String       settleAmount;

    private String       settleCurrency;

    private String       settleDate;

    private String       succTime;

    private String       orgMerOrderId;

    private String       canRefAmt;

    private String       refCnt;

    private String       refAmt;

    private String       bankId;

    private String       ppFlag;

    private String       purpose;

    private String       dcType;

    private String       certifyId;

    private String       msgDestId;

    private String       customerNm;

    private String       phoneNo;

    private String       subOpenId;

    private String       channelType;

    private String       amt;

    private String       orderNo;

    private String       orderTime;

    private String       orderInfo;

    private String       payType;

    private String       timeStamp;

    private String       tradeDetail;

    private String       merId;

    private String       termId;

    private String       batchNo;

    private String       traceNo;

    private String       referNo;

    private String       authCode;

    private String       orderIdScan;

    private String       source;

    private String       md5;

    private String       sendTime;

    private Date         createTime;
    
    private String       paySubType;    //交易子类型
    private List<String>       paySubTypes;    //交易子类型
    private String       startSendTime; //条件查询 发送开始时间
    private String       endSendTime;   //条件查询 发送结束时间

    private String       startTime;     // 订单交易开始日期 20160510000000
    private String       endTime;       // 订单交易结束日期 20160510235959
    private String       status;        //交易状态0非正常交易（包括撤销交易和撤销原交易）1正常交易
    private String       merName;       //商户名称
    //内部商务号列表
    private List<String> innerCodeList;
    //终端号列表
    private List<String> terminalList;
    //创建时间string类型显示
    private String 		 createTimeStr;
    
    public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public List<String> getPaySubTypes() {
        return paySubTypes;
    }

    public void setPaySubTypes(List<String> paySubTypes) {
        this.paySubTypes = paySubTypes;
    }

    /**
     * merName
     *
     * @return  the merName
     * @since   CodingExample Ver 1.0
    */
    
    public String getMerName() {
        return merName;
    }

    /**
     * merName
     *
     * @param   merName    the merName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerName(String merName) {
        this.merName = merName;
    }

    /**
     * status
     *
     * @return  the status
     * @since   CodingExample Ver 1.0
    */
    
    public String getStatus() {
        return status;
    }

    /**
     * status
     *
     * @param   status    the status to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * terminalList
     *
     * @return  the terminalList
     * @since   CodingExample Ver 1.0
    */

    public List<String> getTerminalList() {
        return terminalList;
    }

    /**
     * terminalList
     *
     * @param   terminalList    the terminalList to set
     * @since   CodingExample Ver 1.0
     */

    public void setTerminalList(List<String> terminalList) {
        this.terminalList = terminalList;
    }

    /**
     * innerCodeList
     *
     * @return  the innerCodeList
     * @since   CodingExample Ver 1.0
    */

    public List<String> getInnerCodeList() {
        return innerCodeList;
    }

    /**
     * innerCodeList
     *
     * @param   innerCodeList    the innerCodeList to set
     * @since   CodingExample Ver 1.0
     */

    public void setInnerCodeList(List<String> innerCodeList) {
        this.innerCodeList = innerCodeList;
    }

    /**
     * startTime
     *
     * @return  the startTime
     * @since   CodingExample Ver 1.0
    */

    public String getStartTime() {
        return startTime;
    }

    /**
     * startTime
     *
     * @param   startTime    the startTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * endTime
     *
     * @return  the endTime
     * @since   CodingExample Ver 1.0
    */

    public String getEndTime() {
        return endTime;
    }

    /**
     * endTime
     *
     * @param   endTime    the endTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * startSendTime
     *
     * @return  the startSendTime
     * @since   CodingExample Ver 1.0
    */

    public String getStartSendTime() {
        return startSendTime;
    }

    /**
     * startSendTime
     *
     * @param   startSendTime    the startSendTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartSendTime(String startSendTime) {
        this.startSendTime = startSendTime;
    }

    /**
     * endSendTime
     *
     * @return  the endSendTime
     * @since   CodingExample Ver 1.0
    */

    public String getEndSendTime() {
        return endSendTime;
    }

    /**
     * endSendTime
     *
     * @param   endSendTime    the endSendTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndSendTime(String endSendTime) {
        this.endSendTime = endSendTime;
    }

    /**
     * @return the paySubType
     */
    public String getPaySubType() {
        return paySubType;
    }

    /**
     * @param paySubType the paySubType to set
     */
    public void setPaySubType(String paySubType) {
        this.paySubType = paySubType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType == null ? null : txnType.trim();
    }

    public String getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(String txnSubType) {
        this.txnSubType = txnSubType == null ? null : txnSubType.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getPayTimeOut() {
        return payTimeOut;
    }

    public void setPayTimeOut(String payTimeOut) {
        this.payTimeOut = payTimeOut == null ? null : payTimeOut.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo == null ? null : customerInfo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp == null ? null : customerIp.trim();
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn == null ? null : tn.trim();
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode == null ? null : respCode.trim();
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg == null ? null : respMsg.trim();
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount == null ? null : settleAmount.trim();
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency == null ? null : settleCurrency.trim();
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate == null ? null : settleDate.trim();
    }

    public String getSuccTime() {
        return succTime;
    }

    public void setSuccTime(String succTime) {
        this.succTime = succTime == null ? null : succTime.trim();
    }

    public String getOrgMerOrderId() {
        return orgMerOrderId;
    }

    public void setOrgMerOrderId(String orgMerOrderId) {
        this.orgMerOrderId = orgMerOrderId == null ? null : orgMerOrderId.trim();
    }

    public String getCanRefAmt() {
        return canRefAmt;
    }

    public void setCanRefAmt(String canRefAmt) {
        this.canRefAmt = canRefAmt == null ? null : canRefAmt.trim();
    }

    public String getRefCnt() {
        return refCnt;
    }

    public void setRefCnt(String refCnt) {
        this.refCnt = refCnt == null ? null : refCnt.trim();
    }

    public String getRefAmt() {
        return refAmt;
    }

    public void setRefAmt(String refAmt) {
        this.refAmt = refAmt == null ? null : refAmt.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getPpFlag() {
        return ppFlag;
    }

    public void setPpFlag(String ppFlag) {
        this.ppFlag = ppFlag == null ? null : ppFlag.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType == null ? null : dcType.trim();
    }

    public String getCertifyId() {
        return certifyId;
    }

    public void setCertifyId(String certifyId) {
        this.certifyId = certifyId == null ? null : certifyId.trim();
    }

    public String getMsgDestId() {
        return msgDestId;
    }

    public void setMsgDestId(String msgDestId) {
        this.msgDestId = msgDestId == null ? null : msgDestId.trim();
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm == null ? null : customerNm.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getSubOpenId() {
        return subOpenId;
    }

    public void setSubOpenId(String subOpenId) {
        this.subOpenId = subOpenId == null ? null : subOpenId.trim();
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType == null ? null : channelType.trim();
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt == null ? null : amt.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo == null ? null : orderInfo.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp == null ? null : timeStamp.trim();
    }

    public String getTradeDetail() {
        return tradeDetail;
    }

    public void setTradeDetail(String tradeDetail) {
        this.tradeDetail = tradeDetail == null ? null : tradeDetail.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    /**
     * traceNo
     *
     * @return  the traceNo
     * @since   CodingExample Ver 1.0
    */

    public String getTraceNo() {
        return traceNo;
    }

    /**
     * traceNo
     *
     * @param   traceNo    the traceNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    /**
     * referNo
     *
     * @return  the referNo
     * @since   CodingExample Ver 1.0
    */

    public String getReferNo() {
        return referNo;
    }

    /**
     * referNo
     *
     * @param   referNo    the referNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode == null ? null : authCode.trim();
    }

    public String getOrderIdScan() {
        return orderIdScan;
    }

    public void setOrderIdScan(String orderIdScan) {
        this.orderIdScan = orderIdScan == null ? null : orderIdScan.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime == null ? null : sendTime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}