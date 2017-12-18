package net.fnsco.trading.service.third.ticket.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class TicketOrderDTO extends DTO {
    @ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
    private Integer userId;
    @ApiModelProperty(value = "乘客ID", example = "乘客ID")
    private String  passengerId;
    @ApiModelProperty(value = "车号", example = "车号")
    private String  carNumber;
    @ApiModelProperty(value = "票类型（学生票）", example = "票类型（学生票）")
    private String  ticketType;
    @ApiModelProperty(value = "坐席（二等座）", example = "坐席（二等座）")
    private String  seatType;
    @ApiModelProperty(value = "价格", example = "价格")
    private String price;
    
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
     * userId
     *
     * @return  the userId
     * @since   CodingExample Ver 1.0
    */

    public Integer getUserId() {
        return userId;
    }

    /**
     * userId
     *
     * @param   userId    the userId to set
     * @since   CodingExample Ver 1.0
     */

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * carNumber
     *
     * @return  the carNumber
     * @since   CodingExample Ver 1.0
    */

    public String getCarNumber() {
        return carNumber;
    }

    /**
     * carNumber
     *
     * @param   carNumber    the carNumber to set
     * @since   CodingExample Ver 1.0
     */

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    /**
     * ticketType
     *
     * @return  the ticketType
     * @since   CodingExample Ver 1.0
    */

    public String getTicketType() {
        return ticketType;
    }

    /**
     * ticketType
     *
     * @param   ticketType    the ticketType to set
     * @since   CodingExample Ver 1.0
     */

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * seatType
     *
     * @return  the seatType
     * @since   CodingExample Ver 1.0
    */

    public String getSeatType() {
        return seatType;
    }

    /**
     * seatType
     *
     * @param   seatType    the seatType to set
     * @since   CodingExample Ver 1.0
     */

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    /**
     * passengerId
     *
     * @return  the passengerId
     * @since   CodingExample Ver 1.0
    */

    public String getPassengerId() {
        return passengerId;
    }

    /**
     * passengerId
     *
     * @param   passengerId    the passengerId to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

}
