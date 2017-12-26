package net.fnsco.risk.service.product.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.risk.service.product.entity.ProductDO;
public class ProductProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "risk_product";

    public String update(Map<String, Object> params) {
        ProductDO product = (ProductDO) params.get("product");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (product.getAgentId() != null) {
            SET("agent_id=#{product.agentId}");
        }
        if (StringUtils.isNotBlank(product.getName())){
            SET("name=#{product.name}");
        }
        if (StringUtils.isNotBlank(product.getAmountMin())){
            SET("amount_min=#{product.amountMin}");
        }
        if (StringUtils.isNotBlank(product.getAmountMax())){
            SET("amount_max=#{product.amountMax}");
        }
        if (StringUtils.isNotBlank(product.getRateMin())){
            SET("rate_min=#{product.rateMin}");
        }
        if (StringUtils.isNotBlank(product.getRateMax())){
            SET("rate_max=#{product.rateMax}");
        }
        if (StringUtils.isNotBlank(product.getCycle())){
            SET("cycle=#{product.cycle}");
        }
        if (StringUtils.isNotBlank(product.getDescription())){
            SET("description=#{product.description}");
        }
        if (product.getPayAbilityMin() != null) {
            SET("pay_ability_min=#{product.payAbilityMin}");
        }
        if (product.getCreateTime() != null) {
            SET("create_time=#{product.createTime}");
        }
        if (product.getLastModifyTime() != null) {
            SET("last_modify_time=#{product.lastModifyTime}");
        }
        if (StringUtils.isNotBlank(product.getStatus())){
            SET("status=#{product.status}");
        }
        WHERE("id = #{product.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ProductDO product = (ProductDO) params.get("product");
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
        if (product.getId() != null) {
            WHERE("id=#{product.id}");
        }
        if (product.getAgentId() != null) {
            WHERE("agent_id=#{product.agentId}");
        }
        if (StringUtils.isNotBlank(product.getName())){
            WHERE("name=#{product.name}");
        }
        if (StringUtils.isNotBlank(product.getAmountMin())){
            WHERE("amount_min=#{product.amountMin}");
        }
        if (StringUtils.isNotBlank(product.getAmountMax())){
            WHERE("amount_max=#{product.amountMax}");
        }
        if (StringUtils.isNotBlank(product.getRateMin())){
            WHERE("rate_min=#{product.rateMin}");
        }
        if (StringUtils.isNotBlank(product.getRateMax())){
            WHERE("rate_max=#{product.rateMax}");
        }
        if (StringUtils.isNotBlank(product.getCycle())){
            WHERE("cycle=#{product.cycle}");
        }
        if (StringUtils.isNotBlank(product.getDescription())){
            WHERE("description=#{product.description}");
        }
        if (product.getPayAbilityMin() != null) {
            WHERE("pay_ability_min=#{product.payAbilityMin}");
        }
        if (product.getCreateTime() != null) {
            WHERE("create_time=#{product.createTime}");
        }
        if (product.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{product.lastModifyTime}");
        }
        if (StringUtils.isNotBlank(product.getStatus())){
            WHERE("status=#{product.status}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ProductDO product = (ProductDO) params.get("product");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (product.getId() != null) {
            WHERE("id=#{product.id}");
        }
        if (product.getAgentId() != null) {
            WHERE("agent_id=#{product.agentId}");
        }
        if (StringUtils.isNotBlank(product.getName())){
            WHERE("name=#{product.name}");
        }
        if (StringUtils.isNotBlank(product.getAmountMin())){
            WHERE("amount_min=#{product.amountMin}");
        }
        if (StringUtils.isNotBlank(product.getAmountMax())){
            WHERE("amount_max=#{product.amountMax}");
        }
        if (StringUtils.isNotBlank(product.getRateMin())){
            WHERE("rate_min=#{product.rateMin}");
        }
        if (StringUtils.isNotBlank(product.getRateMax())){
            WHERE("rate_max=#{product.rateMax}");
        }
        if (StringUtils.isNotBlank(product.getCycle())){
            WHERE("cycle=#{product.cycle}");
        }
        if (StringUtils.isNotBlank(product.getDescription())){
            WHERE("description=#{product.description}");
        }
        if (product.getPayAbilityMin() != null) {
            WHERE("pay_ability_min=#{product.payAbilityMin}");
        }
        if (product.getCreateTime() != null) {
            WHERE("create_time=#{product.createTime}");
        }
        if (product.getLastModifyTime() != null) {
            WHERE("last_modify_time=#{product.lastModifyTime}");
        }
        if (StringUtils.isNotBlank(product.getStatus())){
            WHERE("status=#{product.status}");
        }
        }}.toString();
    }
}

