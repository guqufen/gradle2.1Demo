package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditWatchlistBriefGetResponse;

/**
 * ALIPAY API: zhima.credit.watchlist.brief.get request
 * 
 * @author auto create
 * @since 1.0, 2017-03-31 16:37:50
 */
public class ZhimaCreditWatchlistBriefGetRequest implements ZhimaRequest<ZhimaCreditWatchlistBriefGetResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 证件类型对应的证件号码， 如：身份证号， 护照号，userId
	 */
	private String certNo;

	/** 
	* 当前支持3种类型的输入：
IDENTITY_CARD (身份证)
PASSPORT (护照)
ALIPAY_USER_ID (支付宝uid)
	 */
	private String certType;

	/** 
	* 当cert_type 为ALIPAY_USER_ID时证件名称可为空
	 */
	private String name;

	/** 
	* 芝麻开放平台信用产品码， 唯一标示云产品
	 */
	private String productCode;

	/** 
	* 商户请求的唯一标志，长度64位以内字符串，仅限字母数字下划线组合。
该标识作为业务调用的唯一标识，商户要保证其业务唯一性，使用相同transaction_id的查询，
芝麻在一段时间内（一般为1天）返回首次查询结果，
超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费。
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
		return "zhima.credit.watchlist.brief.get";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("cert_no", this.certNo);
		txtParams.put("cert_type", this.certType);
		txtParams.put("name", this.name);
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

	public Class<ZhimaCreditWatchlistBriefGetResponse> getResponseClass() {
		return ZhimaCreditWatchlistBriefGetResponse.class;
	}
}
