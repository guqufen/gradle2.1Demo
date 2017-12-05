package net.fnsco.web.controller.e789.vo;

import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月5日 上午10:13:52
 */

public class MerchantInfoVO extends VO {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -4558187864205219793L;
	
	private String merName;
	
	private String businessLicenseNum;
	
	private String legalPerson;
	
	private String legalPersonMobile;
	
	private String cardNum;
	
	private String openBank;
	
	private String accountNo;

	/**
	 * merName
	 *
	 * @return  the merName
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMerName() {
		return merName;
	}

	/**
	 * merName
	 *
	 * @param   merName    the merName to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMerName(String merName) {
		this.merName = merName;
	}

	/**
	 * businessLicenseNum
	 *
	 * @return  the businessLicenseNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}

	/**
	 * businessLicenseNum
	 *
	 * @param   businessLicenseNum    the businessLicenseNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}

	/**
	 * legalPerson
	 *
	 * @return  the legalPerson
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getLegalPerson() {
		return legalPerson;
	}

	/**
	 * legalPerson
	 *
	 * @param   legalPerson    the legalPerson to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	/**
	 * legalPersonMobile
	 *
	 * @return  the legalPersonMobile
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getLegalPersonMobile() {
		return legalPersonMobile;
	}

	/**
	 * legalPersonMobile
	 *
	 * @param   legalPersonMobile    the legalPersonMobile to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setLegalPersonMobile(String legalPersonMobile) {
		this.legalPersonMobile = legalPersonMobile;
	}

	/**
	 * cardNum
	 *
	 * @return  the cardNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCardNum() {
		return cardNum;
	}

	/**
	 * cardNum
	 *
	 * @param   cardNum    the cardNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * openBank
	 *
	 * @return  the openBank
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getOpenBank() {
		return openBank;
	}

	/**
	 * openBank
	 *
	 * @param   openBank    the openBank to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	/**
	 * accountNo
	 *
	 * @return  the accountNo
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * accountNo
	 *
	 * @param   accountNo    the accountNo to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
