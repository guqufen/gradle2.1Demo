package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class BandListDTO extends DTO{
    private String  mobile;
    private Integer userId;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
