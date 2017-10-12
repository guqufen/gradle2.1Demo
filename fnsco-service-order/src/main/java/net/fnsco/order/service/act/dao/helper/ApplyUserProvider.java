package net.fnsco.order.service.act.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.order.service.act.entity.ApplyUserDO;
public class ApplyUserProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "act_apply_user";

    public String update(Map<String, Object> params) {
        ApplyUserDO applyUser = (ApplyUserDO) params.get("applyUser");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (StringUtils.isNotBlank(applyUser.getUserName())){
            SET("user_name=#{applyUser.userName}");
        }
        if (StringUtils.isNotBlank(applyUser.getContactNum())){
            SET("contact_num=#{applyUser.contactNum}");
        }
        if (StringUtils.isNotBlank(applyUser.getMercName())){
            SET("merc_name=#{applyUser.mercName}");
        }
        if (StringUtils.isNotBlank(applyUser.getFromUserName())){
            SET("from_user_name=#{applyUser.fromUserName}");
        }
        if (StringUtils.isNotBlank(applyUser.getFromUserContactNum())){
            SET("from_user_contact_num=#{applyUser.fromUserContactNum}");
        }
        WHERE("id = #{applyUser.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        ApplyUserDO applyUser = (ApplyUserDO) params.get("applyUser");
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
        if (applyUser.getId() != null) {
            WHERE("id=#{applyUser.id}");
        }
        if (StringUtils.isNotBlank(applyUser.getUserName())){
            WHERE("user_name=#{applyUser.userName}");
        }
        if (StringUtils.isNotBlank(applyUser.getContactNum())){
            WHERE("contact_num=#{applyUser.contactNum}");
        }
        if (StringUtils.isNotBlank(applyUser.getMercName())){
            WHERE("merc_name=#{applyUser.mercName}");
        }
        if (StringUtils.isNotBlank(applyUser.getFromUserName())){
            WHERE("from_user_name=#{applyUser.fromUserName}");
        }
        if (StringUtils.isNotBlank(applyUser.getFromUserContactNum())){
            WHERE("from_user_contact_num=#{applyUser.fromUserContactNum}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        ApplyUserDO applyUser = (ApplyUserDO) params.get("applyUser");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (applyUser.getId() != null) {
            WHERE("id=#{applyUser.id}");
        }
        if (StringUtils.isNotBlank(applyUser.getUserName())){
            WHERE("user_name=#{applyUser.userName}");
        }
        if (StringUtils.isNotBlank(applyUser.getContactNum())){
            WHERE("contact_num=#{applyUser.contactNum}");
        }
        if (StringUtils.isNotBlank(applyUser.getMercName())){
            WHERE("merc_name=#{applyUser.mercName}");
        }
        if (StringUtils.isNotBlank(applyUser.getFromUserName())){
            WHERE("from_user_name=#{applyUser.fromUserName}");
        }
        if (StringUtils.isNotBlank(applyUser.getFromUserContactNum())){
            WHERE("from_user_contact_num=#{applyUser.fromUserContactNum}");
        }
        }}.toString();
    }
}

