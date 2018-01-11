package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.fnsco.core.base.DTO;

//@XmlRootElement(name = "HOResultSet4032R")
@XmlAccessorType(XmlAccessType.FIELD) 
public class E4032BodyDTO extends DTO {

	private static final long serialVersionUID = 1L;
//	@XmlElement(name = "SThirdVoucher", required = true)
	private String SThirdVoucher;// 单笔凭证号,批次内不重复
//	@XmlElement(name = "CstInnerFlowNo", required = true)
	private String CstInnerFlowNo;// 自定义流水号，此字段为订单号，需保持唯一
//	@XmlElement(name = "OppAccNo", required = true)
	private String OppAccNo;// 付款人帐号
//	@XmlElement(name = "OppAccName", required = true)
	private String OppAccName;// 付款人户名
//	@XmlElement(name = "OppBank", required = true)
	private String OppBank;// 付款人银行,见附录银行代码
//	@XmlElement(name = "CardAcctFlag", required = true)
	private String CardAcctFlag;// 卡折标志0-借记卡；1-存折；2-对公账号
//	@XmlElement(name = "Amount", required = true)
	private String Amount;// 金额
//	@XmlElement(name = "IdType", required = true)
	private String IdType;// 证件类型
//	@XmlElement(name = "IdNo", required = true)
	private String IdNo;// 证件号码
//	@XmlElement(name = "Mobile", required = true)
	private String Mobile;// 手机号
//	@XmlElement(name = "PostScript", required = true)
	private String PostScript;// 附言，备注

	public String getSThirdVoucher() {
		return SThirdVoucher;
	}

	public void setSThirdVoucher(String sThirdVoucher) {
		SThirdVoucher = sThirdVoucher;
	}

	public String getCstInnerFlowNo() {
		return CstInnerFlowNo;
	}

	public void setCstInnerFlowNo(String cstInnerFlowNo) {
		CstInnerFlowNo = cstInnerFlowNo;
	}

	public String getOppAccNo() {
		return OppAccNo;
	}

	public void setOppAccNo(String oppAccNo) {
		OppAccNo = oppAccNo;
	}

	public String getOppAccName() {
		return OppAccName;
	}

	public void setOppAccName(String oppAccName) {
		OppAccName = oppAccName;
	}

	public String getOppBank() {
		return OppBank;
	}

	public void setOppBank(String oppBank) {
		OppBank = oppBank;
	}

	public String getCardAcctFlag() {
		return CardAcctFlag;
	}

	public void setCardAcctFlag(String cardAcctFlag) {
		CardAcctFlag = cardAcctFlag;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getIdType() {
		return IdType;
	}

	public void setIdType(String idType) {
		IdType = idType;
	}

	public String getIdNo() {
		return IdNo;
	}

	public void setIdNo(String idNo) {
		IdNo = idNo;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getPostScript() {
		return PostScript;
	}

	public void setPostScript(String postScript) {
		PostScript = postScript;
	}

}
