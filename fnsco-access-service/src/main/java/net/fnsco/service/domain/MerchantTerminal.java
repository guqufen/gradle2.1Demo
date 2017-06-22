package net.fnsco.service.domain;

import com.alibaba.fastjson.JSONObject;

import net.fnsco.core.base.DTO;

/**
 * @desc 商家终端信息表 
 * @author tangliang
 * @date 2017年6月22日 下午3:16:09
 */
public class MerchantTerminal extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1471352444275357646L;

	private Integer id;

    private String innerCode;

    private String merchantCode;

    private Integer channelId;

    private String channelName;

    private String terminalCode;

    private String innerTermCode;

    private String snCode;

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

    private String posFactory;

    private String posType;

    private String mercReferName;

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

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    public String getInnerTermCode() {
        return innerTermCode;
    }

    public void setInnerTermCode(String innerTermCode) {
        this.innerTermCode = innerTermCode == null ? null : innerTermCode.trim();
    }

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode == null ? null : snCode.trim();
    }

    public String getTerminalBatch() {
        return terminalBatch;
    }

    public void setTerminalBatch(String terminalBatch) {
        this.terminalBatch = terminalBatch == null ? null : terminalBatch.trim();
    }

    public String getTerminalPara() {
        return terminalPara;
    }

    public void setTerminalPara(String terminalPara) {
        this.terminalPara = terminalPara == null ? null : terminalPara.trim();
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
        this.debitCardRate = debitCardRate == null ? null : debitCardRate.trim();
    }

    public String getCreditCardRate() {
        return creditCardRate;
    }

    public void setCreditCardRate(String creditCardRate) {
        this.creditCardRate = creditCardRate == null ? null : creditCardRate.trim();
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
        this.dealSwitch = dealSwitch == null ? null : dealSwitch.trim();
    }

    public String getRecordState() {
        return recordState;
    }

    public void setRecordState(String recordState) {
        this.recordState = recordState == null ? null : recordState.trim();
    }

    public String getTermAuditState() {
        return termAuditState;
    }

    public void setTermAuditState(String termAuditState) {
        this.termAuditState = termAuditState == null ? null : termAuditState.trim();
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName == null ? null : termName.trim();
    }

    public String getPosFactory() {
        return posFactory;
    }

    public void setPosFactory(String posFactory) {
        this.posFactory = posFactory == null ? null : posFactory.trim();
    }

    public String getPosType() {
        return posType;
    }

    public void setPosType(String posType) {
        this.posType = posType == null ? null : posType.trim();
    }

    public String getMercReferName() {
        return mercReferName;
    }

    public void setMercReferName(String mercReferName) {
        this.mercReferName = mercReferName == null ? null : mercReferName.trim();
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return JSONObject.toJSONString(this);
    }
    
    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return super.hashCode();
    }
}