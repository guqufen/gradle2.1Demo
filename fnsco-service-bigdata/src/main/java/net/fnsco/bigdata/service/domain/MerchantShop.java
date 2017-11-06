package net.fnsco.bigdata.service.domain;

import java.util.Date;
/**
 * @desc  店铺实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月27日 上午9:49:47
 */
public class MerchantShop {
	private Integer id;

    private String entityInnerCode;

    private String shopInnerCode;

    private String shopName;

    private String area;

    private String address;

    private Date createTimer;

    private String createSource;

    private Integer createUserId;

    private Date lastModefyTimer;

    private Integer lastModefyUserId;

    private Integer status;

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
        this.entityInnerCode = entityInnerCode == null ? null : entityInnerCode.trim();
    }

    public String getShopInnerCode() {
        return shopInnerCode;
    }

    public void setShopInnerCode(String shopInnerCode) {
        this.shopInnerCode = shopInnerCode == null ? null : shopInnerCode.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreateTimer() {
        return createTimer;
    }

    public void setCreateTimer(Date createTimer) {
        this.createTimer = createTimer;
    }

    public String getCreateSource() {
        return createSource;
    }

    public void setCreateSource(String createSource) {
        this.createSource = createSource == null ? null : createSource.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getLastModefyTimer() {
        return lastModefyTimer;
    }

    public void setLastModefyTimer(Date lastModefyTimer) {
        this.lastModefyTimer = lastModefyTimer;
    }

    public Integer getLastModefyUserId() {
        return lastModefyUserId;
    }

    public void setLastModefyUserId(Integer lastModefyUserId) {
        this.lastModefyUserId = lastModefyUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}