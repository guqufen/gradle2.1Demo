package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditBqsDefaultscoreQueryResponse;

/**
 * ALIPAY API: zhima.credit.bqs.defaultscore.query request
 * 
 * @author auto create
 * @since 1.0, 2017-09-07 14:55:48
 */
public class ZhimaCreditBqsDefaultscoreQueryRequest implements ZhimaRequest<ZhimaCreditBqsDefaultscoreQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 申请事件通过比率
	 */
	private String acceptPercentApply;

	/** 
	* 年龄
	 */
	private Long age;

	/** 
	* 申请时间（小时，0-23）
	 */
	private String applyHour;

	/** 
	* 多头申请商户类型数量
	 */
	private Long applyPartnerTypeCount;

	/** 
	* 黑名单命中个数
	 */
	private Long blackCount;

	/** 
	* 本人主要通话活动区域在几线城市
	 */
	private String callActiveArea;

	/** 
	* 排除被叫电话很短的联系人个数
	 */
	private Long contactExcludedCount;

	/** 
	* 朋友圈活动区域在几线城市
	 */
	private String contactsActiveArea;

	/** 
	* 关联设备数量
	 */
	private Long deviceCount;

	/** 
	* 性别
	 */
	private String gender;

	/** 
	* GPS城市数量
	 */
	private Long gpsCityCount;

	/** 
	* 全天未使用通话和短信功能天数
	 */
	private Long inactiveDays;

	/** 
	* IP城市数量
	 */
	private Long ipCityCount;

	/** 
	* 设备中借贷app数量
	 */
	private Long loanAppCount;

	/** 
	* 手机号
	 */
	private String mobile;

	/** 
	* 多头申请商户数量
	 */
	private Long multiapplyCount;

	/** 
	* 夜间通话次数
	 */
	private Long nightCalls;

	/** 
	* 联系人中非手机个数
	 */
	private Long noneMobileCount;

	/** 
	* 仅有被叫联系人个数
	 */
	private Long onlyTerminCount;

	/** 
	* 入网时长
	 */
	private Long openDays;

	/** 
	* 用户在商端的身份标识
	 */
	private String openId;

	/** 
	* 该用户第一次事件距今时间
	 */
	private Long phoneDays;

	/** 
	* 信用产品码，对应云产品的标识
	 */
	private String productCode;

	/** 
	* 省份代码
	 */
	private String provinceId;

	/** 
	* 申请事件拒绝比率
	 */
	private String rejectPercentApply;

	/** 
	* 话费消费总金额
	 */
	private Long sumInfoCostMoney;

	/** 
	* 最常用联系人，多个用逗号分隔
	 */
	private String topContact;

	/** 
	* 代表一笔请求的唯一标志，该标识作为对账的关键信息，对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常，有效期内的反复查询不重新计费。
transaction_id 推荐生成方式是：30位，（其中17位时间值（精确到毫秒）：yyyyMMddHHmmssSSS）加上（13位自增数字：1234567890123）
	 */
	private String transactionId;

	/** 
	* 白名单等级
	 */
	private String whiteGrade;

	/** 
	* 上班时间手机号关联城市数量
	 */
	private String workCityCount;

	public void setAcceptPercentApply(String acceptPercentApply) {
		this.acceptPercentApply = acceptPercentApply;
	}
	public String getAcceptPercentApply() {
		return this.acceptPercentApply;
	}

	public void setAge(Long age) {
		this.age = age;
	}
	public Long getAge() {
		return this.age;
	}

	public void setApplyHour(String applyHour) {
		this.applyHour = applyHour;
	}
	public String getApplyHour() {
		return this.applyHour;
	}

	public void setApplyPartnerTypeCount(Long applyPartnerTypeCount) {
		this.applyPartnerTypeCount = applyPartnerTypeCount;
	}
	public Long getApplyPartnerTypeCount() {
		return this.applyPartnerTypeCount;
	}

	public void setBlackCount(Long blackCount) {
		this.blackCount = blackCount;
	}
	public Long getBlackCount() {
		return this.blackCount;
	}

	public void setCallActiveArea(String callActiveArea) {
		this.callActiveArea = callActiveArea;
	}
	public String getCallActiveArea() {
		return this.callActiveArea;
	}

	public void setContactExcludedCount(Long contactExcludedCount) {
		this.contactExcludedCount = contactExcludedCount;
	}
	public Long getContactExcludedCount() {
		return this.contactExcludedCount;
	}

	public void setContactsActiveArea(String contactsActiveArea) {
		this.contactsActiveArea = contactsActiveArea;
	}
	public String getContactsActiveArea() {
		return this.contactsActiveArea;
	}

	public void setDeviceCount(Long deviceCount) {
		this.deviceCount = deviceCount;
	}
	public Long getDeviceCount() {
		return this.deviceCount;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}

	public void setGpsCityCount(Long gpsCityCount) {
		this.gpsCityCount = gpsCityCount;
	}
	public Long getGpsCityCount() {
		return this.gpsCityCount;
	}

	public void setInactiveDays(Long inactiveDays) {
		this.inactiveDays = inactiveDays;
	}
	public Long getInactiveDays() {
		return this.inactiveDays;
	}

	public void setIpCityCount(Long ipCityCount) {
		this.ipCityCount = ipCityCount;
	}
	public Long getIpCityCount() {
		return this.ipCityCount;
	}

	public void setLoanAppCount(Long loanAppCount) {
		this.loanAppCount = loanAppCount;
	}
	public Long getLoanAppCount() {
		return this.loanAppCount;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return this.mobile;
	}

	public void setMultiapplyCount(Long multiapplyCount) {
		this.multiapplyCount = multiapplyCount;
	}
	public Long getMultiapplyCount() {
		return this.multiapplyCount;
	}

	public void setNightCalls(Long nightCalls) {
		this.nightCalls = nightCalls;
	}
	public Long getNightCalls() {
		return this.nightCalls;
	}

	public void setNoneMobileCount(Long noneMobileCount) {
		this.noneMobileCount = noneMobileCount;
	}
	public Long getNoneMobileCount() {
		return this.noneMobileCount;
	}

	public void setOnlyTerminCount(Long onlyTerminCount) {
		this.onlyTerminCount = onlyTerminCount;
	}
	public Long getOnlyTerminCount() {
		return this.onlyTerminCount;
	}

	public void setOpenDays(Long openDays) {
		this.openDays = openDays;
	}
	public Long getOpenDays() {
		return this.openDays;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getOpenId() {
		return this.openId;
	}

	public void setPhoneDays(Long phoneDays) {
		this.phoneDays = phoneDays;
	}
	public Long getPhoneDays() {
		return this.phoneDays;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceId() {
		return this.provinceId;
	}

	public void setRejectPercentApply(String rejectPercentApply) {
		this.rejectPercentApply = rejectPercentApply;
	}
	public String getRejectPercentApply() {
		return this.rejectPercentApply;
	}

	public void setSumInfoCostMoney(Long sumInfoCostMoney) {
		this.sumInfoCostMoney = sumInfoCostMoney;
	}
	public Long getSumInfoCostMoney() {
		return this.sumInfoCostMoney;
	}

	public void setTopContact(String topContact) {
		this.topContact = topContact;
	}
	public String getTopContact() {
		return this.topContact;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setWhiteGrade(String whiteGrade) {
		this.whiteGrade = whiteGrade;
	}
	public String getWhiteGrade() {
		return this.whiteGrade;
	}

	public void setWorkCityCount(String workCityCount) {
		this.workCityCount = workCityCount;
	}
	public String getWorkCityCount() {
		return this.workCityCount;
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
		return "zhima.credit.bqs.defaultscore.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("accept_percent_apply", this.acceptPercentApply);
		txtParams.put("age", this.age);
		txtParams.put("apply_hour", this.applyHour);
		txtParams.put("apply_partner_type_count", this.applyPartnerTypeCount);
		txtParams.put("black_count", this.blackCount);
		txtParams.put("call_active_area", this.callActiveArea);
		txtParams.put("contact_excluded_count", this.contactExcludedCount);
		txtParams.put("contacts_active_area", this.contactsActiveArea);
		txtParams.put("device_count", this.deviceCount);
		txtParams.put("gender", this.gender);
		txtParams.put("gps_city_count", this.gpsCityCount);
		txtParams.put("inactive_days", this.inactiveDays);
		txtParams.put("ip_city_count", this.ipCityCount);
		txtParams.put("loan_app_count", this.loanAppCount);
		txtParams.put("mobile", this.mobile);
		txtParams.put("multiapply_count", this.multiapplyCount);
		txtParams.put("night_calls", this.nightCalls);
		txtParams.put("none_mobile_count", this.noneMobileCount);
		txtParams.put("only_termin_count", this.onlyTerminCount);
		txtParams.put("open_days", this.openDays);
		txtParams.put("open_id", this.openId);
		txtParams.put("phone_days", this.phoneDays);
		txtParams.put("product_code", this.productCode);
		txtParams.put("province_id", this.provinceId);
		txtParams.put("reject_percent_apply", this.rejectPercentApply);
		txtParams.put("sum_info_cost_money", this.sumInfoCostMoney);
		txtParams.put("top_contact", this.topContact);
		txtParams.put("transaction_id", this.transactionId);
		txtParams.put("white_grade", this.whiteGrade);
		txtParams.put("work_city_count", this.workCityCount);
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

	public Class<ZhimaCreditBqsDefaultscoreQueryResponse> getResponseClass() {
		return ZhimaCreditBqsDefaultscoreQueryResponse.class;
	}
}
