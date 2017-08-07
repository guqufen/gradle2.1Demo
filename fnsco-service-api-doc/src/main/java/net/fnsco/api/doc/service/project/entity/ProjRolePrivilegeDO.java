package net.fnsco.api.doc.service.project.entity;

import java.util.Date;

public class ProjRolePrivilegeDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 项目角色id
     */
    private Long projRoleId;

    /**
     * 项目权限id
     */
    private Long projPrivilegeId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getProjRoleId() {
        return projRoleId;
    }

    public void setProjRoleId(Long projRoleId) {
        this.projRoleId = projRoleId;
    }

    public Long getProjPrivilegeId() {
        return projPrivilegeId;
    }

    public void setProjPrivilegeId(Long projPrivilegeId) {
        this.projPrivilegeId = projPrivilegeId;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", projRoleId="+ projRoleId + ", projPrivilegeId="+ projPrivilegeId + "]";
    }
}