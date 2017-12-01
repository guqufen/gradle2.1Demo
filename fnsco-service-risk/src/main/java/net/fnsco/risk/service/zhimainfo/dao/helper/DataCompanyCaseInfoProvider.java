package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyCaseInfoDO;
public class DataCompanyCaseInfoProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_case_info";

    public String update(Map<String, Object> params) {
        DataCompanyCaseInfoDO dataCompanyCaseInfo = (DataCompanyCaseInfoDO) params.get("dataCompanyCaseInfo");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getInnerCode())){
            SET("inner_code=#{dataCompanyCaseInfo.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getKey())){
            SET("key=#{dataCompanyCaseInfo.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getValue())){
            SET("value=#{dataCompanyCaseInfo.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getSource())){
            SET("source=#{dataCompanyCaseInfo.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getType())){
            SET("type=#{dataCompanyCaseInfo.type}");
        }
        if (dataCompanyCaseInfo.getCreateTime() != null) {
            SET("create_time=#{dataCompanyCaseInfo.createTime}");
        }
        WHERE("id = #{dataCompanyCaseInfo.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyCaseInfoDO dataCompanyCaseInfo = (DataCompanyCaseInfoDO) params.get("dataCompanyCaseInfo");
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
        if (dataCompanyCaseInfo.getId() != null) {
            WHERE("id=#{dataCompanyCaseInfo.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getInnerCode())){
            WHERE("inner_code=#{dataCompanyCaseInfo.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getKey())){
            WHERE("key=#{dataCompanyCaseInfo.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getValue())){
            WHERE("value=#{dataCompanyCaseInfo.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getSource())){
            WHERE("source=#{dataCompanyCaseInfo.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getType())){
            WHERE("type=#{dataCompanyCaseInfo.type}");
        }
        if (dataCompanyCaseInfo.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyCaseInfo.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyCaseInfoDO dataCompanyCaseInfo = (DataCompanyCaseInfoDO) params.get("dataCompanyCaseInfo");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyCaseInfo.getId() != null) {
            WHERE("id=#{dataCompanyCaseInfo.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getInnerCode())){
            WHERE("inner_code=#{dataCompanyCaseInfo.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getKey())){
            WHERE("key=#{dataCompanyCaseInfo.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getValue())){
            WHERE("value=#{dataCompanyCaseInfo.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getSource())){
            WHERE("source=#{dataCompanyCaseInfo.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyCaseInfo.getType())){
            WHERE("type=#{dataCompanyCaseInfo.type}");
        }
        if (dataCompanyCaseInfo.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyCaseInfo.createTime}");
        }
        }}.toString();
    }
}

