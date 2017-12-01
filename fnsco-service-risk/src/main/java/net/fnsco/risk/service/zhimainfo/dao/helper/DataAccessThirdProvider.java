package net.fnsco.risk.service.zhimainfo.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.zhimainfo.entity.DataAccessThirdDO;
public class DataAccessThirdProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "rist_data_access_third";

    public String update(Map<String, Object> params) {
        DataAccessThirdDO dataAccessThird = (DataAccessThirdDO) params.get("dataAccessThird");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (dataAccessThird.getType() != null) {
            SET("type=#{dataAccessThird.type}");
        }
        if (StringUtils.isNotBlank(dataAccessThird.getKey())){
            SET("key=#{dataAccessThird.key}");
        }
        if (StringUtils.isNotBlank(dataAccessThird.getBizNo())){
            SET("biz_no=#{dataAccessThird.bizNo}");
        }
        if (dataAccessThird.getChannel() != null) {
            SET("channel=#{dataAccessThird.channel}");
        }
        if (dataAccessThird.getCreateTime() != null) {
            SET("create_time=#{dataAccessThird.createTime}");
        }
        WHERE("id = #{dataAccessThird.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        DataAccessThirdDO dataAccessThird = (DataAccessThirdDO) params.get("dataAccessThird");
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
        if (dataAccessThird.getId() != null) {
            WHERE("id=#{dataAccessThird.id}");
        }
        if (dataAccessThird.getType() != null) {
            WHERE("type=#{dataAccessThird.type}");
        }
        if (StringUtils.isNotBlank(dataAccessThird.getKey())){
            WHERE("key=#{dataAccessThird.key}");
        }
        if (StringUtils.isNotBlank(dataAccessThird.getBizNo())){
            WHERE("biz_no=#{dataAccessThird.bizNo}");
        }
        if (dataAccessThird.getChannel() != null) {
            WHERE("channel=#{dataAccessThird.channel}");
        }
        if (dataAccessThird.getCreateTime() != null) {
            WHERE("create_time=#{dataAccessThird.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        DataAccessThirdDO dataAccessThird = (DataAccessThirdDO) params.get("dataAccessThird");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (dataAccessThird.getId() != null) {
            WHERE("id=#{dataAccessThird.id}");
        }
        if (dataAccessThird.getType() != null) {
            WHERE("type=#{dataAccessThird.type}");
        }
        if (StringUtils.isNotBlank(dataAccessThird.getKey())){
            WHERE("key=#{dataAccessThird.key}");
        }
        if (StringUtils.isNotBlank(dataAccessThird.getBizNo())){
            WHERE("biz_no=#{dataAccessThird.bizNo}");
        }
        if (dataAccessThird.getChannel() != null) {
            WHERE("channel=#{dataAccessThird.channel}");
        }
        if (dataAccessThird.getCreateTime() != null) {
            WHERE("create_time=#{dataAccessThird.createTime}");
        }
        }}.toString();
    }
}

