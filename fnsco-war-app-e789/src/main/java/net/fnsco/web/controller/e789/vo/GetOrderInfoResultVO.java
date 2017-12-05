package net.fnsco.web.controller.e789.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class GetOrderInfoResultVO extends VO {

    /**
     * 
     */
    @ApiModelProperty(value = "记录ID")
    private Integer    id;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private String     orderNo;

    /**
     * 交易总金额
     */

    @ApiModelProperty(value = "交易总金额")
    private BigDecimal txnAmount;

    /**
     * 分期付款数
     */
    @ApiModelProperty(value = "分期付款数")
    private Integer    installmentNum;

    /**
     * 应答码1000处理中1001成功1002失败1003已退货
     */
    @ApiModelProperty(value = "应答码1000处理中1001成功1002失败1003已退货")
    private String     respCode;

    /**
     * 应答信息
     */
    @ApiModelProperty(value = "应答信息")
    private String     respMsg;

    /**
     * 结算状态0 未结算 1已结算   2结算中   3已退款
     */
    @ApiModelProperty(value = "结算状态0 未结算 1已结算   2结算中   3已退款")
    private Integer    settleStatus;

    /**
     * 订单总价格
     */
    @ApiModelProperty(value = "订单总价格")
    private BigDecimal orderAmount;

    /**
     * 每期金额
     */
    @ApiModelProperty(value = "每期金额")
    private BigDecimal eachMoney;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date       createTime;

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
     * createTime
     *
     * @return  the createTime
     * @since   CodingExample Ver 1.0
    */

    public Date getCreateTime() {
        return createTime;
    }

    /**
     * createTime
     *
     * @param   createTime    the createTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", orderNo=" + orderNo + ", txnAmount=" + txnAmount + ", installmentNum=" + installmentNum + ", respCode=" + respCode + ", respMsg=" + respMsg + ", channelMerId="
               + ", settleStatus=" + settleStatus + ", createTime=" + createTime + "]";
    }
}