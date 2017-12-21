package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class BankListVO extends VO {
	@ApiModelProperty(value="银行卡id",name="id",example="1")
	private Integer id;
	@ApiModelProperty(value="银行卡号",name="cardNum",example="411****5211")
	private String cardNum;
	@ApiModelProperty(value="银行卡名称",name="bankName",example="招商银行")
	private String bankName;
	@ApiModelProperty(value="卡类型",name="type",example="信用卡/储蓄卡")
	private String type;
	
	
	
	/**
	 * id
	 *
	 * @return  the id
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getId() {
		return id;
	}
	/**
	 * id
	 *
	 * @param   id    the id to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * bankName
	 *
	 * @return  the bankName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBankName() {
		return bankName;
	}
	/**
	 * bankName
	 *
	 * @param   bankName    the bankName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * type
	 *
	 * @return  the type
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getType() {
		return type;
	}
	/**
	 * type
	 *
	 * @param   type    the type to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setType(String type) {
		this.type = type;
	}
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
	


}
