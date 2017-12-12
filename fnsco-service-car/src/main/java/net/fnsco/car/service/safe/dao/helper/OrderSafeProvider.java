package net.fnsco.car.service.safe.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.safe.entity.OrderSafeDO;
public class OrderSafeProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_order_safe";

    public String update(Map<String, Object> params) {
        OrderSafeDO orderSafe = (OrderSafeDO) params.get("orderSafe");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (orderSafe.getCustomerId() != null) {
            SET("customer_id=#{orderSafe.customerId}");
        }
        if (orderSafe.getCityId() != null) {
            SET("city_id=#{orderSafe.cityId}");
        }
        if (orderSafe.getCarOriginalPrice() != null) {
            SET("car_original_price=#{orderSafe.carOriginalPrice}");
        }
        if (orderSafe.getInsuCompanyId() != null) {
            SET("insu_company_id=#{orderSafe.insuCompanyId}");
        }
        if (orderSafe.getEstiPremiums() != null) {
            SET("esti_premiums=#{orderSafe.estiPremiums}");
        }
        if (orderSafe.getSuggestCode() != null) {
            SET("suggest_code=#{orderSafe.suggestCode}");
        }
        if (orderSafe.getCreateTime() != null) {
            SET("create_time=#{orderSafe.createTime}");
        }
        if (orderSafe.getLastUpdateTime() != null) {
            SET("last_update_time=#{orderSafe.lastUpdateTime}");
        }
        if (orderSafe.getStatus() != null) {
            SET("status=#{orderSafe.status}");
        }
        WHERE("id = #{orderSafe.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OrderSafeDO orderSafe = (OrderSafeDO) params.get("orderSafe");
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
        if (orderSafe.getId() != null) {
            WHERE("id=#{orderSafe.id}");
        }
        if (orderSafe.getCustomerId() != null) {
            WHERE("customer_id=#{orderSafe.customerId}");
        }
        if (orderSafe.getCityId() != null) {
            WHERE("city_id=#{orderSafe.cityId}");
        }
        if (orderSafe.getCarOriginalPrice() != null) {
            WHERE("car_original_price=#{orderSafe.carOriginalPrice}");
        }
        if (orderSafe.getInsuCompanyId() != null) {
            WHERE("insu_company_id=#{orderSafe.insuCompanyId}");
        }
        if (orderSafe.getEstiPremiums() != null) {
            WHERE("esti_premiums=#{orderSafe.estiPremiums}");
        }
        if (orderSafe.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderSafe.suggestCode}");
        }
        if (orderSafe.getCreateTime() != null) {
            WHERE("create_time=#{orderSafe.createTime}");
        }
        if (orderSafe.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderSafe.lastUpdateTime}");
        }
        if (orderSafe.getStatus() != null) {
            WHERE("status=#{orderSafe.status}");
        }
        if(StringUtils.isNotBlank(orderSafe.getCustomerName())) {
        	WHERE("customer_id in (select id from car_customer where name like CONCAT('%',#{orderSafe.customerName},'%'))");
        }
        if(StringUtils.isNotBlank(orderSafe.getCustomerPhone())) {
        	WHERE("customer_id in (select id from car_customer where mobile = #{orderSafe.customerPhone})");
        }
        if(orderSafe.getSysUserId() != null) {
        	WHERE("id in (SELECT order_id FROM car_user_order_ref WHERE user_id = #{orderSafe.sysUserId} AND type = 2)");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OrderSafeDO orderSafe = (OrderSafeDO) params.get("orderSafe");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (orderSafe.getId() != null) {
            WHERE("id=#{orderSafe.id}");
        }
        if (orderSafe.getCustomerId() != null) {
            WHERE("customer_id=#{orderSafe.customerId}");
        }
        if (orderSafe.getCityId() != null) {
            WHERE("city_id=#{orderSafe.cityId}");
        }
        if (orderSafe.getCarOriginalPrice() != null) {
            WHERE("car_original_price=#{orderSafe.carOriginalPrice}");
        }
        if (orderSafe.getInsuCompanyId() != null) {
            WHERE("insu_company_id=#{orderSafe.insuCompanyId}");
        }
        if (orderSafe.getEstiPremiums() != null) {
            WHERE("esti_premiums=#{orderSafe.estiPremiums}");
        }
        if (orderSafe.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderSafe.suggestCode}");
        }
        if (orderSafe.getCreateTime() != null) {
            WHERE("create_time=#{orderSafe.createTime}");
        }
        if (orderSafe.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderSafe.lastUpdateTime}");
        }
        if (orderSafe.getStatus() != null) {
            WHERE("status=#{orderSafe.status}");
        }
        if(StringUtils.isNotBlank(orderSafe.getCustomerName())) {
        	WHERE("customer_id in (select id from car_customer where name like CONCAT('%',#{orderSafe.customerName},'%'))");
        }
        if(StringUtils.isNotBlank(orderSafe.getCustomerPhone())) {
        	WHERE("customer_id in (select id from car_customer where mobile = #{orderSafe.customerPhone})");
        }
        if(orderSafe.getSysUserId() != null) {
        	WHERE("id in (SELECT order_id FROM car_user_order_ref WHERE user_id = #{orderSafe.sysUserId} AND type = 2)");
        }
        }}.toString();
    }
}

