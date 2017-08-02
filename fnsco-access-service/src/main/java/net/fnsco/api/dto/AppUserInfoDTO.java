package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class AppUserInfoDTO extends DTO{
    private String  userName;
    private Integer sex;
    private String moblie;
    private String  realName;
    public String getMoblie() {
        return moblie;
    }
    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getHeadImagePath() {
        return headImagePath;
    }
    public void setHeadImagePath(String headImagePath) {
        this.headImagePath = headImagePath;
    }
    private String headImagePath;
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
