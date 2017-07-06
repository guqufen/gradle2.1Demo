package net.fnsco.controller.app.jo;

import net.fnsco.core.base.JO;

public class TradeDataJO extends JO {
    private String id;           //交易记录ID
    private String amount;       //交易金额
    private String tradeTime;    //交易时间
    private String status;       //交易状态（1成功 2失败）
    private String statusName;
    private String payType;
    private String payTypeName;

    //交易详情
    private String innerCode;
    //商户名
    private String merName;
    //交易类型1消费2撤销
    private String tradeType;
    //交易类型1消费2撤销
    private String tradeTypeName;
    //终端号
    private String termId;
    //批次号
    private String batchNo;
    //凭证号
    private String traceNo;
    //参数考
    private String referNo;
    //卡号
    private String certifyId;
    //订单号
    private String       orderNo;
    //订单创建时间
    private String       orderTime;
    
    /**
     * orderNo
     *
     * @return  the orderNo
     * @since   CodingExample Ver 1.0
    */
    
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * orderNo
     *
     * @param   orderNo    the orderNo to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * orderTime
     *
     * @return  the orderTime
     * @since   CodingExample Ver 1.0
    */
    
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * orderTime
     *
     * @param   orderTime    the orderTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * certifyId
     *
     * @return  the certifyId
     * @since   CodingExample Ver 1.0
    */
    
    public String getCertifyId() {
        return certifyId;
    }

    /**
     * certifyId
     *
     * @param   certifyId    the certifyId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setCertifyId(String certifyId) {
        this.certifyId = certifyId;
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

    /**
     * tradeTypeName
     *
     * @return  the tradeTypeName
     * @since   CodingExample Ver 1.0
    */

    public String getTradeTypeName() {
        return tradeTypeName;
    }

    /**
     * tradeTypeName
     *
     * @param   tradeTypeName    the tradeTypeName to set
     * @since   CodingExample Ver 1.0
     */

    public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }

    /**
     * termId
     *
     * @return  the termId
     * @since   CodingExample Ver 1.0
    */

    public String getTermId() {
        return termId;
    }

    /**
     * termId
     *
     * @param   termId    the termId to set
     * @since   CodingExample Ver 1.0
     */

    public void setTermId(String termId) {
        this.termId = termId;
    }

    /**
     * batchNo
     *
     * @return  the batchNo
     * @since   CodingExample Ver 1.0
    */

    public String getBatchNo() {
        return batchNo;
    }

    /**
     * batchNo
     *
     * @param   batchNo    the batchNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    /**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */

    public String getInnerCode() {
        return innerCode;
    }

    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
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
     * tradeType
     *
     * @return  the tradeType
     * @since   CodingExample Ver 1.0
    */

    public String getTradeType() {
        return tradeType;
    }

    /**
     * tradeType
     *
     * @param   tradeType    the tradeType to set
     * @since   CodingExample Ver 1.0
     */

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */

    public String getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * tradeTime
     *
     * @return  the tradeTime
     * @since   CodingExample Ver 1.0
    */

    public String getTradeTime() {
        return tradeTime;
    }

    /**
     * tradeTime
     *
     * @param   tradeTime    the tradeTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * amount
     *
     * @return  the amount
     * @since   CodingExample Ver 1.0
    */

    public String getAmount() {
        return amount;
    }

    /**
     * amount
     *
     * @param   amount    the amount to set
     * @since   CodingExample Ver 1.0
     */

    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * statusName
     *
     * @return  the statusName
     * @since   CodingExample Ver 1.0
    */

    public String getStatusName() {
        return statusName;
    }

    /**
     * statusName
     *
     * @param   statusName    the statusName to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * payType
     *
     * @return  the payType
     * @since   CodingExample Ver 1.0
    */

    public String getPayType() {
        return payType;
    }

    /**
     * payType
     *
     * @param   payType    the payType to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayType(String payType) {
        this.payType = payType;
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
     * payTypeName
     *
     * @return  the payTypeName
     * @since   CodingExample Ver 1.0
    */

    public String getPayTypeName() {
        return payTypeName;
    }

    /**
     * payTypeName
     *
     * @param   payTypeName    the payTypeName to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

}
