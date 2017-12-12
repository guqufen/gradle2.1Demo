package net.fnsco.web.controller.jo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class LoanJO {

	@ApiModelProperty(value = "name", name = "name", example = "购车人")
	private String name;
	@ApiModelProperty(value = "mobile", name = "mobile", example = "手机号")
	private String mobile;

	/**
	 * 所在城市
	 */
	@ApiModelProperty(value = "cityId", name = "cityId", example = "所在城市")
	private Integer cityId;

	/**
	 * 贷款金额
	 */
	@ApiModelProperty(value = "amount", name = "amount", example = "贷款金额")
	private BigDecimal amount;
	/**
	 * 推荐码
	 */
	@ApiModelProperty(value = "suggestCode", name = "suggestCode", example = "推荐码")
	private Integer suggestCode;
	
	
	/////////////////////////////////////////
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "verCode", name = "verCode", example = "验证码")
	private String verCode;
	/**
	 * 车品牌
	 */
	@ApiModelProperty(value = "carTypeId", name = "carTypeId", example = "车品牌")
	private Integer carTypeId;
	/**
	 * 车型号
	 */
	@ApiModelProperty(value = "carModel", name = "carModel", example = "车型号")
	private String carModel;

	@ApiModelProperty(value = "fileIds", name = "fileIds", example = "文件id")
	private String fileIds;
	
	@ApiModelProperty(value = "type", name = "type", example = "申请类型")
	private String type;
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the fileIds
	 */
	public String getFileIds() {
		return fileIds;
	}

	/**
	 * @param fileIds the fileIds to set
	 */
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	/**
	 * @return the verCode
	 */
	public String getVerCode() {
		return verCode;
	}

	/**
	 * @param verCode the verCode to set
	 */
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	/**
	 * @return the carTypeId
	 */
	public Integer getCarTypeId() {
		return carTypeId;
	}

	/**
	 * @param carTypeId the carTypeId to set
	 */
	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	/**
	 * @return the carModel
	 */
	public String getCarModel() {
		return carModel;
	}

	/**
	 * @param carModel the carModel to set
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the suggestCode
	 */
	public Integer getSuggestCode() {
		return suggestCode;
	}

	/**
	 * @param suggestCode
	 *            the suggestCode to set
	 */
	public void setSuggestCode(Integer suggestCode) {
		this.suggestCode = suggestCode;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
