package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class PassengerJO extends JO{

    @ApiModelProperty(value = "姓名", example = "姓名")
    private String name;
    @ApiModelProperty(value = "类型", example = "类型")
    private String ticketType;
    @ApiModelProperty(value = "证件类型", example = "姓名")
    private String cardType;
    @ApiModelProperty(value = "证件号码", example = "证件号码")
    private String cardNum;

    /**
     * name
     *
     * @return  the name
     * @since   CodingExample Ver 1.0
    */

    public String getName() {
        return name;
    }

    /**
     * name
     *
     * @param   name    the name to set
     * @since   CodingExample Ver 1.0
     */

    public void setName(String name) {
        this.name = name;
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
     * cardType
     *
     * @return  the cardType
     * @since   CodingExample Ver 1.0
    */

    public String getCardType() {
        return cardType;
    }

    /**
     * cardType
     *
     * @param   cardType    the cardType to set
     * @since   CodingExample Ver 1.0
     */

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

}
