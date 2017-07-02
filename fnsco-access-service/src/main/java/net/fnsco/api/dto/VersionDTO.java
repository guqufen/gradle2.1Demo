package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class VersionDTO extends DTO{
    private String version;
    private String appType;
    private String deviceId;
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getAppType() {
        return appType;
    }
    public void setAppType(String appType) {
        this.appType = appType;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
   
}
