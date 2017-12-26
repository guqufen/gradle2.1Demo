package net.fnsco.risk.service.ability.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.ability.entity.MercPayAbilityDO;
public class MercPayAbilityProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_merc_pay_ability";

    public String update(Map<String, Object> params) {
        MercPayAbilityDO mercPayAbility = (MercPayAbilityDO) params.get("mercPayAbility");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(mercPayAbility.getEntityInnerCode())){
            SET("entity_inner_code=#{mercPayAbility.entityInnerCode}");
        }
        if (StringUtils.isNotBlank(mercPayAbility.getMonth())){
            SET("month=#{mercPayAbility.month}");
        }
        if (mercPayAbility.getPayAbility() != null) {
            SET("pay_ability=#{mercPayAbility.payAbility}");
        }
        WHERE("id = #{mercPayAbility.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        MercPayAbilityDO mercPayAbility = (MercPayAbilityDO) params.get("mercPayAbility");
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
        if (mercPayAbility.getId() != null) {
            WHERE("id=#{mercPayAbility.id}");
        }
        if (StringUtils.isNotBlank(mercPayAbility.getEntityInnerCode())){
            WHERE("entity_inner_code=#{mercPayAbility.entityInnerCode}");
        }
        if (StringUtils.isNotBlank(mercPayAbility.getMonth())){
            WHERE("month=#{mercPayAbility.month}");
        }
        if (mercPayAbility.getPayAbility() != null) {
            WHERE("pay_ability=#{mercPayAbility.payAbility}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        MercPayAbilityDO mercPayAbility = (MercPayAbilityDO) params.get("mercPayAbility");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (mercPayAbility.getId() != null) {
            WHERE("id=#{mercPayAbility.id}");
        }
        if (StringUtils.isNotBlank(mercPayAbility.getEntityInnerCode())){
            WHERE("entity_inner_code=#{mercPayAbility.entityInnerCode}");
        }
        if (StringUtils.isNotBlank(mercPayAbility.getMonth())){
            WHERE("month=#{mercPayAbility.month}");
        }
        if (mercPayAbility.getPayAbility() != null) {
            WHERE("pay_ability=#{mercPayAbility.payAbility}");
        }
        }}.toString();
    }
}

