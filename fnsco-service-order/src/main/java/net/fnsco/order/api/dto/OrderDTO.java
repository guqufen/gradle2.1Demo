package net.fnsco.order.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

public class OrderDTO extends DTO {
    private String salesOrderNo;
    private String orderStatus;
    private String settlementStatus;
    private String payCallBackParams;
    private Date   orderCeateTime;
    private String thirdPayNo;       //thirdPayNo  订单号
    //commId  商户ID
    //payAmount   支付金额
    //npr 分期期数
    //commercialRate  商户费率
    //cardHolderRate  持卡人费率
    //settlementAmount    //结算金额
    //orderStatus 订单状态
    //settlementStatus    结算状态
    //payCallBackParams   商户上送参数
    private String time;             //   交易时间
    private String singData;

    /**
     * singData
     *
     * @return  the singData
     * @since   CodingExample Ver 1.0
    */

    public String getSingData() {
        return singData;
    }

    /**
     * singData
     *
     * @param   singData    the singData to set
     * @since   CodingExample Ver 1.0
     */

    public void setSingData(String singData) {
        this.singData = singData;
    }

    /**
     * time
     *
     * @return  the time
     * @since   CodingExample Ver 1.0
    */

    public String getTime() {
        return time;
    }

    /**
     * time
     *
     * @param   time    the time to set
     * @since   CodingExample Ver 1.0
     */

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * orderCeateTime
     *
     * @return  the orderCeateTime
     * @since   CodingExample Ver 1.0
    */

    public Date getOrderCeateTime() {
        return orderCeateTime;
    }

    /**
     * thirdPayNo
     *
     * @return  the thirdPayNo
     * @since   CodingExample Ver 1.0
    */

    public String getThirdPayNo() {
        return thirdPayNo;
    }

    /**
     * thirdPayNo
     *
     * @param   thirdPayNo    the thirdPayNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setThirdPayNo(String thirdPayNo) {
        this.thirdPayNo = thirdPayNo;
    }

    /**
     * orderCeateTime
     *
     * @param   orderCeateTime    the orderCeateTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderCeateTime(Date orderCeateTime) {
        this.orderCeateTime = orderCeateTime;
    }

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
