package net.fnsco.api.doc.service.inter.entity;

import java.util.Date;

public class InterParamDO {

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
     * 
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
     * 数据类型
     */
    private String type;

    /**
     * 格式化
     */
    private String format;

    /**
     * 参数位置
     */
    private String position;

    /**
     * 是否必输项
     */
    private Integer required;

    /**
     * 
     */
    private String custSchema;

    /**
     * 
     */
    private String extSchema;

    /**
     * 
     */
    private Long refSchemaId;

    /**
     * 默认值
     */
    private String defValue;



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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Long getRefSchemaId() {
        return refSchemaId;
    }

    public void setRefSchemaId(Long refSchemaId) {
        this.refSchemaId = refSchemaId;
    }

    public String getDefValue() {
        return defValue;
    }

    public void setDefValue(String defValue) {
        this.defValue = defValue;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", docId="+ docId + ", interId="+ interId + ", code="+ code + ", name="+ name + ", description="+ description + ", type="+ type + ", format="+ format + ", position="+ position + ", required="+ required + ", custSchema="+ custSchema + ", extSchema="+ extSchema + ", refSchemaId="+ refSchemaId + ", defValue="+ defValue + "]";
    }
}