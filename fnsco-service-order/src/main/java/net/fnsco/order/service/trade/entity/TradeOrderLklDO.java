package net.fnsco.order.service.trade.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradeOrderLklDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 订单ID
     */
    private String orderNo;

    /**
     * 支付订单号
     */
    private String payOrderNo;

    /**
     * 交易总金额
     */
    private BigDecimal txnAmount;

    /**
     * 分期付款数
     */
    private Integer installmentNum;

    /**
     * 应答码1000处理中1001成功1002失败1003已退货
     */
    private String respCode;

    /**
     * 应答信息
     */
    private String respMsg;

    /**
     * 实体商户内部商户号
     */
    private String entityInnerCode;

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
     * 交易子类型00刷卡01微信02支付宝03聚惠分
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
     * 结算状态0 未结算 1已结算   2结算中   3已退款
     */
    private Integer settleStatus;

    /**
     * 创建人id
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 同步状态0未同步1已同步
     */
    private Integer syncStatus;

    /**
     * 内部商户号 
     */
    private String innerCode;

    /**
     * 处理次数,本订单准备付款次数
     */
    private Integer handleNum;

    /**
     * 订单总价格
     */
    private BigDecimal orderAmount;

    /**
     * 每期金额
     */
    private BigDecimal eachMoney;

    /**
     * 持卡人费率
     */
    private String cardHolderRate;



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

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
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

    public String getEntityInnerCode() {
        return entityInnerCode;
    }

    public void setEntityInnerCode(String entityInnerCode) {
        this.entityInnerCode = entityInnerCode;
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

    public Integer getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
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

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public Integer getHandleNum() {
        return handleNum;
    }

    public void setHandleNum(Integer handleNum) {
        this.handleNum = handleNum;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getEachMoney() {
        return eachMoney;
    }

    public void setEachMoney(BigDecimal eachMoney) {
        this.eachMoney = eachMoney;
    }

    public String getCardHolderRate() {
        return cardHolderRate;
    }

    public void setCardHolderRate(String cardHolderRate) {
        this.cardHolderRate = cardHolderRate;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", orderNo="+ orderNo + ", payOrderNo="+ payOrderNo + ", txnAmount="+ txnAmount + ", installmentNum="+ installmentNum + ", respCode="+ respCode + ", respMsg="+ respMsg + ", entityInnerCode="+ entityInnerCode + ", channelMerId="+ channelMerId + ", channelType="+ channelType + ", completeTime="+ completeTime + ", orderCeateTime="+ orderCeateTime + ", txnType="+ txnType + ", txnSubType="+ txnSubType + ", payType="+ payType + ", paySubType="+ paySubType + ", settleAmount="+ settleAmount + ", settleDate="+ settleDate + ", settleStatus="+ settleStatus + ", createUserId="+ createUserId + ", createTime="+ createTime + ", syncStatus="+ syncStatus + ", innerCode="+ innerCode + ", handleNum="+ handleNum + ", orderAmount="+ orderAmount + ", eachMoney="+ eachMoney + ", cardHolderRate="+ cardHolderRate + "]";
    }
}