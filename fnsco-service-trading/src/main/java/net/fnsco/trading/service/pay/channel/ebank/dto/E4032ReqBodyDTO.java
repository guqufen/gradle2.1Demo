package net.fnsco.trading.service.pay.channel.ebank.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "HOResultSet4032R")
@XmlAccessorType(XmlAccessType.FIELD)
public class E4032ReqBodyDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String SThirdVoucher;// 单笔凭证号,批次内不重复
	private String CstInnerFlowNo;// 自定义流水号，此字段为订单号，需保持唯一
	private String OppAccNo;// 付款人帐号
	private String OppAccName;// 付款人姓名
	private String OppBank;// 付款银行
	private String CardAcctFlag;// 卡折标志；0-借记卡；1-存折；2-对公账号
	private BigDecimal Amount;// 金额
	private String IdType;// 证件类型
	private String IdNo;// 证件号码
	private String Mobile;// 手机号
	private String PostScript;// 附言，备注
	private String MerchantName;// 商户名称

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

	public BigDecimal getAmount() {
		return Amount;
	}

	public void setAmount(BigDecimal amount) {
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

	public String getMerchantName() {
		return MerchantName;
	}

	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
