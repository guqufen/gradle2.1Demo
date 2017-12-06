package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:51:59
 */

public class EveryDayTurnoverVO extends VO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 5127446006332514659L;
	
	/**
	 * 统计日期
	 */
	@ApiModelProperty(value = "统计日期(yyyy-MM-dd)", name = "turnoverDate", example = "统计日期(yyyy-MM-dd)")
	private String turnoverDate;
	
	/**
	 * 订单笔数
	 */
	@ApiModelProperty(value = "笔数", name = "orderNum", example = "笔数")
	private String orderNum;
	
	/**
	 * 交易额
	 */
	@ApiModelProperty(value = "交易额", name = "turnover", example = "交易额")
	private String turnover;

	/**
	 * turnoverDate
	 *
	 * @return  the turnoverDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTurnoverDate() {
		return turnoverDate;
	}

	/**
	 * turnoverDate
	 *
	 * @param   turnoverDate    the turnoverDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTurnoverDate(String turnoverDate) {
		this.turnoverDate = turnoverDate;
	}

	/**
	 * orderNum
	 *
	 * @return  the orderNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * orderNum
	 *
	 * @param   orderNum    the orderNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
