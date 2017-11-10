package com.antgroup.zmxy.openplatform.api.response;

import java.util.Date;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.withhold.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:25:21
 */
public class ZhimaMerchantCreditlifeWithholdQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 5174778931968481694L;

	/** 
	 * 代扣签约产生的合约号
	 */
	@ApiField("agreement_no")
	private String agreementNo;

	/** 
	 * 失效日期
	 */
	@ApiField("invalid_time")
	private Date invalidTime;

	/** 
	 * 生效时间
	 */
	@ApiField("valid_time")
	private Date validTime;

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getAgreementNo( ) {
		return this.agreementNo;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public Date getInvalidTime( ) {
		return this.invalidTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	public Date getValidTime( ) {
		return this.validTime;
	}

}
