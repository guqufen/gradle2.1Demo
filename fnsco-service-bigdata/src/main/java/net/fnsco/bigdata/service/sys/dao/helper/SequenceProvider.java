package net.fnsco.bigdata.service.sys.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.bigdata.service.sys.entity.SequenceDO;
public class SequenceProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_sequence";

    public String update(Map<String, Object> params) {
        SequenceDO sequence = (SequenceDO) params.get("sequence");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(sequence.getTableName())){
            SET("table_name=#{sequence.tableName}");
        }
        if (sequence.getCurrentValue() != null) {
            SET("current_value=#{sequence.currentValue}");
        }
        if (sequence.getNextValue() != null) {
            SET("next_value=#{sequence.nextValue}");
        }
        if (sequence.getStep() != null) {
            SET("step=#{sequence.step}");
        }
        WHERE("id = #{sequence.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        SequenceDO sequence = (SequenceDO) params.get("sequence");
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
        if (sequence.getId() != null) {
            WHERE("id=#{sequence.id}");
        }
        if (StringUtils.isNotBlank(sequence.getTableName())){
            WHERE("table_name=#{sequence.tableName}");
        }
        if (sequence.getCurrentValue() != null) {
            WHERE("current_value=#{sequence.currentValue}");
        }
        if (sequence.getNextValue() != null) {
            WHERE("next_value=#{sequence.nextValue}");
        }
        if (sequence.getStep() != null) {
            WHERE("step=#{sequence.step}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        SequenceDO sequence = (SequenceDO) params.get("sequence");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (sequence.getId() != null) {
            WHERE("id=#{sequence.id}");
        }
        if (StringUtils.isNotBlank(sequence.getTableName())){
            WHERE("table_name=#{sequence.tableName}");
        }
        if (sequence.getCurrentValue() != null) {
            WHERE("current_value=#{sequence.currentValue}");
        }
        if (sequence.getNextValue() != null) {
            WHERE("next_value=#{sequence.nextValue}");
        }
        if (sequence.getStep() != null) {
            WHERE("step=#{sequence.step}");
        }
        }}.toString();
    }
}

