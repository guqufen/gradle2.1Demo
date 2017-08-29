package net.fnsco.bigdata.service.domain;

import java.util.Date;

import net.fnsco.bigdata.comm.FileEnum;
import net.fnsco.core.base.DTO;

public class MerchantFile extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7049717427842659897L;

	private Integer id;

    private String innerCode;

    private String fileName;

    private String fileType;

    private String filePath;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


	/////////////////////////////////////////////////////
    public String getFileTypeName() {
		if (FileEnum.FileTypeEnum.INIT_OTHERS.getMap().containsKey(this.fileType)) {
			return FileEnum.FileTypeEnum.INIT_OTHERS.getMap().get(this.fileType);
		}
		return "无";
	}

    @Override
    public String toString() {
        return "[id="+ id + ", innerCode="+ innerCode + ", fileName="+ fileName + ", fileType="+ fileType + ", createTime="+ createTime + "]";
    }
}