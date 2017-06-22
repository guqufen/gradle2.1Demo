package net.fnsco.service.domain;

import java.util.Date;

import net.fnsco.core.base.DTO;
/**
 * @desc 商户基本信息表
 * @author tangliang
 * @date 2017年6月21日 下午1:46:30
 */
public class MerchantCore extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841374213021310345L;

	private Integer id;

    private String innerCode;

    private String name;

    private String abbreviation;

    private String enName;

    private String signDate;

    private String legalPerson;

    private String legalPersonMobile;

    private String legalPersonTel;

    private String legalValidCardType;

    private String cardNum;

    private String cardValidTime;

    private String businessLicenseNum;

    private String businessLicenseValidTime;

    private String taxRegistCode;

    private String registAddress;

    private String mercFlag;

    private Integer source;

    private String modifyUserId;

    private Date modifyTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate == null ? null : signDate.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile == null ? null : legalPersonMobile.trim();
    }

    public String getLegalPersonTel() {
        return legalPersonTel;
    }

    public void setLegalPersonTel(String legalPersonTel) {
        this.legalPersonTel = legalPersonTel == null ? null : legalPersonTel.trim();
    }

    public String getLegalValidCardType() {
        return legalValidCardType;
    }

    public void setLegalValidCardType(String legalValidCardType) {
        this.legalValidCardType = legalValidCardType == null ? null : legalValidCardType.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getCardValidTime() {
        return cardValidTime;
    }

    public void setCardValidTime(String cardValidTime) {
        this.cardValidTime = cardValidTime == null ? null : cardValidTime.trim();
    }

    public String getBusinessLicenseNum() {
        return businessLicenseNum;
    }

    public void setBusinessLicenseNum(String businessLicenseNum) {
        this.businessLicenseNum = businessLicenseNum == null ? null : businessLicenseNum.trim();
    }

    public String getBusinessLicenseValidTime() {
        return businessLicenseValidTime;
    }

    public void setBusinessLicenseValidTime(String businessLicenseValidTime) {
        this.businessLicenseValidTime = businessLicenseValidTime == null ? null : businessLicenseValidTime.trim();
    }

    public String getTaxRegistCode() {
        return taxRegistCode;
    }

    public void setTaxRegistCode(String taxRegistCode) {
        this.taxRegistCode = taxRegistCode == null ? null : taxRegistCode.trim();
    }

    public String getRegistAddress() {
        return registAddress;
    }

    public void setRegistAddress(String registAddress) {
        this.registAddress = registAddress == null ? null : registAddress.trim();
    }

    public String getMercFlag() {
        return mercFlag;
    }

    public void setMercFlag(String mercFlag) {
        this.mercFlag = mercFlag == null ? null : mercFlag.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}