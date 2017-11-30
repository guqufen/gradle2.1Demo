package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyAlterDO;
public class DataCompanyAlterProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_alter";

    public String update(Map<String, Object> params) {
        DataCompanyAlterDO dataCompanyAlter = (DataCompanyAlterDO) params.get("dataCompanyAlter");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyAlter.getInnerCode())){
            SET("inner_code=#{dataCompanyAlter.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getKey())){
            SET("key=#{dataCompanyAlter.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getValue())){
            SET("value=#{dataCompanyAlter.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getSource())){
            SET("source=#{dataCompanyAlter.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getType())){
            SET("type=#{dataCompanyAlter.type}");
        }
        if (dataCompanyAlter.getCreateTime() != null) {
            SET("create_time=#{dataCompanyAlter.createTime}");
        }
        WHERE("id = #{dataCompanyAlter.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyAlterDO dataCompanyAlter = (DataCompanyAlterDO) params.get("dataCompanyAlter");
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
        if (dataCompanyAlter.getId() != null) {
            WHERE("id=#{dataCompanyAlter.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getInnerCode())){
            WHERE("inner_code=#{dataCompanyAlter.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getKey())){
            WHERE("key=#{dataCompanyAlter.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getValue())){
            WHERE("value=#{dataCompanyAlter.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getSource())){
            WHERE("source=#{dataCompanyAlter.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getType())){
            WHERE("type=#{dataCompanyAlter.type}");
        }
        if (dataCompanyAlter.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyAlter.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyAlterDO dataCompanyAlter = (DataCompanyAlterDO) params.get("dataCompanyAlter");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyAlter.getId() != null) {
            WHERE("id=#{dataCompanyAlter.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getInnerCode())){
            WHERE("inner_code=#{dataCompanyAlter.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getKey())){
            WHERE("key=#{dataCompanyAlter.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getValue())){
            WHERE("value=#{dataCompanyAlter.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getSource())){
            WHERE("source=#{dataCompanyAlter.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyAlter.getType())){
            WHERE("type=#{dataCompanyAlter.type}");
        }
        if (dataCompanyAlter.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyAlter.createTime}");
        }
        }}.toString();
    }
}

