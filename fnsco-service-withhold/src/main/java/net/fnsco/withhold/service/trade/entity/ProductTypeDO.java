package net.fnsco.withhold.service.trade.entity;

import java.util.Date;

public class ProductTypeDO {
    private Integer id;
    private String name;
    private String status;
    private Date modifyTime;

    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "ProductTypeDO [id=" + id + ", name=" + name + ", status=" + status + ", modifyTime=" + modifyTime + "]";
    }
}
