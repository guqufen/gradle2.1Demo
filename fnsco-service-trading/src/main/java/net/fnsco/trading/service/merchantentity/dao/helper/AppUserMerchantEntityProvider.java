package net.fnsco.trading.service.merchantentity.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.merchantentity.entity.AppUserMerchantEntityDO;
public class AppUserMerchantEntityProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "u_app_user_merchant_entity";

    public String update(Map<String, Object> params) {
        AppUserMerchantEntityDO appUserMerchantEntity = (AppUserMerchantEntityDO) params.get("appUserMerchantEntity");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(appUserMerchantEntity.getEntityInnerCode())){
            SET("entity_inner_code=#{appUserMerchantEntity.entityInnerCode}");
        }
        if (appUserMerchantEntity.getAppUserId() != null) {
            SET("app_user_id=#{appUserMerchantEntity.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getShopInnerCode())){
            SET("shop_inner_code=#{appUserMerchantEntity.shopInnerCode}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getRoleId())){
            SET("role_id=#{appUserMerchantEntity.roleId}");
        }
        if (appUserMerchantEntity.getModefyTime() != null) {
            SET("modefy_time=#{appUserMerchantEntity.modefyTime}");
        }
        WHERE("id = #{appUserMerchantEntity.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        AppUserMerchantEntityDO appUserMerchantEntity = (AppUserMerchantEntityDO) params.get("appUserMerchantEntity");
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
        if (appUserMerchantEntity.getId() != null) {
            WHERE("id=#{appUserMerchantEntity.id}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getEntityInnerCode())){
            WHERE("entity_inner_code=#{appUserMerchantEntity.entityInnerCode}");
        }
        if (appUserMerchantEntity.getAppUserId() != null) {
            WHERE("app_user_id=#{appUserMerchantEntity.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getShopInnerCode())){
            WHERE("shop_inner_code=#{appUserMerchantEntity.shopInnerCode}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getRoleId())){
            WHERE("role_id=#{appUserMerchantEntity.roleId}");
        }
        if (appUserMerchantEntity.getModefyTime() != null) {
            WHERE("modefy_time=#{appUserMerchantEntity.modefyTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        AppUserMerchantEntityDO appUserMerchantEntity = (AppUserMerchantEntityDO) params.get("appUserMerchantEntity");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (appUserMerchantEntity.getId() != null) {
            WHERE("id=#{appUserMerchantEntity.id}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getEntityInnerCode())){
            WHERE("entity_inner_code=#{appUserMerchantEntity.entityInnerCode}");
        }
        if (appUserMerchantEntity.getAppUserId() != null) {
            WHERE("app_user_id=#{appUserMerchantEntity.appUserId}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getShopInnerCode())){
            WHERE("shop_inner_code=#{appUserMerchantEntity.shopInnerCode}");
        }
        if (StringUtils.isNotBlank(appUserMerchantEntity.getRoleId())){
            WHERE("role_id=#{appUserMerchantEntity.roleId}");
        }
        if (appUserMerchantEntity.getModefyTime() != null) {
            WHERE("modefy_time=#{appUserMerchantEntity.modefyTime}");
        }
        }}.toString();
    }
}

