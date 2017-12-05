package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:30:39
 */

public class CommonVO extends VO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 1517872943490586284L;
	
	@ApiModelProperty(value="返回码",name="resultCode",example="0000")
    private String resultCode;
    @ApiModelProperty(value="返回信息",name="resultMessage",example="绑定成功")
    private String resultMessage;
	/**
	 * resultCode
	 *
	 * @return  the resultCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * resultCode
	 *
	 * @param   resultCode    the resultCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * resultMessage
	 *
	 * @return  the resultMessage
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getResultMessage() {
		return resultMessage;
	}
	/**
	 * resultMessage
	 *
	 * @param   resultMessage    the resultMessage to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
    
}
