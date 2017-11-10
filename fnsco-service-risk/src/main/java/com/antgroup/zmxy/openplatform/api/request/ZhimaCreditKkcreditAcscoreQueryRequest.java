package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditKkcreditAcscoreQueryResponse;

/**
 * ALIPAY API: zhima.credit.kkcredit.acscore.query request
 * 
 * @author auto create
 * @since 1.0, 2017-08-18 13:35:59
 */
public class ZhimaCreditKkcreditAcscoreQueryRequest implements ZhimaRequest<ZhimaCreditKkcreditAcscoreQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 近90天深夜联系人
	 */
	private Long lnizedLnitCttPpl;

	/** 
	* 近150天被叫通话天数
	 */
	private Long lonfizedAnsCttDay;

	/** 
	* 近150天固话通话时长
	 */
	private Long lonfizedRgCttTm;

	/** 
	* 近120天工作日通话联系人占比
	 */
	private String lontwzedWeekCttPplPct;

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
	* 近5月内平均套餐金额占比
	 */
	private String trcLsmfiAvgPlanTotalPct;

	public void setLnizedLnitCttPpl(Long lnizedLnitCttPpl) {
		this.lnizedLnitCttPpl = lnizedLnitCttPpl;
	}
	public Long getLnizedLnitCttPpl() {
		return this.lnizedLnitCttPpl;
	}

	public void setLonfizedAnsCttDay(Long lonfizedAnsCttDay) {
		this.lonfizedAnsCttDay = lonfizedAnsCttDay;
	}
	public Long getLonfizedAnsCttDay() {
		return this.lonfizedAnsCttDay;
	}

	public void setLonfizedRgCttTm(Long lonfizedRgCttTm) {
		this.lonfizedRgCttTm = lonfizedRgCttTm;
	}
	public Long getLonfizedRgCttTm() {
		return this.lonfizedRgCttTm;
	}

	public void setLontwzedWeekCttPplPct(String lontwzedWeekCttPplPct) {
		this.lontwzedWeekCttPplPct = lontwzedWeekCttPplPct;
	}
	public String getLontwzedWeekCttPplPct() {
		return this.lontwzedWeekCttPplPct;
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
		return "zhima.credit.kkcredit.acscore.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("lnized_lnit_ctt_ppl", this.lnizedLnitCttPpl);
		txtParams.put("lonfized_ans_ctt_day", this.lonfizedAnsCttDay);
		txtParams.put("lonfized_rg_ctt_tm", this.lonfizedRgCttTm);
		txtParams.put("lontwzed_week_ctt_ppl_pct", this.lontwzedWeekCttPplPct);
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

	public Class<ZhimaCreditKkcreditAcscoreQueryResponse> getResponseClass() {
		return ZhimaCreditKkcreditAcscoreQueryResponse.class;
	}
}
