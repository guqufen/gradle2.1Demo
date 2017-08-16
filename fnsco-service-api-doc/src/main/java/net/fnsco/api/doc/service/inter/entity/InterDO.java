package net.fnsco.api.doc.service.inter.entity;

import java.util.Date;

public class InterDO {

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
     * api文档id
     */
    private Long docId;

    /**
     * 模块id
     */
    private Long moduleId;

    /**
     * 接口方法名称
     */
    private String name;

    /**
     * 请求url
     */
    private String path;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求协议
     */
    private String scheme;

    /**
     * 概要信息
     */
    private String summary;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 请求格式
     */
    private String consume;

    /**
     * 响应格式
     */
    private String produce;

    /**
     * 是否弃用
     */
    private Integer deprecated;

    /**
     * 
     */
    private Integer sortWeight;



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

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Integer deprecated) {
        this.deprecated = deprecated;
    }

    public Integer getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(Integer sortWeight) {
        this.sortWeight = sortWeight;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", docId="+ docId + ", moduleId="+ moduleId + ", name="+ name + ", path="+ path + ", method="+ method + ", scheme="+ scheme + ", summary="+ summary + ", description="+ description + ", consume="+ consume + ", produce="+ produce + ", deprecated="+ deprecated + ", sortWeight="+ sortWeight + "]";
    }
}