package net.fnsco.web.controller.jo;


import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class LoanJO2 extends JO{
	
	@ApiModelProperty(value = "贷款信息id",  example = "贷款信息id")
	private Integer orderId;

	/**
	 * 车品牌
	 */
	@ApiModelProperty(value = "车品牌",  example = "车品牌")
	private Integer carTypeId;
	/**
	 * 车型号
	 */
	@ApiModelProperty(value = "车型号",  example = "车型号")
	private Integer carSubTypeId;


	
	/**
	 * orderId
	 *
	 * @return  the orderId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * orderId
	 *
	 * @param   orderId    the orderId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the carTypeId
	 */
	public Integer getCarTypeId() {
		return carTypeId;
	}

	/**
	 * @param carTypeId the carTypeId to set
	 */
	public void setCarTypeId(Integer carTypeId) { 
		this.carTypeId = carTypeId;
	}

	/**
	 * @return the carModel
	 */
	public Integer getCarSubTypeId() {
		return carSubTypeId;
	}

	/**
	 * @param carModel the carModel to set
	 */
	public void setCarSubTypeId(Integer carSubTypeId) {
		this.carSubTypeId = carSubTypeId;
	}

	
}
