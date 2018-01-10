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
	@ApiModelProperty(value = "APP登录用户ID", name = "userId")
	private Integer userId;

	/**
	 * 1、账户页；2、火车票页
	 */
	@ApiModelProperty(value = "1、账户页；2、火车票页", name = "type")
	private Integer type;
	
	@ApiModelProperty(value = "1、IOS；2、安卓；3、公用", name = "deviceType")
	private Integer deviceType;
	/**
	 * deviceType
	 *
	 * @return  the deviceType
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getDeviceType() {
		return deviceType;
	}

	/**
	 * deviceType
	 *
	 * @param   deviceType    the deviceType to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * type
	 *
	 * @return  the type
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getType() {
		return type;
	}

	/**
	 * type
	 *
	 * @param   type    the type to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setType(Integer type) {
		this.type = type;
	}

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
