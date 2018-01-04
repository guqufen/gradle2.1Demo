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
	@ApiModelProperty(value = "售价金额(取返回的售价金额)", example = "售价金额(取返回的售价金额)")
	private String inprice;
	@ApiModelProperty(value = "登录用户id", example = "登录用户id")
	private Integer userId;
	@ApiModelProperty(value = "类型0-话费充值;1-流量充值", example = "类型0-话费充值;1-流量充值")
	private Integer type;
	@ApiModelProperty(value = "充值名称:取资费查询返回的name字段", example = "充值名称:取资费查询返回的name字段")
	private String name;
	@ApiModelProperty(value = "支付密码", example = "支付密码")
	private String payPassword;
	@ApiModelProperty(value = "充值方式:0-余额/1-微信/2-支付宝/3-绑定卡代扣", example = "充值方式:0-余额/1-微信/2-支付宝/3-绑定卡代扣")
	private String payType;

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

	public String getInprice() {
		return inprice;
	}

	public void setInprice(String inprice) {
		this.inprice = inprice;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
