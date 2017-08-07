package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserExtDO;
public class UserExtProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_ext";

    public String update(Map<String, Object> params) {
        UserExtDO userExt = (UserExtDO) params.get("userExt");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userExt.getCreateDate() != null) {
            SET("create_date=#{userExt.createDate}");
        }
        if (userExt.getModifyDate() != null) {
            SET("modify_date=#{userExt.modifyDate}");
        }
        if (userExt.getUserId() != null) {
            SET("user_id=#{userExt.userId}");
        }
        if (userExt.getLastFetchSysMsgDate() != null) {
            SET("last_fetch_sys_msg_date=#{userExt.lastFetchSysMsgDate}");
        }
        WHERE("id = #{userExt.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserExtDO userExt = (UserExtDO) params.get("userExt");
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
        if (userExt.getId() != null) {
            WHERE("id=#{userExt.id}");
        }
        if (userExt.getCreateDate() != null) {
            WHERE("create_date=#{userExt.createDate}");
        }
        if (userExt.getModifyDate() != null) {
            WHERE("modify_date=#{userExt.modifyDate}");
        }
        if (userExt.getUserId() != null) {
            WHERE("user_id=#{userExt.userId}");
        }
        if (userExt.getLastFetchSysMsgDate() != null) {
            WHERE("last_fetch_sys_msg_date=#{userExt.lastFetchSysMsgDate}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserExtDO userExt = (UserExtDO) params.get("userExt");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userExt.getId() != null) {
            WHERE("id=#{userExt.id}");
        }
        if (userExt.getCreateDate() != null) {
            WHERE("create_date=#{userExt.createDate}");
        }
        if (userExt.getModifyDate() != null) {
            WHERE("modify_date=#{userExt.modifyDate}");
        }
        if (userExt.getUserId() != null) {
            WHERE("user_id=#{userExt.userId}");
        }
        if (userExt.getLastFetchSysMsgDate() != null) {
            WHERE("last_fetch_sys_msg_date=#{userExt.lastFetchSysMsgDate}");
        }
        }}.toString();
    }
}

