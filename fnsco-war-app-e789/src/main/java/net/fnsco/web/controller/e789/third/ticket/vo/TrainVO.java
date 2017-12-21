package net.fnsco.web.controller.e789.third.ticket.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class TrainVO extends VO {

    @ApiModelProperty(value = "座位价格列表", example = "座位价格列表")
    private List<SeatTypeVO> seatTypeList;
    @ApiModelProperty(value = "出发时间", example = "出发时间")
    private String           startTime;

    @ApiModelProperty(value = "到达时间", example = "到达时间")
    private String           endTime;

    @ApiModelProperty(value = "车号", example = "车号")
    private String           trainCode;

    @ApiModelProperty(value = "耗时", example = "耗时")
    private String           duration;

    @ApiModelProperty(value = "乘车日期", example = "乘车日期")
    private String           trainDate;

    @ApiModelProperty(value = "出发站编号", example = "出发站编号")
    private String           fromStationCode;

    @ApiModelProperty(value = "出发站名称", example = "出发站名称")
    private String           fromStationName;

    @ApiModelProperty(value = "到达站编号", example = "到达站编号")
    private String           toStationCode;

    @ApiModelProperty(value = "到达站名称", example = "到达站名称")
    private String           toStationName;
    
    @ApiModelProperty(value = "当前是否可以接受预定Y是或N", example = "当前是否可以接受预定Y或N")
    private String           canBuyNow;

    /**
     * canBuyNow
     *
     * @return  the canBuyNow
     * @since   CodingExample Ver 1.0
    */

    public String getCanBuyNow() {
        return canBuyNow;
    }

    /**
     * canBuyNow
     *
     * @param   canBuyNow    the canBuyNow to set
     * @since   CodingExample Ver 1.0
     */

    public void setCanBuyNow(String canBuyNow) {
        this.canBuyNow = canBuyNow;
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
     * seatTypeList
     *
     * @return  the seatTypeList
     * @since   CodingExample Ver 1.0
    */

    public List<SeatTypeVO> getSeatTypeList() {
        return seatTypeList;
    }

    /**
     * seatTypeList
     *
     * @param   seatTypeList    the seatTypeList to set
     * @since   CodingExample Ver 1.0
     */

    public void setSeatTypeList(List<SeatTypeVO> seatTypeList) {
        this.seatTypeList = seatTypeList;
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

}
