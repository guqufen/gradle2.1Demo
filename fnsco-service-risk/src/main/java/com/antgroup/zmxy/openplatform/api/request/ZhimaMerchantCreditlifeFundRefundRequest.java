package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaMerchantCreditlifeFundRefundResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.fund.refund request
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:26:12
 */
public class ZhimaMerchantCreditlifeFundRefundRequest implements ZhimaRequest<ZhimaMerchantCreditlifeFundRefundResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 
	 */
	private String bizProduct;

	/** 
	* 商户发起扣款时的订单号
	 */
	private String outOrderNo;

	/** 
	* 退款金额
	 */
	private String payAmount;

	/** 
	* 交易信息说明(退款原因)
	 */
	private String remark;

	public void setBizProduct(String bizProduct) {
		this.bizProduct = bizProduct;
	}
	public String getBizProduct() {
		return this.bizProduct;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getOutOrderNo() {
		return this.outOrderNo;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayAmount() {
		return this.payAmount;
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
		return "zhima.merchant.creditlife.fund.refund";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("biz_product", this.bizProduct);
		txtParams.put("out_order_no", this.outOrderNo);
		txtParams.put("pay_amount", this.payAmount);
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

	public Class<ZhimaMerchantCreditlifeFundRefundResponse> getResponseClass() {
		return ZhimaMerchantCreditlifeFundRefundResponse.class;
	}
}
