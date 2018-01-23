package net.fnsco.order.service.ad.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class AdDTO {

    /**
     * 标题
     */
    private String title;

    /**
     * 图片路径
     */
    private String imgPath;
    private String url;

    /**
     * 摘要
     */
    private String summary;
    /**
     * app显示的优先级
     */
    private Integer priority;
    


    /**
	 * priority
	 *
	 * @return  the priority
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getPriority() {
		return priority;
	}

	/**
	 * priority
	 *
	 * @param   priority    the priority to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPriority(Integer priority) {
		this.priority = priority;
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