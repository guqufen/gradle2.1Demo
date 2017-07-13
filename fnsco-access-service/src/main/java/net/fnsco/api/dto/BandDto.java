package net.fnsco.api.dto;

public class BandDto {
    private String  mobile;
    private Integer appUserId;
    private String innerCode;
    private String merName;
    public String getMerName() {
        return merName;
    }
    public void setMerName(String merName) {
        this.merName = merName;
    }
    public String getInnerCode() {
        return innerCode;
    }
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getAppUserId() {
        return appUserId;
    }
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }
}
