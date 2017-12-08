package net.fnsco.web.vo;

import io.swagger.annotations.ApiModelProperty;

public class QueryCityVO {
	 
	@ApiModelProperty(value = "id", name = "id", example = "城市id")
    private Integer id;

	@ApiModelProperty(value = "name", name = "name", example = "城市名称")
    private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

}
