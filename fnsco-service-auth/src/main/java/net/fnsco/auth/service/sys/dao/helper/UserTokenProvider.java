package net.fnsco.auth.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.auth.service.sys.entity.UserTokenDO;
public class UserTokenProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_user_token";

    public String update(Map<String, Object> params) {
        UserTokenDO userToken = (UserTokenDO) params.get("userToken");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userToken.getUserId() != null) {
            SET("user_id=#{userToken.userId}");
        }
        if (StringUtils.isNotBlank(userToken.getToken())){
            SET("token=#{userToken.token}");
        }
        if (userToken.getExpireTime() != null) {
            SET("expire_time=#{userToken.expireTime}");
        }
        if (userToken.getUpdateTime() != null) {
            SET("update_time=#{userToken.updateTime}");
        }
        WHERE("id = #{userToken.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserTokenDO userToken = (UserTokenDO) params.get("userToken");
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
        if (userToken.getId() != null) {
            WHERE("id=#{userToken.id}");
        }
        if (userToken.getUserId() != null) {
            WHERE("user_id=#{userToken.userId}");
        }
        if (StringUtils.isNotBlank(userToken.getToken())){
            WHERE("token=#{userToken.token}");
        }
        if (userToken.getExpireTime() != null) {
            WHERE("expire_time=#{userToken.expireTime}");
        }
        if (userToken.getUpdateTime() != null) {
            WHERE("update_time=#{userToken.updateTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserTokenDO userToken = (UserTokenDO) params.get("userToken");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userToken.getId() != null) {
            WHERE("id=#{userToken.id}");
        }
        if (userToken.getUserId() != null) {
            WHERE("user_id=#{userToken.userId}");
        }
        if (StringUtils.isNotBlank(userToken.getToken())){
            WHERE("token=#{userToken.token}");
        }
        if (userToken.getExpireTime() != null) {
            WHERE("expire_time=#{userToken.expireTime}");
        }
        if (userToken.getUpdateTime() != null) {
            WHERE("update_time=#{userToken.updateTime}");
        }
        }}.toString();
    }
}

