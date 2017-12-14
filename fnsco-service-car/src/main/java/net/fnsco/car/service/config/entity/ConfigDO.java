package net.fnsco.car.service.config.entity;

import java.util.Date;

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

    /**
     * 排序号
     */
    private Integer orderNo;
    /**
     * 修改日期
     */
    private Date modifyTime;
    /**
     * 保存字段1
     */
    private String keep1;

    /**
     * 
     */
    private String keep2;

    /**
     * 
     */
    private String keep3;



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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
    
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getKeep1() {
        return keep1;
    }

    public void setKeep1(String keep1) {
        this.keep1 = keep1;
    }

    public String getKeep2() {
        return keep2;
    }

    public void setKeep2(String keep2) {
        this.keep2 = keep2;
    }

    public String getKeep3() {
        return keep3;
    }

    public void setKeep3(String keep3) {
        this.keep3 = keep3;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", type="+ type + ", groupName="+ groupName + ", name="+ name + ", value="+ value + ", status="+ status + ", remark="+ remark + ", orderNo="+ orderNo + ", keep1="+ keep1 + ", keep2="+ keep2 + ", keep3="+ keep3 + "]";
    }
}