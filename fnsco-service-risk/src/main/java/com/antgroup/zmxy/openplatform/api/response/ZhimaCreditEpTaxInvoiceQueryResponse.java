package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.domain.TaxInfo;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.ep.tax.invoice.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-11 10:10:35
 */
public class ZhimaCreditEpTaxInvoiceQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 7627887743721517321L;

	/** 
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	@ApiField("biz_no")
	private String bizNo;

	/** 
	 * 无数据的原因
枚举值说明：
EMPTY_RESULT  非航信企业
DATA_DISSATISFY_DEMAND 该企业数据不符合贵行的需求 
REQUEST_LATER 请于T+3日再次访问
	 */
	@ApiField("reason")
	private String reason;

	/** 
	 * 企业税务发票信息，数据说明见产品文档
	 */
	@ApiField("tax_info")
	private TaxInfo taxInfo;

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getBizNo( ) {
		return this.bizNo;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReason( ) {
		return this.reason;
	}

	public void setTaxInfo(TaxInfo taxInfo) {
		this.taxInfo = taxInfo;
	}
	public TaxInfo getTaxInfo( ) {
		return this.taxInfo;
	}

}
