package net.fnsco.finance.api.dto;

import net.fnsco.core.base.DTO;

public class AppUserShopDTO  extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shopInnerCode;//实体内部店铺号 15位
	
	private String shopName;//店铺名（必须40个字符）;

	public String getShopInnerCode() {
		return shopInnerCode;
	}

	public void setShopInnerCode(String shopInnerCode) {
		this.shopInnerCode = shopInnerCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


}
