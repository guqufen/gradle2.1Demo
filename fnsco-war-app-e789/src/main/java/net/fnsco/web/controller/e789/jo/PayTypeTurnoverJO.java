package net.fnsco.web.controller.e789.jo;

import net.fnsco.core.base.JO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:37:49
 */

public class PayTypeTurnoverJO extends JO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 2401665445233019020L;
	
	private String startDate;
	private String endDate;
	private Integer userId;
	/**
	 * startDate
	 *
	 * @return  the startDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getStartDate() {
		return startDate;
	}
	/**
	 * startDate
	 *
	 * @param   startDate    the startDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * endDate
	 *
	 * @return  the endDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getEndDate() {
		return endDate;
	}
	/**
	 * endDate
	 *
	 * @param   endDate    the endDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
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
	
}
