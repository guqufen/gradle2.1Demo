package net.fnsco.order.service.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradeOrderDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 支付订单号
     */
    private Integer payOrderId;

    /**
     * 交易总金额
     */
    private BigDecimal txnAmount;

    /**
     * 分期付款数
     */
    private Integer installmentNum;

    /**
     * 应答码
     */
    private String respCode;

    /**
     * 应答信息1000处理中1001成功1002失败
     */
    private String respMsg;

    /**
     * 渠道内部商户号
     */
    private String mercId;

    /**
     * 渠道商户号
     */
    private String channelMerId;

    /**
     * 渠道类型00爱农01浦发
     */
    private String channelType;

    /**
     * 交易完成时间
     */
    private Date completeTime;

    /**
     * 订单创建时间
     */
    private Date orderCeateTime;

    /**
     * 交易类型1消费2撤销
     */
    private Integer txnType;

    /**
     * 交易子类型
     */
    private Integer txnSubType;

    /**
     * 支付方式00刷卡01二维码02分期付
     */
    private String payType;

    /**
     * 交易子类型00刷卡01微信02支付宝
     */
    private String paySubType;

    /**
     * 清算金额
     */
    private BigDecimal settleAmount;

    /**
     * 清算日期YYYYMMDDhhmmss
     */
    private Date settleDate;

    /**
     * 创建人id
     */
    private String createUserId;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Integer payOrderId) {
        this.payOrderId = payOrderId;
    }

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    public Integer getInstallmentNum() {
        return installmentNum;
    }

    public void setInstallmentNum(Integer installmentNum) {
        this.installmentNum = installmentNum;
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

    public String getMercId() {
        return mercId;
    }

    public void setMercId(String mercId) {
        this.mercId = mercId;
    }

    public String getChannelMerId() {
        return channelMerId;
    }

    public void setChannelMerId(String channelMerId) {
        this.channelMerId = channelMerId;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getOrderCeateTime() {
        return orderCeateTime;
    }

    public void setOrderCeateTime(Date orderCeateTime) {
        this.orderCeateTime = orderCeateTime;
    }

    public Integer getTxnType() {
        return txnType;
    }

    public void setTxnType(Integer txnType) {
        this.txnType = txnType;
    }

    public Integer getTxnSubType() {
        return txnSubType;
    }

    public void setTxnSubType(Integer txnSubType) {
        this.txnSubType = txnSubType;
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

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", orderId="+ orderId + ", payOrderId="+ payOrderId + ", txnAmount="+ txnAmount + ", installmentNum="+ installmentNum + ", respCode="+ respCode + ", respMsg="+ respMsg + ", mercId="+ mercId + ", channelMerId="+ channelMerId + ", channelType="+ channelType + ", completeTime="+ completeTime + ", orderCeateTime="+ orderCeateTime + ", txnType="+ txnType + ", txnSubType="+ txnSubType + ", payType="+ payType + ", paySubType="+ paySubType + ", settleAmount="+ settleAmount + ", settleDate="+ settleDate + ", createUserId="+ createUserId + ", createTime="+ createTime + "]";
    }
}