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

	@ApiModelProperty(value = "账单类型", name = "billType", example = "账单类型:0交易收入1:交易支出")
	private String billType;
	
	@ApiModelProperty(value = "账单类型名称", name = "billTypeName", example = "账单类型:交易收入,交易支出")
	private String billTypeName;
	
	@ApiModelProperty(value = "账单日期", name = "billDayDate", example = "账单日期")
	private String billDayDate;
	
	@ApiModelProperty(value = "账单金额", name = "billDayDate", example = "账单金额")
	private String amount;
	
	@ApiModelProperty(value = "账单状态", name = "status", example = "账单状态(0未执行1执行中2失败3成功)")
	private Integer status;
	
	/**
	 * status
	 *
	 * @return  the status
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getStatus() {
		return status;
	}

	/**
	 * status
	 *
	 * @param   status    the status to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * billTypeName
	 *
	 * @return  the billTypeName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBillTypeName() {
		return billTypeName;
	}

	/**
	 * billTypeName
	 *
	 * @param   billTypeName    the billTypeName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

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
