package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserLoginDO;
public class UserLoginProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_login";

    public String update(Map<String, Object> params) {
        UserLoginDO userLogin = (UserLoginDO) params.get("userLogin");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userLogin.getCreateDate() != null) {
            SET("create_date=#{userLogin.createDate}");
        }
        if (userLogin.getModifyDate() != null) {
            SET("modify_date=#{userLogin.modifyDate}");
        }
        if (userLogin.getLoginDate() != null) {
            SET("login_date=#{userLogin.loginDate}");
        }
        if (userLogin.getLoginFailureCount() != null) {
            SET("login_failure_count=#{userLogin.loginFailureCount}");
        }
        if (userLogin.getLoginCount() != null) {
            SET("login_count=#{userLogin.loginCount}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginIp())){
            SET("login_ip=#{userLogin.loginIp}");
        }
        if (userLogin.getUserId() != null) {
            SET("user_id=#{userLogin.userId}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginType())){
            SET("login_type=#{userLogin.loginType}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginStatus())){
            SET("login_status=#{userLogin.loginStatus}");
        }
        if (StringUtils.isNotBlank(userLogin.getAuthCode())){
            SET("auth_code=#{userLogin.authCode}");
        }
        WHERE("id = #{userLogin.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserLoginDO userLogin = (UserLoginDO) params.get("userLogin");
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
        if (userLogin.getId() != null) {
            WHERE("id=#{userLogin.id}");
        }
        if (userLogin.getCreateDate() != null) {
            WHERE("create_date=#{userLogin.createDate}");
        }
        if (userLogin.getModifyDate() != null) {
            WHERE("modify_date=#{userLogin.modifyDate}");
        }
        if (userLogin.getLoginDate() != null) {
            WHERE("login_date=#{userLogin.loginDate}");
        }
        if (userLogin.getLoginFailureCount() != null) {
            WHERE("login_failure_count=#{userLogin.loginFailureCount}");
        }
        if (userLogin.getLoginCount() != null) {
            WHERE("login_count=#{userLogin.loginCount}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginIp())){
            WHERE("login_ip=#{userLogin.loginIp}");
        }
        if (userLogin.getUserId() != null) {
            WHERE("user_id=#{userLogin.userId}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginType())){
            WHERE("login_type=#{userLogin.loginType}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginStatus())){
            WHERE("login_status=#{userLogin.loginStatus}");
        }
        if (StringUtils.isNotBlank(userLogin.getAuthCode())){
            WHERE("auth_code=#{userLogin.authCode}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserLoginDO userLogin = (UserLoginDO) params.get("userLogin");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userLogin.getId() != null) {
            WHERE("id=#{userLogin.id}");
        }
        if (userLogin.getCreateDate() != null) {
            WHERE("create_date=#{userLogin.createDate}");
        }
        if (userLogin.getModifyDate() != null) {
            WHERE("modify_date=#{userLogin.modifyDate}");
        }
        if (userLogin.getLoginDate() != null) {
            WHERE("login_date=#{userLogin.loginDate}");
        }
        if (userLogin.getLoginFailureCount() != null) {
            WHERE("login_failure_count=#{userLogin.loginFailureCount}");
        }
        if (userLogin.getLoginCount() != null) {
            WHERE("login_count=#{userLogin.loginCount}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginIp())){
            WHERE("login_ip=#{userLogin.loginIp}");
        }
        if (userLogin.getUserId() != null) {
            WHERE("user_id=#{userLogin.userId}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginType())){
            WHERE("login_type=#{userLogin.loginType}");
        }
        if (StringUtils.isNotBlank(userLogin.getLoginStatus())){
            WHERE("login_status=#{userLogin.loginStatus}");
        }
        if (StringUtils.isNotBlank(userLogin.getAuthCode())){
            WHERE("auth_code=#{userLogin.authCode}");
        }
        }}.toString();
    }
}

