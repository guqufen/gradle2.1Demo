package net.fnsco.web.controller.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 理财申请信息保存接口
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 下午2:05:25
 */
public class SaveFinanceJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="投资人姓名",name="name",example="投资人姓名")
	private String name;// 投资人姓名
	@ApiModelProperty(value="所在城市id",name="cityId",example="所在城市id")
	private Integer cityId;// 所在城市id
	/*@ApiModelProperty(value="理财产品id",name="buyType",example="理财产品id")
	private String buyType;// 理财产品id
*/	@ApiModelProperty(value="手机号码",name="mobile",example="手机号码")
	private String mobile;// 手机号码
	@ApiModelProperty(value="验证码",name="code",example="验证码")
	private String code;//验证码
	@ApiModelProperty(value="推荐码",name="suggestCode",example="推荐码")
	private Integer suggestCode;// 推荐码
	@ApiModelProperty(value = "type", name = "type", example = "申请类型")
	private String type;
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
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the buyType
	 *//*
	public String getBuyType() {
		return buyType;
	}
	*//**
	 * @param buyType the buyType to set
	 *//*
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}*/
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the suggestCode
	 */
	public Integer getSuggestCode() {
		return suggestCode;
	}
	/**
	 * @param suggestCode the suggestCode to set
	 */
	public void setSuggestCode(Integer suggestCode) {
		this.suggestCode = suggestCode;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
