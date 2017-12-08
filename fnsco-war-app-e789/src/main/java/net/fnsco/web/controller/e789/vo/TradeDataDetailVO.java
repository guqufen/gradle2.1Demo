package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class TradeDataDetailVO extends VO {
	
	
	@ApiModelProperty(value="流水ID",example="流水ID，可以根据该ID 查询出详情")
	private String traId;
	
	/**
	 * 订单金额
	 */
	@ApiModelProperty(value="订单金额",example="订单金额(格式'0.00'，单位:元)")
	private String orderAmt;
	
	@ApiModelProperty(value="交易子类型00刷卡01微信02支付宝03聚惠分",example="交易子类型00刷卡01微信02支付宝03聚惠分")
	private String paySubType;
	
	@ApiModelProperty(value="交易状态:1000处理中1001成功1002失败1003已退货",example="交易状态:1000处理中1001成功1002失败1003已退货")
	private String tradeStatus;
	
	@ApiModelProperty(value = "交易状态名称", example = "交易状态名称")
	private String tradeStatusName;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间", example = "创建时间(格式yyyy年MM月dd日 hh:mm:ss)")
	private String createDate;
	
	@ApiModelProperty(value = "付款时间", example = "付款时间(格式yyyy年MM月dd日 hh:mm:ss)")
	private String payDate;
	
	@ApiModelProperty(value = "订单号", example = "订单号")
	private String orderNo;
	
	
	@ApiModelProperty(value = "交易信息", example = "交易信息")
	private String respMsg;   // 交易信息
	
	
	/**
	 * respMsg
	 *
	 * @return  the respMsg
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRespMsg() {
		return respMsg;
	}

	/**
	 * respMsg
	 *
	 * @param   respMsg    the respMsg to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	/**
	 * tradeStatusName
	 *
	 * @return  the tradeStatusName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTradeStatusName() {
		return tradeStatusName;
	}

	/**
	 * tradeStatusName
	 *
	 * @param   tradeStatusName    the tradeStatusName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTradeStatusName(String tradeStatusName) {
		this.tradeStatusName = tradeStatusName;
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
	 * orderAmt
	 *
	 * @return  the orderAmt
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOrderAmt() {
		return orderAmt;
	}

	/**
	 * orderAmt
	 *
	 * @param   orderAmt    the orderAmt to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

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
	 * createDate
	 *
	 * @return  the createDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * createDate
	 *
	 * @param   createDate    the createDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * payDate
	 *
	 * @return  the payDate
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getPayDate() {
		return payDate;
	}

	/**
	 * payDate
	 *
	 * @param   payDate    the payDate to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPayDate(String payDate) {
		this.payDate = payDate;
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
	
}
