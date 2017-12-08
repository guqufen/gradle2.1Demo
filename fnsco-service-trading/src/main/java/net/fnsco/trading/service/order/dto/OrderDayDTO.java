package net.fnsco.trading.service.order.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月7日 下午5:59:14
 */

public class OrderDayDTO extends DTO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String tradeDate;
	
	private Integer orderNumber;
	
	private String turnover;

	/**
	 * tradeDate
	 *
	 * @return  the tradeDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTradeDate() {
		return tradeDate;
	}

	/**
	 * tradeDate
	 *
	 * @param   tradeDate    the tradeDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	/**
	 * orderNumber
	 *
	 * @return  the orderNumber
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getOrderNumber() {
		return orderNumber;
	}

	/**
	 * orderNumber
	 *
	 * @param   orderNumber    the orderNumber to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * turnover
	 *
	 * @return  the turnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTurnover() {
		return turnover;
	}

	/**
	 * turnover
	 *
	 * @param   turnover    the turnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	
	
}
