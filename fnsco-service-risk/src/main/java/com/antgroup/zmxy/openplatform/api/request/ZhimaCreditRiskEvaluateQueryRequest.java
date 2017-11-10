package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditRiskEvaluateQueryResponse;

/**
 * ALIPAY API: zhima.credit.risk.evaluate.query request
 * 
 * @author auto create
 * @since 1.0, 2017-09-21 13:15:15
 */
public class ZhimaCreditRiskEvaluateQueryRequest implements ZhimaRequest<ZhimaCreditRiskEvaluateQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 证件号。当证件类型为身份证时，cert_no为身份证号
	 */
	private String certNo;

	/** 
	* 证件类型 目前支持两种IDENTITY_CARD(身份证),ALIPAY_USER_ID(支付宝uid)
	 */
	private String certType;

	/** 
	* 扩展参数，供提供更多信息给规则引擎做风险判断。以JSON字符串形式配置
	 */
	private String extendInfo;

	/** 
	* 姓名，当传入cert_type类型为IDENTITY_CARD时该值为必传项
	 */
	private String name;

	/** 
	* 产品码
	 */
	private String productCode;

	/** 
	* ISV商户传入二级商户APPID
普通商户传入自身APPID
	 */
	private String ruleId;

	/** 
	* 标识对接业务场景，业务场景下商户可做自定义策略配置
	 */
	private String sceneCode;

	/** 
	* 芝麻业务凭证，详见https://b.zmxy.com.cn/technology/openDoc.htm?id=334
	 */
	private String transactionId;

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertNo() {
		return this.certNo;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertType() {
		return this.certType;
	}

	public void setExtendInfo(String extendInfo) {
		this.extendInfo = extendInfo;
	}
	public String getExtendInfo() {
		return this.extendInfo;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
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
		return "zhima.credit.risk.evaluate.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("cert_no", this.certNo);
		txtParams.put("cert_type", this.certType);
		txtParams.put("extend_info", this.extendInfo);
		txtParams.put("name", this.name);
		txtParams.put("product_code", this.productCode);
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

	public Class<ZhimaCreditRiskEvaluateQueryResponse> getResponseClass() {
		return ZhimaCreditRiskEvaluateQueryResponse.class;
	}
}
