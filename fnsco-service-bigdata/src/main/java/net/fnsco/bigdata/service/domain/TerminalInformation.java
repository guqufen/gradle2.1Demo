package net.fnsco.bigdata.service.domain;

import java.util.Date;

public class TerminalInformation {
	//terminal
	private Integer id;

    private String innerCode;

    private Integer posId;

    private String terminalCode;

    private String channelTerminalCode;

    private String terminalBatch;

    private String terminalPara;

    private Integer chargesType;

    private String debitCardRate;

    private String creditCardRate;

    private Integer debitCardFee;

    private Integer creditCardFee;

    private Integer debitCardMaxFee;

    private Integer creditCardMaxFee;

    private String dealSwitch;

    private String recordState;

    private String termAuditState;

    private String termName;

    private String wechatFee;

    private String alipayFee;

    private String terminalType;
    
    //pos
    private Integer channelId;

    private String posName;

    private String snCode;

    private String posFactory;

    private String posType;

    private String status;
    
    private Integer bankId;
    
    private String mercReferName;
    
    private String posAddr;
    
    private Integer posProvince;
    
    private Integer posCity;
    
    private Integer posArea;

    //channel
    private String channelType;

    private String channelMerId;

    private String channelMerKey;

    private Date createTime;

    private Date modifyTime;

    private Integer modifyUserId;
    
    private String merName;

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
		this.innerCode = innerCode;
	}

	public Integer getPosId() {
		return posId;
	}

	public void setPosId(Integer posId) {
		this.posId = posId;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getChannelTerminalCode() {
		return channelTerminalCode;
	}

	public void setChannelTerminalCode(String channelTerminalCode) {
		this.channelTerminalCode = channelTerminalCode;
	}

	public String getTerminalBatch() {
		return terminalBatch;
	}

	public void setTerminalBatch(String terminalBatch) {
		this.terminalBatch = terminalBatch;
	}

	public String getTerminalPara() {
		return terminalPara;
	}

	public void setTerminalPara(String terminalPara) {
		this.terminalPara = terminalPara;
	}

	public Integer getChargesType() {
		return chargesType;
	}

	public void setChargesType(Integer chargesType) {
		this.chargesType = chargesType;
	}

	public String getDebitCardRate() {
		return debitCardRate;
	}

	public void setDebitCardRate(String debitCardRate) {
		this.debitCardRate = debitCardRate;
	}

	public String getCreditCardRate() {
		return creditCardRate;
	}

	public void setCreditCardRate(String creditCardRate) {
		this.creditCardRate = creditCardRate;
	}

	public Integer getDebitCardFee() {
		return debitCardFee;
	}

	public void setDebitCardFee(Integer debitCardFee) {
		this.debitCardFee = debitCardFee;
	}

	public Integer getCreditCardFee() {
		return creditCardFee;
	}

	public void setCreditCardFee(Integer creditCardFee) {
		this.creditCardFee = creditCardFee;
	}

	public Integer getDebitCardMaxFee() {
		return debitCardMaxFee;
	}

	public void setDebitCardMaxFee(Integer debitCardMaxFee) {
		this.debitCardMaxFee = debitCardMaxFee;
	}

	public Integer getCreditCardMaxFee() {
		return creditCardMaxFee;
	}

	public void setCreditCardMaxFee(Integer creditCardMaxFee) {
		this.creditCardMaxFee = creditCardMaxFee;
	}

	public String getDealSwitch() {
		return dealSwitch;
	}

	public void setDealSwitch(String dealSwitch) {
		this.dealSwitch = dealSwitch;
	}

	public String getRecordState() {
		return recordState;
	}

	public void setRecordState(String recordState) {
		this.recordState = recordState;
	}

	public String getTermAuditState() {
		return termAuditState;
	}

	public void setTermAuditState(String termAuditState) {
		this.termAuditState = termAuditState;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getWechatFee() {
		return wechatFee;
	}

	public void setWechatFee(String wechatFee) {
		this.wechatFee = wechatFee;
	}

	public String getAlipayFee() {
		return alipayFee;
	}

	public void setAlipayFee(String alipayFee) {
		this.alipayFee = alipayFee;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String getPosFactory() {
		return posFactory;
	}

	public void setPosFactory(String posFactory) {
		this.posFactory = posFactory;
	}

	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getMercReferName() {
		return mercReferName;
	}

	public void setMercReferName(String mercReferName) {
		this.mercReferName = mercReferName;
	}

	public String getPosAddr() {
		return posAddr;
	}

	public void setPosAddr(String posAddr) {
		this.posAddr = posAddr;
	}

	public Integer getPosProvince() {
		return posProvince;
	}

	public void setPosProvince(Integer posProvince) {
		this.posProvince = posProvince;
	}

	public Integer getPosCity() {
		return posCity;
	}

	public void setPosCity(Integer posCity) {
		this.posCity = posCity;
	}

	public Integer getPosArea() {
		return posArea;
	}

	public void setPosArea(Integer posArea) {
		this.posArea = posArea;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelMerId() {
		return channelMerId;
	}

	public void setChannelMerId(String channelMerId) {
		this.channelMerId = channelMerId;
	}

	public String getChannelMerKey() {
		return channelMerKey;
	}

	public void setChannelMerKey(String channelMerKey) {
		this.channelMerKey = channelMerKey;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}
    
}
