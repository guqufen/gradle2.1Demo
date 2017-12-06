package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:30:01
 */

public class TotalTurnoverVO extends VO {
	

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 6362944390003339158L;
	
	@ApiModelProperty(value = "昨日营业额",example="昨日营业额")
	private String yesterdayTurnover;
	@ApiModelProperty(value = "今日营业额",example="今日营业额")
	private String todayTurnover;
	@ApiModelProperty(value = "本月营业额",example="本月营业额")
	private String thisMonthTurnover;

	/**
	 * yesterdayTurnover
	 *
	 * @return  the yesterdayTurnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getYesterdayTurnover() {
		return yesterdayTurnover;
	}

	/**
	 * yesterdayTurnover
	 *
	 * @param   yesterdayTurnover    the yesterdayTurnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setYesterdayTurnover(String yesterdayTurnover) {
		this.yesterdayTurnover = yesterdayTurnover;
	}

	/**
	 * todayTurnover
	 *
	 * @return  the todayTurnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTodayTurnover() {
		return todayTurnover;
	}

	/**
	 * todayTurnover
	 *
	 * @param   todayTurnover    the todayTurnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTodayTurnover(String todayTurnover) {
		this.todayTurnover = todayTurnover;
	}

	/**
	 * thisMonthTurnover
	 *
	 * @return  the thisMonthTurnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getThisMonthTurnover() {
		return thisMonthTurnover;
	}

	/**
	 * thisMonthTurnover
	 *
	 * @param   thisMonthTurnover    the thisMonthTurnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setThisMonthTurnover(String thisMonthTurnover) {
		this.thisMonthTurnover = thisMonthTurnover;
	}
	
}
