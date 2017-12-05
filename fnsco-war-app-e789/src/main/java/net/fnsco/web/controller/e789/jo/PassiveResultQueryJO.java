package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 中信银行微信/支付宝被扫支付交易结果查询JO
 * 
 * @author Administrator
 *
 */
public class PassiveResultQueryJO extends JO {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "原支付订单号(用于交易状态查询)")
	private String orgOrderId;// 原支付商户订单号(原交易返回，此处便于交易状态查询交易)
	@ApiModelProperty(value = "签名")
	private String signAture;// 签名

	public String getOrgOrderId() {
		return orgOrderId;
	}

	public void setOrgOrderId(String orgOrderId) {
		this.orgOrderId = orgOrderId;
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

}
