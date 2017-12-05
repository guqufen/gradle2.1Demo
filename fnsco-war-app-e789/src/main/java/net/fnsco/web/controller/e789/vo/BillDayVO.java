package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午9:41:17
 */

public class BillDayVO extends VO {
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 2065318222831467840L;

	@ApiModelProperty(value = "账单类型", name = "billType", example = "")
	private String billType;
	
	@ApiModelProperty(value = "账单日期", name = "billDayDate", example = "")
	private String billDayDate;
	
	@ApiModelProperty(value = "账单金额", name = "billDayDate", example = "")
	private String amount;

	/**
	 * billType
	 *
	 * @return  the billType
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBillType() {
		return billType;
	}

	/**
	 * billType
	 *
	 * @param   billType    the billType to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBillType(String billType) {
		this.billType = billType;
	}

	/**
	 * billDayDate
	 *
	 * @return  the billDayDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBillDayDate() {
		return billDayDate;
	}

	/**
	 * billDayDate
	 *
	 * @param   billDayDate    the billDayDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBillDayDate(String billDayDate) {
		this.billDayDate = billDayDate;
	}

	/**
	 * amount
	 *
	 * @return  the amount
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getAmount() {
		return amount;
	}

	/**
	 * amount
	 *
	 * @param   amount    the amount to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
