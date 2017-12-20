package net.fnsco.web.controller.e789.vo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class GetPersonInfoVO extends VO {
	@ApiModelProperty(value="昵称",name="userName",example="昵称")
	private String userName; 
	@ApiModelProperty(value="头像地址",name="headImagePath",example="头像地址")
	private String headImagePath;
	@ApiModelProperty(value="真实姓名",name="realName",example="真实姓名")
	private String realName; 
	@ApiModelProperty(value="是否绑定身份证 true/false",name="isBindingIdCard",example="是否绑定身份证 true/false")
	private boolean isBindingIdCard;
	@ApiModelProperty(value="手机号",name="name",example="手机号")
	private String mobile;
	@ApiModelProperty(value="银行卡是否绑定显示",name="loginRights",example="银行卡是否绑定显示")
	private String isBindingStr;
	@ApiModelProperty(value="银行卡是否绑定 true/false",name="loginRights",example="银行卡是否绑定 true/false")
	private boolean bindingBankCard;
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
	 * @return the isBindingStr
	 */
	public String getIsBindingStr() {
		return isBindingStr;
	}
	/**
	 * @param isBindingStr the isBindingStr to set
	 */
	public void setIsBindingStr(String isBindingStr) {
		this.isBindingStr = isBindingStr;
	}
	/**
	 * @return the bindingBankCard
	 */
	public boolean getIsBindingBankCard() {
		return bindingBankCard;
	}
	/**
	 * @param bindingBankCard the bindingBankCard to set
	 */
	public void setIsBindingBankCard(boolean bindingBankCard) {
		this.bindingBankCard = bindingBankCard;
	}

}
