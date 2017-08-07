package net.fnsco.api.doc.service.other.entity;

import java.util.Date;

public class RespSchemaDO {

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
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 结构
     */
    private String custSchema;

    /**
     * 类型
     */
    private String type;

    /**
     * 
     */
    private Long refSchemaId;



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

    public String getCustSchema() {
        return custSchema;
    }

    public void setCustSchema(String custSchema) {
        this.custSchema = custSchema;
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



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", docId="+ docId + ", moduleId="+ moduleId + ", code="+ code + ", name="+ name + ", description="+ description + ", custSchema="+ custSchema + ", type="+ type + ", refSchemaId="+ refSchemaId + "]";
    }
}