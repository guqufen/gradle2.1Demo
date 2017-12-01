package net.fnsco.web.controller.e789.pay.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class BindBankCardJO extends JO {
    @ApiModelProperty(value = "内部商户号", name = "innerCode", example = "092916342476171")
    private String innerCode;
    @ApiModelProperty(value = "持卡人", name = "cardholder", example = "")
    private String cardholder; 
    @ApiModelProperty(value = "卡号", name = "cardNum", example = "")
    private String cardNum;
    @ApiModelProperty(value = "手机号", name = "mobile", example = "")
    private String mobile;

    /**
	 * @return the cardholder
	 */
	public String getCardholder() {
		return cardholder;
	}

	/**
	 * @param cardholder the cardholder to set
	 */
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
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

	/**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */

    public String getInnerCode() {
        return innerCode;
    }

    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BindBankCardJO [innerCode=" + innerCode + ", cardholder=" + cardholder + ", cardNum=" + cardNum
				+ ", mobile=" + mobile + "]";
	}

    
}
