package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserBasicDO;
public class UserBasicProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_basic";

    public String update(Map<String, Object> params) {
        UserBasicDO userBasic = (UserBasicDO) params.get("userBasic");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userBasic.getCreateDate() != null) {
            SET("create_date=#{userBasic.createDate}");
        }
        if (userBasic.getModifyDate() != null) {
            SET("modify_date=#{userBasic.modifyDate}");
        }
        if (StringUtils.isNotBlank(userBasic.getPhone())){
            SET("phone=#{userBasic.phone}");
        }
        if (StringUtils.isNotBlank(userBasic.getEmail())){
            SET("email=#{userBasic.email}");
        }
        if (StringUtils.isNotBlank(userBasic.getPassword())){
            SET("password=#{userBasic.password}");
        }
        if (userBasic.getValid() != null) {
            SET("valid=#{userBasic.valid}");
        }
        if (StringUtils.isNotBlank(userBasic.getRole())){
            SET("role=#{userBasic.role}");
        }
        if (userBasic.getLocked() != null) {
            SET("locked=#{userBasic.locked}");
        }
        if (userBasic.getLockedDate() != null) {
            SET("locked_date=#{userBasic.lockedDate}");
        }
        if (StringUtils.isNotBlank(userBasic.getRegisterIp())){
            SET("register_ip=#{userBasic.registerIp}");
        }
        WHERE("id = #{userBasic.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserBasicDO userBasic = (UserBasicDO) params.get("userBasic");
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
        if (userBasic.getId() != null) {
            WHERE("id=#{userBasic.id}");
        }
        if (userBasic.getCreateDate() != null) {
            WHERE("create_date=#{userBasic.createDate}");
        }
        if (userBasic.getModifyDate() != null) {
            WHERE("modify_date=#{userBasic.modifyDate}");
        }
        if (StringUtils.isNotBlank(userBasic.getPhone())){
            WHERE("phone=#{userBasic.phone}");
        }
        if (StringUtils.isNotBlank(userBasic.getEmail())){
            WHERE("email=#{userBasic.email}");
        }
        if (StringUtils.isNotBlank(userBasic.getPassword())){
            WHERE("password=#{userBasic.password}");
        }
        if (userBasic.getValid() != null) {
            WHERE("valid=#{userBasic.valid}");
        }
        if (StringUtils.isNotBlank(userBasic.getRole())){
            WHERE("role=#{userBasic.role}");
        }
        if (userBasic.getLocked() != null) {
            WHERE("locked=#{userBasic.locked}");
        }
        if (userBasic.getLockedDate() != null) {
            WHERE("locked_date=#{userBasic.lockedDate}");
        }
        if (StringUtils.isNotBlank(userBasic.getRegisterIp())){
            WHERE("register_ip=#{userBasic.registerIp}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserBasicDO userBasic = (UserBasicDO) params.get("userBasic");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userBasic.getId() != null) {
            WHERE("id=#{userBasic.id}");
        }
        if (userBasic.getCreateDate() != null) {
            WHERE("create_date=#{userBasic.createDate}");
        }
        if (userBasic.getModifyDate() != null) {
            WHERE("modify_date=#{userBasic.modifyDate}");
        }
        if (StringUtils.isNotBlank(userBasic.getPhone())){
            WHERE("phone=#{userBasic.phone}");
        }
        if (StringUtils.isNotBlank(userBasic.getEmail())){
            WHERE("email=#{userBasic.email}");
        }
        if (StringUtils.isNotBlank(userBasic.getPassword())){
            WHERE("password=#{userBasic.password}");
        }
        if (userBasic.getValid() != null) {
            WHERE("valid=#{userBasic.valid}");
        }
        if (StringUtils.isNotBlank(userBasic.getRole())){
            WHERE("role=#{userBasic.role}");
        }
        if (userBasic.getLocked() != null) {
            WHERE("locked=#{userBasic.locked}");
        }
        if (userBasic.getLockedDate() != null) {
            WHERE("locked_date=#{userBasic.lockedDate}");
        }
        if (StringUtils.isNotBlank(userBasic.getRegisterIp())){
            WHERE("register_ip=#{userBasic.registerIp}");
        }
        }}.toString();
    }
}

