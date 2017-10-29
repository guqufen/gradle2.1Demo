package net.fnsco.order.service.trade;

public class TradeOrderJHFDTO {
    private String salesOrderNo;     //    订单号
    private String commId;           //  商户ID
    private String payAmount;        //   支付金额
    private String npr;              // 分期期数
    private String commercialRate;   //  商户费率
    private String cardHolderRate;   //  持卡人费率

    private String settlementAmount; //    结算金额
    private String orderStatus;      // 订单状态
    private String settlementStatus; //    结算状态
    private String payCallBackParams;//   商户上送参数

    /**
     * salesOrderNo
     *
     * @return  the salesOrderNo
     * @since   CodingExample Ver 1.0
    */

    public String getSalesOrderNo() {
        return salesOrderNo;
    }

    /**
     * salesOrderNo
     *
     * @param   salesOrderNo    the salesOrderNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    /**
     * commId
     *
     * @return  the commId
     * @since   CodingExample Ver 1.0
    */

    public String getCommId() {
        return commId;
    }

    /**
     * commId
     *
     * @param   commId    the commId to set
     * @since   CodingExample Ver 1.0
     */

    public void setCommId(String commId) {
        this.commId = commId;
    }

    /**
     * payAmount
     *
     * @return  the payAmount
     * @since   CodingExample Ver 1.0
    */

    public String getPayAmount() {
        return payAmount;
    }

    /**
     * payAmount
     *
     * @param   payAmount    the payAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * npr
     *
     * @return  the npr
     * @since   CodingExample Ver 1.0
    */

    public String getNpr() {
        return npr;
    }

    /**
     * npr
     *
     * @param   npr    the npr to set
     * @since   CodingExample Ver 1.0
     */

    public void setNpr(String npr) {
        this.npr = npr;
    }

    /**
     * commercialRate
     *
     * @return  the commercialRate
     * @since   CodingExample Ver 1.0
    */

    public String getCommercialRate() {
        return commercialRate;
    }

    /**
     * commercialRate
     *
     * @param   commercialRate    the commercialRate to set
     * @since   CodingExample Ver 1.0
     */

    public void setCommercialRate(String commercialRate) {
        this.commercialRate = commercialRate;
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
     * settlementAmount
     *
     * @return  the settlementAmount
     * @since   CodingExample Ver 1.0
    */

    public String getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * settlementAmount
     *
     * @param   settlementAmount    the settlementAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setSettlementAmount(String settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    /**
     * orderStatus
     *
     * @return  the orderStatus
     * @since   CodingExample Ver 1.0
    */

    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * orderStatus
     *
     * @param   orderStatus    the orderStatus to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * settlementStatus
     *
     * @return  the settlementStatus
     * @since   CodingExample Ver 1.0
    */

    public String getSettlementStatus() {
        return settlementStatus;
    }

    /**
     * settlementStatus
     *
     * @param   settlementStatus    the settlementStatus to set
     * @since   CodingExample Ver 1.0
     */

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    /**
     * payCallBackParams
     *
     * @return  the payCallBackParams
     * @since   CodingExample Ver 1.0
    */

    public String getPayCallBackParams() {
        return payCallBackParams;
    }

    /**
     * payCallBackParams
     *
     * @param   payCallBackParams    the payCallBackParams to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayCallBackParams(String payCallBackParams) {
        this.payCallBackParams = payCallBackParams;
    }

}
