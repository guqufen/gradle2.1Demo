/**
 * 
 */
/**
 * @author Administrator
 *
 */
package net.fnsco.web.controller.e789.pay.zxyh.jo;

import net.fnsco.core.base.JO;

public class GenerateQRCodeAliPayJO extends JO {
	
    private String  innerCode; 		// 内部商户号
    private String  ip; 			//发起支付的客户端真实IP
    private String  orderBody;	 	//商品或支付单简要描述
    private String txnAmt;			//订单总金额(交易单位为分，例:1.23元=123) 只能整数
    
	public String getInnerCode() {
		return innerCode;
	}
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
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
}