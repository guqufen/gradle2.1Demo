package net.fnsco.car.service.carBrand.dao.helper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import net.fnsco.car.service.carBrand.entity.CarBrandDO;

public class CarBrandProvider {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String TABLE_NAME = "car_dic_type";

	public String pageList(Map<String, Object> params) {
		
		CarBrandDO brandDO = (CarBrandDO) params.get("carBrandDO");
		
		Integer pageNum = (Integer) params.get("pageNum");
		Integer pageSize = (Integer) params.get("pageSize");
		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize == 0) {
			pageSize = 20;
		}
		int start = (pageNum - 1) * pageSize;
		int limit = pageSize;

		return new SQL() {
			{
				SELECT("r.*, (select name from car_dic_type where id = r.supper_id) supperName");
		        FROM(TABLE_NAME+" r");
		        if(brandDO.getId() != null){
		        	WHERE("id = #{carBrandDO.id}");
		        }
		        if ( !Strings.isNullOrEmpty(brandDO.getName()) ) {
		        	WHERE("name like CONCAT('%',#{carBrandDO.name},'%')");
				}
		        if (brandDO.getSupperId() != null) {
		        	WHERE("supper_id = #{carBrandDO.supperId}");
				}
		        if (brandDO.getLevel() != null) {
		        	WHERE("level = #{carBrandDO.level}");
				}
		        if ( !Strings.isNullOrEmpty(brandDO.getIconImgPath()) ) {
		        	WHERE("icon_img_path = #{carBrandDO.iconImgPath}");
				}
		        if (!Strings.isNullOrEmpty(brandDO.getModel())) {
		        	WHERE("model = #{carBrandDO.model}");
				}
		        if (brandDO.getIsHot() != null) {
		        	WHERE("is_hot = #{carBrandDO.isHot}");
				}
		        if (!Strings.isNullOrEmpty(brandDO.getSupperName())) {
		        	String sql = "SELECT DISTINCT id FROM car_dic_type WHERE name like CONCAT('%',#{carBrandDO.supperName},'%') AND supper_id = 0";
		        	WHERE("id IN ( "+sql+" )"+//1
		        			"OR id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( "+sql+" ) )"+//2
		        			"OR id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( "+sql+" ) ) )"+//3
		        			"OR id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( SELECT DISTINCT id FROM car_dic_type "+
		        			"WHERE supper_id IN ( "+sql+" ) ) ) )"//4
						);
				}
		        ORDER_BY("id asc limit " + start + ", " + limit );
			}
		}.toString();
	}
	
	public String pageListCount(Map<String, Object> params) {

		
		CarBrandDO brandDO = (CarBrandDO) params.get("carBrandDO");
		
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(TABLE_NAME);
				if (brandDO.getId() != null) {
					WHERE("id = #{carBrandDO.id}");
				}
				if (!Strings.isNullOrEmpty(brandDO.getName())) {
					WHERE("name like CONCAT('%',#{carBrandDO.name},'%')");
				}
				if (brandDO.getSupperId() != null) {
					WHERE("supper_id = #{carBrandDO.supperId}");
				}
				if (brandDO.getLevel() != null) {
					WHERE("level = #{carBrandDO.level}");
				}
				if (!Strings.isNullOrEmpty(brandDO.getIconImgPath())) {
					WHERE("icon_img_path = #{carBrandDO.iconImgPath}");
				}
				if (!Strings.isNullOrEmpty(brandDO.getModel())) {
					WHERE("model = #{carBrandDO.model}");
				}
				if (brandDO.getIsHot() != null) {
					WHERE("is_hot = #{carBrandDO.isHot}");
				}
				if (!Strings.isNullOrEmpty(brandDO.getSupperName())) {
					String sql = "SELECT DISTINCT id FROM car_dic_type WHERE name like CONCAT('%',#{carBrandDO.supperName},'%') AND supper_id = 0";
		        	WHERE("id IN ( "+sql+" )"+//1
		        			"OR id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( "+sql+" ) )"+//2
		        			"OR id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( "+sql+" ) ) )"+//3
		        			"OR id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( SELECT DISTINCT id FROM car_dic_type WHERE supper_id IN ( SELECT DISTINCT id FROM car_dic_type "+
		        			"WHERE supper_id IN ( "+sql+" ) ) ) )"//4
						);
				}
			}
		}.toString();
	}
	
	public String selectByCondition(Map<String, Object> params) {
		CarBrandDO brandDO = (CarBrandDO) params.get("carBrandDO");
		Integer limit = (Integer) params.get("limit");
		return new SQL() {
			{
				SELECT("r.*, (select name from car_dic_type where id = r.supper_id) supperName");
		        FROM(TABLE_NAME+" r");
		        if(brandDO.getId() != null){
		        	WHERE("id = #{carBrandDO.id}");
		        }
		        if ( !Strings.isNullOrEmpty(brandDO.getName()) ) {
		        	WHERE("name = #{carBrandDO.name}");
				}
		        if (brandDO.getSupperId() != null) {
		        	WHERE("supper_id = #{carBrandDO.supperId}");
				}
		        if (brandDO.getLevel() != null) {
		        	WHERE("level = #{carBrandDO.level}");
				}
		        if ( !Strings.isNullOrEmpty(brandDO.getIconImgPath()) ) {
		        	WHERE("icon_img_path = #{carBrandDO.iconImgPath}");
				}
		        if (!Strings.isNullOrEmpty(brandDO.getModel())) {
		        	WHERE("model = #{carBrandDO.model}");
				}
		        if (brandDO.getIsHot() != null) {
		        	WHERE("is_hot = #{carBrandDO.isHot}");
				}
		        if( limit != null){
		        	ORDER_BY("id desc limit 8" );
		        }else{
		        	ORDER_BY("case when (level=1) then 0 else 1 end, CONVERT(name USING gbk) asc" );
		        }
			}
		}.toString();
	}
	
	public String update(Map<String, Object> params) {
		CarBrandDO brandDO = (CarBrandDO) params.get("carBrandDO");
		return new SQL() {
			{
				UPDATE(TABLE_NAME);
		        if ( !Strings.isNullOrEmpty(brandDO.getName()) ) {
		        	SET("name = #{carBrandDO.name}");
				}
		        if (brandDO.getSupperId() != null) {
		        	SET("supper_id = #{carBrandDO.supperId}");
				}
		        if (brandDO.getLevel() != null) {
		        	SET("level = #{carBrandDO.level}");
				}
		        if ( !Strings.isNullOrEmpty(brandDO.getIconImgPath()) ) {
		        	SET("icon_img_path = #{carBrandDO.iconImgPath}");
				}
		        if (!Strings.isNullOrEmpty(brandDO.getModel())) {
		        	SET("model = #{carBrandDO.model}");
				}
		        if (brandDO.getIsHot() != null) {
		        	SET("is_hot = #{carBrandDO.isHot}");
				}
		        WHERE("id = #{carBrandDO.id}");
		    
			}
		}.toString();
	}
}
