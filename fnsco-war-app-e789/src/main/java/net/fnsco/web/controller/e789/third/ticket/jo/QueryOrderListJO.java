package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.web.controller.e789.jo.CommonJO;

public class QueryOrderListJO extends CommonJO {

    @ApiModelProperty(value = "订单号", example = "订单号")
    private String orderNo;
    @ApiModelProperty(value = "订单状态,多个以逗号分隔,例1,2,3", example = "订单状态,多个以逗号分隔,例1,2,3")
    private String status;

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

}
