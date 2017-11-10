package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertificationInitializeResponse;

/**
 * ALIPAY API: zhima.customer.certification.initialize request
 * 
 * @author auto create
 * @since 1.0, 2017-05-31 10:19:55
 */
public class ZhimaCustomerCertificationInitializeRequest implements ZhimaRequest<ZhimaCustomerCertificationInitializeResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 认证场景码，支持的场景码有：
FACE：多因子活体人脸认证，
SMART_FACE：多因子快捷活体人脸认证，
FACE_SDK：SDK活体人脸认证
签约的协议决定了可以使用的场景
	 */
	private String bizCode;

	/** 
	* 扩展业务参数，暂时没有用到，接口预留
	 */
	private String extBizParam;

	/** 
	* 值为一个json串，无入参时值为"{}"，有入参时必须指定身份类型identity_type，不同的身份类型对应的身份信息不同
当前支持：
身份信息为证件信息，identity_type值为CERT_INFO：
证件类型为身份证cert_type值为IDENTITY_CARD，必要信息cert_name和cert_no；
身份信息为短信手机号，适用于短信认证，identity_type值为SMS_MOBILE_NO：
证件类型可以为空，当证件类型为身份证cert_type值为IDENTITY_CARD，必要信息cert_name和cert_no，mobile_no可以为空，以上信息没有传入的时候会上用户录入；
身份信息为支付宝UID，identity_type值为USER_ID:
必要信息user_id
示例：{"identity_type": "USER_ID", "user_id": "2088172637486509"}
	 */
	private String identityParam;

	/** 
	* 认证商户自定义配置，支持一些商户可选的功能
need_user_authorization： 值为true或者false
当值为true时，在认证用户引导页会展示用户授权协议，在认证通过后会进行授权，但是授权是否成功不影响认证结果
	 */
	private String merchantConfig;

	/** 
	* 芝麻认证产品码,示例值为真实的产品码
	 */
	private String productCode;

	/** 
	* 商户请求的唯一标志，32位长度的字母数字下划线组合。该标识作为对账的关键信息，商户要保证其唯一性.建议:前面几位字符是商户自定义的简称,中间可以使用一段日期,结尾可以使用一个序列
	 */
	private String transactionId;

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getBizCode() {
		return this.bizCode;
	}

	public void setExtBizParam(String extBizParam) {
		this.extBizParam = extBizParam;
	}
	public String getExtBizParam() {
		return this.extBizParam;
	}

	public void setIdentityParam(String identityParam) {
		this.identityParam = identityParam;
	}
	public String getIdentityParam() {
		return this.identityParam;
	}

	public void setMerchantConfig(String merchantConfig) {
		this.merchantConfig = merchantConfig;
	}
	public String getMerchantConfig() {
		return this.merchantConfig;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
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
		return "zhima.customer.certification.initialize";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("biz_code", this.bizCode);
		txtParams.put("ext_biz_param", this.extBizParam);
		txtParams.put("identity_param", this.identityParam);
		txtParams.put("merchant_config", this.merchantConfig);
		txtParams.put("product_code", this.productCode);
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

	public Class<ZhimaCustomerCertificationInitializeResponse> getResponseClass() {
		return ZhimaCustomerCertificationInitializeResponse.class;
	}
}
