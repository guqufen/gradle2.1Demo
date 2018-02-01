package net.fnsco.core.alipay;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
/**
 * @desc 支付宝请求参数实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年1月31日 下午4:31:09
 */
public class AlipayRequestParams implements Serializable{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1251681892235008039L;
	
	/**
	 * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。示例值:Iphone6 16G
	 */
	private String body;
	
	/**
	 * 商品的标题/交易标题/订单标题/订单关键字等。示例值:大乐透
	 */
	private String subject;
	
	/**
	 * 商户网站唯一订单号。示例值:70501111111S001111119
	 */
	private String outTradeNo;
	
	/**
	 * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。示例值:9.00
	 */
	private String totalAmount;
	
	/**
	 * 回调地址
	 */
	private String notifyUrl;
	
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

	/**
	 * body
	 *
	 * @return  the body
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBody() {
		return body;
	}

	/**
	 * body
	 *
	 * @param   body    the body to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * subject
	 *
	 * @return  the subject
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getSubject() {
		return subject;
	}

	/**
	 * subject
	 *
	 * @param   subject    the subject to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setSubject(String subject) {
		this.subject = subject;
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
	 * totalAmount
	 *
	 * @return  the totalAmount
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * totalAmount
	 *
	 * @param   totalAmount    the totalAmount to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		
		// TODO Auto-generated method stub
		return JSON.toJSONString(this);
		
	}
}
