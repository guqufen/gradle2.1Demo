package net.fnsco.auth.service.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleDO implements Serializable {

	private static final Long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 部门ID
	 */
	private Integer deptId;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 角色和菜单ID对应列表
	 */
	private List<Long> menuIdList;

	/**
	 * 角色和部门id对应列表
	 */
	private List<Long> deptIdList;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<Long> getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(List<Long> deptIdList) {
		this.deptIdList = deptIdList;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}
