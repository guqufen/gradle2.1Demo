package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataLawsuitDetailDO;
public class DataLawsuitDetailProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_lawsuit_detail";

    public String update(Map<String, Object> params) {
        DataLawsuitDetailDO dataLawsuitDetail = (DataLawsuitDetailDO) params.get("dataLawsuitDetail");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataLawsuitDetail.getInnerCode())){
            SET("inner_code=#{dataLawsuitDetail.innerCode}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getKey())){
            SET("key=#{dataLawsuitDetail.key}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getValue())){
            SET("value=#{dataLawsuitDetail.value}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getSource())){
            SET("source=#{dataLawsuitDetail.source}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getType())){
            SET("type=#{dataLawsuitDetail.type}");
        }
        if (dataLawsuitDetail.getCreateTime() != null) {
            SET("create_time=#{dataLawsuitDetail.createTime}");
        }
        WHERE("id = #{dataLawsuitDetail.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataLawsuitDetailDO dataLawsuitDetail = (DataLawsuitDetailDO) params.get("dataLawsuitDetail");
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
        if (dataLawsuitDetail.getId() != null) {
            WHERE("id=#{dataLawsuitDetail.id}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getInnerCode())){
            WHERE("inner_code=#{dataLawsuitDetail.innerCode}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getKey())){
            WHERE("key=#{dataLawsuitDetail.key}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getValue())){
            WHERE("value=#{dataLawsuitDetail.value}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getSource())){
            WHERE("source=#{dataLawsuitDetail.source}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getType())){
            WHERE("type=#{dataLawsuitDetail.type}");
        }
        if (dataLawsuitDetail.getCreateTime() != null) {
            WHERE("create_time=#{dataLawsuitDetail.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataLawsuitDetailDO dataLawsuitDetail = (DataLawsuitDetailDO) params.get("dataLawsuitDetail");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataLawsuitDetail.getId() != null) {
            WHERE("id=#{dataLawsuitDetail.id}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getInnerCode())){
            WHERE("inner_code=#{dataLawsuitDetail.innerCode}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getKey())){
            WHERE("key=#{dataLawsuitDetail.key}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getValue())){
            WHERE("value=#{dataLawsuitDetail.value}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getSource())){
            WHERE("source=#{dataLawsuitDetail.source}");
        }
        if (StringUtils.isNotBlank(dataLawsuitDetail.getType())){
            WHERE("type=#{dataLawsuitDetail.type}");
        }
        if (dataLawsuitDetail.getCreateTime() != null) {
            WHERE("create_time=#{dataLawsuitDetail.createTime}");
        }
        }}.toString();
    }
}

