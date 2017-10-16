package net.fnsco.order.service.act.entity;


public class LoanApplyUserDO {

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
     * 证件类型0身份证1护照2士兵证3军官证4港澳台通行证
     */
    private String cardType;

    /**
     * 身份证号码
     */
    private String cardNum;

    /**
     * 贷款产品类型0贷贷看
     */
    private String loanType;

    /**
     * 内部商户号 15位
     */
    private String innnerCode;



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

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getInnnerCode() {
        return innnerCode;
    }

    public void setInnnerCode(String innnerCode) {
        this.innnerCode = innnerCode;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", userName="+ userName + ", contactNum="+ contactNum + ", cardType="+ cardType + ", cardNum="+ cardNum + ", loanType="+ loanType + ", innnerCode="+ innnerCode + "]";
    }
}