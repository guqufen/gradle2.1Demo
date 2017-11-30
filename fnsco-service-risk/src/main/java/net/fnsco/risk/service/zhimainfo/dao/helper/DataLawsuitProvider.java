package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDO;
public class DataLawsuitProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_lawsuit";

    public String update(Map<String, Object> params) {
        DataLawsuitDO dataLawsuit = (DataLawsuitDO) params.get("dataLawsuit");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataLawsuit.getInnerCode())){
            SET("inner_code=#{dataLawsuit.innerCode}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getKey())){
            SET("key=#{dataLawsuit.key}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getValue())){
            SET("value=#{dataLawsuit.value}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getSource())){
            SET("source=#{dataLawsuit.source}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getType())){
            SET("type=#{dataLawsuit.type}");
        }
        if (dataLawsuit.getCreateTime() != null) {
            SET("create_time=#{dataLawsuit.createTime}");
        }
        WHERE("id = #{dataLawsuit.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataLawsuitDO dataLawsuit = (DataLawsuitDO) params.get("dataLawsuit");
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
        if (dataLawsuit.getId() != null) {
            WHERE("id=#{dataLawsuit.id}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getInnerCode())){
            WHERE("inner_code=#{dataLawsuit.innerCode}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getKey())){
            WHERE("key=#{dataLawsuit.key}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getValue())){
            WHERE("value=#{dataLawsuit.value}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getSource())){
            WHERE("source=#{dataLawsuit.source}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getType())){
            WHERE("type=#{dataLawsuit.type}");
        }
        if (dataLawsuit.getCreateTime() != null) {
            WHERE("create_time=#{dataLawsuit.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataLawsuitDO dataLawsuit = (DataLawsuitDO) params.get("dataLawsuit");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataLawsuit.getId() != null) {
            WHERE("id=#{dataLawsuit.id}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getInnerCode())){
            WHERE("inner_code=#{dataLawsuit.innerCode}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getKey())){
            WHERE("key=#{dataLawsuit.key}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getValue())){
            WHERE("value=#{dataLawsuit.value}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getSource())){
            WHERE("source=#{dataLawsuit.source}");
        }
        if (StringUtils.isNotBlank(dataLawsuit.getType())){
            WHERE("type=#{dataLawsuit.type}");
        }
        if (dataLawsuit.getCreateTime() != null) {
            WHERE("create_time=#{dataLawsuit.createTime}");
        }
        }}.toString();
    }
}

