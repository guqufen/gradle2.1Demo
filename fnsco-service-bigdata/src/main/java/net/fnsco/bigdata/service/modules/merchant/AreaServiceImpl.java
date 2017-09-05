package net.fnsco.bigdata.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.bigdata.api.merchant.AreaService;
import net.fnsco.bigdata.service.dao.master.AreaDAO;
import net.fnsco.bigdata.service.domain.Area;

/**
 * 获取地区信息
 * @author yx
 *
 */
@Service
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDAO areaDAO;

	@Override
	public List<Area> getList() {
		List<Area> areas = areaDAO.getList();
		return areas;
	}

	@Override
	public List<Area> getListBySupperId(int supperId) {
		List<Area> areas = areaDAO.getListBySupperId(supperId);
		return areas;
	}

	@Override
	public List<Area> getProvinceList() {
		List<Area> areas = areaDAO.getProvinceList();
		return areas;
	}
}
