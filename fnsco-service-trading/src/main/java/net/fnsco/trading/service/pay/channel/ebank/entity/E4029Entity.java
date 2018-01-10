package net.fnsco.trading.service.pay.channel.ebank.entity;

import io.swagger.annotations.ApiModelProperty;

public class E4029Entity {

	@ApiModelProperty(name = "付款人帐号", example = "付款人帐号")
	private String OppAccNo;// 付款人帐号
	@ApiModelProperty(name = "付款人户名", example = "付款人户名")
	private String OppAccName;// 付款人户名
	@ApiModelProperty(name = "付款人银行号", example = "付款人银行号")
	private String OppBank;// 付款人银行号,见附录银行代码
	@ApiModelProperty(name = "卡折标志0-借记卡；1-存折；2-对公账号", example = "卡折标志0-借记卡；1-存折；2-对公账号")
	private String CardAcctFlag;// 卡折标志0-借记卡；1-存折；2-对公账号
	@ApiModelProperty(name = "证件类型:1-身份证；2-军官证；3-港澳台通行证；4-中国护照", example = "证件类型:1-身份证；2-军官证；3-港澳台通行证；4-中国护照")
	private String IdType;// 证件类型
	@ApiModelProperty(name = "证件号码", example = "证件号码")
	private String IdNo;// 证件号码
	@ApiModelProperty(name = "手机号", example = "手机号")
	private String Mobile;// 手机号
	@ApiModelProperty(name = "操作标志:1-新增；2-修改；3-删除", example = "操作标志:1-新增；2-修改；3-删除")
	private String TranFlag;// 操作标志:1-新增；2-修改；3-删除

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

	public String getTranFlag() {
		return TranFlag;
	}

	public void setTranFlag(String tranFlag) {
		TranFlag = tranFlag;
	}

}
