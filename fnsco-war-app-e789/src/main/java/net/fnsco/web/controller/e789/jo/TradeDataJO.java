package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月6日 上午10:17:56
 */

public class TradeDataJO extends JO {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 7554645807738373989L;
	
	@ApiModelProperty(value = "APP登录用户ID", name = "userId", example = "")
	private Integer userId;
	@ApiModelProperty(value = "当前页码")
	private Integer pageNum;
	@ApiModelProperty(value = "页码大小")
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
