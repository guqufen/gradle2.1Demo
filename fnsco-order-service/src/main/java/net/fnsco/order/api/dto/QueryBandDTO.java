package net.fnsco.order.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class QueryBandDTO extends DTO{
    private String roleId;
    private String innerCode;
    private Integer id;
    private Integer appUserId;
    public Integer getAppUserId() {
        return appUserId;
    }
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }
    private String merName;
    public String getMerName() {
        return merName;
    }
    public void setMerName(String merName) {
        this.merName = merName;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getInnerCode() {
        return innerCode;
    }
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
}
