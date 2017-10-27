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
    
}