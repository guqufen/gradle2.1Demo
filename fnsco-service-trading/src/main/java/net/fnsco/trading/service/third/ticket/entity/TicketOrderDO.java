package net.fnsco.trading.service.third.ticket.entity;

import java.util.Date;

public class TicketOrderDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 订单ID
     */
    private String orderNo;

    /**
     * 乘车日期
     */
    private String trainDate;

    /**
     * 出发站编号
     */
    private String fromStationCode;

    /**
     * 出发站名称
     */
    private String fromStationName;

    /**
     * 到达站编号
     */
    private String toStationCode;

    /**
     * 到达站名称
     */
    private String toStationName;

    /**
     * 车次（G65）
     */
    private String trainCode;

    /**
     * 状态 0未执行1执行中2失败3成功
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date lastModifyTime;



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
        return "[id="+ id + ", orderNo="+ orderNo + ", trainDate="+ trainDate + ", fromStationCode="+ fromStationCode + ", fromStationName="+ fromStationName + ", toStationCode="+ toStationCode + ", toStationName="+ toStationName + ", trainCode="+ trainCode + ", status="+ status + ", createTime="+ createTime + ", lastModifyTime="+ lastModifyTime + "]";
    }
}