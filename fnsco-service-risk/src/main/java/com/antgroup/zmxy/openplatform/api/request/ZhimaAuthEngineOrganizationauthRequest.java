package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaAuthEngineOrganizationauthResponse;

/**
 * ALIPAY API: zhima.auth.engine.organizationauth request
 * 
 * @author auto create
 * @since 1.0, 2017-07-05 10:31:00
 */
public class ZhimaAuthEngineOrganizationauthRequest implements ZhimaRequest<ZhimaAuthEngineOrganizationauthResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 授权码入参，

1). 若identity_Type=2时，

{"auth_code":"M_P_COMPANY_CERT"}

 

2). 若identity_Type=5时，

{"auth_code":"M_P_COMPANY_UID"}
	 */
	private String bizParams;

	/** 
	* 证件号目前支持2种：
a. 工商注册号：NATIONAL_LEGAL  
b. 社会统一信用代码 : NATIONAL_LEGAL_MERGE

1). 若identity_type=2时：
identity_param={\"certNo\":\"440000400004160\",\"certType\":\"NATIONAL_LEGAL\",\"name\":\"中国移动通信集团广东有限公司\"}"

2). 若identity_type=5时：
identity_param={\"userId\":\"2088xxxxxx\"}"
	 */
	private String identityParam;

	/** 
	* 用户身份标识类型：
5： userId入参的类型标识；
2： 证件号和姓名的入参的类型标识
	 */
	private String identityType;

	public void setBizParams(String bizParams) {
		this.bizParams = bizParams;
	}
	public String getBizParams() {
		return this.bizParams;
	}

	public void setIdentityParam(String identityParam) {
		this.identityParam = identityParam;
	}
	public String getIdentityParam() {
		return this.identityParam;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityType() {
		return this.identityType;
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
		return "zhima.auth.engine.organizationauth";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("biz_params", this.bizParams);
		txtParams.put("identity_param", this.identityParam);
		txtParams.put("identity_type", this.identityType);
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

	public Class<ZhimaAuthEngineOrganizationauthResponse> getResponseClass() {
		return ZhimaAuthEngineOrganizationauthResponse.class;
	}
}
