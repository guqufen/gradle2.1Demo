package net.fnsco.trading.service.third.phoneBill.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class PhoneChargeDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "套餐ID/话费充值金额", example = "套餐ID/话费充值金额")
	private String id;
	@ApiModelProperty(value = "产品名称:10元/10M", example = "产品名称:10元/10M")
	private String name;
	@ApiModelProperty(value = "售价:xx.xx元", example = "xx.xx")
	private String inprice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
