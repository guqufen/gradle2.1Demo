package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "Result")
@XmlAccessorType(XmlAccessType.FIELD) // 表示使用这个类中的 private
										// 非静态字段作为XML的序列化的属性或者元素,对应属性要使用get、set方法。
public class E4031DTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String SrcAccNo;// 企业账号
	private String AGREE_NO;// 委托单位协议号
	private String BusiType;// 费项代码
	private String OppAccNo;// 付款人帐号
	private String Mobile;// 手机号

	public String getSrcAccNo() {
		return SrcAccNo;
	}

	public void setSrcAccNo(String srcAccNo) {
		SrcAccNo = srcAccNo;
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

	public String getOppAccNo() {
		return OppAccNo;
	}

	public void setOppAccNo(String oppAccNo) {
		OppAccNo = oppAccNo;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
