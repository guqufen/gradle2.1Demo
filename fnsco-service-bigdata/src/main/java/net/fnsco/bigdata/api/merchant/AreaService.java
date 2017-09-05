package net.fnsco.bigdata.api.merchant;

import java.util.List;

import net.fnsco.bigdata.service.domain.Area;

public interface AreaService{

	/**
	 * 获取所有省市区的列表
	 * @return
	 */
	public List<Area> getList();
	
	/**
	 * 获取所有supperId的子集
	 * @param supperId
	 * @return
	 */
	public List<Area> getListBySupperId(int supperId);
	
	/**
	 * 获取所有省份列表
	 * @return
	 */
	public List<Area> getProvinceList();
}
