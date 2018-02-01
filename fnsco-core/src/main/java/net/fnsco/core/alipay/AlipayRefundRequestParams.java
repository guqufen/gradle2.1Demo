package net.fnsco.core.alipay;

import java.io.Serializable;

/**
 * @desc 支付宝退款接口请求参数实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年2月1日 下午4:23:32
 */
public class AlipayRefundRequestParams implements Serializable{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -9069347385819409212L;
	
	/**
	 * 退款的原因说明。正常退款
	 */
	private String refundReason;
	/**
	 * 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数。示例值：200.12
	 */
	private String refundAmount;
	/**
	 * 商户网站唯一订单号。示例值:70501111111S001111119
	 */
	private String outTradeNo;
	/**
	 * 回调地址
	 */
	private String notifyUrl;
	/**
	 * refundReason
	 *
	 * @return  the refundReason
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRefundReason() {
		return refundReason;
	}
	/**
	 * refundReason
	 *
	 * @param   refundReason    the refundReason to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	/**
	 * refundAmount
	 *
	 * @return  the refundAmount
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getRefundAmount() {
		return refundAmount;
	}
	/**
	 * refundAmount
	 *
	 * @param   refundAmount    the refundAmount to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	/**
	 * outTradeNo
	 *
	 * @return  the outTradeNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	/**
	 * outTradeNo
	 *
	 * @param   outTradeNo    the outTradeNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * notifyUrl
	 *
	 * @return  the notifyUrl
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * notifyUrl
	 *
	 * @param   notifyUrl    the notifyUrl to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
}
