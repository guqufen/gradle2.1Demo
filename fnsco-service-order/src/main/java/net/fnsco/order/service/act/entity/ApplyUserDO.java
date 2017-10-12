package net.fnsco.order.service.act.entity;


public class ApplyUserDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 申请人联系电话
     */
    private String contactNum;

    /**
     * 店铺名称
     */
    private String mercName;

    /**
     * 推荐人姓名
     */
    private String fromUserName;

    /**
     * 推荐人联系号码
     */
    private String fromUserContactNum;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getMercName() {
        return mercName;
    }

    public void setMercName(String mercName) {
        this.mercName = mercName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserContactNum() {
        return fromUserContactNum;
    }

    public void setFromUserContactNum(String fromUserContactNum) {
        this.fromUserContactNum = fromUserContactNum;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", userName="+ userName + ", contactNum="+ contactNum + ", mercName="+ mercName + ", fromUserName="+ fromUserName + ", fromUserContactNum="+ fromUserContactNum + "]";
    }
}