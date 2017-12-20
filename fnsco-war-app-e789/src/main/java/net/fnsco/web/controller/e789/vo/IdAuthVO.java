package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;
/**
 * 
 * @desc 身份证识别出参
 * @author hjt
 * @version 
 * @Date 2017年12月20日 下午2:03:12
 */
public class IdAuthVO extends VO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "身份证图片保存信息id", name = "fileId", example = "身份证图片保存信息id")
	private Integer fileId;/*身份证图片保存信息id*/
	@ApiModelProperty(value="图像地址",name="headImagePath",example="图像地址")
	private String ImagePath;
	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return ImagePath;
	}
	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	
}
