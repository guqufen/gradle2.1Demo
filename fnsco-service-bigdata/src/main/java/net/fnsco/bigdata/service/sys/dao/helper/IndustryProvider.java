package net.fnsco.bigdata.service.sys.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.bigdata.service.sys.entity.IndustryDO;

import org.apache.commons.lang3.StringUtils;
public class IndustryProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_industry";

    public String update(Map<String, Object> params) {
        IndustryDO industry = (IndustryDO) params.get("industry");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(industry.getCode())){
            SET("code=#{industry.code}");
        }
        if (StringUtils.isNotBlank(industry.getBusinessForm())){
            SET("business_form=#{industry.businessForm}");
        }
        if (StringUtils.isNotBlank(industry.getFirst())){
            SET("first=#{industry.first}");
        }
        if (StringUtils.isNotBlank(industry.getThird())){
            SET("third=#{industry.third}");
        }
        if (StringUtils.isNotBlank(industry.getFourth())){
            SET("fourth=#{industry.fourth}");
        }
        if (industry.getStatus() != null) {
            SET("status=#{industry.status}");
        }
        if (StringUtils.isNotBlank(industry.getRemark())){
            SET("remark=#{industry.remark}");
        }
        WHERE("id = #{industry.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        IndustryDO industry = (IndustryDO) params.get("industry");
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
        if (industry.getId() != null) {
            WHERE("id=#{industry.id}");
        }
        if (StringUtils.isNotBlank(industry.getCode())){
            WHERE("code=#{industry.code}");
        }
        if (StringUtils.isNotBlank(industry.getBusinessForm())){
            WHERE("business_form=#{industry.businessForm}");
        }
        if (StringUtils.isNotBlank(industry.getFirst())){
            WHERE("first=#{industry.first}");
        }
        if (StringUtils.isNotBlank(industry.getThird())){
            WHERE("third=#{industry.third}");
        }
        if (StringUtils.isNotBlank(industry.getFourth())){
            WHERE("fourth=#{industry.fourth}");
        }
        if (industry.getStatus() != null) {
            WHERE("status=#{industry.status}");
        }
        if (StringUtils.isNotBlank(industry.getRemark())){
            WHERE("remark=#{industry.remark}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        IndustryDO industry = (IndustryDO) params.get("industry");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (industry.getId() != null) {
            WHERE("id=#{industry.id}");
        }
        if (StringUtils.isNotBlank(industry.getCode())){
            WHERE("code=#{industry.code}");
        }
        if (StringUtils.isNotBlank(industry.getBusinessForm())){
            WHERE("business_form=#{industry.businessForm}");
        }
        if (StringUtils.isNotBlank(industry.getFirst())){
            WHERE("first=#{industry.first}");
        }
        if (StringUtils.isNotBlank(industry.getThird())){
            WHERE("third=#{industry.third}");
        }
        if (StringUtils.isNotBlank(industry.getFourth())){
            WHERE("fourth=#{industry.fourth}");
        }
        if (industry.getStatus() != null) {
            WHERE("status=#{industry.status}");
        }
        if (StringUtils.isNotBlank(industry.getRemark())){
            WHERE("remark=#{industry.remark}");
        }
        }}.toString();
    }
    
    public String pageNameList(Map<String, Object> params) {
        IndustryDO industry = (IndustryDO) params.get("industry");
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
        if (StringUtils.isNotBlank(industry.getBusinessForm())){
            WHERE("CONCAT_WS(business_form,first,third,fourth) LIKE CONCAT('%', #{industry.businessForm}, '%')");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageNameListCount(Map<String, Object> params) {
        IndustryDO industry = (IndustryDO) params.get("industry");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (StringUtils.isNotBlank(industry.getBusinessForm())){
            WHERE("CONCAT_WS(business_form,first,third,fourth) LIKE CONCAT('%', #{industry.businessForm}, '%')");
        }
        }}.toString();
    }
}

