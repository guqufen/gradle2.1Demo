package net.fnsco.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;
import net.fnsco.core.base.FileEnum;

public class MerchantFileInfoDTO extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7049717427842659897L;

	/**
     * 主键
     */
    private Integer id;

    /**
     * 商户主键
     */
    private String innerCode;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;
    
    /**
     * 文件保存路径
     */
    private String saveURL;

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


	public String getInnerCode() {
		return innerCode;
	}


	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
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


	public String getSaveURL() {
		return saveURL;
	}


	public void setSaveURL(String saveURL) {
		this.saveURL = saveURL;
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
        return "[id="+ id + ", innerCode="+ innerCode + ", fileName="+ fileName + ", fileType="+ fileType + ", saveURL="+ saveURL + ", createTime="+ createTime + "]";
    }
}