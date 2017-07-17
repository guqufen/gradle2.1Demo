package net.fnsco.api.dto;

import java.util.Date;
import java.util.List;

import net.fnsco.core.base.DTO;

public class AppPeopleDTO extends DTO{
    private String roleId;
    private String innerCode;
    private Integer id;
    private Integer appUserId;
    private Date modefyTime;
    public Date getModefyTime() {
        return modefyTime;
    }
    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getInnerCode() {
        return innerCode;
    }
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAppUserId() {
        return appUserId;
    }
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    
}
