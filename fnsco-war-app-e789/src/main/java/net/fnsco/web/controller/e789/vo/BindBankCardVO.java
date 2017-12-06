package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class BindBankCardVO extends VO {
	
	@ApiModelProperty(value="内部商户号",name="innerCode",example="0000")
    private String innerCode;
	@ApiModelProperty(value="开户账号",name="accountNo",example="0000")
    private String accountNo;
	@ApiModelProperty(value="账号开户名",name="accountName",example="0000")
    private String accountName;
	@ApiModelProperty(value="开户手机号",name="accountPhone",example="0000")
    private String accountPhone;
	@ApiModelProperty(value="开户支行名称",name="subBankName",example="0000")
    private String subBankName;
	
	
    /**
	 * @return the innerCode
	 */
	public String getInnerCode() {
		return innerCode;
	}
	/**
	 * @param innerCode the innerCode to set
	 */
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
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
	/**
	 * @return the subBankName
	 */
	public String getSubBankName() {
		return subBankName;
	}
	/**
	 * @param subBankName the subBankName to set
	 */
	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}
	
	
	

}
