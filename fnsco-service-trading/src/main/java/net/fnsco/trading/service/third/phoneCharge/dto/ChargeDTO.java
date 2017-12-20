package net.fnsco.trading.service.third.phoneCharge.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class ChargeDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "待充值流量的手机号", example = "待充值流量的手机号")
	private String phone;
	@ApiModelProperty(value = "流量套餐ID", example = "流量套餐ID")
	private String pid;
	@ApiModelProperty(value = "售价金额(取返回的售价金额)", example = "售价金额(取返回的售价金额)")
	private String inprice;
	@ApiModelProperty(value = "登录用户id", example = "登录用户id")
	private Integer userId;
	@ApiModelProperty(value = "类型0-话费充值;1-流量充值", example = "类型0-话费充值;1-话费==流量充值")
	private Integer type;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
