package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月20日 上午10:03:59
 */

public class Chart7DayDataVO extends VO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 5059223326764043885L;
	
	@ApiModelProperty(value = "总的营业额", name = "totalTurnover", example = "总的营业额(单位格式已经调整好:x.xx)")
	private String totalTurnover;
	
	@ApiModelProperty(value = "总订单数", name = "totalOrderNumber", example = "总订单笔数")
	private Integer totalOrderNumber;
	
	
	private List<EveryDayTurnoverVO> everyDayData;


	/**
	 * totalTurnover
	 *
	 * @return  the totalTurnover
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTotalTurnover() {
		return totalTurnover;
	}


	/**
	 * totalTurnover
	 *
	 * @param   totalTurnover    the totalTurnover to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalTurnover(String totalTurnover) {
		this.totalTurnover = totalTurnover;
	}


	/**
	 * totalOrderNumber
	 *
	 * @return  the totalOrderNumber
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getTotalOrderNumber() {
		return totalOrderNumber;
	}


	/**
	 * totalOrderNumber
	 *
	 * @param   totalOrderNumber    the totalOrderNumber to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalOrderNumber(Integer totalOrderNumber) {
		this.totalOrderNumber = totalOrderNumber;
	}


	/**
	 * everyDayData
	 *
	 * @return  the everyDayData
	 * @since   CodingExample Ver 1.0
	*/
	
	public List<EveryDayTurnoverVO> getEveryDayData() {
		return everyDayData;
	}


	/**
	 * everyDayData
	 *
	 * @param   everyDayData    the everyDayData to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setEveryDayData(List<EveryDayTurnoverVO> everyDayData) {
		this.everyDayData = everyDayData;
	}
	
}
