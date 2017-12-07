package net.fnsco.order.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class AppUserLoginInfoDTO extends DTO{
	private Integer userId;
    private String  userName;
    private String moblie;
    private List<Integer> unReadMsgIds;
    private String headImagePath;
    private String payPassword;
    private Integer merchantNums;
    
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
	 * @return the moblie
	 */
	public String getMoblie() {
		return moblie;
	}
	/**
	 * @param moblie the moblie to set
	 */
	public void setMoblie(String moblie) {
		this.moblie = moblie;
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
	 * @return the payPassword
	 */
	public String getPayPassword() {
		return payPassword;
	}
	/**
	 * @param payPassword the payPassword to set
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	/**
	 * @return the merchantNums
	 */
	public Integer getMerchantNums() {
		return merchantNums;
	}
	/**
	 * @param merchantNums the merchantNums to set
	 */
	public void setMerchantNums(Integer merchantNums) {
		this.merchantNums = merchantNums;
	}

    
}
