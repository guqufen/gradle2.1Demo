package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCustomerContractInitializeResponse;

/**
 * ALIPAY API: zhima.customer.contract.initialize request
 * 
 * @author auto create
 * @since 1.0, 2017-09-19 14:41:27
 */
public class ZhimaCustomerContractInitializeRequest implements ZhimaRequest<ZhimaCustomerContractInitializeResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 合约内容，PDF文件流，BASE64编码
	 */
	private String contractFile;

	/** 
	* 合约名称，展示给签约方
	 */
	private String contractName;

	/** 
	* 芝麻认证产品码,示例值为真实的产品码
	 */
	private String productCode;

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}
	public String getContractFile() {
		return this.contractFile;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractName() {
		return this.contractName;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
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
		return "zhima.customer.contract.initialize";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("contract_file", this.contractFile);
		txtParams.put("contract_name", this.contractName);
		txtParams.put("product_code", this.productCode);
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

	public Class<ZhimaCustomerContractInitializeResponse> getResponseClass() {
		return ZhimaCustomerContractInitializeResponse.class;
	}
}
