package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class AlipayRechargeJO extends JO {
	
	/**
     * app用户ID
     */
    @ApiModelProperty(value = "APP用户id")
    private Integer userId;
    
  //支付总金额
    @ApiModelProperty(value = "支付金额")
    private String  paymentAmount;

	/**
	 * userId
	 *
	 * @return  the userId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getUserId() {
		return userId;
	}

	/**
	 * userId
	 *
	 * @param   userId    the userId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * paymentAmount
	 *
	 * @return  the paymentAmount
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * paymentAmount
	 *
	 * @param   paymentAmount    the paymentAmount to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
    
}
