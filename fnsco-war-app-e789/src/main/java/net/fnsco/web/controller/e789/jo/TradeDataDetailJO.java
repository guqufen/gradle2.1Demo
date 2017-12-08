package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月6日 下午1:42:10
 */

public class TradeDataDetailJO extends JO {
	
//	@ApiModelProperty(value="流水ID",example="流水ID，可以根据该ID 查询出详情")
//	private String traId;
	
	@ApiModelProperty(value="订单号",example="订单号")
	private String orderNo;

	/**
	 * orderNo
	 *
	 * @return  the orderNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNo
	 *
	 * @param   orderNo    the orderNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
//	/**
//	 * traId
//	 *
//	 * @return  the traId
//	 * @since   CodingExample Ver 1.0
//	*/
//	
//	public String getTraId() {
//		return traId;
//	}
//
//	/**
//	 * traId
//	 *
//	 * @param   traId    the traId to set
//	 * @since   CodingExample Ver 1.0
//	 */
//	
//	public void setTraId(String traId) {
//		this.traId = traId;
//	}
	
}
