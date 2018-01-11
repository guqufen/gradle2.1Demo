package net.fnsco.trading.service.pay.channel.ebank.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlAccessorType(XmlAccessType.FIELD) // 表示使用这个类中的 private 非静态字段作为 XML
										// 的序列化的属性或者元素,对应属性要使用get、set方法。
@XmlRootElement(name = "Result") // 定义根节点名称
// @XmlType(propOrder={"ThirdVoucher","AGREE_NO"})//xml格式数据的显示的顺序名字要和定义变量的一样，而不是@XmlElement中的name
public class E4032HeadDTO extends DTO {

	private static final long serialVersionUID = 1L;
//	@XmlElement(name = "ThirdVoucher", required = true) // 定义xml中显示的数据
	private String ThirdVoucher;// 批次凭证号，唯一凭证号，C20
//	@XmlElement(name = "AGREE_NO", required = true)
	private String AGREE_NO;// 委托单位协议号，可选
//	@XmlElement(name = "BusiType", required = true)
	private String BusiType;// 费项代码，默认为M8PAK，可选
//	@XmlElement(name = "Currency", required = true)
	private String Currency;// 币种，默认为RMB，可选o
//	@XmlElement(name = "", required = true)
	private String SrcAccNo;// 企业账号
//	@XmlElement(name = "TotalNum", required = true)
	private Integer TotalNum;// 总笔数,1-走单笔接口；否则走批量接口
//	@XmlElement(name = "TotalAmount", required = true)
	private BigDecimal TotalAmount;// 总金额，S(13,2)
//	@XmlElement(name = "SettleType", required = true)
	private String SettleType;// 实时入账标志，0-实时 ；1：T+1入账
	@XmlElement(name = "HOResultSet4032R",required=true)
	private List<E4032BodyDTO> list;

	public E4032HeadDTO() {

	}

	public E4032HeadDTO(String thirdVoucher, String aGREE_NO, String busiType, String currency, String srcAccNo,
			Integer totalNum, BigDecimal totalAmount, String settleType) {
		ThirdVoucher = thirdVoucher;
		AGREE_NO = aGREE_NO;
		BusiType = busiType;
		Currency = currency;
		SrcAccNo = srcAccNo;
		TotalNum = totalNum;
		TotalAmount = totalAmount;
		SettleType = settleType;
	}

	public String getThirdVoucher() {
		return ThirdVoucher;
	}

	public void setThirdVoucher(String thirdVoucher) {
		ThirdVoucher = thirdVoucher;
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

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getSrcAccNo() {
		return SrcAccNo;
	}

	public void setSrcAccNo(String srcAccNo) {
		SrcAccNo = srcAccNo;
	}

	public Integer getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(Integer totalNum) {
		TotalNum = totalNum;
	}

	public BigDecimal getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		TotalAmount = totalAmount;
	}

	public String getSettleType() {
		return SettleType;
	}

	public void setSettleType(String settleType) {
		SettleType = settleType;
	}

	@Override
	public String toString() {
		return "E4032HeadDTO [ThirdVoucher=" + ThirdVoucher + ", AGREE_NO=" + AGREE_NO + ", BusiType=" + BusiType
				+ ", Currency=" + Currency + ", SrcAccNo=" + SrcAccNo + ", TotalNum=" + TotalNum + ", TotalAmount="
				+ TotalAmount + ", SettleType=" + SettleType + "]";
	}

	public List<E4032BodyDTO> getList() {
		return list;
	}

	public void setList(List<E4032BodyDTO> list) {
		this.list = list;
	}

}
