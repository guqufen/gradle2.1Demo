package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * app修改昵称JO
 * 
 * @author hjt
 *
 */
public class ModifyInfoJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="APP用户ID")
	private Integer userId;// APP用户ID
	@ApiModelProperty(value="用户昵称")
	private String userName;// 用户昵称
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
