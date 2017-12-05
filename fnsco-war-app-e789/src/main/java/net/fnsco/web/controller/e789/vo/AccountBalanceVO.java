package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:09:38
 */

public class AccountBalanceVO extends VO {
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 6314011364816553608L;
	
	/**
	 * 帐号余额
	 */
	@ApiModelProperty(value="帐号可提现余额",name="accountBalance",example="0.00")
	private String accountBalance;
	
	/**
	 * APP登录用户ID
	 */
	@ApiModelProperty(value="APP登录用户ID",name="userId",example="")
	private Integer userId;

	/**
	 * accountBalance
	 *
	 * @return  the accountBalance
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getAccountBalance() {
		return accountBalance;
	}

	/**
	 * accountBalance
	 *
	 * @param   accountBalance    the accountBalance to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

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
	

}
