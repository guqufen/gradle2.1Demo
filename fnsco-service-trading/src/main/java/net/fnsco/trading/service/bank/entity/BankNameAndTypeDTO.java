package net.fnsco.trading.service.bank.entity;

import java.util.Date;

public class BankNameAndTypeDTO {
	private String type;
	private String bank_name;
	private String paCode;
	/**
	 * paCode
	 *
	 * @return  the paCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getPaCode() {
		return paCode;
	}
	/**
	 * paCode
	 *
	 * @param   paCode    the paCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPaCode(String paCode) {
		this.paCode = paCode;
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