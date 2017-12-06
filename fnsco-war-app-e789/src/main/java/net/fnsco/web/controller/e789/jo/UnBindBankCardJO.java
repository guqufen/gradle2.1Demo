package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class UnBindBankCardJO extends JO {
    @ApiModelProperty(value = "银行卡id", name = "bankID", example = "2")
    private String bankID;
    @ApiModelProperty(value = "用户id", name = "userId", example = "3")
    private String userId;
	/**
	 * @return the bankID
	 */
	public String getBankID() {
		return bankID;
	}
	/**
	 * @param bankID the bankID to set
	 */
	public void setBankID(String bankID) {
		this.bankID = bankID;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


    
}
