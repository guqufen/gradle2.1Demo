package com.antgroup.zmxy.openplatform.api.request;

import java.util.Map;

import com.antgroup.zmxy.openplatform.api.ZhimaRequest;
import com.antgroup.zmxy.openplatform.api.internal.util.ZhimaHashMap;
import com.antgroup.zmxy.openplatform.api.response.ZhimaCreditEpNegativeQueryResponse;

/**
 * ALIPAY API: zhima.credit.ep.negative.query request
 * 
 * @author auto create
 * @since 1.0, 2017-05-16 15:50:38
 */
public class ZhimaCreditEpNegativeQueryRequest implements ZhimaRequest<ZhimaCreditEpNegativeQueryResponse> {

	private ZhimaHashMap udfParams; // add user-defined text parameters
	private String apiVersion="1.0";

	/** 
	* 枚举值 1:个人 2:企业
	 */
	private String category;

	/** 
	* 查询个人，必须填入证件号。查询企业，不需要填写证件号
	 */
	private String certName;

	/** 
	* 个人的时候必填，填入IDENTITY_CARD。目前只支持内地身份证。
	 */
	private String certType;

	/** 
	* 枚举值 :
资产冻结:zcdj
资产查封:zccf
欠税信息：satparty
案件串联：anjianparty
工商行政处罚：aicparty
质检处罚：qtsparty
环保处罚：epbparty
	 */
	private String dataType;

	/** 
	* 企业名称或个人姓名
	 */
	private String name;

	/** 
	* 翻页页码
	 */
	private String pageNum;

	/** 
	* 产品码
	 */
	private String productCode;

	/** 
	* transaction_id是代表一笔请求的唯一标志，该标识作为对账的关键信息，对于用户使用相同transaction_id的查询，芝麻在一天（86400秒）内返回首次查询数据，超过有效期的查询即为无效并返回异常（错误码xxxx），有效期内的重复查询不重新计费。 transaction_id 推荐生成方式是：30位，（其中17位时间值（精确到毫秒）：yyyyMMddHHmmssSSS）加上（13位自增数字：1234567890123）
	 */
	private String transactionId;

	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return this.category;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getCertName() {
		return this.certName;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertType() {
		return this.certType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataType() {
		return this.dataType;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageNum() {
		return this.pageNum;
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
		return "zhima.credit.ep.negative.query";
	}

	public Map<String, String> getTextParams() {		
		ZhimaHashMap txtParams = new ZhimaHashMap();
		txtParams.put("category", this.category);
		txtParams.put("cert_name", this.certName);
		txtParams.put("cert_type", this.certType);
		txtParams.put("data_type", this.dataType);
		txtParams.put("name", this.name);
		txtParams.put("page_num", this.pageNum);
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

	public Class<ZhimaCreditEpNegativeQueryResponse> getResponseClass() {
		return ZhimaCreditEpNegativeQueryResponse.class;
	}
}
