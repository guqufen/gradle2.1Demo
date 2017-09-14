package net.fnsco.risk.service.sys.entity;


public class ConfigDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 类型00web
     */
    private String type;

    /**
     * 分组
     */
    private String groupName;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 状态0无效1有效
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", type="+ type + ", groupName="+ groupName + ", name="+ name + ", value="+ value + ", status="+ status + ", remark="+ remark + "]";
    }
}