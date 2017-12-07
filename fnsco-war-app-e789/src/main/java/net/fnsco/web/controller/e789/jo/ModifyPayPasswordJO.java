package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * app修改密码JO
 * 
 * @author hjt
 *
 */
public class ModifyPayPasswordJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="密码")
	private String payPassword;// 密码
	@ApiModelProperty(value="原密码")
	private String oldPayPassword;// 原密码
	@ApiModelProperty(value="用户id")
	private Integer userId;// 用户id
	/**
	 * @return the payPassword
	 */
	public String getPayPassword() {
		return payPassword;
	}
	/**
	 * @param payPassword the payPassword to set
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	/**
	 * @return the oldPayPassword
	 */
	public String getOldPayPassword() {
		return oldPayPassword;
	}
	/**
	 * @param oldPayPassword the oldPayPassword to set
	 */
	public void setOldPayPassword(String oldPayPassword) {
		this.oldPayPassword = oldPayPassword;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
