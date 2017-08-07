package net.fnsco.api.doc.service.user.dao.helper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import net.fnsco.api.doc.service.user.entity.UserCubeDO;
public class UserCubeProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "t_user_cube";

    public String update(Map<String, Object> params) {
        UserCubeDO userCube = (UserCubeDO) params.get("userCube");
        return new SQL() {{
        UPDATE(TABLE_NAME);
        if (userCube.getCreateDate() != null) {
            SET("create_date=#{userCube.createDate}");
        }
        if (userCube.getModifyDate() != null) {
            SET("modify_date=#{userCube.modifyDate}");
        }
        if (userCube.getTotalRegistCount() != null) {
            SET("total_regist_count=#{userCube.totalRegistCount}");
        }
        if (userCube.getDayRegistCount() != null) {
            SET("day_regist_count=#{userCube.dayRegistCount}");
        }
        if (userCube.getDayLoginCount() != null) {
            SET("day_login_count=#{userCube.dayLoginCount}");
        }
        if (userCube.getDayOldLoginCount() != null) {
            SET("day_old_login_count=#{userCube.dayOldLoginCount}");
        }
        if (userCube.getTotalProjCount() != null) {
            SET("total_proj_count=#{userCube.totalProjCount}");
        }
        if (userCube.getDayProjCount() != null) {
            SET("day_proj_count=#{userCube.dayProjCount}");
        }
        WHERE("id = #{userCube.id}");
        }}.toString();
    }

    public String pageList(Map<String, Object> params) {
        UserCubeDO userCube = (UserCubeDO) params.get("userCube");
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
        if (userCube.getId() != null) {
            WHERE("id=#{userCube.id}");
        }
        if (userCube.getCreateDate() != null) {
            WHERE("create_date=#{userCube.createDate}");
        }
        if (userCube.getModifyDate() != null) {
            WHERE("modify_date=#{userCube.modifyDate}");
        }
        if (userCube.getTotalRegistCount() != null) {
            WHERE("total_regist_count=#{userCube.totalRegistCount}");
        }
        if (userCube.getDayRegistCount() != null) {
            WHERE("day_regist_count=#{userCube.dayRegistCount}");
        }
        if (userCube.getDayLoginCount() != null) {
            WHERE("day_login_count=#{userCube.dayLoginCount}");
        }
        if (userCube.getDayOldLoginCount() != null) {
            WHERE("day_old_login_count=#{userCube.dayOldLoginCount}");
        }
        if (userCube.getTotalProjCount() != null) {
            WHERE("total_proj_count=#{userCube.totalProjCount}");
        }
        if (userCube.getDayProjCount() != null) {
            WHERE("day_proj_count=#{userCube.dayProjCount}");
        }
        ORDER_BY("id desc limit " + start + ", " + limit );
        }}.toString();
    }

    public String pageListCount(Map<String, Object> params) {
        UserCubeDO userCube = (UserCubeDO) params.get("userCube");
        return new SQL() {{
        SELECT("count(1)");
        FROM(TABLE_NAME);
        if (userCube.getId() != null) {
            WHERE("id=#{userCube.id}");
        }
        if (userCube.getCreateDate() != null) {
            WHERE("create_date=#{userCube.createDate}");
        }
        if (userCube.getModifyDate() != null) {
            WHERE("modify_date=#{userCube.modifyDate}");
        }
        if (userCube.getTotalRegistCount() != null) {
            WHERE("total_regist_count=#{userCube.totalRegistCount}");
        }
        if (userCube.getDayRegistCount() != null) {
            WHERE("day_regist_count=#{userCube.dayRegistCount}");
        }
        if (userCube.getDayLoginCount() != null) {
            WHERE("day_login_count=#{userCube.dayLoginCount}");
        }
        if (userCube.getDayOldLoginCount() != null) {
            WHERE("day_old_login_count=#{userCube.dayOldLoginCount}");
        }
        if (userCube.getTotalProjCount() != null) {
            WHERE("total_proj_count=#{userCube.totalProjCount}");
        }
        if (userCube.getDayProjCount() != null) {
            WHERE("day_proj_count=#{userCube.dayProjCount}");
        }
        }}.toString();
    }
}

