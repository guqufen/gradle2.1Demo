package net.fnsco.order.service.ad.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class AdDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "title", name = "title", example = "标题")
    private String title;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "imgPath", name = "imgPath", example = "图片路径")
    private String imgPath;
    @ApiModelProperty(value = "url", name = "url", example = "链接url")
    private String url;

    /**
     * 类别（1广告、2资讯）
     */
    private Integer category;

    /**
     * 摘要
     */
    @ApiModelProperty(value = "summary", name = "summary", example = "摘要")
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer createUserId;



    /**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", title="+ title + ", imgPath="+ imgPath + ", category="+ category + ", summary="+ summary + ", content="+ content + ", createTime="+ createTime + ", updateTime="+ updateTime + ", createUserId="+ createUserId + "]";
    }
}