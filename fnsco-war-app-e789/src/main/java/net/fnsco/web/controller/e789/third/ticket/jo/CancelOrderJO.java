package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.web.controller.e789.jo.CommonJO;

public class CancelOrderJO extends CommonJO {
    @ApiModelProperty(value = "订单号", example = "订单号")
    private String orderNo;

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
