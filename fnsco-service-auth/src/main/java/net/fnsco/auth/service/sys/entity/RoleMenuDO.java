package net.fnsco.auth.service.sys.entity;

public class RoleMenuDO {

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId2) {
		this.roleId = roleId2;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId2) {
		this.menuId = menuId2;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + "]";
	}
}