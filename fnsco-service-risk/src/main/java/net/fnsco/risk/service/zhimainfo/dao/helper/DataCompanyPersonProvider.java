package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyPersonDO;
public class DataCompanyPersonProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_person";

    public String update(Map<String, Object> params) {
        DataCompanyPersonDO dataCompanyPerson = (DataCompanyPersonDO) params.get("dataCompanyPerson");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyPerson.getInnerCode())){
            SET("inner_code=#{dataCompanyPerson.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getKey())){
            SET("key=#{dataCompanyPerson.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getValue())){
            SET("value=#{dataCompanyPerson.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getSource())){
            SET("source=#{dataCompanyPerson.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getType())){
            SET("type=#{dataCompanyPerson.type}");
        }
        if (dataCompanyPerson.getCreateTime() != null) {
            SET("create_time=#{dataCompanyPerson.createTime}");
        }
        WHERE("id = #{dataCompanyPerson.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyPersonDO dataCompanyPerson = (DataCompanyPersonDO) params.get("dataCompanyPerson");
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
        if (dataCompanyPerson.getId() != null) {
            WHERE("id=#{dataCompanyPerson.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getInnerCode())){
            WHERE("inner_code=#{dataCompanyPerson.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getKey())){
            WHERE("key=#{dataCompanyPerson.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getValue())){
            WHERE("value=#{dataCompanyPerson.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getSource())){
            WHERE("source=#{dataCompanyPerson.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getType())){
            WHERE("type=#{dataCompanyPerson.type}");
        }
        if (dataCompanyPerson.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyPerson.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyPersonDO dataCompanyPerson = (DataCompanyPersonDO) params.get("dataCompanyPerson");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyPerson.getId() != null) {
            WHERE("id=#{dataCompanyPerson.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getInnerCode())){
            WHERE("inner_code=#{dataCompanyPerson.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getKey())){
            WHERE("key=#{dataCompanyPerson.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getValue())){
            WHERE("value=#{dataCompanyPerson.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getSource())){
            WHERE("source=#{dataCompanyPerson.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyPerson.getType())){
            WHERE("type=#{dataCompanyPerson.type}");
        }
        if (dataCompanyPerson.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyPerson.createTime}");
        }
        }}.toString();
    }
}

