package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 提交流量充值
 * 
 * @author Administrator
 *
 */
public class FlowChargeJO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "待充值流量的手机号", example = "待充值流量的手机号")
	private String phone;
	@ApiModelProperty(value = "流量套餐ID", example = "流量套餐ID")
	private String pid;
	@ApiModelProperty(value="售价金额(取返回的售价金额)",example="售价金额(取返回的售价金额)")
	private String money;
	@ApiModelProperty(value = "登录用户id", example = "登录用户id")
	private Integer userId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
