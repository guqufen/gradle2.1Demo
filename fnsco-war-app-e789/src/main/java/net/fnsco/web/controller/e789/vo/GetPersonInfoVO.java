package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class GetPersonInfoVO extends VO {
	@ApiModelProperty(value="昵称",name="userName",example="昵称")
	private String userName; 
	@ApiModelProperty(value="头像地址",name="headImagePath",example="头像地址")
	private String headImagePath;
	@ApiModelProperty(value="身份认证真实姓名",name="name",example="身份认证真实姓名")
	private String name;
	@ApiModelProperty(value="手机号",name="name",example="手机号")
	private String mobile;
	@ApiModelProperty(value="银行卡是否绑定",name="loginRights",example="银行卡是否绑定")
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the bindingBankCard
	 */
	public boolean isBindingBankCard() {
		return bindingBankCard;
	}
	/**
	 * @param bindingBankCard the bindingBankCard to set
	 */
	public void setBindingBankCard(boolean bindingBankCard) {
		this.bindingBankCard = bindingBankCard;
	}

}
