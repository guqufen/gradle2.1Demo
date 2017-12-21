package net.fnsco.trading.service.order.entity;

import java.util.Date;

public class TradeOrderExtDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String orderNo;

    /**
     * 联系人名
     */
    private String name;

    /**
     * 联系人手机
     */
    private String mobile;

    /**
     * 电话
     */
    private String card;

    /**
     * 
     */
    private Date createTime;



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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", orderNo="+ orderNo + ", name="+ name + ", mobile="+ mobile + ", card="+ card + ", createTime="+ createTime + "]";
    }
}