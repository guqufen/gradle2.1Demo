package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

//输出店员的手机号和userId
public class BandListDTO extends DTO{
    private String  mobile;
    private Integer userId;
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
