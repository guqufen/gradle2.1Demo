package net.fnsco.auth.service.sys.entity;

import java.util.Date;
import java.util.List;

public class RoleDO {
	/**
	 * 角色ID
	 */
	private Integer roleId;

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
	private List<Integer> menuIdList;

	/**
	 * 角色和部门id对应列表
	 */
	private List<Integer> deptIdList;

	/**
	 * 时间的String类型
	 */
	private String createTimeStr;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Integer> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Integer> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public List<Integer> getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(List<Integer> deptIdList) {
		this.deptIdList = deptIdList;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Override
	public String toString() {
		return "[roleId=" + roleId + ", roleName=" + roleName + ", remark=" + remark + ", deptId=" + deptId
				+ ", createTime=" + createTime + "]";
	}
}
