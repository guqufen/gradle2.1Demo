package net.fnsco.web.controller.e789.vo;

import java.util.List;

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
	
	private String billDate;
	
	private String totalExpenditure;
	
	private String totalRevenue;
	
	private List<BillDayVO> billDetail;

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
	 * billDetail
	 *
	 * @return  the billDetail
	 * @since   CodingExample Ver 1.0
	*/
	
	public List<BillDayVO> getBillDetail() {
		return billDetail;
	}

	/**
	 * billDetail
	 *
	 * @param   billDetail    the billDetail to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBillDetail(List<BillDayVO> billDetail) {
		this.billDetail = billDetail;
	}
	
}
