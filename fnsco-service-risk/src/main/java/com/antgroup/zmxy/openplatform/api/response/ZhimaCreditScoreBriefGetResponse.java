package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.credit.score.brief.get response.
 * 
 * @author auto create
 * @since 1.0, 2017-03-31 16:38:13
 */
public class ZhimaCreditScoreBriefGetResponse extends ZhimaResponse {

	private static final long serialVersionUID = 8391933164442573334L;

	/** 
	 * 准入判断结果  Y=准入,N=不准入,N/A=无法评估该用户的信用
	 */
	@ApiField("is_admittance")
	private String isAdmittance;

	public void setIsAdmittance(String isAdmittance) {
		this.isAdmittance = isAdmittance;
	}
	public String getIsAdmittance( ) {
		return this.isAdmittance;
	}

}
