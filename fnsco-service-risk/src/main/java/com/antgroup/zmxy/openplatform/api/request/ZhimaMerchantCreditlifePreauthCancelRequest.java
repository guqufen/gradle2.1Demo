package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaMerchantCreditlifePreauthCancelResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.preauth.cancel request
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:25:57
 */
public class ZhimaMerchantCreditlifePreauthCancelRequest implements ZhimaRequest<ZhimaMerchantCreditlifePreauthCancelResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 待解冻预授权冻结资金订单号，或解冻请求流水号
	 */
	private String outOrderNo;

	/** 
	* 预授权号
	 */
	private String preAuthNo;

	/** 
	* 取消预授权冻结资金原因
	 */
	private String remark;

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getOutOrderNo() {
		return this.outOrderNo;
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
		return "zhima.merchant.creditlife.preauth.cancel";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("out_order_no", this.outOrderNo);
		txtParams.put("pre_auth_no", this.preAuthNo);
		txtParams.put("remark", this.remark);
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

	public Class<ZhimaMerchantCreditlifePreauthCancelResponse> getResponseClass() {
		return ZhimaMerchantCreditlifePreauthCancelResponse.class;
	}
}
