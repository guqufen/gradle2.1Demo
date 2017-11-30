package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyBasicInfoDO;
public class DataCompanyBasicInfoProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_basicInfo";

    public String update(Map<String, Object> params) {
        DataCompanyBasicInfoDO dataCompanyBasicInfo = (DataCompanyBasicInfoDO) params.get("dataCompanyBasicInfo");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getInnerCode())){
            SET("inner_code=#{dataCompanyBasicInfo.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getKey())){
            SET("key=#{dataCompanyBasicInfo.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getValue())){
            SET("value=#{dataCompanyBasicInfo.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getSource())){
            SET("source=#{dataCompanyBasicInfo.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getType())){
            SET("type=#{dataCompanyBasicInfo.type}");
        }
        if (dataCompanyBasicInfo.getCreateTime() != null) {
            SET("create_time=#{dataCompanyBasicInfo.createTime}");
        }
        WHERE("id = #{dataCompanyBasicInfo.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyBasicInfoDO dataCompanyBasicInfo = (DataCompanyBasicInfoDO) params.get("dataCompanyBasicInfo");
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
        if (dataCompanyBasicInfo.getId() != null) {
            WHERE("id=#{dataCompanyBasicInfo.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getInnerCode())){
            WHERE("inner_code=#{dataCompanyBasicInfo.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getKey())){
            WHERE("key=#{dataCompanyBasicInfo.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getValue())){
            WHERE("value=#{dataCompanyBasicInfo.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getSource())){
            WHERE("source=#{dataCompanyBasicInfo.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getType())){
            WHERE("type=#{dataCompanyBasicInfo.type}");
        }
        if (dataCompanyBasicInfo.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyBasicInfo.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyBasicInfoDO dataCompanyBasicInfo = (DataCompanyBasicInfoDO) params.get("dataCompanyBasicInfo");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyBasicInfo.getId() != null) {
            WHERE("id=#{dataCompanyBasicInfo.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getInnerCode())){
            WHERE("inner_code=#{dataCompanyBasicInfo.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getKey())){
            WHERE("key=#{dataCompanyBasicInfo.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getValue())){
            WHERE("value=#{dataCompanyBasicInfo.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getSource())){
            WHERE("source=#{dataCompanyBasicInfo.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyBasicInfo.getType())){
            WHERE("type=#{dataCompanyBasicInfo.type}");
        }
        if (dataCompanyBasicInfo.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyBasicInfo.createTime}");
        }
        }}.toString();
    }
}

