package net.fnsco.web.controller.e789.third.ticket.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.web.controller.e789.jo.CommonJO;

public class TicketOrderJO extends CommonJO {
    @ApiModelProperty(value = "乘客ID,多个乘客以逗号分隔", example = "乘客ID,多个乘客以逗号分隔")
    private String passengerId;
    @ApiModelProperty(value = "车号", example = "车号")
    private String trainCode;
    @ApiModelProperty(value = "票类型（学生票）", example = "票类型（学生票）")
    private String ticketType;
    @ApiModelProperty(value = "坐席（坐席 F:动卧(新增),9:商务座,P:特等座,M:一等座,O（大写字母O，不是数字0）:二等座,6:高级软卧, 4:软卧,3:硬卧,2:软座,1:硬座。）", example = "坐席（二等座）")
    private String seatCode;
    @ApiModelProperty(value = "价格", example = "价格")
    private String price;
    @ApiModelProperty(value = "乘车日期", example = "乘车日期")
    private String trainDate;
    @ApiModelProperty(value = "出发站编号", example = "出发站编号")
    private String fromStationCode;
    @ApiModelProperty(value = "出发站名称", example = "出发站名称")
    private String fromStationName;
    @ApiModelProperty(value = "到达站编号", example = "到达站编号")
    private String toStationCode;
    @ApiModelProperty(value = "到达站名称", example = "到达站名称")
    private String toStationName;

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
     * trainDate
     *
     * @return  the trainDate
     * @since   CodingExample Ver 1.0
    */

    public String getTrainDate() {
        return trainDate;
    }

    /**
     * trainDate
     *
     * @param   trainDate    the trainDate to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    /**
     * fromStationCode
     *
     * @return  the fromStationCode
     * @since   CodingExample Ver 1.0
    */

    public String getFromStationCode() {
        return fromStationCode;
    }

    /**
     * fromStationCode
     *
     * @param   fromStationCode    the fromStationCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setFromStationCode(String fromStationCode) {
        this.fromStationCode = fromStationCode;
    }

    /**
     * fromStationName
     *
     * @return  the fromStationName
     * @since   CodingExample Ver 1.0
    */

    public String getFromStationName() {
        return fromStationName;
    }

    /**
     * fromStationName
     *
     * @param   fromStationName    the fromStationName to set
     * @since   CodingExample Ver 1.0
     */

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    /**
     * toStationCode
     *
     * @return  the toStationCode
     * @since   CodingExample Ver 1.0
    */

    public String getToStationCode() {
        return toStationCode;
    }

    /**
     * toStationCode
     *
     * @param   toStationCode    the toStationCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setToStationCode(String toStationCode) {
        this.toStationCode = toStationCode;
    }

    /**
     * toStationName
     *
     * @return  the toStationName
     * @since   CodingExample Ver 1.0
    */

    public String getToStationName() {
        return toStationName;
    }

    /**
     * toStationName
     *
     * @param   toStationName    the toStationName to set
     * @since   CodingExample Ver 1.0
     */

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
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
     * trainCode
     *
     * @return  the trainCode
     * @since   CodingExample Ver 1.0
    */

    public String getTrainCode() {
        return trainCode;
    }

    /**
     * trainCode
     *
     * @param   trainCode    the trainCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
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
