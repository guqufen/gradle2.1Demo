package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class BindBankCardVO extends VO {
	
//	@ApiModelProperty(value="内部商户号",name="innerCode",example="0000")
//    private String innerCode;
	@ApiModelProperty(value="开户账号",name="accountNo",example="412*****1212")
    private String accountNo;
	@ApiModelProperty(value="账号开户名",name="accountName",example="张三")
    private String accountName;
	@ApiModelProperty(value="开户手机号",name="accountPhone",example="13200001111")
    private String accountPhone;
	
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the accountPhone
	 */
	public String getAccountPhone() {
		return accountPhone;
	}
	/**
	 * @param accountPhone the accountPhone to set
	 */
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	
	
	

}
