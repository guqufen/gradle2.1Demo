package net.fnsco.trading.service.pay.channel.zxyh.dto;

/*2.1.2   被扫订单支付（消费）交易接口
2.1.2.1 功能介绍
用户提供微信或支付宝App端的二维码信息，商户扫描用户二维码信息通过支付平台提供的二维码被扫支付的过程。
*/
public class PassivePayDTO {

	/**
	 * 初始化一些数据域
	 */
	public void init(String merId) {
		this.setStdmercno(merId);// 商户编号 M String(15)
												// 普通商户或一级商户的商户号，指中信内部15位商户号
		this.setSignMethod("02");// 签名方法 M String(2) 签名方式：02：MD5,03：RSA
		this.setStd400chnl("6005");// 接入渠道 M String(4) 6005 持牌机构MIS接入
		this.setStdpaytype("02");// 接入支付类型 M String(2) 02：接口支付。
		this.setStdtermid("WEB");// 终端编号
		this.setAccountFlag("Y");// 分账标识 C String(1)
									// 使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号stdmercno2必传
									// Y-分账其它或不传-不分账
		this.setIndependentTransactionFlag("Y");// 独立商户交易标识 C
												// String(1)传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易
	}

	private String signMethod; // 签名方法 M String(2) 签名方式：02：MD5,03：RSA
	private String stdmsgtype; // 消息类型 M String(4) "48"
	private String stdprocode; // 交易码 M String(6) 481000：微信消费 481003：支付宝二清消费
	private String std400chnl; // 接入渠道 M String(4) 6005 持牌机构MIS接入
	private String stdpaytype; // 接入支付类型 M String(2) 02：接口支付。
	private String stdmercno; // 商户编号 M String(15) 普通商户或一级商户的商户号，指中信内部15位商户号
	private String stdmercno2; // 二级商户号 C String(20)
								// 使用分账功能时上传，是与stdmercno关联的分账子商户号
	private String stdtermid; // 终端编号 M String(8) 终端编号
	private String stdorderid; // 商户订单号 M String(32) 商户系统内部的订单号,32 个字符内、可包含字母,
								// 确保在商户系统唯一
	private String stdbegtime; // 交易起始时间 M String(14)
								// 商户上送交易时间，格式为[yyyyMMddHHmmss],如2009年12月25日9点10分10秒，表示为20091225091010
	private String std400memo; // 商品描述 M String(127) 商品描述
	private String stdtranamt; // 交易金额 M String(12) 订单总金额(交易单位为分，例:1.23元=123),
								// 只能整数
	// 若交易码为信e通消费，且消费类型为
	// 01则代表权益次数，消费类型为
	// 02则代表积分值
	private String stddiscamt; // 不可优惠金额 C String(12) 支付宝不可优惠金额支付宝交易本字段必填。
	private String stdtrancur; // 交易币种 M String(3) 默认是156：人民币
	private String stdauthid; // 授权码 M String((128) 扫码支付授权码，设备读取用户微信信息
	private String stdqytype; // 消费类型 C String(2) 01:权益02:积分
	private String accountFlag; // 分账标识 C String(1)
								// 使用分账功能时上传，当值为Y时，表示交易为分账交易，分账子商户号stdmercno2必传
								// Y-分账其它或不传-不分账
	private String secMerFeeRate; // 分账子商户交易费率 C String(16)
									// 使用分账功能时上传，是分账子商户stdmercno2的交易费率（若费率是0.3%，此值传0.003，小数点后最多四位），此费率值不能比平台商户stdmercno的费率低
	private String signAture; // 签名 M String(1024) 填写对报文摘要的签名
	private String needBankType; // 返回bankType标识 C String(1)
									// 当值为“Y”时，支付成功、查询支付成功的订单会返回付款银行类型bankType
	private String independentTransactionFlag;// 独立商户交易标识 C String(1)
												// 传“Y”，并且accountFlag也传“Y”时，即代表为独立商户交易

	private String stdreciamt;// 实收金额,R
	private String stdpreamt;// 优惠金额,R
	private String stdopenid;// 用户标识,R
	private String stdauthid_code;// 授权码,R
	private String std400mgid;// 响应码,M,
	private String stdrtninfo;// 响应信息,M,交易处理结果信息
	private String stdrefnum;// 平台流水号,M,平台流水号，供后续退货或撤销或对账用
	private String ibslocdate;// 平台受理时间,M,平台受理时间 ，格式[yyyyMMddHHmmss]
	private String qstranamt;// 清算金额,M,平台清算金额，(交易单位为分，例:1.23元=123)，只能整数
	private String qstrancur;// 清算币种,M,平台清算日志，供后续退货或撤销用
	private String qs400trdt;// 清算日期,M,平台清算日期，格式[yyyyMMdd]
	private String bankType;// 付款银行,R, 当needBankType为“Y”且订单支付成功时返回
	private String transactionId;// 渠道订单号,R,订单支付成功时返回
	private String endTime;// 支付完成时间，R,订单支付成功时返回

	private String orgorderid;// 原支付交易的中信订单号或原支付交易的商户订单号

	private String srg400mgid;// 原交易应答码
	private String orgrtninfo;// 原交易应答信息

	private String orgrefnum;// 原支付交易的中信订单号

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getStdmsgtype() {
		return stdmsgtype;
	}

	public void setStdmsgtype(String stdmsgtype) {
		this.stdmsgtype = stdmsgtype;
	}

	public String getStdprocode() {
		return stdprocode;
	}

	public void setStdprocode(String stdprocode) {
		this.stdprocode = stdprocode;
	}

	public String getStd400chnl() {
		return std400chnl;
	}

	public void setStd400chnl(String std400chnl) {
		this.std400chnl = std400chnl;
	}

	public String getStdpaytype() {
		return stdpaytype;
	}

	public void setStdpaytype(String stdpaytype) {
		this.stdpaytype = stdpaytype;
	}

	public String getStdmercno() {
		return stdmercno;
	}

	public void setStdmercno(String stdmercno) {
		this.stdmercno = stdmercno;
	}

	public String getStdmercno2() {
		return stdmercno2;
	}

	public void setStdmercno2(String stdmercno2) {
		this.stdmercno2 = stdmercno2;
	}

	public String getStdtermid() {
		return stdtermid;
	}

	public void setStdtermid(String stdtermid) {
		this.stdtermid = stdtermid;
	}

	public String getStdorderid() {
		return stdorderid;
	}

	public void setStdorderid(String stdorderid) {
		this.stdorderid = stdorderid;
	}

	public String getStdbegtime() {
		return stdbegtime;
	}

	public void setStdbegtime(String stdbegtime) {
		this.stdbegtime = stdbegtime;
	}

	public String getStd400memo() {
		return std400memo;
	}

	public void setStd400memo(String std400memo) {
		this.std400memo = std400memo;
	}

	public String getStdtranamt() {
		return stdtranamt;
	}

	public void setStdtranamt(String stdtranamt) {
		this.stdtranamt = stdtranamt;
	}

	public String getStddiscamt() {
		return stddiscamt;
	}

	public void setStddiscamt(String stddiscamt) {
		this.stddiscamt = stddiscamt;
	}

	public String getStdtrancur() {
		return stdtrancur;
	}

	public void setStdtrancur(String stdtrancur) {
		this.stdtrancur = stdtrancur;
	}

	public String getStdauthid() {
		return stdauthid;
	}

	public void setStdauthid(String stdauthid) {
		this.stdauthid = stdauthid;
	}

	public String getStdqytype() {
		return stdqytype;
	}

	public void setStdqytype(String stdqytype) {
		this.stdqytype = stdqytype;
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

	public String getSignAture() {
		return signAture;
	}

	public void setSignAture(String signAture) {
		this.signAture = signAture;
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

	public String getStdreciamt() {
		return stdreciamt;
	}

	public void setStdreciamt(String stdreciamt) {
		this.stdreciamt = stdreciamt;
	}

	public String getStdpreamt() {
		return stdpreamt;
	}

	public void setStdpreamt(String stdpreamt) {
		this.stdpreamt = stdpreamt;
	}

	public String getStdopenid() {
		return stdopenid;
	}

	public void setStdopenid(String stdopenid) {
		this.stdopenid = stdopenid;
	}

	public String getStdauthid_code() {
		return stdauthid_code;
	}

	public void setStdauthid_code(String stdauthid_code) {
		this.stdauthid_code = stdauthid_code;
	}

	public String getStd400mgid() {
		return std400mgid;
	}

	public void setStd400mgid(String std400mgid) {
		this.std400mgid = std400mgid;
	}

	public String getStdrtninfo() {
		return stdrtninfo;
	}

	public void setStdrtninfo(String stdrtninfo) {
		this.stdrtninfo = stdrtninfo;
	}

	public String getStdrefnum() {
		return stdrefnum;
	}

	public void setStdrefnum(String stdrefnum) {
		this.stdrefnum = stdrefnum;
	}

	public String getIbslocdate() {
		return ibslocdate;
	}

	public void setIbslocdate(String ibslocdate) {
		this.ibslocdate = ibslocdate;
	}

	public String getQstranamt() {
		return qstranamt;
	}

	public void setQstranamt(String qstranamt) {
		this.qstranamt = qstranamt;
	}

	public String getQstrancur() {
		return qstrancur;
	}

	public void setQstrancur(String qstrancur) {
		this.qstrancur = qstrancur;
	}

	public String getQs400trdt() {
		return qs400trdt;
	}

	public void setQs400trdt(String qs400trdt) {
		this.qs400trdt = qs400trdt;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
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

	public String getOrgorderid() {
		return orgorderid;
	}

	public void setOrgorderid(String orgorderid) {
		this.orgorderid = orgorderid;
	}

	public String getOrgrefnum() {
		return orgrefnum;
	}

	public void setOrgrefnum(String orgrefnum) {
		this.orgrefnum = orgrefnum;
	}

	public String getSrg400mgid() {
		return srg400mgid;
	}

	public void setSrg400mgid(String srg400mgid) {
		this.srg400mgid = srg400mgid;
	}

	public String getOrgrtninfo() {
		return orgrtninfo;
	}

	public void setOrgrtninfo(String orgrtninfo) {
		this.orgrtninfo = orgrtninfo;
	}

}
