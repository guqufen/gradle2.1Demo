package net.fnsco.bigdata.service.domain;

import java.util.Date;
import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc 商户基本信息表
 * @author tangliang
 * @date 2017年6月21日 下午1:46:30
 */
public class MerchantCore extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841374213021310345L;

	private Integer id;

	private String innerCode;

	private String merName;
	
	private String merId;

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
	private String sourceStr;
	
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String modifyUserId;

	private Date modifyTime;

	private Integer status;

	private Integer agentId;
	
	private Date createTime;
	
	private String createTimeStr;

	private List<MerchantFile> files;// 关联的所有文件信息

	private List<MerchantContact> contacts;// 关联的联系人信息

	private List<MerchantChannel> channel;// 关联的渠道信息

	private List<MerchantBank> banks;// 关联的银行卡信息
	private String openFixQr;
	
	private String channelType; //渠道类型
	
	private String entityInnerCode;//实体商户innerCode
	
	private String entityMerName;//实体商户名称
	
	//新增省市区字段，商户性质商户类别
	 private Integer registProvince;//省
	 private Integer registCity;//市
	 private Integer registArea;//区
	 private Integer etpsAttr;//商户性质
	 private Integer etpsTp;//商户种类 
	 private String registAddressDetail;
	
	

	public String getRegistAddressDetail() {
		return registAddressDetail;
	}

	public void setRegistAddressDetail(String registAddressDetail) {
		this.registAddressDetail = registAddressDetail;
	}

	public Integer getRegistProvince() {
		return registProvince;
	}

	public void setRegistProvince(Integer registProvince) {
		this.registProvince = registProvince;
	}

	public Integer getRegistCity() {
		return registCity;
	}

	public void setRegistCity(Integer registCity) {
		this.registCity = registCity;
	}

	public Integer getRegistArea() {
		return registArea;
	}

	public void setRegistArea(Integer registArea) {
		this.registArea = registArea;
	}

	public Integer getEtpsAttr() {
		return etpsAttr;
	}

	public void setEtpsAttr(Integer etpsAttr) {
		this.etpsAttr = etpsAttr;
	}

	public Integer getEtpsTp() {
		return etpsTp;
	}

	public void setEtpsTp(Integer etpsTp) {
		this.etpsTp = etpsTp;
	}

	public String getEntityMerName() {
		return entityMerName;
	}

	public void setEntityMerName(String entityMerName) {
		this.entityMerName = entityMerName;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
     * openFixQr
     *
     * @return  the openFixQr
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenFixQr() {
        return openFixQr;
    }

    /**
     * openFixQr
     *
     * @param   openFixQr    the openFixQr to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenFixQr(String openFixQr) {
        this.openFixQr = openFixQr;
    }

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
	 * agentId
	 *
	 * @return the agentId
	 * @since CodingExample Ver 1.0
	 */

	public Integer getAgentId() {
		return agentId;
	}

	/**
	 * agentId
	 *
	 * @param agentId
	 *            the agentId to set
	 * @since CodingExample Ver 1.0
	 */

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	/**
	 * channel
	 *
	 * @return the channel
	 * @since CodingExample Ver 1.0
	 */

	private String modifyTimeStr;

	public String getModifyTimeStr() {
		return modifyTimeStr;
	}

	public void setModifyTimeStr(String modifyTimeStr) {
		this.modifyTimeStr = modifyTimeStr;
	}

	public List<MerchantChannel> getChannel() {
		return channel;
	}

	/**
	 * channel
	 *
	 * @param channel
	 *            the channel to set
	 * @since CodingExample Ver 1.0
	 */

	public void setChannel(List<MerchantChannel> channel) {
		this.channel = channel;
	}

	/**
	 * banks
	 *
	 * @return the banks
	 * @since CodingExample Ver 1.0
	 */

	public List<MerchantBank> getBanks() {
		return banks;
	}

	/**
	 * banks
	 *
	 * @param banks
	 *            the banks to set
	 * @since CodingExample Ver 1.0
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
	
	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
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
	public String getSourceStr() {
		return sourceStr;
	}

	public void setSourceStr(String sourceStr) {
		this.sourceStr = sourceStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}