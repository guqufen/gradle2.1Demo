package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: ssdata.findata.common.jumpurl.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-11-03 15:49:48
 */
public class SsdataFindataCommonJumpurlQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 7571368944244133292L;

	/** 
	 * 
	 */
	@ApiField("jump_url")
	private String jumpUrl;

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	public String getJumpUrl( ) {
		return this.jumpUrl;
	}

}
