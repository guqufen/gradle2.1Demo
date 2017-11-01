package net.fnsco.finance.api.dto;

import net.fnsco.core.base.DTO;

public class AppUserEntityDTO  extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String entityInnerCode;//实体内部商户号 15位
	
	private String mercName;//商户名（必须40个字符）;

	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public String getMercName() {
		return mercName;
	}

	public void setMercName(String mercName) {
		this.mercName = mercName;
	}

}
