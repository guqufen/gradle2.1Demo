package net.fnsco.bigdata.api.dto;

public class TradeResultZXDTO {
	public void init(String merId) {
		this.setMerId(merId);
		this.setEncoding("UTF-8");
		this.setSignMethod("02");
		this.setTxnType("38");//
		this.setChannelType("6002");

	}

	// 微信
	private String encoding; // M String(10) 默认取值：UTF-8
	private String signMethod; // M String(2) 签名方式：
	// 02：MD5
	// 03：RSA
	private String signAture;; // M String(1024) 填写对报文摘要的签名
	private String txnType; // M String(2) 38：查询交易状态
	private String txnSubType;// M String(6) 383000：微信查询
	// 380501：QQ查询
	// 380601：京东查询
	private String channelType; // M String(4) 6002：商户互联网渠道
	private String payAccessType; // M String(2) 02：接口支付
	private String merId; // M String(15) 普通商户或一级商户的商户号
	private String secMerId; // C String(15) 使用分账功能时上传，是与merId关联的分账子商户号
	private String origOrderId;// M String(32) 原商户交易订单号orderId
	private String origOrderTime; // M String(14) 原始商户订单号交易起始时间
	private String orderTime;// M String(14) 商户上送交易时间，格式
	// 为[yyyyMMddHHmmss] ,
	// 如2009年12月25日9点10分10秒
	// 表示为20091225091010
	private String fetchOrderNo; // C String(1) 传的值为Y表示获取，不传该字段表示不获取
	private String wxOrderNo; // C String(64)
								// 该字段填微信订单号，传该字段则根据填的微信订单号查询，不传该字段则根据origOrderId查询
	private String qqOrderNo; // C String(64)
								// 该字段填QQ订单号，传该字段则根据填的QQ订单号查询，不传该字段则根据origOrderId查询
	private String accountFlag;// C String(1)
								// 使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号secMerId必传
	// Y-分账
	// 其它或不传-不分账
	private String independentTransactionFlag; // C String(1)
												// 传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易

	////////////////////// 支付宝////////////////////////////////////////////////////
	private String seqId; // M String(32) 该字段填原交易中信流水号或原交易商户订单号

	/**
	 * encoding
	 *
	 * @return the encoding
	 * @since CodingExample Ver 1.0
	 */

	public String getEncoding() {
		return encoding;
	}

	/**
	 * encoding
	 *
	 * @param encoding
	 *            the encoding to set
	 * @since CodingExample Ver 1.0
	 */

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * signMethod
	 *
	 * @return the signMethod
	 * @since CodingExample Ver 1.0
	 */

	public String getSignMethod() {
		return signMethod;
	}

	/**
	 * signMethod
	 *
	 * @param signMethod
	 *            the signMethod to set
	 * @since CodingExample Ver 1.0
	 */

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	/**
	 * signAture
	 *
	 * @return the signAture
	 * @since CodingExample Ver 1.0
	 */

	public String getSignAture() {
		return signAture;
	}

	/**
	 * signAture
	 *
	 * @param signAture
	 *            the signAture to set
	 * @since CodingExample Ver 1.0
	 */

	public void setSignAture(String signAture) {
		this.signAture = signAture;
	}

	/**
	 * txnType
	 *
	 * @return the txnType
	 * @since CodingExample Ver 1.0
	 */

	public String getTxnType() {
		return txnType;
	}

	/**
	 * txnType
	 *
	 * @param txnType
	 *            the txnType to set
	 * @since CodingExample Ver 1.0
	 */

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	/**
	 * txnSubType
	 *
	 * @return the txnSubType
	 * @since CodingExample Ver 1.0
	 */

	public String getTxnSubType() {
		return txnSubType;
	}

	/**
	 * txnSubType
	 *
	 * @param txnSubType
	 *            the txnSubType to set
	 * @since CodingExample Ver 1.0
	 */

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	/**
	 * channelType
	 *
	 * @return the channelType
	 * @since CodingExample Ver 1.0
	 */

	public String getChannelType() {
		return channelType;
	}

	/**
	 * channelType
	 *
	 * @param channelType
	 *            the channelType to set
	 * @since CodingExample Ver 1.0
	 */

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	/**
	 * payAccessType
	 *
	 * @return the payAccessType
	 * @since CodingExample Ver 1.0
	 */

	public String getPayAccessType() {
		return payAccessType;
	}

	/**
	 * payAccessType
	 *
	 * @param payAccessType
	 *            the payAccessType to set
	 * @since CodingExample Ver 1.0
	 */

	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}

	/**
	 * merId
	 *
	 * @return the merId
	 * @since CodingExample Ver 1.0
	 */

	public String getMerId() {
		return merId;
	}

	/**
	 * merId
	 *
	 * @param merId
	 *            the merId to set
	 * @since CodingExample Ver 1.0
	 */

	public void setMerId(String merId) {
		this.merId = merId;
	}

	/**
	 * secMerId
	 *
	 * @return the secMerId
	 * @since CodingExample Ver 1.0
	 */

	public String getSecMerId() {
		return secMerId;
	}

	/**
	 * secMerId
	 *
	 * @param secMerId
	 *            the secMerId to set
	 * @since CodingExample Ver 1.0
	 */

	public void setSecMerId(String secMerId) {
		this.secMerId = secMerId;
	}

	/**
	 * origOrderId
	 *
	 * @return the origOrderId
	 * @since CodingExample Ver 1.0
	 */

	public String getOrigOrderId() {
		return origOrderId;
	}

	/**
	 * origOrderId
	 *
	 * @param origOrderId
	 *            the origOrderId to set
	 * @since CodingExample Ver 1.0
	 */

	public void setOrigOrderId(String origOrderId) {
		this.origOrderId = origOrderId;
	}

	/**
	 * origOrderTime
	 *
	 * @return the origOrderTime
	 * @since CodingExample Ver 1.0
	 */

	public String getOrigOrderTime() {
		return origOrderTime;
	}

	/**
	 * origOrderTime
	 *
	 * @param origOrderTime
	 *            the origOrderTime to set
	 * @since CodingExample Ver 1.0
	 */

	public void setOrigOrderTime(String origOrderTime) {
		this.origOrderTime = origOrderTime;
	}

	/**
	 * orderTime
	 *
	 * @return the orderTime
	 * @since CodingExample Ver 1.0
	 */

	public String getOrderTime() {
		return orderTime;
	}

	/**
	 * orderTime
	 *
	 * @param orderTime
	 *            the orderTime to set
	 * @since CodingExample Ver 1.0
	 */

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * fetchOrderNo
	 *
	 * @return the fetchOrderNo
	 * @since CodingExample Ver 1.0
	 */

	public String getFetchOrderNo() {
		return fetchOrderNo;
	}

	/**
	 * fetchOrderNo
	 *
	 * @param fetchOrderNo
	 *            the fetchOrderNo to set
	 * @since CodingExample Ver 1.0
	 */

	public void setFetchOrderNo(String fetchOrderNo) {
		this.fetchOrderNo = fetchOrderNo;
	}

	/**
	 * wxOrderNo
	 *
	 * @return the wxOrderNo
	 * @since CodingExample Ver 1.0
	 */

	public String getWxOrderNo() {
		return wxOrderNo;
	}

	/**
	 * wxOrderNo
	 *
	 * @param wxOrderNo
	 *            the wxOrderNo to set
	 * @since CodingExample Ver 1.0
	 */

	public void setWxOrderNo(String wxOrderNo) {
		this.wxOrderNo = wxOrderNo;
	}

	/**
	 * qqOrderNo
	 *
	 * @return the qqOrderNo
	 * @since CodingExample Ver 1.0
	 */

	public String getQqOrderNo() {
		return qqOrderNo;
	}

	/**
	 * qqOrderNo
	 *
	 * @param qqOrderNo
	 *            the qqOrderNo to set
	 * @since CodingExample Ver 1.0
	 */

	public void setQqOrderNo(String qqOrderNo) {
		this.qqOrderNo = qqOrderNo;
	}

	/**
	 * accountFlag
	 *
	 * @return the accountFlag
	 * @since CodingExample Ver 1.0
	 */

	public String getAccountFlag() {
		return accountFlag;
	}

	/**
	 * accountFlag
	 *
	 * @param accountFlag
	 *            the accountFlag to set
	 * @since CodingExample Ver 1.0
	 */

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	/**
	 * independentTransactionFlag
	 *
	 * @return the independentTransactionFlag
	 * @since CodingExample Ver 1.0
	 */

	public String getIndependentTransactionFlag() {
		return independentTransactionFlag;
	}

	/**
	 * independentTransactionFlag
	 *
	 * @param independentTransactionFlag
	 *            the independentTransactionFlag to set
	 * @since CodingExample Ver 1.0
	 */

	public void setIndependentTransactionFlag(String independentTransactionFlag) {
		this.independentTransactionFlag = independentTransactionFlag;
	}

	/**
	 * seqId
	 *
	 * @return the seqId
	 * @since CodingExample Ver 1.0
	 */

	public String getSeqId() {
		return seqId;
	}

	/**
	 * seqId
	 *
	 * @param seqId
	 *            the seqId to set
	 * @since CodingExample Ver 1.0
	 */

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

}
