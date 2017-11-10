package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.auth.engine.multiauth response.
 * 
 * @author auto create
 * @since 1.0, 2017-07-26 21:17:44
 */
public class ZhimaAuthEngineMultiauthResponse extends ZhimaResponse {

	private static final long serialVersionUID = 1847128152942925631L;

	/** 
	 * 用户在商端的身份标识
	 */
	@ApiField("open_id")
	private String openId;

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId( ) {
		return this.openId;
	}

}
