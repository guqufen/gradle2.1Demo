package net.fnsco.trading.service.oilcard.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.trading.service.oilcard.entity.OilCardApplyDO;
public class OilCardApplyProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "act_oil_card_apply";

    public String update(Map<String, Object> params) {
        OilCardApplyDO oilCardApply = (OilCardApplyDO) params.get("oilCardApply");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(oilCardApply.getInnerCode())){
            SET("inner_code=#{oilCardApply.innerCode}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getMobile())){
            SET("mobile=#{oilCardApply.mobile}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getName())){
            SET("name=#{oilCardApply.name}");
        }
        if (oilCardApply.getCreateTime() != null) {
            SET("create_time=#{oilCardApply.createTime}");
        }
        WHERE("id = #{oilCardApply.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        OilCardApplyDO oilCardApply = (OilCardApplyDO) params.get("oilCardApply");
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
        if (oilCardApply.getId() != null) {
            WHERE("id=#{oilCardApply.id}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getInnerCode())){
            WHERE("inner_code=#{oilCardApply.innerCode}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getMobile())){
            WHERE("mobile=#{oilCardApply.mobile}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getName())){
            WHERE("name=#{oilCardApply.name}");
        }
        if (oilCardApply.getCreateTime() != null) {
            WHERE("create_time=#{oilCardApply.createTime}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        OilCardApplyDO oilCardApply = (OilCardApplyDO) params.get("oilCardApply");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (oilCardApply.getId() != null) {
            WHERE("id=#{oilCardApply.id}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getInnerCode())){
            WHERE("inner_code=#{oilCardApply.innerCode}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getMobile())){
            WHERE("mobile=#{oilCardApply.mobile}");
        }
        if (StringUtils.isNotBlank(oilCardApply.getName())){
            WHERE("name=#{oilCardApply.name}");
        }
        if (oilCardApply.getCreateTime() != null) {
            WHERE("create_time=#{oilCardApply.createTime}");
        }
        }}.toString();
    }
}

