package net.fnsco.finance.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;
import net.fnsco.finance.service.domain.FinanceIoType;
import net.fnsco.finance.api.dto.AppUserShopDTO;
public class IoTypeAndShopDTO  extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<FinanceIoType> financeIoTypeList;//交易子类型列表
	
	private List<AppUserShopDTO> appUserShopDTOList;//店铺列表

	public List<FinanceIoType> getFinanceIoTypeList() {
		return financeIoTypeList;
	}

	public void setFinanceIoTypeList(List<FinanceIoType> financeIoTypeList) {
		this.financeIoTypeList = financeIoTypeList;
	}

	public List<AppUserShopDTO> getAppUserShopDTOList() {
		return appUserShopDTOList;
	}

	public void setAppUserShopDTOList(List<AppUserShopDTO> appUserShopDTOList) {
		this.appUserShopDTOList = appUserShopDTOList;
	}

	
}
