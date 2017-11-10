package com.antgroup.zmxy.openplatform.api.domain;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;

/**
 * 信用月账单
 *
 * @author auto create
 * @since 1.0, 2017-08-14 16:54:47
 */
public class MonthlyCreditBill extends ZhimaObject {

	private static final long serialVersionUID = 2574512224334874577L;

	/** 
	 * null
	 */
	@ApiListField("cr_monthly_count_list")
	@ApiField("credit_record_monthly_count")
	private List<CreditRecordMonthlyCount> crMonthlyCountList;

	/** 
	 * null
	 */
	@ApiField("month")
	private String month;

	/** 
	 * 芝麻分及其变动情况
	 */
	@ApiField("zm_score_info")
	private ZmScoreInfo zmScoreInfo;

	public void setCrMonthlyCountList(List<CreditRecordMonthlyCount> crMonthlyCountList) {
		this.crMonthlyCountList = crMonthlyCountList;
	}
	public List<CreditRecordMonthlyCount> getCrMonthlyCountList( ) {
		return this.crMonthlyCountList;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonth( ) {
		return this.month;
	}

	public void setZmScoreInfo(ZmScoreInfo zmScoreInfo) {
		this.zmScoreInfo = zmScoreInfo;
	}
	public ZmScoreInfo getZmScoreInfo( ) {
		return this.zmScoreInfo;
	}

}
