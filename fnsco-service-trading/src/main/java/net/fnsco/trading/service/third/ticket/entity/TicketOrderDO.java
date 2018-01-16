package net.fnsco.trading.service.third.ticket.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TicketOrderDO {

    /**
     * 
     */
    private Integer    id;

    /**
     * app用户ID
     */
    private Integer    appUserId;

    /**
     * 订单ID
     */
    private String     orderNo;
    /**
     * 聚合数据支付订单ID
     */
    private String     payOrderNo;
    /**
     * 乘车日期
     */
    private String     trainDate;

    /**
     * 出发站编号
     */
    private String     fromStationCode;

    /**
     * 出发站名称
     */
    private String     fromStationName;

    /**
     * 到达站编号
     */
    private String     toStationCode;

    /**
     * 到达站名称
     */
    private String     toStationName;

    /**
     * 车次（G65）
     */
    private String     trainCode;

    /**
     * 此订单的总金额
     */
    private BigDecimal orderAmount;

    /**
     * 取票订单号，等同于您在12306官网订票时提供的订单编号
     */
    private String     trainOrderNumber;

    /**
     * 支付时间，请求出票（从聚合账户扣款）时间
     */
    private Date       payTime;

    /**
     * 应答信息
     */
    private String     respMsg;

    /**
     * 应答码
     */
    private String     respCode;

    /**
     * 状态 0未占座1占座中2已占座3支付完成4取消订单
     */
    private Integer    status;

    /**
     * 创建时间
     */
    private Date       createTime;

    /**
     * 最后更新时间
     */
    private Date       lastModifyTime;
    /**
     * 到达时刻 "12:37"
     */
    private String     arriveTime;

    /**
     * 当前站出发时刻07:00
     */
    private String     startTime;

    /**
     * 历时（出发站到目的站） "05:37"
     */
    private String     runTime;

    private Integer[]  statuses;

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
     * statuses
     *
     * @return  the statuses
     * @since   CodingExample Ver 1.0
    */

    public Integer[] getStatuses() {
        return statuses;
    }

    /**
     * statuses
     *
     * @param   statuses    the statuses to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatuses(Integer[] statuses) {
        this.statuses = statuses;
    }

    /**
     * appUserId
     *
     * @return  the appUserId
     * @since   CodingExample Ver 1.0
    */

    public Integer getAppUserId() {
        return appUserId;
    }

    /**
     * appUserId
     *
     * @param   appUserId    the appUserId to set
     * @since   CodingExample Ver 1.0
     */

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    /**
     * orderAmount
     *
     * @return  the orderAmount
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * orderAmount
     *
     * @param   orderAmount    the orderAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * trainOrderNumber
     *
     * @return  the trainOrderNumber
     * @since   CodingExample Ver 1.0
    */

    public String getTrainOrderNumber() {
        return trainOrderNumber;
    }

    /**
     * trainOrderNumber
     *
     * @param   trainOrderNumber    the trainOrderNumber to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrainOrderNumber(String trainOrderNumber) {
        this.trainOrderNumber = trainOrderNumber;
    }

    /**
     * payTime
     *
     * @return  the payTime
     * @since   CodingExample Ver 1.0
    */

    public Date getPayTime() {
        return payTime;
    }

    /**
     * payTime
     *
     * @param   payTime    the payTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

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
     * respCode
     *
     * @return  the respCode
     * @since   CodingExample Ver 1.0
    */

    public String getRespCode() {
        return respCode;
    }

    /**
     * respCode
     *
     * @param   respCode    the respCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setRespCode(String respCode) {
        this.respCode = respCode;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getFromStationCode() {
        return fromStationCode;
    }

    public void setFromStationCode(String fromStationCode) {
        this.fromStationCode = fromStationCode;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public String getToStationCode() {
        return toStationCode;
    }

    public void setToStationCode(String toStationCode) {
        this.toStationCode = toStationCode;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", orderNo=" + orderNo + ", trainDate=" + trainDate + ", fromStationCode=" + fromStationCode + ", fromStationName=" + fromStationName + ", toStationCode=" + toStationCode
               + ", toStationName=" + toStationName + ", trainCode=" + trainCode + ", status=" + status + ", createTime=" + createTime + ", lastModifyTime=" + lastModifyTime + "]";
    }
}