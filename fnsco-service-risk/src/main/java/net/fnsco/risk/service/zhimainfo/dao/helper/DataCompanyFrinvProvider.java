package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrinvDO;
public class DataCompanyFrinvProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_frinv";

    public String update(Map<String, Object> params) {
        DataCompanyFrinvDO dataCompanyFrinv = (DataCompanyFrinvDO) params.get("dataCompanyFrinv");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyFrinv.getInnerCode())){
            SET("inner_code=#{dataCompanyFrinv.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getKey())){
            SET("key=#{dataCompanyFrinv.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getValue())){
            SET("value=#{dataCompanyFrinv.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getSource())){
            SET("source=#{dataCompanyFrinv.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getType())){
            SET("type=#{dataCompanyFrinv.type}");
        }
        if (dataCompanyFrinv.getCreateTime() != null) {
            SET("create_time=#{dataCompanyFrinv.createTime}");
        }
        WHERE("id = #{dataCompanyFrinv.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyFrinvDO dataCompanyFrinv = (DataCompanyFrinvDO) params.get("dataCompanyFrinv");
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
        if (dataCompanyFrinv.getId() != null) {
            WHERE("id=#{dataCompanyFrinv.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getInnerCode())){
            WHERE("inner_code=#{dataCompanyFrinv.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getKey())){
            WHERE("key=#{dataCompanyFrinv.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getValue())){
            WHERE("value=#{dataCompanyFrinv.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getSource())){
            WHERE("source=#{dataCompanyFrinv.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getType())){
            WHERE("type=#{dataCompanyFrinv.type}");
        }
        if (dataCompanyFrinv.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyFrinv.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyFrinvDO dataCompanyFrinv = (DataCompanyFrinvDO) params.get("dataCompanyFrinv");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyFrinv.getId() != null) {
            WHERE("id=#{dataCompanyFrinv.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getInnerCode())){
            WHERE("inner_code=#{dataCompanyFrinv.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getKey())){
            WHERE("key=#{dataCompanyFrinv.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getValue())){
            WHERE("value=#{dataCompanyFrinv.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getSource())){
            WHERE("source=#{dataCompanyFrinv.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrinv.getType())){
            WHERE("type=#{dataCompanyFrinv.type}");
        }
        if (dataCompanyFrinv.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyFrinv.createTime}");
        }
        }}.toString();
    }
}

