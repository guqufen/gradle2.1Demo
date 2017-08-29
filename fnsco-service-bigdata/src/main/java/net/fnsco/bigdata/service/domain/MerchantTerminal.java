package net.fnsco.bigdata.service.domain;

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

    private Integer posId;

    private String terminalCode;

    private String innerTermCode;

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
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    public String getInnerTermCode() {
        return innerTermCode;
    }

    public void setInnerTermCode(String innerTermCode) {
        this.innerTermCode = innerTermCode == null ? null : innerTermCode.trim();
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


    /**
     * wechatFee
     *
     * @return  the wechatFee
     * @since   CodingExample Ver 1.0
    */
    
    public String getWechatFee() {
        return wechatFee;
    }

    /**
     * wechatFee
     *
     * @param   wechatFee    the wechatFee to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setWechatFee(String wechatFee) {
        this.wechatFee = wechatFee;
    }

    /**
     * alipayFee
     *
     * @return  the alipayFee
     * @since   CodingExample Ver 1.0
    */
    
    public String getAlipayFee() {
        return alipayFee;
    }

    /**
     * alipayFee
     *
     * @param   alipayFee    the alipayFee to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAlipayFee(String alipayFee) {
        this.alipayFee = alipayFee;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
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