package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class TradeDataDTO extends DTO {

    private String amt;         //金额，格式为“100.01”
    private String orderNo;     // 订单号（不唯一）
    private String orderTime;   // 订单时间戳
    private String orderInfo;   // 订单信息 
    private String batchBillNo; // 撤销时会有  无用**

    private String payType;     // 支付方式
    private String referNo;     // 检索参考号 无用**
    private String timeStamp;   // 交易时间戳
    private String tradeDetail; // 交易详情（JSON串）

    private String merId;       // 商户号
    private String termId;      // 终端号
    private String batchNo;     // 批次号
    private String sysTraceNo;  // 凭证号
    private String authCode;    // 授权码
    private String orderIdScan; // 扫码交易的订单号
    private String source;      //流水来源00拉卡拉机器
    private String md5;         //参数md5值
    private String sendTime;    //发送时间
    private String validate;    //是否校验1验证0不验证
    private String paySubType;  //交易子类型
    
    private String startSendTime;//条件查询 发送开始时间
    private String endSendTime;//条件查询 发送结束时间
    
    private String respCode;
    /**
     * respCode
     *
     * @return  the respCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getRespCode() {
        return respCode;
    }

    /**
     * respCode
     *
     * @param   respCode    the respCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setRespCode(String respCode) {
        this.respCode = respCode;
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
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5 the md5 to set
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * @return the sendTime
     */
    public String getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime the sendTime to set
     */
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the validate
     */
    public String getValidate() {
        return validate;
    }

    /**
     * @param validate the validate to set
     */
    public void setValidate(String validate) {
        this.validate = validate;
    }

    /**
     * @return the amt
     */
    public String getAmt() {
        return amt;
    }

    /**
     * @param amt the amt to set
     */
    public void setAmt(String amt) {
        this.amt = amt;
    }

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return the orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return the orderInfo
     */
    public String getOrderInfo() {
        return orderInfo;
    }

    /**
     * @param orderInfo the orderInfo to set
     */
    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * @return the batchBillNo
     */
    public String getBatchBillNo() {
        return batchBillNo;
    }

    /**
     * @param batchBillNo the batchBillNo to set
     */
    public void setBatchBillNo(String batchBillNo) {
        this.batchBillNo = batchBillNo;
    }

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return the referNo
     */
    public String getReferNo() {
        return referNo;
    }

    /**
     * @param referNo the referNo to set
     */
    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the tradeDetail
     */
    public String getTradeDetail() {
        return tradeDetail;
    }

    /**
     * @param tradeDetail the tradeDetail to set
     */
    public void setTradeDetail(String tradeDetail) {
        this.tradeDetail = tradeDetail;
    }

    /**
     * @return the merId
     */
    public String getMerId() {
        return merId;
    }

    /**
     * @param merId the merId to set
     */
    public void setMerId(String merId) {
        this.merId = merId;
    }

    /**
     * @return the termId
     */
    public String getTermId() {
        return termId;
    }

    /**
     * @param termId the termId to set
     */
    public void setTermId(String termId) {
        this.termId = termId;
    }

    /**
     * @return the batchNo
     */
    public String getBatchNo() {
        return batchNo;
    }

    /**
     * @param batchNo the batchNo to set
     */
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * @return the sysTraceNo
     */
    public String getSysTraceNo() {
        return sysTraceNo;
    }

    /**
     * @param sysTraceNo the sysTraceNo to set
     */
    public void setSysTraceNo(String sysTraceNo) {
        this.sysTraceNo = sysTraceNo;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the orderIdScan
     */
    public String getOrderIdScan() {
        return orderIdScan;
    }

    /**
     * @param orderIdScan the orderIdScan to set
     */
    public void setOrderIdScan(String orderIdScan) {
        this.orderIdScan = orderIdScan;
    }

    //    private String pay_tp;      //支付方式    
    //    private String order_no;    //订单号 
    //    private String batchbillno; //批次流水号   
    //    private String time_stamp;  //交易时间戳   
    //    private String reason;      //失败原因     
    //    private String remark;      //附加数据    
    //
    //    private String merid;       //  商户号
    //    private String termid;      //  终端号
    //    private String batchno;     //  批次号
    //    private String systraceno;  //  凭证号
    //    private String authcode;    //  授权号
    //    private String orderid_scan;//  扫码订单号

}
