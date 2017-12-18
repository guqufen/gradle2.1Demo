package net.fnsco.trading.service.withdraw.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月18日 上午10:04:05
 */

public class MonthWithdrawCountDTO extends DTO {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 2268636672822988695L;
	
	/**
	 * 总交易额
	 */
	private String totalAmount;
	
	/**
	 * 交易类型
	 */
	private Integer tradeType;

	/**
	 * totalAmount
	 *
	 * @return  the totalAmount
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * totalAmount
	 *
	 * @param   totalAmount    the totalAmount to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * tradeType
	 *
	 * @return  the tradeType
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getTradeType() {
		return tradeType;
	}

	/**
	 * tradeType
	 *
	 * @param   tradeType    the tradeType to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	
}
