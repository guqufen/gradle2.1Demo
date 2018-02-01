package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2018年2月1日 下午3:20:06
 */

public class AlipayAppPayParamsVO extends VO {
	
	 @ApiModelProperty(value = "调起APP支付参数", name = "params", example = "支付参数(无需处理，直接使用)")
	 private String params;

	/**
	 * params
	 *
	 * @return  the params
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getParams() {
		return params;
	}

	/**
	 * params
	 *
	 * @param   params    the params to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setParams(String params) {
		this.params = params;
	}
	 
	 
}
