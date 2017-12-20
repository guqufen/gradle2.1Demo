package net.fnsco.trading.service.userfile.entity;

import java.util.Date;

public class AppUserFileDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 内部商户号
     */
    private Integer appUserId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型0身份证
     */
    private String fileType;

    /**
     * 文件保存路径
     */
    private String filePath;

    /**
     * 创建时间
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", appUserId="+ appUserId + ", fileName="+ fileName + ", fileType="+ fileType + ", filePath="+ filePath + ", createTime="+ createTime + "]";
    }
}