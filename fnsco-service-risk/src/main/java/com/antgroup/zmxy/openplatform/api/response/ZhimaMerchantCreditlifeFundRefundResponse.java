package com.antgroup.zmxy.openplatform.api.response;

import java.util.Date;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.fund.refund response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:26:12
 */
public class ZhimaMerchantCreditlifeFundRefundResponse extends ZhimaResponse {

	private static final long serialVersionUID = 6112564226768137864L;

	/** 
	 * 资金流水号(支付宝)
	 */
	@ApiField("alipay_fund_order_no")
	private String alipayFundOrderNo;

	/** 
	 * 退款交易是否成功
	 */
	@ApiField("fund_change")
	private String fundChange;

	/** 
	 * 退款手续费(商家)
	 */
	@ApiField("refund_fee")
	private String refundFee;

	/** 
	 * 退款受理时间（格式:yyyy-MM-dd hh:mm:ss）
	 */
	@ApiField("refund_pay_time")
	private Date refundPayTime;

	public void setAlipayFundOrderNo(String alipayFundOrderNo) {
		this.alipayFundOrderNo = alipayFundOrderNo;
	}
	public String getAlipayFundOrderNo( ) {
		return this.alipayFundOrderNo;
	}

	public void setFundChange(String fundChange) {
		this.fundChange = fundChange;
	}
	public String getFundChange( ) {
		return this.fundChange;
	}

	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	public String getRefundFee( ) {
		return this.refundFee;
	}

	public void setRefundPayTime(Date refundPayTime) {
		this.refundPayTime = refundPayTime;
	}
	public Date getRefundPayTime( ) {
		return this.refundPayTime;
	}

}
