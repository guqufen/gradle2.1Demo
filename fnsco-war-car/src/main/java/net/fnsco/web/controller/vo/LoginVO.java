package net.fnsco.web.controller.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class LoginVO extends VO {
	@ApiModelProperty(value="app用户id",name="userId",example="app用户id")
	private Integer userId;
	@ApiModelProperty(value="手机号",name="mobile",example="手机号")
	private String mobile; 
	@ApiModelProperty(value="用户名",name="userName",example="用户名")
	private String userName; 
	@ApiModelProperty(value="头像地址",name="headImagePath",example="头像地址")
	private String headImagePath;
	@ApiModelProperty(value="用户权限 no:没有商户 yes：有商户",name="loginRights",example="用户权限no:没有商户 yes：有商户")
	private String loginRights;
	@ApiModelProperty(value="是否有支付密码",name="loginRights",example="是否有支付密码")
	private boolean beingPayPassword;
	@ApiModelProperty(value="未读取消息列表",name="unReadMsgIds",example="未读取消息列表")
	private List<Integer> unReadMsgIds;
	
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
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the headImagePath
	 */
	public String getHeadImagePath() {
		return headImagePath;
	}
	/**
	 * @param headImagePath the headImagePath to set
	 */
	public void setHeadImagePath(String headImagePath) {
		this.headImagePath = headImagePath;
	}
	/**
	 * @return the loginRights
	 */
	public String getLoginRights() {
		return loginRights;
	}
	/**
	 * @param loginRights the loginRights to set
	 */
	public void setLoginRights(String loginRights) {
		this.loginRights = loginRights;
	}
	/**
	 * @return the beingPayPassword
	 */
	public boolean isBeingPayPassword() {
		return beingPayPassword;
	}
	/**
	 * @param beingPayPassword the beingPayPassword to set
	 */
	public void setBeingPayPassword(boolean beingPayPassword) {
		this.beingPayPassword = beingPayPassword;
	}
	/**
	 * @return the unReadMsgIds
	 */
	public List<Integer> getUnReadMsgIds() {
		return unReadMsgIds;
	}
	/**
	 * @param unReadMsgIds the unReadMsgIds to set
	 */
	public void setUnReadMsgIds(List<Integer> unReadMsgIds) {
		this.unReadMsgIds = unReadMsgIds;
	}

}
