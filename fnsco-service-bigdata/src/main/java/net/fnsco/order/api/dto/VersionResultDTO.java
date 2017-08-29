package net.fnsco.order.api.dto;

public class VersionResultDTO {
    private String  forceUpdate;
    private String  version;
    private String  downloadUrl1;
    private String  downloadUrl2;
    private Boolean isUpdate;
    private String  remark;
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
