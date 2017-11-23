package net.fnsco.web.controller.app.jo;

import net.fnsco.core.base.JO;

public class DiscoveryJO extends JO {
    private Integer  deviceType;
    private String version;
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }
}
