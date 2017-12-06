package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class BankListVO extends VO {
	@ApiModelProperty(value="银行卡号",name="cardNum",example="0000")
	private String cardNum;
	@ApiModelProperty(value="开户行银行名称",name="bankCodeName",example="0000")
	private String bankCodeName;
	
	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		return cardNum;
	}
	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	/**
	 * @return the bankCodeName
	 */
	public String getBankCodeName() {
		return bankCodeName;
	}
	/**
	 * @param bankCodeName the bankCodeName to set
	 */
	public void setBankCodeName(String bankCodeName) {
		this.bankCodeName = bankCodeName;
	}
	


}
