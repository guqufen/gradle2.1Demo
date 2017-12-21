package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @deprecated 
 * @author   binghui.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年12月21日 上午11:55:39
 *
 */
public class ValidateBankJO {
	@ApiModelProperty(value="用户id" ,name="userId")
	private Integer userId;
	@ApiModelProperty(value = "持卡人", name = "name")
	private String name;
	@ApiModelProperty(value = "卡号", name = "cardNum")
	private String cardNum;
	@ApiModelProperty(value = "手机号", name = "mobile")
	private String mobile;
	
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
	 * name
	 *
	 * @return  the name
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getName() {
		return name;
	}
	/**
	 * name
	 *
	 * @param   name    the name to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setName(String name) {
		this.name = name;
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
	 * mobile
	 *
	 * @return  the mobile
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getMobile() {
		return mobile;
	}
	/**
	 * mobile
	 *
	 * @param   mobile    the mobile to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
