package net.fnsco.web.controller.e789.pay.zxyh.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 中信银行微信/支付宝被扫支付交易JO
 * 
 * @author Administrator
 *
 */
public class PassivePayJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="内部商户号")
	private String innerCode;// 内部商户号
	@ApiModelProperty(value="商品描述")
	private String body;// 商品描述
	@ApiModelProperty(value="交易金额")
	private String amt;// 交易金额
	@ApiModelProperty(value="授权码(付款码)")
	private String authid;// 授权码，扫码支付授权码
	@ApiModelProperty(value="签名")
	private String signAture;// 签名
	@ApiModelProperty(value="原支付订单号(用于交易状态查询)")
	private String orgOrderId;// 原支付商户订单号(原交易返回，此处便于交易状态查询交易)

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
