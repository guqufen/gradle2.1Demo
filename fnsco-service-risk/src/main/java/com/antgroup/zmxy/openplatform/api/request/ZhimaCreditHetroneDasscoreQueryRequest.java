package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditHetroneDasscoreQueryResponse;

/**
 * ALIPAY API: zhima.credit.hetrone.dasscore.query request
 * 
 * @author auto create
 * @since 1.0, 2017-09-07 14:52:06
 */
public class ZhimaCreditHetroneDasscoreQueryRequest implements ZhimaRequest<ZhimaCreditHetroneDasscoreQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 近3月交易总金额
	 */
	private Long amtBankcardTransacThreeMonths;

	/** 
	* 近十二个月有交易的月数
	 */
	private Long cntBankcardTransacTwelveMonths;

	/** 
	* 手机在网时长
	 */
	private Long cntMobileOnline;

	/** 
	* 通讯录分数
	 */
	private Long contactScore;

	/** 
	* 最近有无境外消费
	 */
	private Boolean existsBankcardTransacOversea;

	/** 
	* 性别
	 */
	private String gender;

	/** 
	* 用户在商端的身份标识
	 */
	private String openId;

	/** 
	* 信用产品码，对应云产品的标识
	 */
	private String productCode;

	/** 
	* 代表一笔请求的唯一标志，该标识作为对账的关键信息，对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常，有效期内的反复查询不重新计费。
transaction_id 推荐生成方式是：30位，（其中17位时间值（精确到毫秒）：yyyyMMddHHmmssSSS）加上（13位自增数字：1234567890123）
	 */
	private String transactionId;

	public void setAmtBankcardTransacThreeMonths(Long amtBankcardTransacThreeMonths) {
		this.amtBankcardTransacThreeMonths = amtBankcardTransacThreeMonths;
	}
	public Long getAmtBankcardTransacThreeMonths() {
		return this.amtBankcardTransacThreeMonths;
	}

	public void setCntBankcardTransacTwelveMonths(Long cntBankcardTransacTwelveMonths) {
		this.cntBankcardTransacTwelveMonths = cntBankcardTransacTwelveMonths;
	}
	public Long getCntBankcardTransacTwelveMonths() {
		return this.cntBankcardTransacTwelveMonths;
	}

	public void setCntMobileOnline(Long cntMobileOnline) {
		this.cntMobileOnline = cntMobileOnline;
	}
	public Long getCntMobileOnline() {
		return this.cntMobileOnline;
	}

	public void setContactScore(Long contactScore) {
		this.contactScore = contactScore;
	}
	public Long getContactScore() {
		return this.contactScore;
	}

	public void setExistsBankcardTransacOversea(Boolean existsBankcardTransacOversea) {
		this.existsBankcardTransacOversea = existsBankcardTransacOversea;
	}
	public Boolean getExistsBankcardTransacOversea() {
		return this.existsBankcardTransacOversea;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId() {
		return this.openId;
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
		return "zhima.credit.hetrone.dasscore.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("amt_bankcard_transac_three_months", this.amtBankcardTransacThreeMonths);
		txtParams.put("cnt_bankcard_transac_twelve_months", this.cntBankcardTransacTwelveMonths);
		txtParams.put("cnt_mobile_online", this.cntMobileOnline);
		txtParams.put("contact_score", this.contactScore);
		txtParams.put("exists_bankcard_transac_oversea", this.existsBankcardTransacOversea);
		txtParams.put("gender", this.gender);
		txtParams.put("open_id", this.openId);
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

	public Class<ZhimaCreditHetroneDasscoreQueryResponse> getResponseClass() {
		return ZhimaCreditHetroneDasscoreQueryResponse.class;
	}
}
