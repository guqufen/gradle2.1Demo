package net.fnsco.car.service.loan.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.car.service.loan.entity.OrderLoanDO;
public class OrderLoanProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_order_loan";

    public String update(Map<String, Object> params) {
        OrderLoanDO orderLoan = (OrderLoanDO) params.get("orderLoan");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (orderLoan.getCustomerId() != null) {
            SET("customer_id=#{orderLoan.customerId}");
        }
        if (orderLoan.getCityId() != null) {
            SET("city_id=#{orderLoan.cityId}");
        }
        if (orderLoan.getAmount() != null) {
            SET("amount=#{orderLoan.amount}");
        }
        if (orderLoan.getSuggestCode() != null) {
            SET("suggest_code=#{orderLoan.suggestCode}");
        }
        if (orderLoan.getCreateTime() != null) {
            SET("create_time=#{orderLoan.createTime}");
        }
        if (orderLoan.getLastUpdateTime() != null) {
            SET("last_update_time=#{orderLoan.lastUpdateTime}");
        }
        if (orderLoan.getStatus() != null) {
            SET("status=#{orderLoan.status}");
        }
        WHERE("id = #{orderLoan.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OrderLoanDO orderLoan = (OrderLoanDO) params.get("orderLoan");
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
        if (orderLoan.getId() != null) {
            WHERE("id=#{orderLoan.id}");
        }
        if (orderLoan.getCustomerId() != null) {
            WHERE("customer_id=#{orderLoan.customerId}");
        }
        if (orderLoan.getCityId() != null) {
            WHERE("city_id=#{orderLoan.cityId}");
        }
        if (orderLoan.getAmount() != null) {
            WHERE("amount=#{orderLoan.amount}");
        }
        if (orderLoan.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderLoan.suggestCode}");
        }
        if (orderLoan.getCreateTime() != null) {
            WHERE("create_time=#{orderLoan.createTime}");
        }
        if (orderLoan.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderLoan.lastUpdateTime}");
        }
        if (orderLoan.getStatus() != null) {
            WHERE("status=#{orderLoan.status}");
        }
        if(StringUtils.isNotBlank(orderLoan.getCustomerName())) {
        	WHERE("customer_id in (select id from car_customer where name like CONCAT('%',#{orderLoan.customerName},'%'))");
        }
        if(StringUtils.isNotBlank(orderLoan.getCustomerPhone())) {
        	WHERE("customer_id in (select id from car_customer where mobile = #{orderLoan.customerPhone})");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OrderLoanDO orderLoan = (OrderLoanDO) params.get("orderLoan");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (orderLoan.getId() != null) {
            WHERE("id=#{orderLoan.id}");
        }
        if (orderLoan.getCustomerId() != null) {
            WHERE("customer_id=#{orderLoan.customerId}");
        }
        if (orderLoan.getCityId() != null) {
            WHERE("city_id=#{orderLoan.cityId}");
        }
        if (orderLoan.getAmount() != null) {
            WHERE("amount=#{orderLoan.amount}");
        }
        if (orderLoan.getSuggestCode() != null) {
            WHERE("suggest_code=#{orderLoan.suggestCode}");
        }
        if (orderLoan.getCreateTime() != null) {
            WHERE("create_time=#{orderLoan.createTime}");
        }
        if (orderLoan.getLastUpdateTime() != null) {
            WHERE("last_update_time=#{orderLoan.lastUpdateTime}");
        }
        if (orderLoan.getStatus() != null) {
            WHERE("status=#{orderLoan.status}");
        }
        if(StringUtils.isNotBlank(orderLoan.getCustomerName())) {
        	WHERE("customer_id in (select id from car_customer where name like CONCAT('%',#{orderLoan.customerName},'%'))");
        }
        if(StringUtils.isNotBlank(orderLoan.getCustomerPhone())) {
        	WHERE("customer_id in (select id from car_customer where mobile = #{orderLoan.customerPhone})");
        }
        }}.toString();
    }
}

