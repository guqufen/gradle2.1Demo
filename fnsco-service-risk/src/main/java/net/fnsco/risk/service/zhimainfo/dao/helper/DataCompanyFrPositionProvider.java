package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataCompanyFrPositionDO;
public class DataCompanyFrPositionProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_data_company_fr_position";

    public String update(Map<String, Object> params) {
        DataCompanyFrPositionDO dataCompanyFrPosition = (DataCompanyFrPositionDO) params.get("dataCompanyFrPosition");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getInnerCode())){
            SET("inner_code=#{dataCompanyFrPosition.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getKey())){
            SET("key=#{dataCompanyFrPosition.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getValue())){
            SET("value=#{dataCompanyFrPosition.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getSource())){
            SET("source=#{dataCompanyFrPosition.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getType())){
            SET("type=#{dataCompanyFrPosition.type}");
        }
        if (dataCompanyFrPosition.getCreateTime() != null) {
            SET("create_time=#{dataCompanyFrPosition.createTime}");
        }
        WHERE("id = #{dataCompanyFrPosition.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataCompanyFrPositionDO dataCompanyFrPosition = (DataCompanyFrPositionDO) params.get("dataCompanyFrPosition");
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
        if (dataCompanyFrPosition.getId() != null) {
            WHERE("id=#{dataCompanyFrPosition.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getInnerCode())){
            WHERE("inner_code=#{dataCompanyFrPosition.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getKey())){
            WHERE("key=#{dataCompanyFrPosition.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getValue())){
            WHERE("value=#{dataCompanyFrPosition.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getSource())){
            WHERE("source=#{dataCompanyFrPosition.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getType())){
            WHERE("type=#{dataCompanyFrPosition.type}");
        }
        if (dataCompanyFrPosition.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyFrPosition.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataCompanyFrPositionDO dataCompanyFrPosition = (DataCompanyFrPositionDO) params.get("dataCompanyFrPosition");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataCompanyFrPosition.getId() != null) {
            WHERE("id=#{dataCompanyFrPosition.id}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getInnerCode())){
            WHERE("inner_code=#{dataCompanyFrPosition.innerCode}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getKey())){
            WHERE("key=#{dataCompanyFrPosition.key}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getValue())){
            WHERE("value=#{dataCompanyFrPosition.value}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getSource())){
            WHERE("source=#{dataCompanyFrPosition.source}");
        }
        if (StringUtils.isNotBlank(dataCompanyFrPosition.getType())){
            WHERE("type=#{dataCompanyFrPosition.type}");
        }
        if (dataCompanyFrPosition.getCreateTime() != null) {
            WHERE("create_time=#{dataCompanyFrPosition.createTime}");
        }
        }}.toString();
    }
}

