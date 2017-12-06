package net.fnsco.order.api.dto;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.DTO;

public class VersionDTO extends DTO {
	@ApiModelProperty(value ="版本号")
    private String  version;
	@ApiModelProperty(value ="类型 1:安卓/2: IOS")
    private Integer appType;
	@ApiModelProperty(value ="deviceId")
    private String  deviceId;
	@ApiModelProperty(value ="app编号,手动分配")
    private String  appCode;

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

    /**
     * appType
     *
     * @return  the appType
     * @since   CodingExample Ver 1.0
    */

    public Integer getAppType() {
        return appType;
    }

    /**
     * appType
     *
     * @param   appType    the appType to set
     * @since   CodingExample Ver 1.0
     */

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
