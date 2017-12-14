package net.fnsco.web.controller.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class IndexVO {
	@ApiModelProperty(value = "交易金额" , example = "交易金额")
	private BigDecimal amount;//交易金额
	@ApiModelProperty(value = "交易数量" , example = "交易数量")
	private Integer number;//交易数量
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
