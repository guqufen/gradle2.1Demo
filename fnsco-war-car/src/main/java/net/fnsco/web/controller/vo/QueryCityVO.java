package net.fnsco.web.controller.vo;

import io.swagger.annotations.ApiModelProperty;

public class QueryCityVO {
	 
	@ApiModelProperty(value = "value", name = "value", example = "城市id")
    private Integer value;

	@ApiModelProperty(value = "text", name = "text", example = "城市名称")
    private String text;

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	
	

}
