package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaMerchantSceneCreditpayRiskevalQueryResponse;

/**
 * ALIPAY API: zhima.merchant.scene.creditpay.riskeval.query request
 * 
 * @author auto create
 * @since 1.0, 2017-06-19 11:21:17
 */
public class ZhimaMerchantSceneCreditpayRiskevalQueryRequest implements ZhimaRequest<ZhimaMerchantSceneCreditpayRiskevalQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 是否需要授权
	 */
	private Boolean needAuth;

	/** 
	* 支付宝userid
	 */
	private String userId;

	public void setNeedAuth(Boolean needAuth) {
		this.needAuth = needAuth;
	}
	public Boolean getNeedAuth() {
		return this.needAuth;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return this.userId;
	}
	private String channel;
	private String platform;	
	private String scene;
	private String extParams;

	public String getApiVersion() {
		return this.apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public void setChannel(String channel){
		this.channel=channel;
	}

    public String getChannel(){
    	return this.channel;
    }

	public void setPlatform(String platform){
		this.platform=platform;
	}

    public String getPlatform(){
    	return this.platform;
    }
    
    public void setScene(String scene){
		this.scene=scene;
	}

    public String getScene(){
    	return this.scene;
    }
    
    public void setExtParams(String extParams){
		this.extParams=extParams;
	}

    public String getExtParams(){
    	return this.extParams;
    }
    
	public String getApiMethodName() {
		return "zhima.merchant.scene.creditpay.riskeval.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("need_auth", this.needAuth);
		txtParams.put("user_id", this.userId);
		if(udfParams != null) {
			txtParams.putAll(this.udfParams);
		}
		return txtParams;
	}

	public void putOtherTextParam(String key, String value) {
		if(this.udfParams == null) {
			this.udfParams = new ZhimaHashMap();
		}
		this.udfParams.put(key, value);
	}

	public Class<ZhimaMerchantSceneCreditpayRiskevalQueryResponse> getResponseClass() {
		return ZhimaMerchantSceneCreditpayRiskevalQueryResponse.class;
	}
}
