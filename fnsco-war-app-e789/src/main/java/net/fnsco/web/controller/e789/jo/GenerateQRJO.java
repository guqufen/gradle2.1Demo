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
	public String getTxnAmt() {
		return txnAmt;
	}
	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}
}