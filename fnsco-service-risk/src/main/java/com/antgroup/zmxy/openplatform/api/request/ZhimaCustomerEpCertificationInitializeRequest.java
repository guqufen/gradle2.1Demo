package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerEpCertificationInitializeResponse;

/**
 * ALIPAY API: zhima.customer.ep.certification.initialize request
 * 
 * @author auto create
 * @since 1.0, 2017-07-27 20:20:02
 */
public class ZhimaCustomerEpCertificationInitializeRequest implements ZhimaRequest<ZhimaCustomerEpCertificationInitializeResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 认证场景码，支持的场景码有： EP_ALIPAY_ACCOUNT,  签约的协议决定了可以使用的场景
	 */
	private String bizCode;

	/** 
	* 扩展业务参数，暂时没有用到，接口预留
	 */
	private String extBizParam;

	/** 
	* 值为一个json串，无入参时值为"{}"，有入参时必须指定身份类型identity_type，不同的身份类型对应的身份信息不同 
当前支持的身份信息为证件信息，identity_type=EP_CERT_INFO  
需要输入法人证件三要素，企业证件三要素，如 {"identity_type": "EP_CERT_INFO", "cert_type": "IDENTITY_CARD", "cert_name": "收委", "cert_no":"260104197909275964", "ep_cert_type": "NATIONAL_LEGAL_MERGE", "ep_cert_name": "xxx有限公司", "ep_cert_no":"260104199909275964"}； 
特别备注： 
上述json串中的 ep_cert_type 属性仅支持2种类型：
NATIONAL_LEGAL：工商注册号类型
NATIONAL_LEGAL_MERGE ： 社会统一信用代码类型
	 */
	private String identityParam;

	/** 
	* 认证商户自定义配置，支持一些商户可选的功能,目前为预留的属性值
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
		return "zhima.customer.ep.certification.initialize";
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

	public Class<ZhimaCustomerEpCertificationInitializeResponse> getResponseClass() {
		return ZhimaCustomerEpCertificationInitializeResponse.class;
	}
}
