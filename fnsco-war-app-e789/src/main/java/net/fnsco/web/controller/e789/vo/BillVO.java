package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午6:02:16
 */

public class BillVO extends VO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -5286210613589072764L;
	
	@ApiModelProperty(value = "账单日期(yyyy-MM)", name = "billDate", example = "账单日期(yyyy-MM)")
	private String billDate;
	
	@ApiModelProperty(value = "账单总支出", name = "totalExpenditure", example = "账单总支出")
	private String totalExpenditure;
	
	@ApiModelProperty(value = "账单总收入", name = "totalRevenue", example = "账单总收入")
	private String totalRevenue;
	
	@ApiModelProperty(value = "每日账单列表", name = "billDetails", example = "每日账单列表")
	private List<BillDayVO> billDetails;

	/**
	 * billDate
	 *
	 * @return  the billDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBillDate() {
		return billDate;
	}

	/**
	 * billDate
	 *
	 * @param   billDate    the billDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	/**
	 * totalExpenditure
	 *
	 * @return  the totalExpenditure
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTotalExpenditure() {
		return totalExpenditure;
	}

	/**
	 * totalExpenditure
	 *
	 * @param   totalExpenditure    the totalExpenditure to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalExpenditure(String totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}

	/**
	 * totalRevenue
	 *
	 * @return  the totalRevenue
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTotalRevenue() {
		return totalRevenue;
	}

	/**
	 * totalRevenue
	 *
	 * @param   totalRevenue    the totalRevenue to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	/**
	 * billDetails
	 *
	 * @return  the billDetails
	 * @since   CodingExample Ver 1.0
	*/
	
	public List<BillDayVO> getBillDetails() {
		return billDetails;
	}

	/**
	 * billDetails
	 *
	 * @param   billDetails    the billDetails to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBillDetails(List<BillDayVO> billDetails) {
		this.billDetails = billDetails;
	}
	
}
