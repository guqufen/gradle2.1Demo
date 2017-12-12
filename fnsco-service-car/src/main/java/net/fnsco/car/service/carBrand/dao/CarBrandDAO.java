package net.fnsco.car.service.carBrand.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import net.fnsco.car.service.carBrand.dao.helper.CarBrandProvider;
import net.fnsco.car.service.carBrand.entity.CarBrandDO;

public interface CarBrandDAO {

	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "supper_id", property = "supperId"), @Result(column = "level", property = "level"),
			@Result(column = "icon_img_path", property = "iconImgPath"), @Result(column = "model", property = "model"),
			@Result(column = "is_hot", property = "isHot"), @Result(column = "supperName", property = "supperName") })
	@SelectProvider(type = CarBrandProvider.class, method = "pageList")
	public List<CarBrandDO> pageList(@Param("carBrandDO") CarBrandDO carBrandDO, @Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize);

	@SelectProvider(type = CarBrandProvider.class, method = "pageListCount")
	public Integer pageListCount(@Param("carBrandDO") CarBrandDO carBrandDO);

	@Insert("INSERT INTO car_dic_type(id, name, supper_id, level, icon_img_path, model,is_hot) values(#{id},#{name},#{supperId},#{level},#{iconImgPath},#{model},#{isHot})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(CarBrandDO carBrandDO);
	
	@SelectProvider(type = CarBrandProvider.class, method = "update")
	public void update(@Param("carBrandDO") CarBrandDO carBrandDO);
	
	@Delete("delete from car_dic_type where id = #{id}")
	public void delete(CarBrandDO carBrandDO);

	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "supper_id", property = "supperId"), @Result(column = "level", property = "level"),
			@Result(column = "icon_img_path", property = "iconImgPath"), @Result(column = "model", property = "model"),
			@Result(column = "is_hot", property = "isHot") })
	@Select("select * from car_dic_type where level='1' order by CONVERT(name USING gbk) asc")
	public List<CarBrandDO> selectAllFirstLevel();
	
	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "supper_id", property = "supperId"), @Result(column = "level", property = "level"),
			@Result(column = "icon_img_path", property = "iconImgPath"), @Result(column = "model", property = "model"),
			@Result(column = "is_hot", property = "isHot") })
	@Select("select * from car_dic_type order by id")
	public List<CarBrandDO> queryAll();

	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "supper_id", property = "supperId"), @Result(column = "level", property = "level"),
			@Result(column = "icon_img_path", property = "iconImgPath"), @Result(column = "model", property = "model"),
			@Result(column = "is_hot", property = "isHot"), @Result(column = "supperName", property = "supperName")})
	@SelectProvider(type = CarBrandProvider.class, method = "selectByCondition")
	public List<CarBrandDO> selectByCondition(@Param("carBrandDO") CarBrandDO carBrandDO, @Param("limit") Integer limit);

	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
			@Result(column = "supper_id", property = "supperId"), @Result(column = "level", property = "level"),
			@Result(column = "icon_img_path", property = "iconImgPath"), @Result(column = "model", property = "model"),
			@Result(column = "is_hot", property = "isHot") })
	@Select("select * from car_dic_type where supper_id = #{id} or supper_id in (select distinct id from car_dic_type where supper_id =#{id}) or id =#{id} order by id")
	public List<CarBrandDO> selectChild(Integer id);
	
	/**
	 * getById:(根据ID查找)
	 *
	 * @param  @param id
	 * @param  @return    设定文件
	 * @return CarBrandDO    DOM对象
	 * @author tangliang
	 * @date   2017年12月11日 下午3:56:16
	 */
	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),
		@Result(column = "supper_id", property = "supperId"), @Result(column = "level", property = "level"),
		@Result(column = "icon_img_path", property = "iconImgPath"), @Result(column = "model", property = "model"),
		@Result(column = "is_hot", property = "isHot") })
	@Select("SELECT * FROM car_dic_type WHERE id = #{id}")
	public CarBrandDO getById(@Param("id") int id);

	@Results({ @Result(column = "id", property = "id"), @Result(column = "name", property = "name"),})
	@Select("SELECT id,name FROM car_dic_type WHERE supper_id = '0'")
	public List<CarBrandDO> getFirstLevel();
}
