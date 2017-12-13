package net.fnsco.trading.service.third.phoneCharge.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class PrePaidCheckDTO extends DTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "卡类ID", example = "卡类ID")
	private String cardid;
	@ApiModelProperty(value = "卡类名称", example = "卡类名称")
	private String cardname;
	@ApiModelProperty(value = "购买价格", example = "购买价格")
	private String inprice;
	@ApiModelProperty(value = "手机号码归属地", example = "手机号码归属地")
	private String game_area;

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public String getGame_area() {
		return game_area;
	}

	public void setGame_area(String game_area) {
		this.game_area = game_area;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
