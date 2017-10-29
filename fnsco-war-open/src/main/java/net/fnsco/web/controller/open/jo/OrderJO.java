package net.fnsco.web.controller.open.jo;

public class OrderJO {
    private String salesOrderNo;
    private String orderStatus;
    private String settlementStatus;
    private String payCallBackParams;

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
