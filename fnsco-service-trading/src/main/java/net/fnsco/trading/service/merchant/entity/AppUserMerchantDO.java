package net.fnsco.trading.service.merchant.entity;

import java.util.Date;

public class AppUserMerchantDO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 角色ID1店员2店主0未知
     */
    private String roleId;

    /**
     * 内部商务号
     */
    private String innerCode;

    /**
     * app用户ID
     */
    private Integer appUserId;

    /**
     * 绑定时间
     */
    private Date modefyTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Date getModefyTime() {
        return modefyTime;
    }

    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", roleId="+ roleId + ", innerCode="+ innerCode + ", appUserId="+ appUserId + ", modefyTime="+ modefyTime + "]";
    }
}