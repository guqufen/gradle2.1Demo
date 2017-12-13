package net.fnsco.core.utils.dto;

/**
 * 身份证实名认证DTO
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月13日 上午10:44:31
 */
public class IdCardDTO {
    private String reason;/*是否成功*/
    private String realname;/*姓名*/
    private String sex;/*性别*/
    private String nation;/*民族*/
    private String born;/*出生日期*/
    private String address;/*地址*/
    private String idcard;/*身份证号*/
    private String side;//front:正面识别;back:反面识别;
    private Integer orderid;/*本次查询流水号*/
    private String begin;/*签发日期*/
    private String department;/*签发机关*/
    private String end;/*失效日期*/ 
    private Integer res;/*1：匹配 2：不匹配*/
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * @param realname the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}
	/**
	 * @param nation the nation to set
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * @return the born
	 */
	public String getBorn() {
		return born;
	}
	/**
	 * @param born the born to set
	 */
	public void setBorn(String born) {
		this.born = born;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the idcard
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * @param idcard the idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
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
	 * @return the orderid
	 */
	public Integer getOrderid() {
		return orderid;
	}
	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * @return the begin
	 */
	public String getBegin() {
		return begin;
	}
	/**
	 * @param begin the begin to set
	 */
	public void setBegin(String begin) {
		this.begin = begin;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	/**
	 * @return the res
	 */
	public Integer getRes() {
		return res;
	}
	/**
	 * @param res the res to set
	 */
	public void setRes(Integer res) {
		this.res = res;
	}
    
}
