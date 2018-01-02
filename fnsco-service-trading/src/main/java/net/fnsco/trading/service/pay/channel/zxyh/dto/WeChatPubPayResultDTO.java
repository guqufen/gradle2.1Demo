package net.fnsco.trading.service.pay.channel.zxyh.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class WeChatPubPayResultDTO extends DTO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "应答码", example = "应答码")
	private String respCode;
	@ApiModelProperty(value = "应答信息", example = "应答信息")
	private String respMsg;
	@ApiModelProperty(value = "商户订单号", example = "商户订单号")
	private String orderNo;
	@ApiModelProperty(value = "公众号ID，供微信JSAPI调用参数", example = "公众号ID，供微信JSAPI调用参数")
	private String appId;
	@ApiModelProperty(value = "时间戳，供微信JSAPI调用参数", example = "时间戳，供微信JSAPI调用参数")
	private String timeStamp;
	@ApiModelProperty(value = "随即字符串，供微信JSAPI调用参数", example = "随即字符串，供微信JSAPI调用参数")
	private String nonceStr;
	@ApiModelProperty(value = "订单详情扩展字符串，供微信JSAPI调用参数", example = "订单详情扩展字符串，供微信JSAPI调用参数")
	private String extDetail;

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getExtDetail() {
		return extDetail;
	}

	public void setExtDetail(String extDetail) {
		this.extDetail = extDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
