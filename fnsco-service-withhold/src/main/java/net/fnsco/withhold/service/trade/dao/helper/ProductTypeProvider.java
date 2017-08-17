package net.fnsco.withhold.service.trade.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.withhold.service.trade.entity.ProductTypeDO;

import org.apache.commons.lang3.StringUtils;

public class ProductTypeProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "w_product_type";

    public String update(Map<String, Object> params) {
        ProductTypeDO productType = (ProductTypeDO) params.get("productType");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(productType.getName())){
            SET("name=#{productType.name}");
        }
        if (StringUtils.isNotBlank(productType.getStatus())){
            SET("status=#{productType.status}");
        }
        if (productType.getModifyTime() != null) {
            SET("modify_time=#{productType.modifyTime}");
        }
        WHERE("id = #{productType.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProductTypeDO productType = (ProductTypeDO) params.get("productType");
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
        if (productType.getId() != null) {
            WHERE("id=#{productType.id}");
        }
        if (StringUtils.isNotBlank(productType.getName())){
            WHERE("name=#{productType.name}");
        }
//        if (StringUtils.isNotBlank(productType.getStatus())){
//            WHERE("status=#{productType.status}");
//        }        productType.getStatus()
        if (productType.getModifyTime() != null) {
            WHERE("modify_time=#{productType.modifyTime}");
        }
        if(StringUtils.isNotBlank(productType.getStatus())){
            WHERE(" status = '"+productType.getStatus()+"'");
        }else{
            WHERE("status in (0,1)");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProductTypeDO productType = (ProductTypeDO) params.get("productType");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (productType.getId() != null) {
            WHERE("id=#{productType.id}");
        }
        if (StringUtils.isNotBlank(productType.getName())){
            WHERE("name=#{productType.name}");
        }
//        if (StringUtils.isNotBlank(productType.getStatus())){
//            WHERE("status=#{productType.status}");
//        }
        if (productType.getModifyTime() != null) {
            WHERE("modify_time=#{productType.modifyTime}");
        }
        if(StringUtils.isNotBlank(productType.getStatus())){
            WHERE(" status = '"+productType.getStatus()+"'");
        }else{
            WHERE("status in (0,1)");
        }
        }}.toString();
    }
}

