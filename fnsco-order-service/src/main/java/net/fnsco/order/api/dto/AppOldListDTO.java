package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

public class AppOldListDTO extends DTO{
    private String mobile;
    private String merName;
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMerName() {
        return merName;
    }
    public void setMerName(String merName) {
        this.merName = merName;
    }
}
