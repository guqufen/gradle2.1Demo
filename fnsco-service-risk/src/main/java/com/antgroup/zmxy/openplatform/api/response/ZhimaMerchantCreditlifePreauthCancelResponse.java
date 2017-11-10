package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.preauth.cancel response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:25:57
 */
public class ZhimaMerchantCreditlifePreauthCancelResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8634574445644611856L;

	/** 
	 * 显示入参的外部订单号
	 */
	@ApiField("out_order_no")
	private String outOrderNo;

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getOutOrderNo( ) {
		return this.outOrderNo;
	}

}
