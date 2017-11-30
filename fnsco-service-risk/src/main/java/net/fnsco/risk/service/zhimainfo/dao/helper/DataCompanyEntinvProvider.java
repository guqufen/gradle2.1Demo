package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyEntinvDO;
public class DataCompanyEntinvProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_entinv";

    public String update(Map<String, Object> params) {
        DataCompanyEntinvDO dataCompanyEntinv = (DataCompanyEntinvDO) params.get("dataCompanyEntinv");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyEntinv.getInnerCode())){
            SET("inner_code=#{dataCompanyEntinv.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getKey())){
            SET("key=#{dataCompanyEntinv.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getValue())){
            SET("value=#{dataCompanyEntinv.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getSource())){
            SET("source=#{dataCompanyEntinv.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getType())){
            SET("type=#{dataCompanyEntinv.type}");
        }
        if (dataCompanyEntinv.getCreateTime() != null) {
            SET("create_time=#{dataCompanyEntinv.createTime}");
        }
        WHERE("id = #{dataCompanyEntinv.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyEntinvDO dataCompanyEntinv = (DataCompanyEntinvDO) params.get("dataCompanyEntinv");
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
        if (dataCompanyEntinv.getId() != null) {
            WHERE("id=#{dataCompanyEntinv.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getInnerCode())){
            WHERE("inner_code=#{dataCompanyEntinv.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getKey())){
            WHERE("key=#{dataCompanyEntinv.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getValue())){
            WHERE("value=#{dataCompanyEntinv.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getSource())){
            WHERE("source=#{dataCompanyEntinv.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getType())){
            WHERE("type=#{dataCompanyEntinv.type}");
        }
        if (dataCompanyEntinv.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyEntinv.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyEntinvDO dataCompanyEntinv = (DataCompanyEntinvDO) params.get("dataCompanyEntinv");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyEntinv.getId() != null) {
            WHERE("id=#{dataCompanyEntinv.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getInnerCode())){
            WHERE("inner_code=#{dataCompanyEntinv.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getKey())){
            WHERE("key=#{dataCompanyEntinv.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getValue())){
            WHERE("value=#{dataCompanyEntinv.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getSource())){
            WHERE("source=#{dataCompanyEntinv.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyEntinv.getType())){
            WHERE("type=#{dataCompanyEntinv.type}");
        }
        if (dataCompanyEntinv.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyEntinv.createTime}");
        }
        }}.toString();
    }
}

