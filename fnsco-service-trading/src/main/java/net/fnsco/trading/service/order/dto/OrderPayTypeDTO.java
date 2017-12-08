package net.fnsco.trading.service.order.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月7日 下午4:54:39
 */

public class OrderPayTypeDTO extends DTO{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private String payType;
	
	private Integer orderNum;
	
	private BigDecimal turnover;

	/**
	 * payType
	 *
	 * @return  the payType
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getPayType() {
		return payType;
	}

	/**
	 * payType
	 *
	 * @param   payType    the payType to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * orderNum
	 *
	 * @return  the orderNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * orderNum
	 *
	 * @param   orderNum    the orderNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * turnover
	 *
	 * @return  the turnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public BigDecimal getTurnover() {
		return turnover;
	}

	/**
	 * turnover
	 *
	 * @param   turnover    the turnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTurnover(BigDecimal turnover) {
		this.turnover = turnover;
	}
	
}
