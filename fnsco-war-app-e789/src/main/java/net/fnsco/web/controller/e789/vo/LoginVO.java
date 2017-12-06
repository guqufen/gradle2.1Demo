package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class LoginVO extends VO {
	@ApiModelProperty(value="用户名",name="userName",example="用户名")
	private String userName; 
	@ApiModelProperty(value="头像地址",name="headImagePath",example="头像地址")
	private String headImagePath;
	@ApiModelProperty(value="用户权限",name="loginRights",example="用户权限")
	private String loginRights;
	@ApiModelProperty(value="是否有支付密码",name="loginRights",example="是否有支付密码")
	private boolean beingPayPassword;
	@ApiModelProperty(value="未读取消息列表",name="unReadMsgIds",example="未读取消息列表")
	private List<Integer> unReadMsgIds;
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
