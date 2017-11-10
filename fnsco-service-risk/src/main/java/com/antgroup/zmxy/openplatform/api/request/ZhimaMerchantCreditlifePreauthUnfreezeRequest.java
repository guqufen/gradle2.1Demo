package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaMerchantCreditlifePreauthUnfreezeResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.preauth.unfreeze request
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:25:40
 */
public class ZhimaMerchantCreditlifePreauthUnfreezeRequest implements ZhimaRequest<ZhimaMerchantCreditlifePreauthUnfreezeResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 待解冻资金(元)
	 */
	private String payAmount;

	/** 
	* 预授权后产生的预授权号
	 */
	private String preAuthNo;

	/** 
	* 发起资金解冻原因
	 */
	private String remark;

	/** 
	* 交易流水号
	 */
	private String transactionId;

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayAmount() {
		return this.payAmount;
	}

	public void setPreAuthNo(String preAuthNo) {
		this.preAuthNo = preAuthNo;
	}
	public String getPreAuthNo() {
		return this.preAuthNo;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
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
		return "zhima.merchant.creditlife.preauth.unfreeze";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("pay_amount", this.payAmount);
		txtParams.put("pre_auth_no", this.preAuthNo);
		txtParams.put("remark", this.remark);
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

	public Class<ZhimaMerchantCreditlifePreauthUnfreezeResponse> getResponseClass() {
		return ZhimaMerchantCreditlifePreauthUnfreezeResponse.class;
	}
}
