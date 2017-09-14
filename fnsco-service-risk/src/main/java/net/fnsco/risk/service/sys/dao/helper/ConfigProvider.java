package net.fnsco.risk.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.sys.entity.ConfigDO;
public class ConfigProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_config";

    public String update(Map<String, Object> params) {
        ConfigDO config = (ConfigDO) params.get("config");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(config.getType())){
            SET("type=#{config.type}");
        }
        if (StringUtils.isNotBlank(config.getGroupName())){
            SET("group_name=#{config.groupName}");
        }
        if (StringUtils.isNotBlank(config.getName())){
            SET("name=#{config.name}");
        }
        if (StringUtils.isNotBlank(config.getValue())){
            SET("value=#{config.value}");
        }
        if (config.getStatus() != null) {
            SET("status=#{config.status}");
        }
        if (StringUtils.isNotBlank(config.getRemark())){
            SET("remark=#{config.remark}");
        }
        WHERE("id = #{config.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ConfigDO config = (ConfigDO) params.get("config");
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
        if (config.getId() != null) {
            WHERE("id=#{config.id}");
        }
        if (StringUtils.isNotBlank(config.getType())){
            WHERE("type=#{config.type}");
        }
        if (StringUtils.isNotBlank(config.getGroupName())){
            WHERE("group_name=#{config.groupName}");
        }
        if (StringUtils.isNotBlank(config.getName())){
            WHERE("name=#{config.name}");
        }
        if (StringUtils.isNotBlank(config.getValue())){
            WHERE("value=#{config.value}");
        }
        if (config.getStatus() != null) {
            WHERE("status=#{config.status}");
        }
        if (StringUtils.isNotBlank(config.getRemark())){
            WHERE("remark=#{config.remark}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ConfigDO config = (ConfigDO) params.get("config");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (config.getId() != null) {
            WHERE("id=#{config.id}");
        }
        if (StringUtils.isNotBlank(config.getType())){
            WHERE("type=#{config.type}");
        }
        if (StringUtils.isNotBlank(config.getGroupName())){
            WHERE("group_name=#{config.groupName}");
        }
        if (StringUtils.isNotBlank(config.getName())){
            WHERE("name=#{config.name}");
        }
        if (StringUtils.isNotBlank(config.getValue())){
            WHERE("value=#{config.value}");
        }
        if (config.getStatus() != null) {
            WHERE("status=#{config.status}");
        }
        if (StringUtils.isNotBlank(config.getRemark())){
            WHERE("remark=#{config.remark}");
        }
        }}.toString();
    }
}

