package net.fnsco.web.controller.jo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

/**
 * 保险申请估算保费接口
 * @desc TODO
 * @author hjt
 * @version 
 * @Date 2017年12月8日 下午4:27:47
 */
public class EstiPremiumsJO extends JO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="汽车原价")
	private BigDecimal carOriginalPrice;// 汽车原价
	@ApiModelProperty(value="保险公司ID")
	private Integer insuCompanyId;// 保险公司ID
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
