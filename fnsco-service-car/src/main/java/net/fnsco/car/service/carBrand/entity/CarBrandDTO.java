package net.fnsco.car.service.carBrand.entity;

import java.util.Set;

import io.swagger.annotations.ApiModelProperty;

public class CarBrandDTO {

	@ApiModelProperty(value = "字母A-Z", example = "字母A-Z")
	private String letter;
	@ApiModelProperty(value = "汽车品牌集合", example = "汽车品牌集合")
	private Set<CarBrandDO> body;

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Set<CarBrandDO> getBody() {
		return body;
	}

	public void setBody(Set<CarBrandDO> body) {
		this.body = body;
	}

}
