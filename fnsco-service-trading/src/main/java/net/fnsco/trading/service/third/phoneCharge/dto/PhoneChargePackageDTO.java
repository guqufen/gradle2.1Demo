package net.fnsco.trading.service.third.phoneCharge.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class PhoneChargePackageDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "运营商:中国联通/移动/电信", example = "运营商:中国联通/移动/电信")
	private String company;
	@ApiModelProperty(value = "号码归属地", example = "号码归属地")
	private String cardArea;
	@ApiModelProperty(value = "名称售价列表", example = "名称售价列表")
	private List<PhoneChargeDTO> list;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCardArea() {
		return cardArea;
	}

	public void setCardArea(String cardArea) {
		this.cardArea = cardArea;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<PhoneChargeDTO> getList() {
		return list;
	}

	public void setList(List<PhoneChargeDTO> list) {
		this.list = list;
	}

}
