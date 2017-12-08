package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月6日 上午10:05:44
 */

public class TradeDataListVO extends VO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -225995636512846962L;
	
	/**
	 * 交易状态
	 */
	@ApiModelProperty(value="交易状态1000处理中1001成功1002失败1003已退货",example="交易状态1000处理中1001成功1002失败1003已退货")
	private String tradeStatus;
	
	/**
	 * 交易时间(格式MM月dd日 hh:mm:ss)
	 */
	@ApiModelProperty(value="交易时间(格式MM月dd日 hh:mm:ss)",example="交易时间(格式MM月dd日 hh:mm:ss)")
	private String tradeDate;
	
	/**
	 * 交易金额(保留两位有效小数，单位:元)
	 */
	@ApiModelProperty(value="交易金额(保留两位有效小数，单位:元)",example="交易金额(保留两位有效小数，单位:元)")
	private String tradeAmt;
	
	@ApiModelProperty(value="流水ID",example="流水ID，可以根据该ID 查询出详情")
	private String traId;
	
	@ApiModelProperty(value="交易子类型00刷卡01微信02支付宝03聚惠分",example="交易子类型00刷卡01微信02支付宝03聚惠分")
	private String paySubType;
	
	@ApiModelProperty(value="订单号",example="订单号")
	private String orderNo;
	
	/**
	 * paySubType
	 *
	 * @return  the paySubType
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getPaySubType() {
		return paySubType;
	}

	/**
	 * paySubType
	 *
	 * @param   paySubType    the paySubType to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPaySubType(String paySubType) {
		this.paySubType = paySubType;
	}

	/**
	 * orderNo
	 *
	 * @return  the orderNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNo
	 *
	 * @param   orderNo    the orderNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * serialversionuid
	 *
	 * @return  the serialversionuid
	 * @since   CodingExample Ver 1.0
	*/
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * traId
	 *
	 * @return  the traId
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTraId() {
		return traId;
	}

	/**
	 * traId
	 *
	 * @param   traId    the traId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTraId(String traId) {
		this.traId = traId;
	}

	/**
	 * tradeStatus
	 *
	 * @return  the tradeStatus
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTradeStatus() {
		return tradeStatus;
	}

	/**
	 * tradeStatus
	 *
	 * @param   tradeStatus    the tradeStatus to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

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
	 * tradeAmt
	 *
	 * @return  the tradeAmt
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTradeAmt() {
		return tradeAmt;
	}

	/**
	 * tradeAmt
	 *
	 * @param   tradeAmt    the tradeAmt to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	
}
