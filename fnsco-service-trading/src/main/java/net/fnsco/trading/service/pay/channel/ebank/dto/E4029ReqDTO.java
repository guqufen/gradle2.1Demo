package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

/**
 * E4029付款人协议单笔维护DTO
 * @author 伯约
 *
 */
@XmlRootElement(name = "Result") // 定义根节点名称
@XmlAccessorType(XmlAccessType.FIELD) // 表示使用这个类中的 private 非静态字段作为
										// XML的序列化的属性或者元素,对应属性要使用get、set方法。
public class E4029ReqDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String SrcAccNo;// 企业账号
	private String SrcAccName;// 收款人户名
	private String AGREE_NO;// 委托单位协议号。可选
	private String BusiType;// 费项代码，默认为M8PAK，可选
	private String TranFlag;// 操作标志：1-新增；2-修改；3-删除
	private String OppAccNo;// 付款人帐号
	private String OppAccName;// 付款人户名
	private String OppBank;// 付款人银行
	private String Mobile;// 手机号
	private String CardAcctFlag;// 卡折标志:0-借记卡；1-存折；2-对公账号
	private String IdType;// 证件:类型
	private String IdNo;// 证件号码
	private String DetailNo;// 明细序号;修改删除必备，从4028接口获取
	private String Remark;// 用途，可选

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

	public String getAGREE_NO() {
		return AGREE_NO;
	}

	public void setAGREE_NO(String aGREE_NO) {
		AGREE_NO = aGREE_NO;
	}

	public String getBusiType() {
		return BusiType;
	}

	public void setBusiType(String busiType) {
		BusiType = busiType;
	}

	public String getTranFlag() {
		return TranFlag;
	}

	public void setTranFlag(String tranFlag) {
		TranFlag = tranFlag;
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

}
