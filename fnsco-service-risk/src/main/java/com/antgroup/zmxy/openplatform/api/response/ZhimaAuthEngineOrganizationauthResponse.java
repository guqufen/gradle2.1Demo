package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.auth.engine.organizationauth response.
 * 
 * @author auto create
 * @since 1.0, 2017-07-05 10:31:00
 */
public class ZhimaAuthEngineOrganizationauthResponse extends ZhimaResponse {

	private static final long serialVersionUID = 4322945528448328424L;

	/** 
	 * 27位openId，用于标识芝麻会员在
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
