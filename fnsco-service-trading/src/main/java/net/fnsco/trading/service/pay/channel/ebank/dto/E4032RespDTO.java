package net.fnsco.trading.service.pay.channel.ebank.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "Result") // 定义根节点名称
@XmlAccessorType(XmlAccessType.FIELD) // 表示使用这个类中的 private 非静态字段作为
										// XML的序列化的属性或者元素,对应属性要使用get、set方法。
public class E4032RespDTO extends DTO {

	private static final long serialVersionUID = 1L;
	private String ThirdVoucher;// 批次凭证号
	private String BussSeqNo;// 业务流水号

	public String getThirdVoucher() {
		return ThirdVoucher;
	}

	public void setThirdVoucher(String thirdVoucher) {
		ThirdVoucher = thirdVoucher;
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

}
