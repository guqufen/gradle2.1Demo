package net.fnsco.order.service.domain;

import java.util.Date;
import java.util.List;

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

    private String merName;

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
    
    private Integer agentId;
    
    private List<MerchantFile> files;//关联的所有文件信息
    
    private List<MerchantContact> contacts;//关联的联系人信息
    
    private List<MerchantTerminal> terminal;//关联的终端信息
    
    private List<MerchantChannel> channel;//关联的渠道信息
    
    private List<MerchantBank> banks;//关联的银行卡信息
    
    
    public List<MerchantFile> getFiles() {
		return files;
	}

	public void setFiles(List<MerchantFile> files) {
		this.files = files;
	}

	public List<MerchantContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<MerchantContact> contacts) {
		this.contacts = contacts;
	}
	/**
     * terminal
     *
     * @return  the terminal
     * @since   CodingExample Ver 1.0
    */
    
    public List<MerchantTerminal> getTerminal() {
        return terminal;
    }
    /**
     * agentId
     *
     * @return  the agentId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getAgentId() {
        return agentId;
    }

    /**
     * agentId
     *
     * @param   agentId    the agentId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    /**
     * terminal
     *
     * @param   terminal    the terminal to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTerminal(List<MerchantTerminal> terminal) {
        this.terminal = terminal;
    }
	/**
     * channel
     *
     * @return  the channel
     * @since   CodingExample Ver 1.0
    */
    
    public List<MerchantChannel> getChannel() {
        return channel;
    }

    /**
     * channel
     *
     * @param   channel    the channel to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setChannel(List<MerchantChannel> channel) {
        this.channel = channel;
    }

    /**
     * banks
     *
     * @return  the banks
     * @since   CodingExample Ver 1.0
    */
    
    public List<MerchantBank> getBanks() {
        return banks;
    }

    /**
     * banks
     *
     * @param   banks    the banks to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setBanks(List<MerchantBank> banks) {
        this.banks = banks;
    }

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

    public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
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