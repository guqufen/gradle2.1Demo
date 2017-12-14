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
	@ApiModelProperty(value="理财产品id",name="financeType",example="理财产品id")
	private Integer financeType;// 理财产品id
	@ApiModelProperty(value="预计收益",name="earnings",example="预计收益")
	private Integer earnings;// 预计收益
	@ApiModelProperty(value="手机号码",name="mobile",example="手机号码")
	private String mobile;// 手机号码
	@ApiModelProperty(value="验证码",name="verCode",example="验证码")
	private String verCode;//验证码
	@ApiModelProperty(value="推荐码",name="suggestCode",example="推荐码")
	private String suggestCode;// 推荐码
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
	 * @return the financeType
	 */
	public Integer getFinanceType() {
		return financeType;
	}
	/**
	 * @param financeType the financeType to set
	 */
	public void setFinanceType(Integer financeType) {
		this.financeType = financeType;
	}
	/**
	 * @return the earnings
	 */
	public Integer getEarnings() {
		return earnings;
	}
	/**
	 * @param earnings the earnings to set
	 */
	public void setEarnings(Integer earnings) {
		this.earnings = earnings;
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
	 * @return the verCode
	 */
	public String getVerCode() {
		return verCode;
	}
	/**
	 * @param verCode the verCode to set
	 */
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
	/**
	 * @return the suggestCode
	 */
	public String getSuggestCode() {
		return suggestCode;
	}
	/**
	 * @param suggestCode the suggestCode to set
	 */
	public void setSuggestCode(String suggestCode) {
		this.suggestCode = suggestCode;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
