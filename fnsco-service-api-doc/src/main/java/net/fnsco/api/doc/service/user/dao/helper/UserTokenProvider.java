package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserTokenDO;
public class UserTokenProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_token";

    public String update(Map<String, Object> params) {
        UserTokenDO userToken = (UserTokenDO) params.get("userToken");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userToken.getCreateDate() != null) {
            SET("create_date=#{userToken.createDate}");
        }
        if (userToken.getModifyDate() != null) {
            SET("modify_date=#{userToken.modifyDate}");
        }
        if (userToken.getUserId() != null) {
            SET("user_id=#{userToken.userId}");
        }
        if (StringUtils.isNotBlank(userToken.getLoginIp())){
            SET("login_ip=#{userToken.loginIp}");
        }
        if (StringUtils.isNotBlank(userToken.getToken())){
            SET("token=#{userToken.token}");
        }
        if (userToken.getExpireDate() != null) {
            SET("expire_date=#{userToken.expireDate}");
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
        if (userToken.getCreateDate() != null) {
            WHERE("create_date=#{userToken.createDate}");
        }
        if (userToken.getModifyDate() != null) {
            WHERE("modify_date=#{userToken.modifyDate}");
        }
        if (userToken.getUserId() != null) {
            WHERE("user_id=#{userToken.userId}");
        }
        if (StringUtils.isNotBlank(userToken.getLoginIp())){
            WHERE("login_ip=#{userToken.loginIp}");
        }
        if (StringUtils.isNotBlank(userToken.getToken())){
            WHERE("token=#{userToken.token}");
        }
        if (userToken.getExpireDate() != null) {
            WHERE("expire_date=#{userToken.expireDate}");
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
        if (userToken.getCreateDate() != null) {
            WHERE("create_date=#{userToken.createDate}");
        }
        if (userToken.getModifyDate() != null) {
            WHERE("modify_date=#{userToken.modifyDate}");
        }
        if (userToken.getUserId() != null) {
            WHERE("user_id=#{userToken.userId}");
        }
        if (StringUtils.isNotBlank(userToken.getLoginIp())){
            WHERE("login_ip=#{userToken.loginIp}");
        }
        if (StringUtils.isNotBlank(userToken.getToken())){
            WHERE("token=#{userToken.token}");
        }
        if (userToken.getExpireDate() != null) {
            WHERE("expire_date=#{userToken.expireDate}");
        }
        }}.toString();
    }
}

