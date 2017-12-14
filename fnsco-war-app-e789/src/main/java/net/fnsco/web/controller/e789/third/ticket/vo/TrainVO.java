package net.fnsco.web.controller.e789.third.ticket.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class TrainVO extends VO {

    @ApiModelProperty(value = "价格", example = "价格")
    private String fare;

    @ApiModelProperty(value = "车票类型", example = "车票类型")
    private String ticketType;

    @ApiModelProperty(value = "出发时间", example = "出发时间")
    private String startTime;

    @ApiModelProperty(value = "到达时间", example = "到达时间")
    private String endTime;

    @ApiModelProperty(value = "车号", example = "车号")
    private String carNumber;

    @ApiModelProperty(value = "耗时", example = "耗时")
    private String duration;

    @ApiModelProperty(value = "起点站", example = "起点站")
    private String startSite;

    @ApiModelProperty(value = "终点站", example = "终点站")
    private String endSite;

    /**
     * fare
     *
     * @return  the fare
     * @since   CodingExample Ver 1.0
    */

    public String getFare() {
        return fare;
    }

    /**
     * fare
     *
     * @param   fare    the fare to set
     * @since   CodingExample Ver 1.0
     */

    public void setFare(String fare) {
        this.fare = fare;
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
     * startTime
     *
     * @return  the startTime
     * @since   CodingExample Ver 1.0
    */

    public String getStartTime() {
        return startTime;
    }

    /**
     * startTime
     *
     * @param   startTime    the startTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * endTime
     *
     * @return  the endTime
     * @since   CodingExample Ver 1.0
    */

    public String getEndTime() {
        return endTime;
    }

    /**
     * endTime
     *
     * @param   endTime    the endTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
     * duration
     *
     * @return  the duration
     * @since   CodingExample Ver 1.0
    */

    public String getDuration() {
        return duration;
    }

    /**
     * duration
     *
     * @param   duration    the duration to set
     * @since   CodingExample Ver 1.0
     */

    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * startSite
     *
     * @return  the startSite
     * @since   CodingExample Ver 1.0
    */

    public String getStartSite() {
        return startSite;
    }

    /**
     * startSite
     *
     * @param   startSite    the startSite to set
     * @since   CodingExample Ver 1.0
     */

    public void setStartSite(String startSite) {
        this.startSite = startSite;
    }

    /**
     * endSite
     *
     * @return  the endSite
     * @since   CodingExample Ver 1.0
    */

    public String getEndSite() {
        return endSite;
    }

    /**
     * endSite
     *
     * @param   endSite    the endSite to set
     * @since   CodingExample Ver 1.0
     */

    public void setEndSite(String endSite) {
        this.endSite = endSite;
    }

}
