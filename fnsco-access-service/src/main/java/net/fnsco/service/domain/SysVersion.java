package net.fnsco.service.domain;

import java.util.Date;

public class SysVersion {
    private Integer id;

    private String appName;

    private String appCode;

    private Integer appType;

    private String version;

    private Integer forceUpdate;

    private String remark;

    private String versionDesc;

    private String downloadUrl1;

    private String downloadUrl2;

    private Date lastModifyTime;

    private Integer lastModifyUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc == null ? null : versionDesc.trim();
    }

    public String getDownloadUrl1() {
        return downloadUrl1;
    }

    public void setDownloadUrl1(String downloadUrl1) {
        this.downloadUrl1 = downloadUrl1 == null ? null : downloadUrl1.trim();
    }

    public String getDownloadUrl2() {
        return downloadUrl2;
    }

    public void setDownloadUrl2(String downloadUrl2) {
        this.downloadUrl2 = downloadUrl2 == null ? null : downloadUrl2.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }
}