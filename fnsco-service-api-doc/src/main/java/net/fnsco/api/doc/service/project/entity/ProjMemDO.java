package net.fnsco.api.doc.service.project.entity;

import java.util.Date;

public class ProjMemDO {

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
     * 项目id
     */
    private Long projId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 
     */
    private String role;

    /**
     * 项目角色id
     */
    private Long projRoleId;



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

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getProjRoleId() {
        return projRoleId;
    }

    public void setProjRoleId(Long projRoleId) {
        this.projRoleId = projRoleId;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", projId="+ projId + ", userId="+ userId + ", role="+ role + ", projRoleId="+ projRoleId + "]";
    }
}