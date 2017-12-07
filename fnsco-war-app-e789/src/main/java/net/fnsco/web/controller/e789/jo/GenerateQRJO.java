/**
 * 
 */
/**
 * @author Administrator
 *
 */
package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class GenerateQRJO extends JO {
	
	@ApiModelProperty(value = "发起支付的客户端真实IP", name = "ip", example = "发起支付的客户端真实IP")
    private String  ip; 			
	@ApiModelProperty(value = "商品或支付单简要描述", name = "orderBody", example = "商品或支付单简要描述")
    private String  orderBody;	 	
	@ApiModelProperty(value = "订单总金额", name = "txnAmt", example = "订单总金额")
    private String txnAmt;			
	@ApiModelProperty(value = "用户id", name = "", example = "用户id")
    private Integer userId;
    
	/**
     * 交易子类型41微信42支付宝
     */
	@ApiModelProperty(value = "交易子类型41微信42支付宝", name = "", example = "交易子类型41微信42支付宝")
    private String paySubType;
    /**
     * 渠道类型00拉卡拉01浦发02爱农03法奈昇04聚惠分05中信银行90富友
     */
	@ApiModelProperty(value = "渠道类型", name = "", example = "渠道类型")
    private String channelType;
    
    
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