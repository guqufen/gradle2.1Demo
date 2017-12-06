package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:25:28
 */

public class WithdrawCashJO extends JO{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -158067819660448483L;
	
	/**
	 * APP用户登录ID
	 */
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
	private Integer userId;
	
	/**
	 * 提现金额
	 */
	@ApiModelProperty(value = "提现金额", name = "cashAccount", example = "")
	private String cashAccount;
	
	/**
	 * 提现到银行卡ID
	 */
	@ApiModelProperty(value = "提现到银行卡ID(获取绑定银行卡有返回)", name = "bankCardId", example = "")
	private Integer bankCardId;
	
	@ApiModelProperty(value = "支付密码", name = "payPassword", example = "")
	private String payPassword;
	
	/**
	 * userId
	 *
	 * @return  the userId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getUserId() {
		return userId;
	}

	/**
	 * userId
	 *
	 * @param   userId    the userId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * cashAccount
	 *
	 * @return  the cashAccount
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCashAccount() {
		return cashAccount;
	}

	/**
	 * cashAccount
	 *
	 * @param   cashAccount    the cashAccount to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}

	/**
	 * bankCardId
	 *
	 * @return  the bankCardId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getBankCardId() {
		return bankCardId;
	}

	/**
	 * bankCardId
	 *
	 * @param   bankCardId    the bankCardId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBankCardId(Integer bankCardId) {
		this.bankCardId = bankCardId;
	}
	
}
