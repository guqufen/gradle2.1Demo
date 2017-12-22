package net.fnsco.trading.service.third.ticket.entity;


public class TicketContactDO {

    /**
     * 
     */
    private Integer id;

    /**
     * app用户ID
     */
    private Integer appUserId;

    /**
     * 联系人名
     */
    private String name;

    /**
     * 联系人手机
     */
    private String mobile;

    /**
     * 证件类型0身份证1护照2士兵证3军官证4港澳台通行证
     */
    private String cardType;

    /**
     * 证件号码
     */
    private String cardNum;

    /**
     * 乘客类型（学生票）
     */
    private String ticketType;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", appUserId="+ appUserId + ", name="+ name + ", mobile="+ mobile + ", cardType="+ cardType + ", cardNum="+ cardNum + ", ticketType="+ ticketType + "]";
    }
}