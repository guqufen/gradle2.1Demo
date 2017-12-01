package net.fnsco.web.controller.e789.pay.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class BindBankCardVO extends VO {
    @ApiModelProperty(value="返回码",name="resultCode",example="0000")
    private String resultCode;
    @ApiModelProperty(value="返回信息",name="resultMessage",example="绑定成功")
    private String resultMessage;
	
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the resultMessage
	 */
	public String getResultMessage() {
		return resultMessage;
	}
	/**
	 * @param resultMessage the resultMessage to set
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BindBankCardVO [resultCode=" + resultCode + ", resultMessage=" + resultMessage + "]";
	}

}
