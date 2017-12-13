package net.fnsco.web.controller.jo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class LoanJO extends JO{

	
	
	@ApiModelProperty(value = "购车人",  example = "购车人")
	private String name;
	@ApiModelProperty(value = "手机号", example = "手机号")
	private String mobile;

	/**
	 * 所在城市
	 */
	@ApiModelProperty(value = "所在城市", example = "所在城市")
	private Integer cityId;

	/**
	 * 贷款金额
	 */
	@ApiModelProperty(value = "贷款金额", example = "贷款金额")
	private BigDecimal amount;
	/**
	 * 推荐码
	 */
	@ApiModelProperty(value = "验证码",  example = "验证码")
	private Integer suggestCode;
	
	
	/////////////////////////////////////////
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码",  example = "验证码")
	private String verCode;
	/**
	 * 车品牌
	 */
	@ApiModelProperty(value = "车品牌",  example = "车品牌")
	private Integer carTypeId;
	/**
	 * 车型号
	 */
	@ApiModelProperty(value = "车型号",  example = "车型号")
	private Integer carSubTypeId;

	@ApiModelProperty(value = "文件id", example = "文件id")
	private String fileIds;
	
	@ApiModelProperty(value = "申请类型", example = "申请类型")
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
	public Integer getCarSubTypeId() {
		return carSubTypeId;
	}

	/**
	 * @param carModel the carModel to set
	 */
	public void setCarSubTypeId(Integer carSubTypeId) {
		this.carSubTypeId = carSubTypeId;
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
