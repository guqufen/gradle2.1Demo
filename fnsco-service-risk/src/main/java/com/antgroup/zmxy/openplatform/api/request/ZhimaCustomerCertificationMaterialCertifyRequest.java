package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerCertificationMaterialCertifyResponse;

/**
 * ALIPAY API: zhima.customer.certification.material.certify request
 * 
 * @author auto create
 * @since 1.0, 2017-06-05 13:42:56
 */
public class ZhimaCustomerCertificationMaterialCertifyRequest implements ZhimaRequest<ZhimaCustomerCertificationMaterialCertifyResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 认证场景码，支持的场景码有： 
FACE_API：通过接口进行人脸识别
签约的协议决定了可以使用的场景
	 */
	private String bizCode;

	/** 
	* 扩展业务参数，暂时没有用到，接口预留
	 */
	private String extBizParam;

	/** 
	* 值为一个json串，必须指定身份类型identity_type，不同的身份类型对应的身份信息不同
当前支持：
身份信息为证件信息，identity_type值为CERT_INFO:
证件类型为身份证cert_type值为IDENTITY_CARD,必要信息cert_type，cert_name和cert_no；
身份信息为个人正面照片，identity_type值为FACIAL_PICTURE_FRONT:
必要信息cert_type，cert_name和cert_no和FACIAL_PICTURE_FRONT
示例：{"identity_type": "FACIAL_PICTURE_FRONT", "cert_type": "IDENTITY_CARD", "cert_name": "收委", "cert_no": "260104197909275964", "FACIAL_PICTURE_FRONT": "/9j/4AAQSkZJR"}
	 */
	private String identityParam;

	/** 
	* 认证过程中需要的认证材料，不同认证场景需要的材料不同
biz_code值为FACE_API时需要材料FACIAL_PICTURE_FRONT
	 */
	private String materials;

	/** 
	* 认证商户自定义配置，支持一些商户可选的功能
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

	public void setMaterials(String materials) {
		this.materials = materials;
	}
	public String getMaterials() {
		return this.materials;
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
		return "zhima.customer.certification.material.certify";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("biz_code", this.bizCode);
		txtParams.put("ext_biz_param", this.extBizParam);
		txtParams.put("identity_param", this.identityParam);
		txtParams.put("materials", this.materials);
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

	public Class<ZhimaCustomerCertificationMaterialCertifyResponse> getResponseClass() {
		return ZhimaCustomerCertificationMaterialCertifyResponse.class;
	}
}
