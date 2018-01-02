package net.fnsco.trading.service.pay.channel.zxyh.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class H5PayReqDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户ID", example = "用户ID")
	private Integer userId;// 用户ID
	@ApiModelProperty(value = "客户端真实IP", example = "客户端真实IP")
	private String termIp;// 客户端真实IP termIp M String(16) 发起支付的客户端真实IP
	@ApiModelProperty(value = "商品描述 ", example = "商品描述 ")
	private String orderBody;// 商品描述 orderBody M String(32) 商品或支付单简要描述
	@ApiModelProperty(value = "交易金额", example = "交易金额")
	private String amt;// 交易金额
	@ApiModelProperty(value = "应用描述", example = "应用描述")
	private String sceneInfo;// 应用描述 sceneInfo M String(1024) 所填内容参考下面备注

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTermIp() {
		return termIp;
	}

	public void setTermIp(String termIp) {
		this.termIp = termIp;
	}

	public String getOrderBody() {
		return orderBody;
	}

	public void setOrderBody(String orderBody) {
		this.orderBody = orderBody;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getSceneInfo() {
		return sceneInfo;
	}

	public void setSceneInfo(String sceneInfo) {
		this.sceneInfo = sceneInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
