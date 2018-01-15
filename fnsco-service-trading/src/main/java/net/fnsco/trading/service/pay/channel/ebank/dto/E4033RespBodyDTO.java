package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "list")
@XmlAccessorType(XmlAccessType.FIELD)
public class E4033RespBodyDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String SThirdVoucher;// 但比凭证号
	private String CstInnerFlowNo;// 自定义流水号
	private String OppAccNo;// 付款人帐号
	private String OppAccName;// 付款人户名
	private String IdType;// 证件类型
	private String IdNo;// 证件号码
	private String Amount;// 金额
	private String Fee;// 手续费
	private String stt;// 明晰状态，20-表示成功；30-表示失败；31-被拒绝(失败)；40-处理中；99-为异常
	private String errorCode;// 错误码，批量有效，单笔轻忽略
	private String sttInfo;// 状态描述
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

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getFee() {
		return Fee;
	}

	public void setFee(String fee) {
		Fee = fee;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getSttInfo() {
		return sttInfo;
	}

	public void setSttInfo(String sttInfo) {
		this.sttInfo = sttInfo;
	}

	public String getPostScript() {
		return PostScript;
	}

	public void setPostScript(String postScript) {
		PostScript = postScript;
	}

}
