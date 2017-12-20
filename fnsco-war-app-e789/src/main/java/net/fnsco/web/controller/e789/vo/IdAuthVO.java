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
	@ApiModelProperty(value="图像地址",name="headImagePath",example="图像地址")
	private String ImagePath;
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
