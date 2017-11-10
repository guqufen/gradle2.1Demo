package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaMerchantSceneCreditpaySignriskevalQueryResponse;

/**
 * ALIPAY API: zhima.merchant.scene.creditpay.signriskeval.query request
 * 
 * @author auto create
 * @since 1.0, 2017-07-20 22:17:31
 */
public class ZhimaMerchantSceneCreditpaySignriskevalQueryRequest implements ZhimaRequest<ZhimaMerchantSceneCreditpaySignriskevalQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 二级商户应用id
	 */
	private String linkedAppId;

	/** 
	* 二级商户merchantId
	 */
	private String linkedMerchantId;

	/** 
	* openId
	 */
	private String openId;

	/** 
	* 规则id
	 */
	private String ruleId;

	/** 
	* 风险评估场景码
	 */
	private String sceneCode;

	/** 
	* 支付宝uid
	 */
	private String userId;

	public void setLinkedAppId(String linkedAppId) {
		this.linkedAppId = linkedAppId;
	}
	public String getLinkedAppId() {
		return this.linkedAppId;
	}

	public void setLinkedMerchantId(String linkedMerchantId) {
		this.linkedMerchantId = linkedMerchantId;
	}
	public String getLinkedMerchantId() {
		return this.linkedMerchantId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId() {
		return this.openId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleId() {
		return this.ruleId;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}
	public String getSceneCode() {
		return this.sceneCode;
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
		return "zhima.merchant.scene.creditpay.signriskeval.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("linked_app_id", this.linkedAppId);
		txtParams.put("linked_merchant_id", this.linkedMerchantId);
		txtParams.put("open_id", this.openId);
		txtParams.put("rule_id", this.ruleId);
		txtParams.put("scene_code", this.sceneCode);
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

	public Class<ZhimaMerchantSceneCreditpaySignriskevalQueryResponse> getResponseClass() {
		return ZhimaMerchantSceneCreditpaySignriskevalQueryResponse.class;
	}
}
