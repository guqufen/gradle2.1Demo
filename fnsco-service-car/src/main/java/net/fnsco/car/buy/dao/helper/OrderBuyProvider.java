package net.fnsco.car.buy.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.car.buy.entity.OrderBuyDO;
public class OrderBuyProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_order_buy";

    public String update(Map<String, Object> params) {
        OrderBuyDO orderBuy = (OrderBuyDO) params.get("orderBuy");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (orderBuy.getCustomerId() != null) {
            SET("customer_id=#{orderBuy.customerId}");
        }
        if (orderBuy.getCityId() != null) {
            SET("city_id=#{orderBuy.cityId}");
        }
        if (orderBuy.getCarTypeId() != null) {
            SET("car_type_id=#{orderBuy.carTypeId}");
        }
        if (StringUtils.isNotBlank(orderBuy.getCarModel())){
            SET("car_model=#{orderBuy.carModel}");
        }
        if (StringUtils.isNotBlank(orderBuy.getBuyType())){
            SET("buy_type=#{orderBuy.buyType}");
        }
        if (orderBuy.getSuggestCode() != null) {
            SET("suggest_code=#{orderBuy.suggestCode}");
        }
        if (orderBuy.getCreateTime() != null) {
            SET("create_time=#{orderBuy.createTime}");
        }
        if (orderBuy.getLastUpdateTime() != null) {
            SET("last_update_time=#{orderBuy.lastUpdateTime}");
        }
        if (orderBuy.getStatus() != null) {
            SET("status=#{orderBuy.status}");
        }
        WHERE("id = #{orderBuy.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OrderBuyDO orderBuy = (OrderBuyDO) params.get("orderBuy");
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
        if (orderBuy.getId() != null) {
            WHERE("id=#{orderBuy.id}");
        }
        if (orderBuy.getCustomerId() != null) {
            WHERE("customer_id=#{orderBuy.customerId}");
        }
        if (orderBuy.getCityId() != null) {
            WHERE("city_id=#{orderBuy.cityId}");
        }
        if (orderBuy.getCarTypeId() != null) {
            WHERE("car_type_id=#{orderBuy.carTypeId}");
        }
        if (StringUtils.isNotBlank(orderBuy.getCarModel())){
            WHERE("car_model=#{orderBuy.carModel}");
        }
        if (StringUtils.isNotBlank(orderBuy.getBuyType())){
            WHERE("buy_type=#{orderBuy.buyType}");
        }
        if (orderBuy.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderBuy.suggestCode}");
        }
        if (orderBuy.getCreateTime() != null) {
            WHERE("create_time=#{orderBuy.createTime}");
        }
        if (orderBuy.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderBuy.lastUpdateTime}");
        }
        if (orderBuy.getStatus() != null) {
            WHERE("status=#{orderBuy.status}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OrderBuyDO orderBuy = (OrderBuyDO) params.get("orderBuy");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (orderBuy.getId() != null) {
            WHERE("id=#{orderBuy.id}");
        }
        if (orderBuy.getCustomerId() != null) {
            WHERE("customer_id=#{orderBuy.customerId}");
        }
        if (orderBuy.getCityId() != null) {
            WHERE("city_id=#{orderBuy.cityId}");
        }
        if (orderBuy.getCarTypeId() != null) {
            WHERE("car_type_id=#{orderBuy.carTypeId}");
        }
        if (StringUtils.isNotBlank(orderBuy.getCarModel())){
            WHERE("car_model=#{orderBuy.carModel}");
        }
        if (StringUtils.isNotBlank(orderBuy.getBuyType())){
            WHERE("buy_type=#{orderBuy.buyType}");
        }
        if (orderBuy.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderBuy.suggestCode}");
        }
        if (orderBuy.getCreateTime() != null) {
            WHERE("create_time=#{orderBuy.createTime}");
        }
        if (orderBuy.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderBuy.lastUpdateTime}");
        }
        if (orderBuy.getStatus() != null) {
            WHERE("status=#{orderBuy.status}");
        }
        }}.toString();
    }
}
