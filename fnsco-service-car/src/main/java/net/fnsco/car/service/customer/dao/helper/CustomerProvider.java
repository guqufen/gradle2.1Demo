package net.fnsco.car.service.customer.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.car.service.customer.entity.CustomerDO;

import org.apache.commons.lang3.StringUtils;
public class CustomerProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "car_customer";

    public String update(Map<String, Object> params) {
        CustomerDO customer = (CustomerDO) params.get("customer");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(customer.getName())){
            SET("name=#{customer.name}");
        }
        if (StringUtils.isNotBlank(customer.getMobile())){
            SET("mobile=#{customer.mobile}");
        }
        if (customer.getCreateTime() != null) {
            SET("create_time=#{customer.createTime}");
        }
        WHERE("id = #{customer.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        CustomerDO customer = (CustomerDO) params.get("customer");
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
        if (customer.getId() != null) {
            WHERE("id=#{customer.id}");
        }
        if (StringUtils.isNotBlank(customer.getName())){
            WHERE("name=#{customer.name}");
        }
        if (StringUtils.isNotBlank(customer.getMobile())){
            WHERE("mobile=#{customer.mobile}");
        }
        if (customer.getCreateTime() != null) {
            WHERE("create_time=#{customer.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        CustomerDO customer = (CustomerDO) params.get("customer");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (customer.getId() != null) {
            WHERE("id=#{customer.id}");
        }
        if (StringUtils.isNotBlank(customer.getName())){
            WHERE("name=#{customer.name}");
        }
        if (StringUtils.isNotBlank(customer.getMobile())){
            WHERE("mobile=#{customer.mobile}");
        }
        if (customer.getCreateTime() != null) {
            WHERE("create_time=#{customer.createTime}");
        }
        }}.toString();
    }
}

