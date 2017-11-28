package net.fnsco.web.controller.trade.jo.zxyh;

public class ZxyhPassivePayJO {

	private String payType;//支付方式：WX_PAY-微信支付；ZFB_PAY-支付宝支付；WX_CX-微信支付结果查询；ZFB_CX-支付宝支付结果查询
	private String innerCode;//内部商户号
	private String body;//商品描述
	private String amt;//交易金额
	private String discamt;//支付宝不可优惠金额(支付宝交易必填字段)
	private String authid;//授权码，扫码支付授权码
	private String signAture;//签名
}
