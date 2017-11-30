package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyShareHolderDO;
public class DataCompanyShareHolderProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_share_holder";

    public String update(Map<String, Object> params) {
        DataCompanyShareHolderDO dataCompanyShareHolder = (DataCompanyShareHolderDO) params.get("dataCompanyShareHolder");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getInnerCode())){
            SET("inner_code=#{dataCompanyShareHolder.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getKey())){
            SET("key=#{dataCompanyShareHolder.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getValue())){
            SET("value=#{dataCompanyShareHolder.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getSource())){
            SET("source=#{dataCompanyShareHolder.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getType())){
            SET("type=#{dataCompanyShareHolder.type}");
        }
        if (dataCompanyShareHolder.getCreateTime() != null) {
            SET("create_time=#{dataCompanyShareHolder.createTime}");
        }
        WHERE("id = #{dataCompanyShareHolder.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyShareHolderDO dataCompanyShareHolder = (DataCompanyShareHolderDO) params.get("dataCompanyShareHolder");
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
        if (dataCompanyShareHolder.getId() != null) {
            WHERE("id=#{dataCompanyShareHolder.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getInnerCode())){
            WHERE("inner_code=#{dataCompanyShareHolder.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getKey())){
            WHERE("key=#{dataCompanyShareHolder.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getValue())){
            WHERE("value=#{dataCompanyShareHolder.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getSource())){
            WHERE("source=#{dataCompanyShareHolder.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getType())){
            WHERE("type=#{dataCompanyShareHolder.type}");
        }
        if (dataCompanyShareHolder.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyShareHolder.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyShareHolderDO dataCompanyShareHolder = (DataCompanyShareHolderDO) params.get("dataCompanyShareHolder");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyShareHolder.getId() != null) {
            WHERE("id=#{dataCompanyShareHolder.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getInnerCode())){
            WHERE("inner_code=#{dataCompanyShareHolder.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getKey())){
            WHERE("key=#{dataCompanyShareHolder.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getValue())){
            WHERE("value=#{dataCompanyShareHolder.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getSource())){
            WHERE("source=#{dataCompanyShareHolder.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyShareHolder.getType())){
            WHERE("type=#{dataCompanyShareHolder.type}");
        }
        if (dataCompanyShareHolder.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyShareHolder.createTime}");
        }
        }}.toString();
    }
}

