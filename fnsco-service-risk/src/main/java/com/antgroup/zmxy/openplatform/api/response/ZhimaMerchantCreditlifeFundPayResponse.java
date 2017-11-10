package com.antgroup.zmxy.openplatform.api.response;

import java.util.Date;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.fund.pay response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:26:31
 */
public class ZhimaMerchantCreditlifeFundPayResponse extends ZhimaResponse {

	private static final long serialVersionUID = 6739144124772582371L;

	/** 
	 * 
	 */
	@ApiField("alipay_fund_order_no")
	private String alipayFundOrderNo;

	/** 
	 * 支付处理结果(PAY_SUCCESS:支付成功,PAY_FAILED:支付处理失败,PAY_INPROGRESS:支付处理中)
	 */
	@ApiField("pay_status")
	private String payStatus;

	/** 
	 * 支付处理时间
	 */
	@ApiField("pay_time")
	private Date payTime;

	public void setAlipayFundOrderNo(String alipayFundOrderNo) {
		this.alipayFundOrderNo = alipayFundOrderNo;
	}
	public String getAlipayFundOrderNo( ) {
		return this.alipayFundOrderNo;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayStatus( ) {
		return this.payStatus;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getPayTime( ) {
		return this.payTime;
	}

}
