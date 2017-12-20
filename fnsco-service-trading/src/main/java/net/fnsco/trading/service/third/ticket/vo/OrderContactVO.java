package net.fnsco.trading.service.third.ticket.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class OrderContactVO extends VO {

    @ApiModelProperty(value = "ID", example = "ID")
    private Integer    id;

    @ApiModelProperty(value = "订单ID", example = "订单ID")
    private String     orderNo;

    @ApiModelProperty(value = "乘客id", example = "乘客id")
    private Integer    passengerId;

    @ApiModelProperty(value = "乘客名称", example = "乘客名称")
    private String     passengerName;

    @ApiModelProperty(value = "车票类型", example = "车票类型")
    private String     ticketType;

    @ApiModelProperty(value = "车票类型名称", example = "车票类型名称")
    private String     ticketTypeName;

    @ApiModelProperty(value = "证件类型id", example = "证件类型id")
    private String     cardTypeId;

    @ApiModelProperty(value = "证件类型名称", example = "证件类型名称")
    private String     cardTypeName;

    @ApiModelProperty(value = "证件号", example = "证件号")
    private String     cardNum;

    @ApiModelProperty(value = "票价", example = "票价")
    private BigDecimal price;

    @ApiModelProperty(value = "座位编号", example = "座位编号")
    private String     seatCode;

    @ApiModelProperty(value = "座位名称", example = "座位名称")
    private String     seatName;

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
     * passengerId
     *
     * @return  the passengerId
     * @since   CodingExample Ver 1.0
    */

    public Integer getPassengerId() {
        return passengerId;
    }

    /**
     * passengerId
     *
     * @param   passengerId    the passengerId to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * passengerName
     *
     * @return  the passengerName
     * @since   CodingExample Ver 1.0
    */

    public String getPassengerName() {
        return passengerName;
    }

    /**
     * passengerName
     *
     * @param   passengerName    the passengerName to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
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
     * ticketTypeName
     *
     * @return  the ticketTypeName
     * @since   CodingExample Ver 1.0
    */

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    /**
     * ticketTypeName
     *
     * @param   ticketTypeName    the ticketTypeName to set
     * @since   CodingExample Ver 1.0
     */

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    /**
     * cardTypeId
     *
     * @return  the cardTypeId
     * @since   CodingExample Ver 1.0
    */

    public String getCardTypeId() {
        return cardTypeId;
    }

    /**
     * cardTypeId
     *
     * @param   cardTypeId    the cardTypeId to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    /**
     * cardTypeName
     *
     * @return  the cardTypeName
     * @since   CodingExample Ver 1.0
    */

    public String getCardTypeName() {
        return cardTypeName;
    }

    /**
     * cardTypeName
     *
     * @param   cardTypeName    the cardTypeName to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    /**
     * cardNum
     *
     * @return  the cardNum
     * @since   CodingExample Ver 1.0
    */

    public String getCardNum() {
        return cardNum;
    }

    /**
     * cardNum
     *
     * @param   cardNum    the cardNum to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    /**
     * price
     *
     * @return  the price
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getPrice() {
        return price;
    }

    /**
     * price
     *
     * @param   price    the price to set
     * @since   CodingExample Ver 1.0
     */

    public void setPrice(BigDecimal price) {
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