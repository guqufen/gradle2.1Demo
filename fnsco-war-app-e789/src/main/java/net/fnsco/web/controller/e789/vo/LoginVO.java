package net.fnsco.web.controller.e789.vo;

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
	@ApiModelProperty(value="真实姓名",name="realName",example="真实姓名")
	private String realName; 
	@ApiModelProperty(value="是否绑定身份证 true/false",name="isBindingIdCard",example="是否绑定身份证 true/false")
	private boolean isBindingIdCard;
	@ApiModelProperty(value="银行卡是否绑定 true/false",name="isbindingBankCard",example="银行卡是否绑定 true/false")
	private boolean isBindingBankCard;
	@ApiModelProperty(value="头像地址",name="headImagePath",example="头像地址")
	private String headImagePath;
	@ApiModelProperty(value="用户权限 true/false",name="loginRights",example="用户权限 true/false")
	private boolean isMerchant;
	@ApiModelProperty(value="是否有支付密码 true/false",name="loginRights",example="是否有支付密码 true/false")
	private boolean hasPayPassword;
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
	 * @return the isBindingIdCard
	 */
	public boolean getIsBindingIdCard() {
		return isBindingIdCard;
	}
	/**
	 * @param isBindingIdCard the isBindingIdCard to set
	 */
	public void setIsBindingIdCard(boolean isBindingIdCard) {
		this.isBindingIdCard = isBindingIdCard;
	}
	/**
	 * @return the isbindingBankCard
	 */
	public boolean getIsBindingBankCard() {
		return isBindingBankCard;
	}
	/**
	 * @param isbindingBankCard the isbindingBankCard to set
	 */
	public void setIsBindingBankCard(boolean isBindingBankCard) {
		this.isBindingBankCard = isBindingBankCard;
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
	 * @return the isMerchant
	 */
	public boolean getIsMerchant() {
		return isMerchant;
	}
	/**
	 * @param isMerchant the isMerchant to set
	 */
	public void setIsMerchant(boolean isMerchant) {
		this.isMerchant = isMerchant;
	}
	/**
	 * @return the hasPayPassword
	 */
	public boolean getHasPayPassword() {
		return hasPayPassword;
	}
	/**
	 * @param hasPayPassword the hasPayPassword to set
	 */
	public void setHasPayPassword(boolean hasPayPassword) {
		this.hasPayPassword = hasPayPassword;
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
