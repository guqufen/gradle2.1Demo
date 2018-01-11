package net.fnsco.trading.service.pay.channel.ebank.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.fnsco.core.base.DTO;

@XmlRootElement(name = "Result") // 定义根节点名称
@XmlAccessorType(XmlAccessType.FIELD) // 表示使用这个类中的 private 非静态字段作为
										// XML的序列化的属性或者元素,对应属性要使用get、set方法。
public class E4028RespDTO extends DTO {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "list")
	private ArrayList<E4028RespBodyDTO> list;
	private String AGREE_NO;// 委托单位协议号
	private String TotalNum;// 所有记录数
	private String TotalPage;// 总页数
	private String PerPageNum;// 每页记录数

	public String getAGREE_NO() {
		return AGREE_NO;
	}

	public void setAGREE_NO(String aGREE_NO) {
		AGREE_NO = aGREE_NO;
	}

	public String getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(String totalNum) {
		TotalNum = totalNum;
	}

	public String getTotalPage() {
		return TotalPage;
	}

	public void setTotalPage(String totalPage) {
		TotalPage = totalPage;
	}

	public String getPerPageNum() {
		return PerPageNum;
	}

	public void setPerPageNum(String perPageNum) {
		PerPageNum = perPageNum;
	}

	public ArrayList<E4028RespBodyDTO> getList() {
		return list;
	}

	public void setList(ArrayList<E4028RespBodyDTO> list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "E4028RespDTO [AGREE_NO=" + AGREE_NO + ", TotalNum=" + TotalNum + ", TotalPage=" + TotalPage
				+ ", PerPageNum=" + PerPageNum + ", list=" + list + "]";
	}

}
