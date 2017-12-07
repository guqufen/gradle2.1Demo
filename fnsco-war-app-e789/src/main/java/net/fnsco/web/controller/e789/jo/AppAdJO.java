package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午5:05:46
 */

public class AppAdJO extends JO{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -5484727521062637573L;
	
	/**
	 * APP登录用户ID
	 */
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "66")
	private Integer userId;

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
	 
}
