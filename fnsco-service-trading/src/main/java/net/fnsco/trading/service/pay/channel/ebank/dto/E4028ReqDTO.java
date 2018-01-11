package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "Result") // 定义根节点名称
@XmlAccessorType(XmlAccessType.FIELD) // 表示使用这个类中的 private 非静态字段作为
										// XML的序列化的属性或者元素,对应属性要使用get、set方法。
public class E4028ReqDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String SrcAccNo;// 企业帐号
	private String AGREE_NO;// 委托单位协议号
	private String BusiType;// 费项代码，默认M8PAK
	private String StartDate;// 开始日期，yyyymmdd
	private String EndDate;// 结束日期，yyyymmdd
	private String OppAccNo;// 付款人帐号
	private String OppAccName;// 付款人户名
	private String Mobile;// 付款人手机号
	private String Status;// 状态，0-正常；1-停用；2-待认证；3-认证中，目前只支持状态0作为查询条件
	private String PageNo;// 页码，从1开始,每页十条记录

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

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
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

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPageNo() {
		return PageNo;
	}

	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}

	@Override
	public String toString() {
		return "E4028ReqDTO [SrcAccNo=" + SrcAccNo + ", AGREE_NO=" + AGREE_NO + ", BusiType=" + BusiType
				+ ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", OppAccNo=" + OppAccNo + ", OppAccName="
				+ OppAccName + ", Mobile=" + Mobile + ", Status=" + Status + ", PageNo=" + PageNo + "]";
	}

}
