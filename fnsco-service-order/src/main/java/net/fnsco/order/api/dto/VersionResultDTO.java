package net.fnsco.order.api.dto;

import io.swagger.annotations.ApiModelProperty;

public class VersionResultDTO {
	@ApiModelProperty(value = "强制升级 0否 1是",example = "强制升级 0否 1是")
    private String  forceUpdate;
	@ApiModelProperty(value = "版本号",example = "强制升级 0否 1是")
    private String  version;
	@ApiModelProperty(value = "下载地址",example = "下载地址")
    private String  downloadUrl1;
	@ApiModelProperty(value = "下载地址2",example = "下载地址2")
    private String  downloadUrl2;
	@ApiModelProperty(value = "是否需要更新",example = "是否需要更新")
    private Boolean isUpdate;
	@ApiModelProperty(value = "说明",example = "说明")
    private String  remark;
	@ApiModelProperty(value = "版本说明",example = "版本说明")
    private String  versionDesc;
    
    /**
     * versionDesc
     *
     * @return  the versionDesc
     * @since   CodingExample Ver 1.0
    */
    
    public String getVersionDesc() {
        return versionDesc;
    }

    /**
     * versionDesc
     *
     * @param   versionDesc    the versionDesc to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    /**
     * remark
     *
     * @return  the remark
     * @since   CodingExample Ver 1.0
    */
    
    public String getRemark() {
        return remark;
    }

    /**
     * remark
     *
     * @param   remark    the remark to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * forceUpdate
     *
     * @return  the forceUpdate
     * @since   CodingExample Ver 1.0
    */

    public String getForceUpdate() {
        return forceUpdate;
    }

    /**
     * forceUpdate
     *
     * @param   forceUpdate    the forceUpdate to set
     * @since   CodingExample Ver 1.0
     */

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    /**
     * isUpdate
     *
     * @return  the isUpdate
     * @since   CodingExample Ver 1.0
    */

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    /**
     * isUpdate
     *
     * @param   isUpdate    the isUpdate to set
     * @since   CodingExample Ver 1.0
     */

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    /**
     * version
     *
     * @return  the version
     * @since   CodingExample Ver 1.0
    */

    public String getVersion() {
        return version;
    }

    /**
     * version
     *
     * @param   version    the version to set
     * @since   CodingExample Ver 1.0
     */

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * downloadUrl1
     *
     * @return  the downloadUrl1
     * @since   CodingExample Ver 1.0
    */

    public String getDownloadUrl1() {
        return downloadUrl1;
    }

    /**
     * downloadUrl1
     *
     * @param   downloadUrl1    the downloadUrl1 to set
     * @since   CodingExample Ver 1.0
     */

    public void setDownloadUrl1(String downloadUrl1) {
        this.downloadUrl1 = downloadUrl1;
    }

    /**
     * downloadUrl2
     *
     * @return  the downloadUrl2
     * @since   CodingExample Ver 1.0
    */

    public String getDownloadUrl2() {
        return downloadUrl2;
    }

    /**
     * downloadUrl2
     *
     * @param   downloadUrl2    the downloadUrl2 to set
     * @since   CodingExample Ver 1.0
     */

    public void setDownloadUrl2(String downloadUrl2) {
        this.downloadUrl2 = downloadUrl2;
    }

}
