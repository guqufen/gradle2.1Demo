/**
 * 
 */
/**
 * @author Administrator
 *
 */
package net.fnsco.web.controller.e789.jo;

import net.fnsco.core.base.JO;

public class GenerateQRCodeJO extends JO {
	
//    private String  innerCode; 		// 内部商户号
    private String  ip; 			//发起支付的客户端真实IP
    private String  orderBody;	 	//商品或支付单简要描述
    private String txnAmt;			//订单总金额(交易单位为分，例:1.23元=123) 只能整数
    
    private Integer userId;
    /**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
     * 交易子类型41微信42支付宝
     */
    private String     paySubType;
    /**
     * 渠道类型00拉卡拉01浦发02爱农03法奈昇04聚惠分05中信银行90富友
     */
    private String channelType;
    
    
	/**
	 * @return the channelType
	 */
	public String getChannelType() {
		return channelType;
	}
	/**
	 * @param channelType the channelType to set
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	/**
	 * @return the paySubType
	 */
	public String getPaySubType() {
		return paySubType;
	}
	/**
	 * @param paySubType the paySubType to set
	 */
	public void setPaySubType(String paySubType) {
		this.paySubType = paySubType;
	}
//	public String getInnerCode() {
//		return innerCode;
//	}
//	public void setInnerCode(String innerCode) {
//		this.innerCode = innerCode;
//	}
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