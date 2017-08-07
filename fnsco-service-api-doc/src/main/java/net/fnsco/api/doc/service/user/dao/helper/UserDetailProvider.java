package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserDetailDO;
public class UserDetailProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_detail";

    public String update(Map<String, Object> params) {
        UserDetailDO userDetail = (UserDetailDO) params.get("userDetail");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userDetail.getCreateDate() != null) {
            SET("create_date=#{userDetail.createDate}");
        }
        if (userDetail.getModifyDate() != null) {
            SET("modify_date=#{userDetail.modifyDate}");
        }
        if (userDetail.getUserId() != null) {
            SET("user_id=#{userDetail.userId}");
        }
        if (StringUtils.isNotBlank(userDetail.getAddress())){
            SET("address=#{userDetail.address}");
        }
        if (userDetail.getAreaId() != null) {
            SET("area_id=#{userDetail.areaId}");
        }
        if (userDetail.getBirth() != null) {
            SET("birth=#{userDetail.birth}");
        }
        if (StringUtils.isNotBlank(userDetail.getCountry())){
            SET("country=#{userDetail.country}");
        }
        if (StringUtils.isNotBlank(userDetail.getGender())){
            SET("gender=#{userDetail.gender}");
        }
        if (StringUtils.isNotBlank(userDetail.getName())){
            SET("name=#{userDetail.name}");
        }
        if (StringUtils.isNotBlank(userDetail.getNickName())){
            SET("nick_name=#{userDetail.nickName}");
        }
        if (StringUtils.isNotBlank(userDetail.getZipCode())){
            SET("zip_code=#{userDetail.zipCode}");
        }
        if (StringUtils.isNotBlank(userDetail.getHeadUrl())){
            SET("head_url=#{userDetail.headUrl}");
        }
        if (StringUtils.isNotBlank(userDetail.getUniversity())){
            SET("university=#{userDetail.university}");
        }
        WHERE("id = #{userDetail.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserDetailDO userDetail = (UserDetailDO) params.get("userDetail");
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
        if (userDetail.getId() != null) {
            WHERE("id=#{userDetail.id}");
        }
        if (userDetail.getCreateDate() != null) {
            WHERE("create_date=#{userDetail.createDate}");
        }
        if (userDetail.getModifyDate() != null) {
            WHERE("modify_date=#{userDetail.modifyDate}");
        }
        if (userDetail.getUserId() != null) {
            WHERE("user_id=#{userDetail.userId}");
        }
        if (StringUtils.isNotBlank(userDetail.getAddress())){
            WHERE("address=#{userDetail.address}");
        }
        if (userDetail.getAreaId() != null) {
            WHERE("area_id=#{userDetail.areaId}");
        }
        if (userDetail.getBirth() != null) {
            WHERE("birth=#{userDetail.birth}");
        }
        if (StringUtils.isNotBlank(userDetail.getCountry())){
            WHERE("country=#{userDetail.country}");
        }
        if (StringUtils.isNotBlank(userDetail.getGender())){
            WHERE("gender=#{userDetail.gender}");
        }
        if (StringUtils.isNotBlank(userDetail.getName())){
            WHERE("name=#{userDetail.name}");
        }
        if (StringUtils.isNotBlank(userDetail.getNickName())){
            WHERE("nick_name=#{userDetail.nickName}");
        }
        if (StringUtils.isNotBlank(userDetail.getZipCode())){
            WHERE("zip_code=#{userDetail.zipCode}");
        }
        if (StringUtils.isNotBlank(userDetail.getHeadUrl())){
            WHERE("head_url=#{userDetail.headUrl}");
        }
        if (StringUtils.isNotBlank(userDetail.getUniversity())){
            WHERE("university=#{userDetail.university}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserDetailDO userDetail = (UserDetailDO) params.get("userDetail");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userDetail.getId() != null) {
            WHERE("id=#{userDetail.id}");
        }
        if (userDetail.getCreateDate() != null) {
            WHERE("create_date=#{userDetail.createDate}");
        }
        if (userDetail.getModifyDate() != null) {
            WHERE("modify_date=#{userDetail.modifyDate}");
        }
        if (userDetail.getUserId() != null) {
            WHERE("user_id=#{userDetail.userId}");
        }
        if (StringUtils.isNotBlank(userDetail.getAddress())){
            WHERE("address=#{userDetail.address}");
        }
        if (userDetail.getAreaId() != null) {
            WHERE("area_id=#{userDetail.areaId}");
        }
        if (userDetail.getBirth() != null) {
            WHERE("birth=#{userDetail.birth}");
        }
        if (StringUtils.isNotBlank(userDetail.getCountry())){
            WHERE("country=#{userDetail.country}");
        }
        if (StringUtils.isNotBlank(userDetail.getGender())){
            WHERE("gender=#{userDetail.gender}");
        }
        if (StringUtils.isNotBlank(userDetail.getName())){
            WHERE("name=#{userDetail.name}");
        }
        if (StringUtils.isNotBlank(userDetail.getNickName())){
            WHERE("nick_name=#{userDetail.nickName}");
        }
        if (StringUtils.isNotBlank(userDetail.getZipCode())){
            WHERE("zip_code=#{userDetail.zipCode}");
        }
        if (StringUtils.isNotBlank(userDetail.getHeadUrl())){
            WHERE("head_url=#{userDetail.headUrl}");
        }
        if (StringUtils.isNotBlank(userDetail.getUniversity())){
            WHERE("university=#{userDetail.university}");
        }
        }}.toString();
    }
}

