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
    private String title;

    /**
     * 图片路径
     */
    private String img_path;
    private String url;

    /**
     * 类别（1广告、2资讯）
     */
    private Integer category;

    /**
     * 摘要
     */
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
	 * img_path
	 *
	 * @return  the img_path
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getImg_path() {
		return img_path;
	}

	/**
	 * img_path
	 *
	 * @param   img_path    the img_path to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

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

}