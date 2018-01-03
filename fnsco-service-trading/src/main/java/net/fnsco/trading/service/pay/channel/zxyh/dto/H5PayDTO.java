package net.fnsco.trading.service.pay.channel.zxyh.dto;

import net.fnsco.core.base.DTO;

public class H5PayDTO extends DTO {

	// 初始化一些数据域
	public void init(String merId) {
		this.setEncoding("UTF-8");// 编码方式，默认UTF-8
		this.setSignMethod("02");// 签名方式，02-MD5
		this.setTxnType("01");// 交易类型，01-消费
		this.setTxnSubType("010133");// 交易子类型，010133-WAP支付
		this.setChannelType("6002");// 接入渠道，6002-商户互联网渠道
		this.setPayAccessType("02");// 接入支付类型，02-接口支付
		this.setAccountFlag("Y");// 分账标识 C String(1) Y-分账其它或不传-不分账
		this.setCurrencyType("156");// 交易币种，默认156
		this.setMerId(merId);// 普通商户或一级商户的商户号
		this.setTermId("WEB");// 终端号，默认WEB
	}

	private static final long serialVersionUID = 1L;
	private String encoding;// 编码方式 M String(10) 默认取值：UTF-8
	private String signMethod;// 签名方法 M String(2) 签名方式：02：MD5;03：RSA
	private String signAture;// 签名 M String(1024) 填写对报文摘要的签名
	private String txnType;// 交易类型 String(2) 01：消费；
	private String txnSubType;// 交易子类型 M String(6) 010133：WAP支付
	private String channelType;// 接入渠道 M String(4) 6002：商户互联网渠道
	private String payAccessType;// 接入支付类型 M String(2) 02：接口支付
	private String backEndUrl;// 后台通知地址 backEndUrl M String(512) 接收支付网关异步通知回调地址
	private String merId;// 商户编号 M String(15) 普通商户或一级商户的商户号
	private String secMerId;// 分账子商户号 secMerId C String(15)
							// 使用分账功能时上传，是与merId关联的分账子商户号
	private String termId;// 终端编号 termId C String(8) 终端编号默认WEB
	private String termIp;// 客户端真实IP termIp M String(16) 发起支付的客户端真实IP
	private String orderId;// 商户订单号 orderId M String(32) 商户系统内部的订单号,32
							// 个字符内、可包含字母, 确保在商户系统唯一
	private String orderTime;// 交易起始时间 orderTime M String(14)
								// 订单生成时间，格式为[yyyyMMddHHmmss] ,
								// 如2009年12月25日9点10分10秒表示为20091225091010
	private String orderBody;// 商品描述 orderBody M String(32) 商品或支付单简要描述
	private String orderDetail;// 商品详情 orderDetail C String(8192) 商品详情
	private String orderGoodsTag;// 商品标记 orderGoodsTag C String(32)
									// 商品标记，代金券或立减优惠功能的参数
	private String txnAmt;// 交易金额 txnAmt M String(12)
							// 订单总金额(交易单位为分，例:1.23元=123)，只能整数
	private String currencyType;// 交易币种 currencyType M String(3) 默认是156：人民币
	private String accountFlag;// 分账标识 accountFlag C String(1)
								// 使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号secMerId必传
								// Y-分账 其它或不传-不分账
	private String secMerFeeRate;// 分账子商户交易费率 secMerFeeRate C String(16)
									// 使用分账功能时上传，是分账子商户secMerId的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户merId的费率低
	private String attach;// 附加数据 attach C String(127)
							// 附加数据，在支付通知中原样返回，该字段主要用于商户携带订单的自定义数据，要求格式如下：bank_mch_name=xxxxx&bank_mch_id=xxxx&商户自定义参数
	private String limitPay;// 指定支付方式 limitPay C String(32)
							// no_credit--指定不能使用信用卡支付
	private String needBankType;// 是否需要获取支付行类型 needBankType C String(1)
								// 传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
	private String sceneInfo;// 应用描述 sceneInfo M String(1024) 所填内容参考下面备注
	private String orderType;// 订单类型 orderType R String(1)
								// 京东支付必填，固定值：0或者1（0：：实物，1：虚拟）
	private String userId;// 用户账号 userId R String(64) 京东支付必填
	private String callbackUrl;// 支付成功跳转的URL callbackUrl R String(256) 京东支付必填
	private String requestType;// 请求类型 requestType R String(2) 京东支付必填
								// PC：在PC端网页调用 H5：在移动端H5调用
	// 应答加的域
	private String respCode;// 响应码
	private String respMsg;// 响应信息
	private String txnSeqId;// 平台交易流水号，供后续退货或撤销或对账用
	private String txnTime;// 平台受理时间
	private String prepayid;// 预支付交易会话标识,该值有效期为20分钟
	private String mwebUrl;// 支付跳转链接,mwebUrl为拉起微信支付
	// 收银台的中转url，可通过访问此url 来拉起微信支付收银台。
	// 将mwebUrl中的ydsd.html改成ydsdrd.html，再在链接后加两个参数，cburl与pid，pid是请求mwebUrl的唯一标识，cburl是支付完成之后的跳转页面地址。
	// *原直接调用mweburl拉起支付页面的，仍然支持。没有跳转需求的商户可以保持原接口调用不变。

	// 交易通知加的域
	private String settleAmt;// 清算金额
	private String settleCurrencyCode;// 清算币种
	private String settleDate;// 清算日期
	private String transactionId;// 渠道订单号
	private String endTime;// 支付完成时间
	private String bankType;// 付款银行
	private String orderOpenId;// 支付用户的ID

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

	public String getBackEndUrl() {
		return backEndUrl;
	}

	public void setBackEndUrl(String backEndUrl) {
		this.backEndUrl = backEndUrl;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSecMerId() {
		return secMerId;
	}

	public void setSecMerId(String secMerId) {
		this.secMerId = secMerId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermIp() {
		return termIp;
	}

	public void setTermIp(String termIp) {
		this.termIp = termIp;
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

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getOrderGoodsTag() {
		return orderGoodsTag;
	}

	public void setOrderGoodsTag(String orderGoodsTag) {
		this.orderGoodsTag = orderGoodsTag;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	public String getSecMerFeeRate() {
		return secMerFeeRate;
	}

	public void setSecMerFeeRate(String secMerFeeRate) {
		this.secMerFeeRate = secMerFeeRate;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public String getNeedBankType() {
		return needBankType;
	}

	public void setNeedBankType(String needBankType) {
		this.needBankType = needBankType;
	}

	public String getSceneInfo() {
		return sceneInfo;
	}

	public void setSceneInfo(String sceneInfo) {
		this.sceneInfo = sceneInfo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getPrepayid() {
		return prepayid;
	}

	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}

	public String getMwebUrl() {
		return mwebUrl;
	}

	public void setMwebUrl(String mwebUrl) {
		this.mwebUrl = mwebUrl;
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
