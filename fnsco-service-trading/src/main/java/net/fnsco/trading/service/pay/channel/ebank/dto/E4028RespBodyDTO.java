package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
public class E4028RespBodyDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String OppAccNo;// 付款人帐号
	private String OppAccName;// 付款人户名
	private String OppBank;// 付款人银行行号
	private String SrcAccNo;// 收款人帐号
	private String SrcAccName;// 收款人户名
	private String EffectDate;// 生效日期
	private String Status;// 协议状态，0-正常；1-停用；2-待认证；3-认证中
	private String Mobile;// 手机号
	private String CardAcctFlag;// 卡折标志0-借记卡；1-存折；2-对公账号
	private String IdType;// 证件类型
	private String IdNo;// 证件号码
	private String BusiType;// 费项代码
	private String DetailNo;// 明细序号,银行关于此条明细的唯一标志，可用于删除或修改；
	private String Remark;// 备注 ,失败原因，如该付款人帐号已存在

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

	public String getSrcAccNo() {
		return SrcAccNo;
	}

	public void setSrcAccNo(String srcAccNo) {
		SrcAccNo = srcAccNo;
	}

	public String getSrcAccName() {
		return SrcAccName;
	}

	public void setSrcAccName(String srcAccName) {
		SrcAccName = srcAccName;
	}

	public String getEffectDate() {
		return EffectDate;
	}

	public void setEffectDate(String effectDate) {
		EffectDate = effectDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getCardAcctFlag() {
		return CardAcctFlag;
	}

	public void setCardAcctFlag(String cardAcctFlag) {
		CardAcctFlag = cardAcctFlag;
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

	public String getBusiType() {
		return BusiType;
	}

	public void setBusiType(String busiType) {
		BusiType = busiType;
	}

	public String getDetailNo() {
		return DetailNo;
	}

	public void setDetailNo(String detailNo) {
		DetailNo = detailNo;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "E4028RespBodyDTO [OppAccNo=" + OppAccNo + ", OppAccName=" + OppAccName + ", OppBank=" + OppBank
				+ ", SrcAccNo=" + SrcAccNo + ", SrcAccName=" + SrcAccName + ", EffectDate=" + EffectDate + ", Status="
				+ Status + ", Mobile=" + Mobile + ", CardAcctFlag=" + CardAcctFlag + ", IdType=" + IdType + ", IdNo="
				+ IdNo + ", BusiType=" + BusiType + ", DetailNo=" + DetailNo + ", Remark=" + Remark + "]";
	}

}
