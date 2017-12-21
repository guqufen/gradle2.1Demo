package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class BindBankCardJO extends JO {
    @ApiModelProperty(value = "持卡人姓名", name = "cardholder", example = "张三")
    private String bankCardholder; 
    @ApiModelProperty(value = "卡号", name = "cardNum", example = "623******2825")
    private String bankCardNum;
    @ApiModelProperty(value = "手机号", name = "mobile", example = "13233332222")
    private String mobile;
    @ApiModelProperty(value = "app用户id", name = "userId", example = "22")
    private String userId;
    @ApiModelProperty(value = "设备id", name = "deviceId")
    private String deviceId;
    @ApiModelProperty(value = "验证码", name = "code")
    private String code;

    
	/**
	 * code
	 *
	 * @return  the code
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getCode() {
		return code;
	}

	/**
	 * code
	 *
	 * @param   code    the code to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * deviceId
	 *
	 * @return  the deviceId
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * deviceId
	 *
	 * @param   deviceId    the deviceId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	/**
	 * @return the bankCardholder
	 */
	public String getBankCardholder() {
		return bankCardholder;
	}

	/**
	 * @param bankCardholder the bankCardholder to set
	 */
	public void setBankCardholder(String bankCardholder) {
		this.bankCardholder = bankCardholder;
	}

	/**
	 * @return the bankCardNum
	 */
	public String getBankCardNum() {
		return bankCardNum;
	}

	/**
	 * @param bankCardNum the bankCardNum to set
	 */
	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

  
}
