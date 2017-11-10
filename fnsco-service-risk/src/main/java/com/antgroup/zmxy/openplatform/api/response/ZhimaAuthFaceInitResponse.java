package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.auth.face.init response.
 * 
 * @author auto create
 * @since 1.0, 2017-06-23 17:18:57
 */
public class ZhimaAuthFaceInitResponse extends ZhimaResponse {

	private static final long serialVersionUID = 1694733829891839991L;

	/** 
	 * 请求返回的加密串
	 */
	@ApiField("auth_msg")
	private String authMsg;

	public void setAuthMsg(String authMsg) {
		this.authMsg = authMsg;
	}
	public String getAuthMsg( ) {
		return this.authMsg;
	}

}
