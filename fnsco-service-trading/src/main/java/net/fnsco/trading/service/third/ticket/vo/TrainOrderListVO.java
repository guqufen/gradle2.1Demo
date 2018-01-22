package net.fnsco.trading.service.third.ticket.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class TrainOrderListVO extends VO {

    @ApiModelProperty(value = "订单客户信息列表", example = "订单客户信息列表")
    private List<OrderContactVO> orderContactList;

    // @ApiModelProperty(value = "出发时间", example = "出发时间")
    @ApiModelProperty(value = "车号", example = "车号")
    private String               trainCode;

    @ApiModelProperty(value = "乘车日期", example = "乘车日期")
    private String               trainDate;

    @ApiModelProperty(value = "出发站编号", example = "出发站编号")
    private String               fromStationCode;

    @ApiModelProperty(value = "出发站名称", example = "出发站名称")
    private String               fromStationName;

    @ApiModelProperty(value = "到达站编号", example = "到达站编号")
    private String               toStationCode;

    @ApiModelProperty(value = "到达站名称", example = "到达站名称")
    private String               toStationName;

    @ApiModelProperty(value = "订单唯一标识", example = "订单唯一标识")
    private Integer              id;

    @ApiModelProperty(value = "订单编号", example = "订单编号")
    private String               orderNo;

    @ApiModelProperty(value = "支付订单ID", example = "支付订单ID")
    private String               payOrderNo;

    @ApiModelProperty(value = "状态 0未占座1占座中2已占座3占座失败4支付中5支付完成6取消订单7退票中8退票完成", example = "状态")
    private Integer              status;

    @ApiModelProperty(value = "状态 0未占座1占座中2已占座3占座失败4支付中5支付完成6取消订单7退票中8退票完成", example = "状态")
    private String               statusName;

    @ApiModelProperty(value = "创建时间", example = "创建时间")
    private String               createTime;
    @ApiModelProperty(value = "出发时间", example = "出发时间")
    private String               startTime;
    @ApiModelProperty(value = "到达时间", example = "到达时间")
    private String               arriveTime;
    @ApiModelProperty(value = "耗时", example = "耗时")
    private String               runTime;
    /**
     * 应答信息
     */
    private String               respMsg;

    /**
     * respMsg
     *
     * @return  the respMsg
     * @since   CodingExample Ver 1.0
    */

    public String getRespMsg() {
        return respMsg;
    }

    /**
     * respMsg
     *
     * @param   respMsg    the respMsg to set
     * @since   CodingExample Ver 1.0
     */

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
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
     * arriveTime
     *
     * @return  the arriveTime
     * @since   CodingExample Ver 1.0
    */

    public String getArriveTime() {
        return arriveTime;
    }

    /**
     * arriveTime
     *
     * @param   arriveTime    the arriveTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * runTime
     *
     * @return  the runTime
     * @since   CodingExample Ver 1.0
    */

    public String getRunTime() {
        return runTime;
    }

    /**
     * runTime
     *
     * @param   runTime    the runTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    /**
     * statusName
     *
     * @return  the statusName
     * @since   CodingExample Ver 1.0
    */

    public String getStatusName() {
        return statusName;
    }

    /**
     * statusName
     *
     * @param   statusName    the statusName to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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
     * orderContactList
     *
     * @return  the orderContactList
     * @since   CodingExample Ver 1.0
    */

    public List<OrderContactVO> getOrderContactList() {
        return orderContactList;
    }

    /**
     * orderContactList
     *
     * @param   orderContactList    the orderContactList to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderContactList(List<OrderContactVO> orderContactList) {
        this.orderContactList = orderContactList;
    }

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
     * payOrderNo
     *
     * @return  the payOrderNo
     * @since   CodingExample Ver 1.0
    */

    public String getPayOrderNo() {
        return payOrderNo;
    }

    /**
     * payOrderNo
     *
     * @param   payOrderNo    the payOrderNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

    /**
     * status
     *
     * @return  the status
     * @since   CodingExample Ver 1.0
    */

    public Integer getStatus() {
        return status;
    }

    /**
     * status
     *
     * @param   status    the status to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * createTime
     *
     * @return  the createTime
     * @since   CodingExample Ver 1.0
    */

    public String getCreateTime() {
        return createTime;
    }

    /**
     * createTime
     *
     * @param   createTime    the createTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
