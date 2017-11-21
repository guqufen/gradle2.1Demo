package net.fnsco.bigdata.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.bigdata.service.sys.entity.PayCategroryWxDO;

import org.apache.commons.lang3.StringUtils;

public class PayCategroryWxProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_pay_categrory_wx";

    public String update(Map<String, Object> params) {
        PayCategroryWxDO payCategroryWx = (PayCategroryWxDO) params.get("payCategroryWx");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(payCategroryWx.getEtpsAttr())){
            SET("etps_attr=#{payCategroryWx.etpsAttr}");
        }
        if (payCategroryWx.getGroupId() != null) {
            SET("group_id=#{payCategroryWx.groupId}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getGroupName())){
            SET("group_name=#{payCategroryWx.groupName}");
        }
        if (payCategroryWx.getCategroryId() != null) {
            SET("categrory_id=#{payCategroryWx.categroryId}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getCategroryName())){
            SET("categrory_name=#{payCategroryWx.categroryName}");
        }
        WHERE("id = #{payCategroryWx.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        PayCategroryWxDO payCategroryWx = (PayCategroryWxDO) params.get("payCategroryWx");
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
        if (payCategroryWx.getId() != null) {
            WHERE("id=#{payCategroryWx.id}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getEtpsAttr())){
            WHERE("etps_attr=#{payCategroryWx.etpsAttr}");
        }
        if (payCategroryWx.getGroupId() != null) {
            WHERE("group_id=#{payCategroryWx.groupId}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getGroupName())){
            WHERE("group_name=#{payCategroryWx.groupName}");
        }
        if (payCategroryWx.getCategroryId() != null) {
            WHERE("categrory_id=#{payCategroryWx.categroryId}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getCategroryName())){
            WHERE("categrory_name=#{payCategroryWx.categroryName}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        PayCategroryWxDO payCategroryWx = (PayCategroryWxDO) params.get("payCategroryWx");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (payCategroryWx.getId() != null) {
            WHERE("id=#{payCategroryWx.id}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getEtpsAttr())){
            WHERE("etps_attr=#{payCategroryWx.etpsAttr}");
        }
        if (payCategroryWx.getGroupId() != null) {
            WHERE("group_id=#{payCategroryWx.groupId}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getGroupName())){
            WHERE("group_name=#{payCategroryWx.groupName}");
        }
        if (payCategroryWx.getCategroryId() != null) {
            WHERE("categrory_id=#{payCategroryWx.categroryId}");
        }
        if (StringUtils.isNotBlank(payCategroryWx.getCategroryName())){
            WHERE("categrory_name=#{payCategroryWx.categroryName}");
        }
        }}.toString();
    }
}

