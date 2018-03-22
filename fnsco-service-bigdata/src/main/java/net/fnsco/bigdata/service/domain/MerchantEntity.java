package net.fnsco.bigdata.service.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @desc  商户店铺信息
 * @author   tangliang
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年10月26日 下午2:47:10
 */
public class MerchantEntity {
	
	private Integer id;

    private String entityInnerCode;

    private String mercName;

    private String legalPerson;

    private String legalPersonMobile;

    private String businessLicenseNum;

    private String cardType;

    private String cardNum;

    private Integer status;

    private Date createTimer;

    private String createSource;

    private Integer createUserId;

    private Date lastModefyTimer;

    private Integer lastModefyUserId;
    
    private BigDecimal scores;
    
    //新增字段
    private Integer agentId;
    private String registAddress; //拼接的详细地址
	private Integer registProvince;//省
    private Integer registCity;//市
    private Integer registArea;//区
    private Integer etpsAttr;//商户性质
    private Integer etpsTp;//商户种类
    private String registAddressDetail;//手写详细地址
    
    private String registProvinceName;  //省名称
    private String registCityName; //市名称
    private String registAreaName; //区名称
    private String industryCode;//行业代码
    
    private String industryName;
    
    
    
    
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

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getRegistCityName() {
		return registCityName;
	}

	public void setRegistCityName(String registCityName) {
		this.registCityName = registCityName;
	}

	public String getRegistAreaName() {
		return registAreaName;
	}

	public void setRegistAreaName(String registAreaName) {
		this.registAreaName = registAreaName;
	}

	public String getRegistAddress() {
		return registAddress;
	}

	public void setRegistAddress(String registAddress) {
		this.registAddress = registAddress;
	}

	public String getRegistProvinceName() {
		return registProvinceName;
	}

	public void setRegistProvinceName(String registProvinceName) {
		this.registProvinceName = registProvinceName;
	}

	

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

	public BigDecimal getScores() {
		return scores;
	}

	public void setScores(BigDecimal scores) {
		this.scores = scores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getMercName() {
		return mercName;
	}

	public void setMercName(String mercName) {
		this.mercName = mercName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}

	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
	}

	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}

	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTimer() {
		return createTimer;
	}

	public void setCreateTimer(Date createTimer) {
		this.createTimer = createTimer;
	}

	public String getCreateSource() {
		return createSource;
	}

	public void setCreateSource(String createSource) {
		this.createSource = createSource;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getLastModefyTimer() {
		return lastModefyTimer;
	}

	public void setLastModefyTimer(Date lastModefyTimer) {
		this.lastModefyTimer = lastModefyTimer;
	}

	public Integer getLastModefyUserId() {
		return lastModefyUserId;
	}

	public void setLastModefyUserId(Integer lastModefyUserId) {
		this.lastModefyUserId = lastModefyUserId;
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MerchantEntity [id=" + id + ", entityInnerCode=" + entityInnerCode + ", mercName=" + mercName
				+ ", legalPerson=" + legalPerson + ", legalPersonMobile=" + legalPersonMobile + ", businessLicenseNum="
				+ businessLicenseNum + ", cardType=" + cardType + ", cardNum=" + cardNum + ", status=" + status
				+ ", createTimer=" + createTimer + ", createSource=" + createSource + ", createUserId=" + createUserId
				+ ", lastModefyTimer=" + lastModefyTimer + ", lastModefyUserId=" + lastModefyUserId + ", scores="
				+ scores + ", agentId=" + agentId + ", registAddress=" + registAddress + ", registProvince="
				+ registProvince + ", registCity=" + registCity + ", registArea=" + registArea + ", etpsAttr="
				+ etpsAttr + ", etpsTp=" + etpsTp + ", registAddressDetail=" + registAddressDetail
				+ ", registProvinceName=" + registProvinceName + ", registCityName=" + registCityName
				+ ", registAreaName=" + registAreaName + ", industryCode=" + industryCode + ", industryName="
				+ industryName + "]";
	}
	
	
    
}