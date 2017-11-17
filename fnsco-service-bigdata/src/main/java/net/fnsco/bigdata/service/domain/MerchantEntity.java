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
    private Integer regist_province;//省
    private Integer regist_city;//市
    private Integer regist_area;//区
    private Integer etps_attr;//商户性质
    private Integer etps_tp;//商户种类

	public Integer getRegist_province() {
		return regist_province;
	}

	public void setRegist_province(Integer regist_province) {
		this.regist_province = regist_province;
	}

	public Integer getRegist_city() {
		return regist_city;
	}

	public void setRegist_city(Integer regist_city) {
		this.regist_city = regist_city;
	}

	public Integer getRegist_area() {
		return regist_area;
	}

	public void setRegist_area(Integer regist_area) {
		this.regist_area = regist_area;
	}

	public Integer getEtps_attr() {
		return etps_attr;
	}

	public void setEtps_attr(Integer etps_attr) {
		this.etps_attr = etps_attr;
	}

	public Integer getEtps_tp() {
		return etps_tp;
	}

	public void setEtps_tp(Integer etps_tp) {
		this.etps_tp = etps_tp;
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
    
}