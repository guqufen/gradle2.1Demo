package net.fnsco.trading.service.merchant.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.trading.service.merchant.entity.AppUserMerchantDO;

import org.apache.commons.lang3.StringUtils;
public class AppUserMerchantProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "u_app_user_merchant";

    public String update(Map<String, Object> params) {
        AppUserMerchantDO appUserMerchant = (AppUserMerchantDO) params.get("appUserMerchant");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(appUserMerchant.getRoleId())){
            SET("role_id=#{appUserMerchant.roleId}");
        }
        if (StringUtils.isNotBlank(appUserMerchant.getInnerCode())){
            SET("inner_code=#{appUserMerchant.innerCode}");
        }
        if (appUserMerchant.getAppUserId() != null) {
            SET("app_user_id=#{appUserMerchant.appUserId}");
        }
        if (appUserMerchant.getModefyTime() != null) {
            SET("modefy_time=#{appUserMerchant.modefyTime}");
        }
        WHERE("id = #{appUserMerchant.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppUserMerchantDO appUserMerchant = (AppUserMerchantDO) params.get("appUserMerchant");
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
        if (appUserMerchant.getId() != null) {
            WHERE("id=#{appUserMerchant.id}");
        }
        if (StringUtils.isNotBlank(appUserMerchant.getRoleId())){
            WHERE("role_id=#{appUserMerchant.roleId}");
        }
        if (StringUtils.isNotBlank(appUserMerchant.getInnerCode())){
            WHERE("inner_code=#{appUserMerchant.innerCode}");
        }
        if (appUserMerchant.getAppUserId() != null) {
            WHERE("app_user_id=#{appUserMerchant.appUserId}");
        }
        if (appUserMerchant.getModefyTime() != null) {
            WHERE("modefy_time=#{appUserMerchant.modefyTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppUserMerchantDO appUserMerchant = (AppUserMerchantDO) params.get("appUserMerchant");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appUserMerchant.getId() != null) {
            WHERE("id=#{appUserMerchant.id}");
        }
        if (StringUtils.isNotBlank(appUserMerchant.getRoleId())){
            WHERE("role_id=#{appUserMerchant.roleId}");
        }
        if (StringUtils.isNotBlank(appUserMerchant.getInnerCode())){
            WHERE("inner_code=#{appUserMerchant.innerCode}");
        }
        if (appUserMerchant.getAppUserId() != null) {
            WHERE("app_user_id=#{appUserMerchant.appUserId}");
        }
        if (appUserMerchant.getModefyTime() != null) {
            WHERE("modefy_time=#{appUserMerchant.modefyTime}");
        }
        }}.toString();
    }
}

