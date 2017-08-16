package net.fnsco.auth.service.sys.entity;

import java.util.Date;

public class RoleDO {
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 部门ID
	 */
	private Integer deptId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 时间的String类型
	 */
	private String createTimeSt;
	public String getCreateTimeSt() {
		return createTimeSt;
	}
	public void setCreateTimeSt(String createTimeSt) {
		this.createTimeSt = createTimeSt;
	}

	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
	@Override
	public String toString() {
		return "[roleId="+ roleId + ", roleName="+ roleName + ", remark="+ remark + ", deptId="+ deptId + ", createTime="+ createTime + "]";
	}
}