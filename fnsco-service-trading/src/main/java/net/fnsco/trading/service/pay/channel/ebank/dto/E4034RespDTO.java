package net.fnsco.trading.service.pay.channel.ebank.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "Result")
@XmlAccessorType(XmlAccessType.FIELD)
public class E4034RespDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String ThirdVoucher;// 批次凭证号
	private String BusiType;// 费项代码
	private String Currency;// 币种
	private String SrcAccNo;// 企业账号
	private String SettleType;// 实时入账标志，0-实时；1-T1入账
	private String BStt;// 批次状态，20-处理成功；30-失败
	private String BatchInfo;// 批次消息
	private String BussSeqNo;// 业务流水号
	@XmlElement(name = "list")
	private List<E4033RespBodyDTO> list;

	public String getThirdVoucher() {
		return ThirdVoucher;
	}

	public void setThirdVoucher(String thirdVoucher) {
		ThirdVoucher = thirdVoucher;
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

	public String getSettleType() {
		return SettleType;
	}

	public void setSettleType(String settleType) {
		SettleType = settleType;
	}

	public String getBStt() {
		return BStt;
	}

	public void setBStt(String bStt) {
		BStt = bStt;
	}

	public String getBatchInfo() {
		return BatchInfo;
	}

	public void setBatchInfo(String batchInfo) {
		BatchInfo = batchInfo;
	}

	public List<E4033RespBodyDTO> getList() {
		return list;
	}

	public void setList(List<E4033RespBodyDTO> list) {
		this.list = list;
	}

	public String getBussSeqNo() {
		return BussSeqNo;
	}

	public void setBussSeqNo(String bussSeqNo) {
		BussSeqNo = bussSeqNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "E4034RespDTO [ThirdVoucher=" + ThirdVoucher + ", BusiType=" + BusiType + ", Currency=" + Currency
				+ ", SrcAccNo=" + SrcAccNo + ", SettleType=" + SettleType + ", BStt=" + BStt + ", BatchInfo="
				+ BatchInfo + ", BussSeqNo=" + BussSeqNo + ", list=" + list + "]";
	}

}
