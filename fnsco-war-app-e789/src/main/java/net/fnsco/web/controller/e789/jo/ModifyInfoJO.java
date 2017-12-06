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
	@ApiModelProperty(value="修改类型（1修改昵称，2修改图片）")
	private Integer modifyType;// 修改类型（1修改昵称，2修改图片）
	@ApiModelProperty(value="修改内容")
	private String modifyContent;// 修改内容
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
	 * @return the modifyType
	 */
	public Integer getModifyType() {
		return modifyType;
	}


	/**
	 * @param modifyType the modifyType to set
	 */
	public void setModifyType(Integer modifyType) {
		this.modifyType = modifyType;
	}


	/**
	 * @return the modifyContent
	 */
	public String getModifyContent() {
		return modifyContent;
	}


	/**
	 * @param modifyContent the modifyContent to set
	 */
	public void setModifyContent(String modifyContent) {
		this.modifyContent = modifyContent;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
