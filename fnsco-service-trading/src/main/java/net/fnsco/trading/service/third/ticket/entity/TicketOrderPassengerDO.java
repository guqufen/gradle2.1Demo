package net.fnsco.trading.service.third.ticket.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TicketOrderPassengerDO {

    /**
     * 
     */
    private Integer    id;

    /**
     * 订单ID
     */
    private String     orderNo;

    /**
     * 乘客id
     */
    private Integer    passengerId;

    /**
     * 乘客名称
     */
    private String     passengerName;

    /**
     * 车票类型
     */
    private String     ticketType;

    /**
     * 车票类型名称
     */
    private String     ticketTypeName;

    /**
     * 证件类型id
     */
    private String     cardTypeId;

    /**
     * 证件类型名称
     */
    private String     cardTypeName;

    /**
     * 证件号
     */
    private String     cardNum;

    /**
     * 票价
     */
    private BigDecimal price;

    /**
     * 座位编号
     */
    private String     seatCode;

    /**
     * 座位名称
     */
    private String     seatName;

    /**
     * 创建时间
     */
    private Date       createTime;

    /**
     * 
     */
    private Date       lastModifyTime;
    /**
     * 车票编号，退票时会用到
     */
    private String     ticketNo;
    /**
     * 车厢08车厢,010座
     */
    private String     cxin;
    /**
     * 退款金额
     */
    private BigDecimal returnMoney;
    /**
     * 失败原因，退票失败时有值
     */
    private String     returnFailMsg;

    /**
     * returnMoney
     *
     * @return  the returnMoney
     * @since   CodingExample Ver 1.0
    */

    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    /**
     * returnMoney
     *
     * @param   returnMoney    the returnMoney to set
     * @since   CodingExample Ver 1.0
     */

    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }

    /**
     * returnFailMsg
     *
     * @return  the returnFailMsg
     * @since   CodingExample Ver 1.0
    */

    public String getReturnFailMsg() {
        return returnFailMsg;
    }

    /**
     * returnFailMsg
     *
     * @param   returnFailMsg    the returnFailMsg to set
     * @since   CodingExample Ver 1.0
     */

    public void setReturnFailMsg(String returnFailMsg) {
        this.returnFailMsg = returnFailMsg;
    }

    /**
     * cxin
     *
     * @return  the cxin
     * @since   CodingExample Ver 1.0
    */

    public String getCxin() {
        return cxin;
    }

    /**
     * cxin
     *
     * @param   cxin    the cxin to set
     * @since   CodingExample Ver 1.0
     */

    public void setCxin(String cxin) {
        this.cxin = cxin;
    }

    /**
     * ticketNo
     *
     * @return  the ticketNo
     * @since   CodingExample Ver 1.0
    */

    public String getTicketNo() {
        return ticketNo;
    }

    /**
     * ticketNo
     *
     * @param   ticketNo    the ticketNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
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

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    public String getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(String cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
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
        return "[id=" + id + ", orderNo=" + orderNo + ", passengerId=" + passengerId + ", passengerName=" + passengerName + ", ticketType=" + ticketType + ", ticketTypeName=" + ticketTypeName
               + ", cardTypeId=" + cardTypeId + ", cardTypeName=" + cardTypeName + ", cardNum=" + cardNum + ", price=" + price + ", seatCode=" + seatCode + ", seatName=" + seatName + ",createTime="
               + createTime + ", lastModifyTime=" + lastModifyTime + "]";
    }
}