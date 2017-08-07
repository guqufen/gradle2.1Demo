package net.fnsco.api.doc.service.other.entity;

import java.util.Date;

public class InterRespDO {

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
     * 
     */
    private Long docId;

    /**
     * 接口id
     */
    private Long interId;

    /**
     * 编码
     */
    private String code;

    /**
     * 
     */
    private String name;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 响应类型
     */
    private String type;

    /**
     * 响应数据结构id
     */
    private Long refSchemaId;

    /**
     * 是否是默认
     */
    private Integer def;

    /**
     * 
     */
    private Integer required;

    /**
     * 自定义结构体
     */
    private String custSchema;

    /**
     * 
     */
    private String extSchema;

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

    public Long getInterId() {
        return interId;
    }

    public void setInterId(Long interId) {
        this.interId = interId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRefSchemaId() {
        return refSchemaId;
    }

    public void setRefSchemaId(Long refSchemaId) {
        this.refSchemaId = refSchemaId;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getCustSchema() {
        return custSchema;
    }

    public void setCustSchema(String custSchema) {
        this.custSchema = custSchema;
    }

    public String getExtSchema() {
        return extSchema;
    }

    public void setExtSchema(String extSchema) {
        this.extSchema = extSchema;
    }

    public Integer getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(Integer sortWeight) {
        this.sortWeight = sortWeight;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", docId="+ docId + ", interId="+ interId + ", code="+ code + ", name="+ name + ", description="+ description + ", type="+ type + ", refSchemaId="+ refSchemaId + ", def="+ def + ", required="+ required + ", custSchema="+ custSchema + ", extSchema="+ extSchema + ", sortWeight="+ sortWeight + "]";
    }
}