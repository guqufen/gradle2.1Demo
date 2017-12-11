package net.fnsco.car.service.ftemp.entity;

import java.util.Date;

public class OrderFileTempDO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 内部商户号
     */
    private String orderNo;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型0行驶证1车辆登记证2车辆大本
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
        return "[id="+ id + ", orderNo="+ orderNo + ", fileName="+ fileName + ", fileType="+ fileType + ", filePath="+ filePath + ", createTime="+ createTime + "]";
    }
}