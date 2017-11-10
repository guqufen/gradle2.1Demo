package com.antgroup.zmxy.openplatform.api.request;

import java.util.List;
import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditRiskEvaluateRuleQueryResponse;

/**
 * ALIPAY API: zhima.credit.risk.evaluate.rule.query request
 * 
 * @author auto create
 * @since 1.0, 2017-06-29 14:03:10
 */
public class ZhimaCreditRiskEvaluateRuleQueryRequest implements ZhimaRequest<ZhimaCreditRiskEvaluateRuleQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 签约产品标示，唯一对应一个产品
	 */
	private String productCode;

	/** 
	* 可选参数，传值则标示只查询对应规则配置值，不传则输出所有规则配置值
	 */
	private List<String> ruleCode;

	/** 
	* 1000806 【规则标识，使用APPID】
如果是ISV商户： 传入二级商户APPID
如果是普通商户：传入自己调用APPID
	 */
	private String ruleId;

	/** 
	* 标识对接业务场景，业务场景下商户可做自定义策略配置
	 */
	private String sceneCode;

	/** 
	* 唯一标示商户每一笔请求
	 */
	private String transactionId;

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
	}

	public void setRuleCode(List<String> ruleCode) {
		this.ruleCode = ruleCode;
	}
	public List<String> getRuleCode() {
		return this.ruleCode;
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

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionId() {
		return this.transactionId;
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
		return "zhima.credit.risk.evaluate.rule.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("product_code", this.productCode);
		txtParams.put("rule_code", this.ruleCode);
		txtParams.put("rule_id", this.ruleId);
		txtParams.put("scene_code", this.sceneCode);
		txtParams.put("transaction_id", this.transactionId);
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

	public Class<ZhimaCreditRiskEvaluateRuleQueryResponse> getResponseClass() {
		return ZhimaCreditRiskEvaluateRuleQueryResponse.class;
	}
}
