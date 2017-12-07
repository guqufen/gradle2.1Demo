package net.fnsco.trading.service.merchantentity.entity;

import java.util.Date;

public class AppUserMerchantEntityDO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 内部商务号
     */
    private String entityInnerCode;

    /**
     * app用户ID
     */
    private Integer appUserId;

    /**
     * 店铺ID
     */
    private String shopInnerCode;

    /**
     * 角色ID1店员2店主0未知
     */
    private String roleId;

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

    public String getEntityInnerCode() {
        return entityInnerCode;
    }

    public void setEntityInnerCode(String entityInnerCode) {
        this.entityInnerCode = entityInnerCode;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getShopInnerCode() {
        return shopInnerCode;
    }

    public void setShopInnerCode(String shopInnerCode) {
        this.shopInnerCode = shopInnerCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getModefyTime() {
        return modefyTime;
    }

    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", entityInnerCode="+ entityInnerCode + ", appUserId="+ appUserId + ", shopInnerCode="+ shopInnerCode + ", roleId="+ roleId + ", modefyTime="+ modefyTime + "]";
    }
}