package net.fnsco.web.controller.open.jo;

import net.fnsco.core.base.JO;

public class FuiouJO extends JO {

	private static final long serialVersionUID = 1L;

	private String version;// 版本号

	private String ins_cd;// 机构号

	private String merchantno_fuiou;// 富友商户号

	private String card_type;// 卡属性：01-借记卡；02-信用卡；03-准贷记卡；04-预付卡

	private String pay_type;// 交易类型：1-微信；2-支付宝；3-银行卡；4-现金；5无卡支付；6-qq钱包；7-京东钱包

	private String scan_type;// 扫码类型：1-主扫；2-被扫(扫码交易存在)

	private String terminal_id;// 富友终端号

	private String out_trade_no;// 富友支付订单号(订单号，系统跟踪号)

	private String retri_ref_no;// 扫码支付订单号

	private String out_refund_no;// 富友退款订单号(原交易订单号，原参考号)，撤销/退款交易存在

	private String channel_trade_no;// 通道订单号，渠道返回的订单号

	private String pay_status;// 交易状态

	private String pay_msg;// 交易状态描述，1-支付成功；2-退款成功；3-撤销成功；4-冲正成功

	private String total_fee;// 总交易金额，支付金额，分

	private String refund_fee;// 退款金额，分

	private String createtime;// 交易时间：yyyyMMddHHmmss

	private String settle_date;// 富友清算日期：yyyyMMdd

	private String cardno_encrypt;// 加密卡号(MD5+salt),银行卡交易存在

	private String cardno;// 卡号，前6后4，银行卡交易存在

	private String issuer;// 发卡行代码，银行卡交易存在

	private String terminal_trace;// 凭证号

	private String reference;// 备注

	private String buyer_id;// 买家在渠道帐号,扫码支付存在

	private String openid;// 买家在渠道帐号，扫码支付存在

	private String in_order_no;// 外部订单号，商户传入时存在

	private String key_sign;// 签名
	
	private String key;//密钥

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRetri_ref_no() {
		return retri_ref_no;
	}

	public void setRetri_ref_no(String retri_ref_no) {
		this.retri_ref_no = retri_ref_no;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIns_cd() {
		return ins_cd;
	}

	public void setIns_cd(String ins_cd) {
		this.ins_cd = ins_cd;
	}

	public String getMerchantno_fuiou() {
		return merchantno_fuiou;
	}

	public void setMerchantno_fuiou(String merchantno_fuiou) {
		this.merchantno_fuiou = merchantno_fuiou;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getScan_type() {
		return scan_type;
	}

	public void setScan_type(String scan_type) {
		this.scan_type = scan_type;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public String getChannel_trade_no() {
		return channel_trade_no;
	}

	public void setChannel_trade_no(String channel_trade_no) {
		this.channel_trade_no = channel_trade_no;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getPay_msg() {
		return pay_msg;
	}

	public void setPay_msg(String pay_msg) {
		this.pay_msg = pay_msg;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getSettle_date() {
		return settle_date;
	}

	public void setSettle_date(String settle_date) {
		this.settle_date = settle_date;
	}

	public String getCardno_encrypt() {
		return cardno_encrypt;
	}

	public void setCardno_encrypt(String cardno_encrypt) {
		this.cardno_encrypt = cardno_encrypt;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getTerminal_trace() {
		return terminal_trace;
	}

	public void setTerminal_trace(String terminal_trace) {
		this.terminal_trace = terminal_trace;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIn_order_no() {
		return in_order_no;
	}

	public void setIn_order_no(String in_order_no) {
		this.in_order_no = in_order_no;
	}

	public String getKey_sign() {
		return key_sign;
	}

	public void setKey_sign(String key_sign) {
		this.key_sign = key_sign;
	}

}
