package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * app修改密码JO
 * 
 * @author hjt
 *
 */
public class ModifyPasswordJO extends JO {

	private static final long serialVersionUID = 1L;

	/*@ApiModelProperty(value="手机号码")
	private String mobile;// 手机号码
*/	@ApiModelProperty(value="密码")
	private String password;// 密码
	@ApiModelProperty(value="原密码")
	private String oldPassword;// 原密码
	@ApiModelProperty(value="用户id")
	private Integer userId;// 用户id
	/*public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}*/
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
