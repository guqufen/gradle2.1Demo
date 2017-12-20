package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;
/**
 * 
 * @desc 身份证实名验证入参
 * @author hjt
 * @version 
 * @Date 2017年12月20日 下午2:02:47
 */
public class IdentifyJO extends JO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "身份证图片保存信息id正面", name = "fileId", example = "身份证图片保存信息id正面")
	private Integer frontFileId;/*身份证图片保存信息id正面*/
	@ApiModelProperty(value = "身份证图片保存信息id反面", name = "fileId", example = "身份证图片保存信息id反面")
	private Integer backFileId;/*身份证图片保存信息id反面*/
	/**
	 * @return the frontFileId
	 */
	public Integer getFrontFileId() {
		return frontFileId;
	}
	/**
	 * @param frontFileId the frontFileId to set
	 */
	public void setFrontFileId(Integer frontFileId) {
		this.frontFileId = frontFileId;
	}
	/**
	 * @return the backFileId
	 */
	public Integer getBackFileId() {
		return backFileId;
	}
	/**
	 * @param backFileId the backFileId to set
	 */
	public void setBackFileId(Integer backFileId) {
		this.backFileId = backFileId;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
