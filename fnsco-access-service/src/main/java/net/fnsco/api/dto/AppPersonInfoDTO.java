package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class AppPersonInfoDTO extends DTO{
    private String  userName;
    private Integer sex;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
