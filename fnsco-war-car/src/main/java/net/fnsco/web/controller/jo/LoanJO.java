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
