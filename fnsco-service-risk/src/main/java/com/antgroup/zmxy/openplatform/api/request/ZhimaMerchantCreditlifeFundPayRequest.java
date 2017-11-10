package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaMerchantCreditlifeFundPayResponse;

/**
 * ALIPAY API: zhima.merchant.creditlife.fund.pay request
 * 
 * @author auto create
 * @since 1.0, 2017-08-04 10:26:31
 */
public class ZhimaMerchantCreditlifeFundPayRequest implements ZhimaRequest<ZhimaMerchantCreditlifeFundPayResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 代扣协议号(代扣扣款时必须提供)
	 */
	private String agreementNo;

	/** 
	* 扣款类型(withholding_pay:代扣扣款,preauth_pay:预授权转支付)
	 */
	private String fundPayType;

	/** 
	* 
	 */
	private String goodsTitle;

	/** 
	* 商品类型(0:虚拟物品,1:实物)
	 */
	private String goodsType;

	/** 
	* 商户订单号
	 */
	private String outOrderNo;

	/** 
	* 支付金额
	 */
	private String payAmount;

	/** 
	* 预授权号(付款方式为预授权转支付时必须提供)
	 */
	private String preAuthNo;

	/** 
	* 芝麻用户id
	 */
	private String roleId;

	/** 
	* 收款方支付宝id
	 */
	private String sellerId;

	/** 
	* 
	 */
	private String transactionId;

	/** 
	* 支付宝用户id（付款方id）
	 */
	private String userId;

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getAgreementNo() {
		return this.agreementNo;
	}

	public void setFundPayType(String fundPayType) {
		this.fundPayType = fundPayType;
	}
	public String getFundPayType() {
		return this.fundPayType;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	public String getGoodsTitle() {
		return this.goodsTitle;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsType() {
		return this.goodsType;
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

	public void setPreAuthNo(String preAuthNo) {
		this.preAuthNo = preAuthNo;
	}
	public String getPreAuthNo() {
		return this.preAuthNo;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleId() {
		return this.roleId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerId() {
		return this.sellerId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return this.userId;
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
		return "zhima.merchant.creditlife.fund.pay";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("agreement_no", this.agreementNo);
		txtParams.put("fund_pay_type", this.fundPayType);
		txtParams.put("goods_title", this.goodsTitle);
		txtParams.put("goods_type", this.goodsType);
		txtParams.put("out_order_no", this.outOrderNo);
		txtParams.put("pay_amount", this.payAmount);
		txtParams.put("pre_auth_no", this.preAuthNo);
		txtParams.put("role_id", this.roleId);
		txtParams.put("seller_id", this.sellerId);
		txtParams.put("transaction_id", this.transactionId);
		txtParams.put("user_id", this.userId);
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

	public Class<ZhimaMerchantCreditlifeFundPayResponse> getResponseClass() {
		return ZhimaMerchantCreditlifeFundPayResponse.class;
	}
}
