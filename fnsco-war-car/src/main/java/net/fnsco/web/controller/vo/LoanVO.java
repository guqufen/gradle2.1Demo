package net.fnsco.web.controller.vo;

import io.swagger.annotations.ApiModelProperty;

public class LoanVO {
	
	
	@ApiModelProperty(value="贷款订单id",example="贷款订单id")
	private Integer orderNo;

	/**
	 * orderNo
	 *
	 * @return  the orderNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNo
	 *
	 * @param   orderNo    the orderNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
