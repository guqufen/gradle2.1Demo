package net.fnsco.risk.service.zhimainfo.entity;

import java.util.Date;

public class DataCompanyPersonDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 内部商户号
     */
    private String innerCode;

    /**
     * 
     */
    private String key;

    /**
     * 
     */
    private String value;

    /**
     * 0芝麻信用
     */
    private String source;

    /**
     * 涉诉类型。cpws-裁判文书; zxgg-执行公告; sxgg-失信公告; ktgg-开庭公告; fygg-法院公告; ajlc-案件流程信息; bgt-曝光台。
     */
    private String type;

    /**
     * 
     */
    private Date createTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", innerCode="+ innerCode + ", key="+ key + ", value="+ value + ", source="+ source + ", type="+ type + ", createTime="+ createTime + "]";
    }
}