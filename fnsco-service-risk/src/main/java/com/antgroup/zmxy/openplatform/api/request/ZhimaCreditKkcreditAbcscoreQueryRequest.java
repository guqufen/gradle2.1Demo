package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditKkcreditAbcscoreQueryResponse;

/**
 * ALIPAY API: zhima.credit.kkcredit.abcscore.query request
 * 
 * @author auto create
 * @since 1.0, 2017-08-22 15:59:07
 */
public class ZhimaCreditKkcreditAbcscoreQueryRequest implements ZhimaRequest<ZhimaCreditKkcreditAbcscoreQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 年龄
	 */
	private Long age;

	/** 
	* 未销信用卡开户距今月数的平均数
	 */
	private String crdAgeUnclsAvg;

	/** 
	* 性别，男=1，女=0
	 */
	private Long gender;

	/** 
	* 近90天深夜联系人
	 */
	private Long lnizedLnitCttPpl;

	/** 
	* 当前正常的信用卡账户已用额度与可用额度之比的均值
	 */
	private String normCdtBalUsedPctAvg;

	/** 
	* 芝麻会员在商户端的身份标识。
	 */
	private String openId;

	/** 
	* 手机注册时长
	 */
	private Long phoneUseMth;

	/** 
	* 产品码
	 */
	private String productCode;

	/** 
	* 近150天短信发送联系人
	 */
	private Long smsLonfizedSendPpl;

	/** 
	* 商户传入的业务流水号。此字段由商户生成，需确保唯一性，用于定位每一次请求，后续按此流水进行对帐。生成规则: 固定30位数字串，前17位为精确到毫秒的时间yyyyMMddhhmmssSSS，后13位为自增数字。
	 */
	private String transactionId;

	/** 
	* 近5月套餐金额占比
	 */
	private String trcLsmfiAvgPlanTotalPct;

	public void setAge(Long age) {
		this.age = age;
	}
	public Long getAge() {
		return this.age;
	}

	public void setCrdAgeUnclsAvg(String crdAgeUnclsAvg) {
		this.crdAgeUnclsAvg = crdAgeUnclsAvg;
	}
	public String getCrdAgeUnclsAvg() {
		return this.crdAgeUnclsAvg;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}
	public Long getGender() {
		return this.gender;
	}

	public void setLnizedLnitCttPpl(Long lnizedLnitCttPpl) {
		this.lnizedLnitCttPpl = lnizedLnitCttPpl;
	}
	public Long getLnizedLnitCttPpl() {
		return this.lnizedLnitCttPpl;
	}

	public void setNormCdtBalUsedPctAvg(String normCdtBalUsedPctAvg) {
		this.normCdtBalUsedPctAvg = normCdtBalUsedPctAvg;
	}
	public String getNormCdtBalUsedPctAvg() {
		return this.normCdtBalUsedPctAvg;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId() {
		return this.openId;
	}

	public void setPhoneUseMth(Long phoneUseMth) {
		this.phoneUseMth = phoneUseMth;
	}
	public Long getPhoneUseMth() {
		return this.phoneUseMth;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
	}

	public void setSmsLonfizedSendPpl(Long smsLonfizedSendPpl) {
		this.smsLonfizedSendPpl = smsLonfizedSendPpl;
	}
	public Long getSmsLonfizedSendPpl() {
		return this.smsLonfizedSendPpl;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTrcLsmfiAvgPlanTotalPct(String trcLsmfiAvgPlanTotalPct) {
		this.trcLsmfiAvgPlanTotalPct = trcLsmfiAvgPlanTotalPct;
	}
	public String getTrcLsmfiAvgPlanTotalPct() {
		return this.trcLsmfiAvgPlanTotalPct;
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
		return "zhima.credit.kkcredit.abcscore.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("age", this.age);
		txtParams.put("crd_age_uncls_avg", this.crdAgeUnclsAvg);
		txtParams.put("gender", this.gender);
		txtParams.put("lnized_lnit_ctt_ppl", this.lnizedLnitCttPpl);
		txtParams.put("norm_cdt_bal_used_pct_avg", this.normCdtBalUsedPctAvg);
		txtParams.put("open_id", this.openId);
		txtParams.put("phone_use_mth", this.phoneUseMth);
		txtParams.put("product_code", this.productCode);
		txtParams.put("sms_lonfized_send_ppl", this.smsLonfizedSendPpl);
		txtParams.put("transaction_id", this.transactionId);
		txtParams.put("trc_lsmfi_avg_plan_total_pct", this.trcLsmfiAvgPlanTotalPct);
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

	public Class<ZhimaCreditKkcreditAbcscoreQueryResponse> getResponseClass() {
		return ZhimaCreditKkcreditAbcscoreQueryResponse.class;
	}
}
