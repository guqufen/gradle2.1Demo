package net.fnsco.core.utils.dto;

/**
 * 身份证实名认证DTO
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月13日 上午10:44:31
 */
public class IdCardFaceDTO {
	 private int type;
	 private String address;/*住址*/
	 private String birthday;/*生日，格式为YYYY-MM-DD*/
	 private String gender;/*性别（男/女）*/
	 private String id_card_number;/*身份证号*/
	 private String name;/*姓名*/
	 private String race;/*民族（汉字）*/
	 private String side;/*表示身份证的国徽面或人像面。返回值为：front: 人像面back: 国徽面*/
	 private String issuedBy;/*签发机关*/
	 private String validDate;/*有效日期，返回值有两种格式：一个16位长度的字符串：YYYY.MM.DD-YYYY.MM.DD或是：YYYY.MM.DD-长期*/
	 private String errorMessage;/*当请求失败时才会返回此字符串，具体返回内容见后续错误信息章节。否则此字段不存在。*/
	 private int timeUsed;/*整个请求所花费的时间，单位为毫秒。*/
	 /**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the id_card_number
	 */
	public String getId_card_number() {
		return id_card_number;
	}
	/**
	 * @param id_card_number the id_card_number to set
	 */
	public void setId_card_number(String id_card_number) {
		this.id_card_number = id_card_number;
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
	 * @return the race
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
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
	 * @return the issuedBy
	 */
	public String getIssuedBy() {
		return issuedBy;
	}
	/**
	 * @param issuedBy the issuedBy to set
	 */
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	/**
	 * @return the validDate
	 */
	public String getValidDate() {
		return validDate;
	}
	/**
	 * @param validDate the validDate to set
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the timeUsed
	 */
	public int getTimeUsed() {
		return timeUsed;
	}
	/**
	 * @param timeUsed the timeUsed to set
	 */
	public void setTimeUsed(int timeUsed) {
		this.timeUsed = timeUsed;
	}
}