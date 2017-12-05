package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月4日 下午6:01:12
 */

public class BillJO extends JO {
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = -3866083215614970606L;
	/**
	 * APP登录用户ID
	 */
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
	private Integer userId;
	
	/**
	 * 当前页码数
	 */
	@ApiModelProperty(value = "当前页码数", name = "pageNum", example = "")
	private Integer pageNum;
	
	/**
	 * 页码大小
	 */
	@ApiModelProperty(value = "页码大小", name = "pageSize", example = "")
	private Integer pageSize;

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

	/**
	 * pageNum
	 *
	 * @return  the pageNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getPageNum() {
		return pageNum;
	}

	/**
	 * pageNum
	 *
	 * @param   pageNum    the pageNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * pageSize
	 *
	 * @return  the pageSize
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * pageSize
	 *
	 * @param   pageSize    the pageSize to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
