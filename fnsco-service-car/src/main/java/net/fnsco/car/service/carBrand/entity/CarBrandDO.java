package net.fnsco.car.service.carBrand.entity;

import io.swagger.annotations.ApiModelProperty;

public class CarBrandDO {

	@ApiModelProperty(value = "主键", example = "主键")
	private Integer id;
	@ApiModelProperty(value = "名称", example = "名称")
	private String name;
	@ApiModelProperty(value = "上级菜单id", example = "上级菜单id")
	private Integer supperId;
	@ApiModelProperty(value = "上级菜单名称", example = "上级菜单名称")
	private String supperName;
	@ApiModelProperty(value = "级别", example = "级别")
	private Integer level;
	@ApiModelProperty(value = "图标地址", example = "图标地址")
	private String iconImgPath;
	@ApiModelProperty(value = "所属车型<小型车、suv车>", example = "所属车型<小型车、suv车>")
	private String model;
	@ApiModelProperty(value = "是否热门品牌0-否;1-是", example = "是否热门品牌0-否;1-是")
	private Integer isHot;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSupperId() {
		return supperId;
	}

	public void setSupperId(Integer supperId) {
		this.supperId = supperId;
	}

	public String getSupperName() {
		return supperName;
	}

	public void setSupperName(String supperName) {
		this.supperName = supperName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIconImgPath() {
		return iconImgPath;
	}

	public void setIconImgPath(String iconImgPath) {
		this.iconImgPath = iconImgPath;
	}

	public String getModal() {
		return model;
	}

	public void setModal(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

}
