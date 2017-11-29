package net.fnsco.web.controller.e789.pay.zxyh.jo;

import net.fnsco.core.base.JO;

public class ZxyhPassivePayJO extends JO {

	private static final long serialVersionUID = 1L;

	private String payType;// 支付方式：WX_PAY-微信支付；ZFB_PAY-支付宝支付；WX_CX-微信支付结果查询；ZFB_CX-支付宝支付结果查询
	private String innerCode;// 内部商户号
	private String body;// 商品描述
	private String amt;// 交易金额
	private String discamt;// 支付宝不可优惠金额(支付宝交易必填字段)
	private String authid;// 授权码，扫码支付授权码
	private String signAture;// 签名
	
	private String orgOrderId;//原支付商户订单号(原交易返回，此处便于交易状态查询交易)

	// 返回
	// private String orderId;// 订单号(商户订单号)
	// private String begTime;// 交易起始时间
	// private String endTime;// 支付完成时间(交易成功是返回该字段)
	// private String reciAmt;// 实收金额,支付宝
	// private String preAmt;// 优惠金额
	// private String openId;// 用户标识
	// private String respCode;// 应答码
	// private String respMsg;// 应答信息

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getDiscamt() {
		return discamt;
	}

	public void setDiscamt(String discamt) {
		this.discamt = discamt;
	}

	public String getAuthid() {
		return authid;
	}

	public void setAuthid(String authid) {
		this.authid = authid;
	}

	public String getSignAture() {
		return signAture;
	}

	public void setSignAture(String signAture) {
		this.signAture = signAture;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrgOrderId() {
		return orgOrderId;
	}

	public void setOrgOrderId(String orgOrderId) {
		this.orgOrderId = orgOrderId;
	}

}
