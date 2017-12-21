package net.fnsco.web.controller.e789.third.ticket.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class SeatTypeVO extends VO {
    @ApiModelProperty(value = "坐席代码", example = "坐席代码（二等座）")
    private String seatCode;
    @ApiModelProperty(value = "坐席名称（二等座）", example = "坐席名称（二等座）")
    private String seatName;
    @ApiModelProperty(value = "价格", example = "价格")
    private String price;
    @ApiModelProperty(value = "余票数量", example = "余票数量")
    private String num;

    /**
     * num
     *
     * @return  the num
     * @since   CodingExample Ver 1.0
    */

    public String getNum() {
        return num;
    }

    /**
     * num
     *
     * @param   num    the num to set
     * @since   CodingExample Ver 1.0
     */

    public void setNum(String num) {
        this.num = num;
    }

    /**
     * price
     *
     * @return  the price
     * @since   CodingExample Ver 1.0
    */

    public String getPrice() {
        return price;
    }

    /**
     * price
     *
     * @param   price    the price to set
     * @since   CodingExample Ver 1.0
     */

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * seatCode
     *
     * @return  the seatCode
     * @since   CodingExample Ver 1.0
    */

    public String getSeatCode() {
        return seatCode;
    }

    /**
     * seatCode
     *
     * @param   seatCode    the seatCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    /**
     * seatName
     *
     * @return  the seatName
     * @since   CodingExample Ver 1.0
    */

    public String getSeatName() {
        return seatName;
    }

    /**
     * seatName
     *
     * @param   seatName    the seatName to set
     * @since   CodingExample Ver 1.0
     */

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

}
