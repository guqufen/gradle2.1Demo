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
				SELECT("*");
		        FROM(TABLE_NAME);
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
		        if (!Strings.isNullOrEmpty(brandDO.getModal())) {
		        	WHERE("model = #{carBrandDO.model}");
				}
		        if (brandDO.getIsHot() != null) {
		        	WHERE("is_hot = #{carBrandDO.isHot}");
				}
		        ORDER_BY("id desc limit " + start + ", " + limit );
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
					WHERE("name = #{carBrandDO.name}");
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
				if (!Strings.isNullOrEmpty(brandDO.getModal())) {
					WHERE("model = #{carBrandDO.model}");
				}
				if (brandDO.getIsHot() != null) {
					WHERE("is_hot = #{carBrandDO.isHot}");
				}
			}
		}.toString();
	}
	
	public String selectByCondition(Map<String, Object> params) {
		CarBrandDO brandDO = (CarBrandDO) params.get("carBrandDO");
		return new SQL() {
			{
				SELECT("*");
		        FROM(TABLE_NAME);
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
		        if (!Strings.isNullOrEmpty(brandDO.getModal())) {
		        	WHERE("model = #{carBrandDO.model}");
				}
		        if (brandDO.getIsHot() != null) {
		        	WHERE("is_hot = #{carBrandDO.isHot}");
				}
		        ORDER_BY("id desc limit 8" );
			}
		}.toString();
	}
}
