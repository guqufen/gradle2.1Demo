package net.fnsco.trading.service.pay.channel.zxyh.dto;

import net.fnsco.core.base.DTO;

public class WeChatPubPayDTO extends DTO {

	// 初始化一些数据域
	public void init(String merId) {
		this.setEncoding("UTF-8");// 编码方式，默认UTF-8
		this.setSignMethod("02");// 签名方式，02-MD5
		this.setTxnType("01");// 交易类型，01-消费
		// this.setTxnSubType("010133");// 交易子类型，010133-WAP支付
		this.setChannelType("6002");// 接入渠道，6002-商户互联网渠道
		this.setPayAccessType("02");// 接入支付类型，02-接口支付
		this.setAccountFlag("Y");// 分账标识 C String(1) Y-分账其它或不传-不分账
		this.setCurrencyType("156");// 交易币种，默认156
		this.setMerId(merId);// 普通商户或一级商户的商户号
		this.setTermId("WEB");// 终端号，默认WEB
		this.setIndependentTransactionFlag("Y");// 独立商户交易标识,传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易
		this.setNeedBankType("Y");// 是否需要获取支付行类型,传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
	}

	private static final long serialVersionUID = 1L;
	private String encoding;// 编码方式 encoding M String(10) 默认取值：UTF-8
	private String signMethod;// 签名方法 signMethod M String(2) 签名方式： 02：MD5 03：RSA
	private String txnType;// 交易类型 txnType M String(2) 01：消费
	private String txnSubType;// 交易子类型 txnSubType M String(6) 010131：微信公众号支付
								// 010134：微信小程序支付 010502：QQ公众号支付
	private String channelType;// 接入渠道 channelType M String(4) 6002：商户互联网渠道
	private String payAccessType;// 接入支付类型 payAccessType M String(2) 02：接口支付
	private String backEndUrl;// 后台通知地址 backEndUrl M String(512) 接收支付网关异步通知回调地址
	private String merId;// 商户编号 merId M String(15) 普通商户或一级商户的商户号
	private String secMerId;// 分账子商户号 secMerId C String(15)
							// 使用分账功能时上传，是与merId关联的分账子商户号
	private String termId;// 终端编号 termId C String(8) 终端编号默认WEB
	private String termIp;// 终端IP termIp C String(16) 接入商机器IP
	private String orderId;// 商户订单号 orderId M String(32) 商户系统内部的订单号,32
							// 个字符内、可包含字母, 确保在商户系统唯一
	private String orderTime;// 交易起始时间 orderTime M String(14)
								// 订单生成时间，格式为[yyyyMMddHHmmss]
								// ,如2009年12月25日9点10分10秒表示为20091225091010
	private String productId;// 商品ID productId C Strng(32)
								// trade_type=010130，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	private String orderBody;// 商品描述 orderBody M String(32) 商品或支付单简要描述
	private String orderDetail;// 商品详情 orderDetail C String(8192) 商品详情
	private String orderGoodsTag;// 商品标记 orderGoodsTag C String(32)
									// 商品标记，代金券或立减优惠功能的参数
	private String orderSubOpenid;// 用户子标识 orderSubOpenid M String(128)
									// 用户在商户公众号的唯一标识（openid），QQ公众号支付非必填
	private String txnAmt;// 交易金额 txnAmt M String(12)
							// 订单总金额(交易单位为分，例:1.23元=123)，只能整数
	private String currencyType;// 交易币种 currencyType M String(3) 默认是156：人民币
	private String accountFlag;// 分账标识 accountFlag C String(1)
								// 使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号secMerId必传
								// Y-分账 其它或不传-不分账
	private String secMerFeeRate;// 分账子商户交易费率 secMerFeeRate C String(16)
	private String attach;// 附加数据 attach C String(127)
	private String limitPay;// 指定支付方式 limitPay C String(32)
							// no_credit--指定不能使用信用卡支付
	private String needBankType;// 是否需要获取支付行类型 needBankType C String(1)
								// 传“Y”，即会在查询订单以及异步通知时返回“bankType”（支付行类型）、“orderOpenId”（支付用户id）字段
	private String independentTransactionFlag;// 独立商户交易标识
												// independentTransactionFlag C
												// String(1)
												// 传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易

	// 返回的数据
	private String respCode;// 响应码 respCode M 参考：4.1章节附录 B 应答码说明
	private String respMsg;// 响应信息 respMsg M 交易处理结果信息
	private String errCode;// 错误代码 errCode C
	private String errCodeDesc;// 错误代码描述 errCodeDesc C
	private String txnSeqId;// 平台交易流水号 txnSeqId M 平台流水号，供后续退货或撤销或对账用
	private String txnTime;// 平台受理时间 txnTime M 平台受理时间 ， 格式[yyyyMMddHHmmss]
	private String prepayId;// 预支付交易会话标识 prepayId M 预支付会话标识，该值有效期为20分钟，供QQ
							// JSAPI调用参数
	private String appId;// 公众号ID appId M 供微信JSAPI调用参数
	private String timeStamp;// 时间戳 timeStamp M 供微信JSAPI调用参数
	private String nonceStr;// 随机字符串 nonceStr M 供微信JSAPI调用参数
	// private String package;// 订单详情扩展字符串 package M 供微信JSAPI调用参数

	// 通知
	private String endTime;// 支付完成时间
	private String transactionId;// 渠道订单号
	private String settleAmt;// 清算金额
	private String settleDate;// 清算日期

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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getOrderSubOpenid() {
		return orderSubOpenid;
	}

	public void setOrderSubOpenid(String orderSubOpenid) {
		this.orderSubOpenid = orderSubOpenid;
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

	public String getIndependentTransactionFlag() {
		return independentTransactionFlag;
	}

	public void setIndependentTransactionFlag(String independentTransactionFlag) {
		this.independentTransactionFlag = independentTransactionFlag;
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

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDesc() {
		return errCodeDesc;
	}

	public void setErrCodeDesc(String errCodeDesc) {
		this.errCodeDesc = errCodeDesc;
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

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

}
