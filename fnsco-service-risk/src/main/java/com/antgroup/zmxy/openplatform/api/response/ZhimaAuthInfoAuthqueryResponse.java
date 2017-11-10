package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.auth.info.authquery response.
 * 
 * @author auto create
 * @since 1.0, 2017-04-07 10:48:13
 */
public class ZhimaAuthInfoAuthqueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 2371783516847145745L;

	/** 
	 * 用户是否对该应用授权
	 */
	@ApiField("authorized")
	private Boolean authorized;

	/** 
	 * 
	 */
	@ApiField("open_id")
	private String openId;

	public void setAuthorized(Boolean authorized) {
		this.authorized = authorized;
	}
	public Boolean getAuthorized( ) {
		return this.authorized;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId( ) {
		return this.openId;
	}

}
