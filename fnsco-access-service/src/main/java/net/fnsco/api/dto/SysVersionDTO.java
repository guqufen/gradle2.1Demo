package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

public class SysVersionDTO extends DTO{
    private String version;
    private String appType;
    private String deviceId;
    private String appCode;
    
    /**
     * appCode
     *
     * @return  the appCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getAppCode() {
        return appCode;
    }
    /**
     * appCode
     *
     * @param   appCode    the appCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
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
