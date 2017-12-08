package net.fnsco.trading.service.bank.entity;


public class AppUserBankDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 内部商户号 15位
     */
    private String appUserId;

    /**
     * 账户类型0对私1对公
     */
    private String accountType;

    /**
     * 开户账号
     */
    private String accountNo;

    /**
     * 账户开户名
     */
    private String accountName;

    /**
     * 开户人身份证号
     */
    private String accountCardId;

    /**
     * 支行名称
     */
    private String subBankName;

    /**
     * 开户行所在省
     */
    private String openBankPrince;

    /**
     * 开户行
     */
    private String openBank;

    /**
     * 开户行所在市
     */
    private String openBankCity;

    /**
     * 开户行行号
     */
    private String openBankNum;

    /**
     * 开户手机号
     */
    private String accountPhone;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountCardId() {
        return accountCardId;
    }

    public void setAccountCardId(String accountCardId) {
        this.accountCardId = accountCardId;
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    public String getOpenBankPrince() {
        return openBankPrince;
    }

    public void setOpenBankPrince(String openBankPrince) {
        this.openBankPrince = openBankPrince;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getOpenBankCity() {
        return openBankCity;
    }

    public void setOpenBankCity(String openBankCity) {
        this.openBankCity = openBankCity;
    }

    public String getOpenBankNum() {
        return openBankNum;
    }

    public void setOpenBankNum(String openBankNum) {
        this.openBankNum = openBankNum;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", appUserId="+ appUserId + ", accountType="+ accountType + ", accountNo="+ accountNo + ", accountName="+ accountName + ", accountCardId="+ accountCardId + ", subBankName="+ subBankName + ", openBankPrince="+ openBankPrince + ", openBank="+ openBank + ", openBankCity="+ openBankCity + ", openBankNum="+ openBankNum + ", accountPhone="+ accountPhone + "]";
    }
}