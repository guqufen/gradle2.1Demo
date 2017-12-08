package net.fnsco.car.service.city.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.city.entity.DicCityDO;
public class DicCityProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_dic_city";

    public String update(Map<String, Object> params) {
        DicCityDO dicCity = (DicCityDO) params.get("dicCity");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dicCity.getName())){
            SET("name=#{dicCity.name}");
        }
        if (dicCity.getSupperId() != null) {
            SET("supper_id=#{dicCity.supperId}");
        }
        if (dicCity.getLevel() != null) {
            SET("level=#{dicCity.level}");
        }
        WHERE("id = #{dicCity.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DicCityDO dicCity = (DicCityDO) params.get("dicCity");
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
        return new SQL() {{
        SELECT("*");
        FROM(TABLE_NAME);
        if (dicCity.getId() != null) {
            WHERE("id=#{dicCity.id}");
        }
        if (StringUtils.isNotBlank(dicCity.getName())){
            WHERE("name=#{dicCity.name}");
        }
        if (dicCity.getSupperId() != null) {
            WHERE("supper_id=#{dicCity.supperId}");
        }
        if (dicCity.getLevel() != null) {
            WHERE("level=#{dicCity.level}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DicCityDO dicCity = (DicCityDO) params.get("dicCity");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dicCity.getId() != null) {
            WHERE("id=#{dicCity.id}");
        }
        if (StringUtils.isNotBlank(dicCity.getName())){
            WHERE("name=#{dicCity.name}");
        }
        if (dicCity.getSupperId() != null) {
            WHERE("supper_id=#{dicCity.supperId}");
        }
        if (dicCity.getLevel() != null) {
            WHERE("level=#{dicCity.level}");
        }
        }}.toString();
    }
}

