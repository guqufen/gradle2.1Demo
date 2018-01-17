package net.fnsco.bigdata.api.dto;

public class BankCardTypeDTO {
	private String type;
	private String bank_name;
	private String bankCardNum;
	private String cardTotalLength;
	
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
	 * bankCardNum
	 *
	 * @return  the bankCardNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBankCardNum() {
		return bankCardNum;
	}
	/**
	 * bankCardNum
	 *
	 * @param   bankCardNum    the bankCardNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}
	/**
	 * cardTotalLength
	 *
	 * @return  the cardTotalLength
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCardTotalLength() {
		return cardTotalLength;
	}
	/**
	 * cardTotalLength
	 *
	 * @param   cardTotalLength    the cardTotalLength to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCardTotalLength(String cardTotalLength) {
		this.cardTotalLength = cardTotalLength;
	}
	/**
	 * bank_name
	 *
	 * @return  the bank_name
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBank_name() {
		return bank_name;
	}
	/**
	 * bank_name
	 *
	 * @param   bank_name    the bank_name to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	

}
