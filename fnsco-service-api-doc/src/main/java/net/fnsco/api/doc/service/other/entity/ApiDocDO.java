package net.fnsco.api.doc.service.other.entity;

import java.util.Date;

public class ApiDocDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 项目id
     */
    private Long projId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 访问主机
     */
    private String host;

    /**
     * 基路径
     */
    private String basePath;

    /**
     * 
     */
    private Integer pub;

    /**
     * 
     */
    private Integer open;

    /**
     * 
     */
    private String scheme;

    /**
     * 
     */
    private String consume;

    /**
     * 
     */
    private String produce;

    /**
     * 版本
     */
    private String version;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Integer getPub() {
        return pub;
    }

    public void setPub(Integer pub) {
        this.pub = pub;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", projId="+ projId + ", title="+ title + ", description="+ description + ", host="+ host + ", basePath="+ basePath + ", pub="+ pub + ", open="+ open + ", scheme="+ scheme + ", consume="+ consume + ", produce="+ produce + ", version="+ version + "]";
    }
}