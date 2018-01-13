package net.fnsco.trading.service.pay.channel.ebank.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "Result")
@XmlAccessorType(XmlAccessType.FIELD)
public class E4032ReqDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String ThirdVoucher;// 批次凭证号
	private String AGREE_NO;// 委托协议号
	private String BusiType;// 费项代码
	private String Currency;// 币种
	private String SrcAccNo;// 企业账号
	private String TotalNum;// 总笔数，1-单笔接口
	private BigDecimal TotalAmount;// 总金额，1-走单笔接口
	private String SettleType;// 实时入账标志，0-实时；1-T+1入账默认
	@XmlElement(name = "HOResultSet4032R")
	private List<E4032ReqBodyDTO> list;// 请求列表

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

	public String getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(String totalNum) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<E4032ReqBodyDTO> getList() {
		return list;
	}

	public void setList(List<E4032ReqBodyDTO> list) {
		this.list = list;
	}

}
