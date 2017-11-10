package com.antgroup.zmxy.openplatform.api.domain;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

/**
 * 芝麻分及其变动情况
 *
 * @author auto create
 * @since 1.0, 2017-07-21 15:06:29
 */
public class ZmScoreInfo extends ZhimaObject {

	private static final long serialVersionUID = 7278249266229524665L;

	/** 
	 * null
	 */
	@ApiField("zm_score")
	private String zmScore;

	/** 
	 * null
	 */
	@ApiField("zm_score_change")
	private String zmScoreChange;

	public void setZmScore(String zmScore) {
		this.zmScore = zmScore;
	}
	public String getZmScore( ) {
		return this.zmScore;
	}

	public void setZmScoreChange(String zmScoreChange) {
		this.zmScoreChange = zmScoreChange;
	}
	public String getZmScoreChange( ) {
		return this.zmScoreChange;
	}

}
