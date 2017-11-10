package com.antgroup.zmxy.openplatform.api.response;

import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;

import com.antgroup.zmxy.openplatform.api.ZhimaResponse;

/**
 * ALIPAY API: zhima.customer.certification.query response.
 * 
 * @author auto create
 * @since 1.0, 2017-08-21 20:21:26
 */
public class ZhimaCustomerCertificationQueryResponse extends ZhimaResponse {

	private static final long serialVersionUID = 7543449292283872185L;

	/** 
	 * 认证的主体属性信息
	 */
	@ApiField("attribute_info")
	private String attributeInfo;

	/** 
	 * 认证过程中认证过的各渠道的状态，中间材料等数据
	 */
	@ApiField("channel_statuses")
	private String channelStatuses;

	/** 
	 * 认证没有通过的原因
	 */
	@ApiField("failed_reason")
	private String failedReason;

	/** 
	 * 认证的主体信息。如果初始化接口里面设置需要用户授权，这里就会返回OPEN_ID
	 */
	@ApiField("identity_info")
	private String identityInfo;

	/** 
	 * 认证是否通过，通过为true，不通过为false
	 */
	@ApiField("passed")
	private String passed;

	public void setAttributeInfo(String attributeInfo) {
		this.attributeInfo = attributeInfo;
	}
	public String getAttributeInfo( ) {
		return this.attributeInfo;
	}

	public void setChannelStatuses(String channelStatuses) {
		this.channelStatuses = channelStatuses;
	}
	public String getChannelStatuses( ) {
		return this.channelStatuses;
	}

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	public String getFailedReason( ) {
		return this.failedReason;
	}

	public void setIdentityInfo(String identityInfo) {
		this.identityInfo = identityInfo;
	}
	public String getIdentityInfo( ) {
		return this.identityInfo;
	}

	public void setPassed(String passed) {
		this.passed = passed;
	}
	public String getPassed( ) {
		return this.passed;
	}

}
