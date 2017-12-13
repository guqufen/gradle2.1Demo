package net.fnsco.car.service.finance.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.finance.entity.OrderFinanceDO;
public class OrderFinanceProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_order_finance";

    public String update(Map<String, Object> params) {
        OrderFinanceDO orderFinance = (OrderFinanceDO) params.get("orderFinance");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (orderFinance.getCustomerId() != null) {
            SET("customer_id=#{orderFinance.customerId}");
        }
        if (orderFinance.getCityId() != null) {
            SET("city_id=#{orderFinance.cityId}");
        }
        if (StringUtils.isNotBlank(orderFinance.getBuyType())){
            SET("buy_type=#{orderFinance.buyType}");
        }
        if (orderFinance.getSuggestCode() != null) {
            SET("suggest_code=#{orderFinance.suggestCode}");
        }
        if (orderFinance.getCreateTime() != null) {
            SET("create_time=#{orderFinance.createTime}");
        }
        if (orderFinance.getLastUpdateTime() != null) {
            SET("last_update_time=#{orderFinance.lastUpdateTime}");
        }
        if (orderFinance.getStatus() != null) {
            SET("status=#{orderFinance.status}");
        }
        WHERE("id = #{orderFinance.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OrderFinanceDO orderFinance = (OrderFinanceDO) params.get("orderFinance");
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
        if (orderFinance.getId() != null) {
            WHERE("id=#{orderFinance.id}");
        }
        if (orderFinance.getCustomerId() != null) {
            WHERE("customer_id=#{orderFinance.customerId}");
        }
        if (orderFinance.getCityId() != null) {
            WHERE("city_id=#{orderFinance.cityId}");
        }
        if (StringUtils.isNotBlank(orderFinance.getBuyType())){
            WHERE("buy_type=#{orderFinance.buyType}");
        }
        if (orderFinance.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderFinance.suggestCode}");
        }
        if (orderFinance.getCreateTime() != null) {
            WHERE("create_time=#{orderFinance.createTime}");
        }
        if (orderFinance.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderFinance.lastUpdateTime}");
        }
        if (orderFinance.getStatus() != null) {
            WHERE("status=#{orderFinance.status}");
        }
        if(StringUtils.isNotBlank(orderFinance.getCustomerName())) {
        	WHERE("customer_id in (select id from car_customer where name like CONCAT('%',#{orderFinance.customerName},'%'))");
        }
        if(StringUtils.isNotBlank(orderFinance.getCustomerPhone())) {
        	WHERE("customer_id in (select id from car_customer where mobile = #{orderFinance.customerPhone})");
        }
        if(orderFinance.getSysUserId() != null) {
        	WHERE("suggest_code in (SELECT suggest_code FROM car_agent WHERE id in (SELECT agent_id FROM sys_user WHERE id=#{orderFinance.sysUserId}))");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OrderFinanceDO orderFinance = (OrderFinanceDO) params.get("orderFinance");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (orderFinance.getId() != null) {
            WHERE("id=#{orderFinance.id}");
        }
        if (orderFinance.getCustomerId() != null) {
            WHERE("customer_id=#{orderFinance.customerId}");
        }
        if (orderFinance.getCityId() != null) {
            WHERE("city_id=#{orderFinance.cityId}");
        }
        if (StringUtils.isNotBlank(orderFinance.getBuyType())){
            WHERE("buy_type=#{orderFinance.buyType}");
        }
        if (orderFinance.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderFinance.suggestCode}");
        }
        if (orderFinance.getCreateTime() != null) {
            WHERE("create_time=#{orderFinance.createTime}");
        }
        if (orderFinance.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderFinance.lastUpdateTime}");
        }
        if (orderFinance.getStatus() != null) {
            WHERE("status=#{orderFinance.status}");
        }
        if(StringUtils.isNotBlank(orderFinance.getCustomerName())) {
        	WHERE("customer_id in (select id from car_customer where name like CONCAT('%',#{orderFinance.customerName},'%'))");
        }
        if(StringUtils.isNotBlank(orderFinance.getCustomerPhone())) {
        	WHERE("customer_id in (select id from car_customer where mobile = #{orderFinance.customerPhone})");
        }
        if(orderFinance.getSysUserId() != null) {
        	WHERE("suggest_code in (SELECT suggest_code FROM car_agent WHERE id in (SELECT agent_id FROM sys_user WHERE id=#{orderFinance.sysUserId})) ");
        }
        }}.toString();
    }
}

