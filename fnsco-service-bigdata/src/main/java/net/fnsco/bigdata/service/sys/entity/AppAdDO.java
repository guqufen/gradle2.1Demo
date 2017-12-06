package net.fnsco.bigdata.service.sys.entity;

import java.util.Date;

public class AppAdDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件路径
     */
    private String filepath;

    /**
     * 类别（1广告、2资讯）
     */
    private Integer category;

    /**
     * 内容
     */
    private String content;

    /**
     * 
     */
    private Date createtime;

    /**
     * 
     */
    private Date updatetime;

    /**
     * 
     */
    private Integer createuserid;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", filename="+ filename + ", filepath="+ filepath + ", category="+ category + ", content="+ content + ", createtime="+ createtime + ", updatetime="+ updatetime + ", createuserid="+ createuserid + "]";
    }
}