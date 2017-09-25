package net.fnsco.order.service.sys.entity;

import java.util.Date;

public class ImportErrorDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 导入文件名
     */
    private String importFileName;

    /**
     * 导入出错数据
     */
    private String data;

    /**
     * 错误原因
     */
    private String errorMsg;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 导入用户ID
     */
    private Integer createUserId;

    /**
     * 错误行
     */
    private Integer rowNumber;
    /**
     * 导入文件类型
     */
    private Integer importType;
    /**
     * 
     */
    private Date startCreateTime;
    private Date endCreateTime;
    
    /**
     * startCreateTime
     *
     * @return  the startCreateTime
     * @since   CodingExample Ver 1.0
    */
    
    public Date getStartCreateTime() {
        return startCreateTime;
    }

    /**
     * startCreateTime
     *
     * @param   startCreateTime    the startCreateTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    /**
     * endCreateTime
     *
     * @return  the endCreateTime
     * @since   CodingExample Ver 1.0
    */
    
    public Date getEndCreateTime() {
        return endCreateTime;
    }

    /**
     * endCreateTime
     *
     * @param   endCreateTime    the endCreateTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    /**
     * importType
     *
     * @return  the importType
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getImportType() {
        return importType;
    }

    /**
     * importType
     *
     * @param   importType    the importType to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setImportType(Integer importType) {
        this.importType = importType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImportFileName() {
        return importFileName;
    }

    public void setImportFileName(String importFileName) {
        this.importFileName = importFileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", importFileName="+ importFileName + ", data="+ data + ", errorMsg="+ errorMsg + ", createTime="+ createTime + ", createUserId="+ createUserId + ", rowNumber="+ rowNumber + "]";
    }
}