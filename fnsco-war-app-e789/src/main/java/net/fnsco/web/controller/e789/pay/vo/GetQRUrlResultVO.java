package net.fnsco.web.controller.e789.pay.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class GetQRUrlResultVO extends VO {
    @ApiModelProperty(value = "二维码url", name = "username", example = "http://")
    private String url;
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    /**
     * url
     *
     * @return  the url
     * @since   CodingExample Ver 1.0
    */

    public String getUrl() {
        return url;
    }

    /**
     * url
     *
     * @param   url    the url to set
     * @since   CodingExample Ver 1.0
     */

    public void setUrl(String url) {
        this.url = url;
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
