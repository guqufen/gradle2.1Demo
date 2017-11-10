package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.risk.apply response.
 * 
 * @author auto create
 * @since 1.0, 2017-04-06 09:38:31
 */
public class ZhimaMerchantCreditlifeRiskApplyResponse extends ZhimaResponse {

	private static final long serialVersionUID = 5444667966562856192L;

	/** 
	 * 
	 */
	@ApiField("ivs_details")
	private String ivsDetails;

	/** 
	 * 
	 */
	@ApiField("open_id")
	private String openId;

	/** 
	 * 
	 */
	@ApiField("watchlist_detail")
	private String watchlistDetail;

	/** 
	 * 
	 */
	@ApiField("zm_risk")
	private String zmRisk;

	/** 
	 * 
	 */
	@ApiField("zm_score")
	private String zmScore;

	public void setIvsDetails(String ivsDetails) {
		this.ivsDetails = ivsDetails;
	}
	public String getIvsDetails( ) {
		return this.ivsDetails;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId( ) {
		return this.openId;
	}

	public void setWatchlistDetail(String watchlistDetail) {
		this.watchlistDetail = watchlistDetail;
	}
	public String getWatchlistDetail( ) {
		return this.watchlistDetail;
	}

	public void setZmRisk(String zmRisk) {
		this.zmRisk = zmRisk;
	}
	public String getZmRisk( ) {
		return this.zmRisk;
	}

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}
	public String getZmScore( ) {
		return this.zmScore;
	}

}
