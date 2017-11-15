package net.fnsco.risk.service.sys.entity;

import java.io.Serializable;
import java.util.Date;

public class MerAllocationDO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;// 主键

	private String innerCode;// 内部商户号

	private String entityInnerCode;// 实体商户号

	private String merName;// 商户名称

	private String abbreviation;//

	private String enName;// 商户英文名称

	private String signDate;// 登记时间

	private String legalPerson;// 商户法人姓名

	private String legalPersonMobile;// 商户法人手机号码

	private String legalPersonTel;// 法人联系电话

	private String legalValidCardType;// 法人有效证件：0-身份证

	private String cardNum;// 证件号码

	private String cardValidTime;// 证件有效期

	private String businessLicenseNum;// 营业执照号码

	private String businessLicenseValidTime;// 营业执照有效期

	private String taxRegistCode;// 税务登记号

	private String registAddress;// 商户注册地址

	private String mercFlag;// 商户标签

	private Integer source;// 商户注册来源

	private String sourceStr;// 注册来源说明：0-web；1-App；2-浙付通导入

	private String modifyUserId;// 创建人ID

	private Date modifyTime;// 创建时间

	private Integer status;// 状态：0-删除；1-正常

	private Integer agentId;// 代理商ID<商户所属ID>

	private String agentStr;// 代理商名称

	private Integer reportStatus;// 商户报表状态：0待审核1审核通过2审核失败3待编辑4已提交的待编辑

	private String openFixQr;

	public Integer getReportStatus() {
		return reportStatus;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAgentStr() {
		return agentStr;
	}

	public void setAgentStr(String agentStr) {
		this.agentStr = agentStr;
	}

	/**
	 * openFixQr
	 *
	 * @return the openFixQr
	 * @since CodingExample Ver 1.0
	 */

	public String getOpenFixQr() {
		return openFixQr;
	}

	/**
	 * openFixQr
	 *
	 * @param openFixQr
	 *            the openFixQr to set
	 * @since CodingExample Ver 1.0
	 */

	public void setOpenFixQr(String openFixQr) {
		this.openFixQr = openFixQr;
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

	public String getSourceStr() {
		return sourceStr;
	}

	public void setSourceStr(String sourceStr) {
		this.sourceStr = sourceStr;
	}
}
