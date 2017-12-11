package net.fnsco.web.controller.jo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 保险申请信息保存接口
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 下午2:11:06
 */
public class SaveSafeJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="投资人姓名")
	private String name;// 投资人姓名
	@ApiModelProperty(value="所在城市id")
	private Integer cityId;// 所在城市id
	@ApiModelProperty(value="汽车原价")
	private BigDecimal carOriginalPrice;// 汽车原价
	@ApiModelProperty(value="保险公司ID")
	private Integer insuCompanyId;// 保险公司ID
	@ApiModelProperty(value="预估保费")
	private BigDecimal estiPremiums;// 预估保费
	@ApiModelProperty(value="手机号码")
	private String mobile;// 手机号码
	@ApiModelProperty(value="验证码")
	private String code;//验证码
	@ApiModelProperty(value="推荐码")
	private Integer suggestCode;// 推荐码
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
	 * @return the carOriginalPrice
	 */
	public BigDecimal getCarOriginalPrice() {
		return carOriginalPrice;
	}
	/**
	 * @param carOriginalPrice the carOriginalPrice to set
	 */
	public void setCarOriginalPrice(BigDecimal carOriginalPrice) {
		this.carOriginalPrice = carOriginalPrice;
	}
	/**
	 * @return the insuCompanyId
	 */
	public Integer getInsuCompanyId() {
		return insuCompanyId;
	}
	/**
	 * @param insuCompanyId the insuCompanyId to set
	 */
	public void setInsuCompanyId(Integer insuCompanyId) {
		this.insuCompanyId = insuCompanyId;
	}
	/**
	 * @return the estiPremiums
	 */
	public BigDecimal getEstiPremiums() {
		return estiPremiums;
	}
	/**
	 * @param estiPremiums the estiPremiums to set
	 */
	public void setEstiPremiums(BigDecimal estiPremiums) {
		this.estiPremiums = estiPremiums;
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
	
}
