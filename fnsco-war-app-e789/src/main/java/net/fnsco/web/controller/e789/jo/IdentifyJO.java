package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class IdentifyJO extends JO {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "真是姓名", name = "realName", example = "真是姓名")
	private String realName;
	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}
