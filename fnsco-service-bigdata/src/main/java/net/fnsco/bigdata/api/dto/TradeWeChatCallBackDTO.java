package net.fnsco.bigdata.api.dto;

public class TradeWeChatCallBackDTO {

	private String encoding;// 编码方式		R
	private String signMethod;//签名方法		R
	private String signAture;//签名		M
	private String txnType;//交易类型		R
	private String txnSubType;//交易子类型		R
	private String channelType;//接入渠道		R
	private String payAccessType;//接入支付类型		R
	private String merId;//商户编号		R
	private String termId;//终端编号		C
	private String orderId;//商户订单号		R
	private String orderTime;//商户订单发送时间		R
	private String orderBody;//商品描述		R
	private String txnAmt;//交易金额		R
	private String orderSubOpenid;//用户子标识		R
	private String currencyType;//交易币种		R
	private String backEndUrl;//商户后台通知url		R
	private String respCode;//响应码		M	参考：4.1章节附录 B 应答码说明
	private String respMsg;//响应信息		M	交易处理结果信息
	private String txnSeqId;//平台交易流水号		M	平台流水号，供后续退货或撤销或对账用
	private String txnTime;//平台受理时间		M	平台受理时间 ，	格式[yyyyMMddHHmmss]
	private String settleAmt;//清算金额		M	平台清算金额，(交易单位为分，例:1.23元=123)，只能整数

	private String settleCurrencyCode;//清算币种		M	平台清算日志，供后续退货或撤销用
	private String settleDate;//清算日期		M	平台清算日期，格式[yyyyMMdd] 
	private String transactionId;//渠道订单号		M	渠道订单号
	private String endTime;//支付完成时间		M	支付完成时间，格式为yyyyMMddHHmmss
	private String attach;//商家数据包		R	仅微信、QQ钱包返回；商家数据包，原样返回
	private String bankType;//付款银行		R	仅微信、QQ钱包返回；银行类型，采用字符串类型的银行标识
	private String orderOpenId;//支付用户id		R	仅微信、QQ钱包返回；用户在商户appid下的唯一标识
	
	
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getSignAture() {
		return signAture;
	}
	public void setSignAture(String signAture) {
		this.signAture = signAture;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getTxnSubType() {
		return txnSubType;
	}
	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getPayAccessType() {
		return payAccessType;
	}
	public void setPayAccessType(String payAccessType) {
		this.payAccessType = payAccessType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getTermId() {
		return termId;
	}
	public void setTermId(String termId) {
		this.termId = termId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderBody() {
		return orderBody;
	}
	public void setOrderBody(String orderBody) {
		this.orderBody = orderBody;
	}
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
	public String getOrderSubOpenid() {
		return orderSubOpenid;
	}
	public void setOrderSubOpenid(String orderSubOpenid) {
		this.orderSubOpenid = orderSubOpenid;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getBackEndUrl() {
		return backEndUrl;
	}
	public void setBackEndUrl(String backEndUrl) {
		this.backEndUrl = backEndUrl;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getTxnSeqId() {
		return txnSeqId;
	}
	public void setTxnSeqId(String txnSeqId) {
		this.txnSeqId = txnSeqId;
	}
	public String getTxnTime() {
		return txnTime;
	}
	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}
	public String getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}
	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getOrderOpenId() {
		return orderOpenId;
	}
	public void setOrderOpenId(String orderOpenId) {
		this.orderOpenId = orderOpenId;
	}
}
