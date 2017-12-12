package net.fnsco.car.service.carBrand;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.car.service.carBrand.dao.CarBrandDAO;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;
import net.fnsco.car.service.carBrand.entity.CarBrandDTO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

@Service
public class CarBrandService extends BaseService {

	@Autowired
	private CarBrandDAO carBrandDAO;
	@Autowired
	private Environment env;

	/**
	 * 分页查询，汽车品牌
	 * 
	 * @param carBrandDO
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public ResultPageDTO<CarBrandDO> page(CarBrandDO carBrandDO, Integer pageNum, Integer pageSize) {
		List<CarBrandDO> pageList = carBrandDAO.pageList(carBrandDO, pageNum, pageSize);
		for (CarBrandDO carBrandDO2 : pageList) {
			if( !Strings.isNullOrEmpty(carBrandDO2.getIconImgPath()) ){
				carBrandDO2.setIconImgPath(env.getProperty("web.base.url")+carBrandDO2.getIconImgPath());
			}
		}
		Integer count = carBrandDAO.pageListCount(carBrandDO);
		ResultPageDTO<CarBrandDO> pager = new ResultPageDTO<CarBrandDO>(count, pageList);
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
	 * 更新数据
	 * 
	 * @param carBrandDO
	 */
	public ResultDTO update(CarBrandDO carBrandDO) {

		// 先通过id查找更新前数据
		CarBrandDO carBrandDO2 = carBrandDAO.getById(carBrandDO.getId());

		// 如果上级菜单id发生变化，则通过supperId=id去查找该id是否下挂下级菜单
		if (carBrandDO2.getSupperId() != carBrandDO.getSupperId()) {
			CarBrandDO carBrandDO3 = new CarBrandDO();

			// 设置supperId，查询是否含有下级id
			carBrandDO3.setSupperId(carBrandDO.getId());
			List<CarBrandDO> list = carBrandDAO.selectByCondition(carBrandDO3, null);
			if (list.size() > 0) {
				return ResultDTO.fail("有下级菜单，请先处理好下级菜单再执行本次更新，本次更新无效");
			}
		}
		carBrandDAO.update(carBrandDO);
		return ResultDTO.success();
	}

	/**
	 * 根据ID删除数据
	 * 
	 * @param carBrandDO
	 */
	public ResultDTO<Object> delete(CarBrandDO carBrandDO) {

			CarBrandDO carBrandDO2 = new CarBrandDO();
			carBrandDO2.setSupperId(carBrandDO.getId());
			List<CarBrandDO> list = carBrandDAO.selectByCondition(carBrandDO2, null);
			if (list.size() > 0) {
				return ResultDTO.fail("有下级菜单，请先处理好下级菜单再执行本次删除,本次删除无效");
			}

		carBrandDAO.delete(carBrandDO);
		return ResultDTO.success();
	}

	/**
	 * 查询汽车热门品牌
	 * @return
	 */
	public ResultDTO selectHot() {
		CarBrandDO carBrandDO = new CarBrandDO();
		carBrandDO.setIsHot(1);
		List<CarBrandDO> list = carBrandDAO.selectByCondition(carBrandDO, 8);
		for (CarBrandDO carBrandDO2 : list) {
				carBrandDO2.setIconImgPath(env.getProperty("web.base.url")+carBrandDO2.getIconImgPath());
		}
		return ResultDTO.success(list);
	}

	/**
	 * 查询菜单树
	 * @param carBrandDO
	 * @return
	 */
	public ResultDTO selectMenuTree() {

		CarBrandDO carBrandDO = new CarBrandDO();
		List<CarBrandDO> list = carBrandDAO.selectByCondition(carBrandDO, null);

		for (CarBrandDO carBrandDO2 : list) {
			if (!Strings.isNullOrEmpty(carBrandDO2.getIconImgPath())) {
				carBrandDO2.setIconImgPath(env.getProperty("web.base.url") + carBrandDO2.getIconImgPath());
			}
		}

		// 添加顶级菜单
		CarBrandDO root = new CarBrandDO();
		root.setId(0);
		root.setName("总菜单");
		root.setSupperId(-1);
		root.setLevel(-1);
		list.add(root);

		return ResultDTO.success(list);
	}
	
	/**
	 * 查询所有的汽车品牌,并按照第一个汉字的首字母排序，分配集合
	 * 
	 * @return
	 */
	public ResultDTO<List<CarBrandDTO>> selectAll() {

		List<CarBrandDO> list = carBrandDAO.selectAllFirstLevel();
		Map<String, Set<CarBrandDO>> map = new TreeMap<>();

		List<CarBrandDTO> carList = new ArrayList<>();

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

		for ( Entry<String, Set<CarBrandDO>> m : map.entrySet()) {
			CarBrandDTO carBrandDTO = new CarBrandDTO();
			carBrandDTO.setLetter(m.getKey());
			carBrandDTO.setBody(m.getValue());
			carList.add(carBrandDTO);
		}

		return ResultDTO.success(carList);
	}

	public ResultDTO selectChild(Integer id) {

		// 通过父id查找关联的子Id(二级子id和三级子id和四级id)
		List<CarBrandDO> list = carBrandDAO.selectChild(id);
		return ResultDTO.success(list);
	}
	
	// 查询
	 public CarBrandDO doQueryById (Integer id) {
		 CarBrandDO obj = this.carBrandDAO.getById(id);
	     return obj;
	 }

	public List<CarBrandDO> queryCityList() {
		List<CarBrandDO> list = this.carBrandDAO.getFirstLevel();
		return list;
	}
}
