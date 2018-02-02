package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;
import net.fnsco.trading.service.third.reCharge.dto.ChargeDTO;

/**
 * 提交流量充值
 * 
 * @author Administrator
 *
 */
public class FlowChargeJO extends JO {

	private static final long serialVersionUID = 1L;
//	@ApiModelProperty(value = "待充值流量的手机号", example = "待充值流量的手机号")
//	private String phone;
//	@ApiModelProperty(value = "流量套餐ID", example = "流量套餐ID")
//	private String pid;
//	@ApiModelProperty(value = "售价金额(取返回的售价金额)", example = "售价金额(取返回的售价金额)")
//	private String inprice;
//	@ApiModelProperty(value = "登录用户id", example = "登录用户id")
//	private Integer userId;
//	@ApiModelProperty(value = "类型0-话费充值;1-流量充值", example = "类型0-话费充值;1-流量充值")
//	private Integer type;
//	@ApiModelProperty(value = "充值名称:取资费查询返回的name字段", example = "充值名称:取资费查询返回的name字段")
//	private String name;
	@ApiModelProperty(value = "支付密码", example = "支付密码")
	private String payPassword;
//	@ApiModelProperty(value = "充值方式:0-余额/1-支付宝/2-微信/3-银行卡支付", example = "充值方式:0-余额/1-支付宝/2-微信/3-银行卡支付")
//	private String payType;

//	@ApiModelProperty(value = "充值DTO", example = "充值DTO")
	private ChargeDTO chargeDTO;
	// 以下主要针对支付宝方式交易参数
//	/**
//	 * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。示例值:Iphone6 16G
//	 */
//	private String body;
//
//	/**
//	 * 商品的标题/交易标题/订单标题/订单关键字等。示例值:大乐透
//	 */
//	private String subject;
//
//	/**
//	 * 商户网站唯一订单号。示例值:70501111111S001111119
//	 */
//	private String outTradeNo;
//
//	/**
//	 * 回调地址
//	 */
//	private String notifyUrl;

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public ChargeDTO getChargeDTO() {
		return chargeDTO;
	}

	public void setChargeDTO(ChargeDTO chargeDTO) {
		this.chargeDTO = chargeDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
