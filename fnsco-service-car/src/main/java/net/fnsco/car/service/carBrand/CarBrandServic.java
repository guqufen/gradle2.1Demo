package net.fnsco.car.service.carBrand;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.car.service.carBrand.dao.CarBrandDAO;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class CarBrandServic extends BaseService {

	@Autowired
	private CarBrandDAO carBrandDAO;

	/**
	 * 分页查询，汽车品牌
	 * 
	 * @param carBrandDO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ResultPageDTO<CarBrandDO> page(CarBrandDO carBrandDO, Integer pageNum, Integer pageSize) {
		List<CarBrandDO> list = carBrandDAO.pageList(carBrandDO, pageNum, pageSize);
		Integer count = carBrandDAO.pageListCount(carBrandDO);
		ResultPageDTO<CarBrandDO> pager = new ResultPageDTO<CarBrandDO>(count, list);
		return pager;
	}

	/**
	 * 保存数据
	 * 
	 * @param carBrandDO
	 */
	public void insert(CarBrandDO carBrandDO) {
		carBrandDAO.insert(carBrandDO);
	}

	/**
	 * 查询汽车热门品牌
	 * @return
	 */
	public ResultDTO selectHot() {
		CarBrandDO carBrandDO = new CarBrandDO();
		carBrandDO.setIsHot(1);
		List<CarBrandDO> list = carBrandDAO.selectByCondition(carBrandDO);
		return ResultDTO.success(list);
	}

	/**
	 * 查询所有的汽车品牌,并按照第一个汉字的首字母排序，分配集合
	 * 
	 * @return
	 */
	public ResultDTO<Map<String, Set<CarBrandDO>>> selectAll() {

		List<CarBrandDO> list = carBrandDAO.selectAll();
		Map<String, Set<CarBrandDO>> map = new TreeMap<>();

		for (CarBrandDO carBrandDO : list) {

			/**
			 * 获取名称的每个汉字的首字母
			 */
			String letter = ChineseInital.getAllFirstLetter(carBrandDO.getName());

			/**
			 * 判断每个汉字首字母的第一个字母是否在map里面存在，若已经存在则直接添加进set；否则，新建一个set
			 */
			if (map.get(letter.substring(0, 1)) != null) {
				map.get(letter.substring(0, 1)).add(carBrandDO);
			} else {
				Set<CarBrandDO> set = new HashSet<>();
				set.add(carBrandDO);
				map.put(letter.substring(0, 1), set);
			}
		}

		return ResultDTO.success(map);
	}

	public ResultDTO selectChild(Integer id) {

		// 通过父id查找关联的子Id(二级子id和三级子id)
		List<CarBrandDO> list = carBrandDAO.selectChild(id);
		return ResultDTO.success(list);
	}
}
