package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class IdAuthVO extends VO {
	@ApiModelProperty(value = "用户id", name = "appUserId", example = "用户id")
	private Integer appUserId;/*用户id*/
	@ApiModelProperty(value = "姓名", name = "realName", example = "姓名")
	private String realName;/*姓名*/
	@ApiModelProperty(value = "身份证号", name = "idCard", example = "身份证号")
	private String idCard;/*身份证号*/
	@ApiModelProperty(value = "front:正面识别;back:反面识别", name = "side", example = "front:正面识别;back:反面识别")
	private String side;//front:正面识别;back:反面识别;
	@ApiModelProperty(value = "失效日期", name = "endTime", example = "失效日期")
	private String endTime;/*失效日期*/
	@ApiModelProperty(value = "正面照地址", name = "fileURL", example = "正面照地址")
	private String fileURL;/*正面照地址*/
	/**
	 * @return the appUserId
	 */
	public Integer getAppUserId() {
		return appUserId;
	}
	/**
	 * @param appUserId the appUserId to set
	 */
	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}
	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * @return the side
	 */
	public String getSide() {
		return side;
	}
	/**
	 * @param side the side to set
	 */
	public void setSide(String side) {
		this.side = side;
	}
	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the fileURL
	 */
	public String getFileURL() {
		return fileURL;
	}
	/**
	 * @param fileURL the fileURL to set
	 */
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
}
