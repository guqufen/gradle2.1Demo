package net.fnsco.order.service.ad.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class AdDTO {

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
     * 摘要
     */
    @ApiModelProperty(value = "summary", name = "summary", example = "摘要")
    private String summary;


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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}