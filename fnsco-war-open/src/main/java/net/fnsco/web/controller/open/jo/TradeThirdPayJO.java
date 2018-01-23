package net.fnsco.web.controller.open.jo;

import java.math.BigDecimal;
import java.util.Date;

public class TradeThirdPayJO {

    /**
     * 
     */
    private Integer    id;

    /**
     * 订单ID
     */
    private String     orderNo;

    /**
     * 交易总金额
     */
    private BigDecimal txnAmount;

    /**
     * 分期付款数
     */
    private Integer    installmentNum;

    /**
     * 应答码1000处理中1001成功1002失败1003已退货
     */
    private String     respCode;

    /**
     * 应答信息
     */
    private String     respMsg;

    /**
     * 交易完成时间
     */
    private String     completeTime;

    /**
     * 清算金额
     */
    private BigDecimal settleAmount;

    /**
     * 清算日期YYYYMMDDhhmmss
     */
    private Date       settleDate;

    /**
     * 结算状态0 未结算 1已结算   2结算中   3已退款
     */
    private Integer    settleStatus;

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
    private String     cardHolderRate;

    /**
     * 创建时间
     */
    private String     createTime;
    /**
     * 内部商户号 15位
     */
    private String     innerCode;
    private String     mercName;

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
     * mercName
     *
     * @return  the mercName
     * @since   CodingExample Ver 1.0
    */

    public String getMercName() {
        return mercName;
    }

    /**
     * mercName
     *
     * @param   mercName    the mercName to set
     * @since   CodingExample Ver 1.0
     */

    public void setMercName(String mercName) {
        this.mercName = mercName;
    }

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */

    public Integer getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */

    public void setId(Integer id) {
        this.id = id;
    }

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
     * txnAmount
     *
     * @return  the txnAmount
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    /**
     * txnAmount
     *
     * @param   txnAmount    the txnAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    /**
     * installmentNum
     *
     * @return  the installmentNum
     * @since   CodingExample Ver 1.0
    */

    public Integer getInstallmentNum() {
        return installmentNum;
    }

    /**
     * installmentNum
     *
     * @param   installmentNum    the installmentNum to set
     * @since   CodingExample Ver 1.0
     */

    public void setInstallmentNum(Integer installmentNum) {
        this.installmentNum = installmentNum;
    }

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
     * respMsg
     *
     * @return  the respMsg
     * @since   CodingExample Ver 1.0
    */

    public String getRespMsg() {
        return respMsg;
    }

    /**
     * respMsg
     *
     * @param   respMsg    the respMsg to set
     * @since   CodingExample Ver 1.0
     */

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    /**
     * completeTime
     *
     * @return  the completeTime
     * @since   CodingExample Ver 1.0
    */

    public String getCompleteTime() {
        return completeTime;
    }

    /**
     * completeTime
     *
     * @param   completeTime    the completeTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * settleAmount
     *
     * @return  the settleAmount
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    /**
     * settleAmount
     *
     * @param   settleAmount    the settleAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    /**
     * settleDate
     *
     * @return  the settleDate
     * @since   CodingExample Ver 1.0
    */

    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * settleDate
     *
     * @param   settleDate    the settleDate to set
     * @since   CodingExample Ver 1.0
     */

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * settleStatus
     *
     * @return  the settleStatus
     * @since   CodingExample Ver 1.0
    */

    public Integer getSettleStatus() {
        return settleStatus;
    }

    /**
     * settleStatus
     *
     * @param   settleStatus    the settleStatus to set
     * @since   CodingExample Ver 1.0
     */

    public void setSettleStatus(Integer settleStatus) {
        this.settleStatus = settleStatus;
    }

    /**
     * orderAmount
     *
     * @return  the orderAmount
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * orderAmount
     *
     * @param   orderAmount    the orderAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * eachMoney
     *
     * @return  the eachMoney
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getEachMoney() {
        return eachMoney;
    }

    /**
     * eachMoney
     *
     * @param   eachMoney    the eachMoney to set
     * @since   CodingExample Ver 1.0
     */

    public void setEachMoney(BigDecimal eachMoney) {
        this.eachMoney = eachMoney;
    }

    /**
     * cardHolderRate
     *
     * @return  the cardHolderRate
     * @since   CodingExample Ver 1.0
    */

    public String getCardHolderRate() {
        return cardHolderRate;
    }

    /**
     * cardHolderRate
     *
     * @param   cardHolderRate    the cardHolderRate to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardHolderRate(String cardHolderRate) {
        this.cardHolderRate = cardHolderRate;
    }

    /**
     * createTime
     *
     * @return  the createTime
     * @since   CodingExample Ver 1.0
    */

    public String getCreateTime() {
        return createTime;
    }

    /**
     * createTime
     *
     * @param   createTime    the createTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
