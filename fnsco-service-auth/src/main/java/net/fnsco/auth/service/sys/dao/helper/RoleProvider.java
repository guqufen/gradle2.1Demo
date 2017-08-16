package net.fnsco.auth.service.sys.dao.helper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.auth.service.sys.entity.RoleDO;

public class RoleProvider {

	private Logger              logger     = LoggerFactory.getLogger(this.getClass());

    private static final String TABLE_NAME = "sys_role";

	public String queryList(Map<String, Object> params) {
		RoleDO role = (RoleDO) params.get("role");
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

		return new SQL() {
			{
				SELECT("*");
				FROM(TABLE_NAME);
				if (role.getRoleId() != null) {
					WHERE("role_id=#{role.roleId}");
				}
				if (StringUtils.isNotBlank(role.getRoleName())) {
					WHERE("role_name like CONCAT('%',#{role.roleName},'%')");
				}
				if (role.getDeptId() != null) {
					WHERE("dept_id = #{role.deptId}");
				}
				ORDER_BY("role_id desc limit " + start + ", " + limit);
			}
		}.toString();
	}
	
	public String pageListCount(Map<String, Object> params) {
        RoleDO role = (RoleDO) params.get("role");
        return new SQL() {
            {
                SELECT("count(1)");
                FROM(TABLE_NAME);
                if (role.getRoleId() != null) {
					WHERE("role_id=#{role.roleId}");
				}
				if (StringUtils.isNotBlank(role.getRoleName())) {
					WHERE("role_name like CONCAT('%',#{role.roleName},'%')");
				}
				if (role.getDeptId() != null) {
					WHERE("dept_id = #{role.deptId}");
				}
            }
        }.toString();
    }
	
	public String update(Map<String, Object> params) {
		RoleDO roleDO = (RoleDO) params.get("roleDO");
		return new SQL() {
			{
				UPDATE(TABLE_NAME);
				if (StringUtils.isNotBlank(roleDO.getRoleName())) {
					SET("role_name=#{roleDO.roleName}");
				}
				if (StringUtils.isNotBlank(roleDO.getRemark())) {
					SET("remark=#{roleDO.remark}");
				}
				if (null != roleDO.getCreateTime()) {
					SET("create_time=#{roleDO.createTime}");
				}
				if (null != roleDO.getDeptId()) {
					SET("dept_id=#{roleDO.deptId}");
				}
				if (roleDO.getRoleId() != null) {
					WHERE("role_id=#{roleDO.roleId}");
				}
			}
		}.toString();
	}
}
