package net.fnsco.auth.service.sys.entity;


public class DeptDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 上级部门ID，一级部门为0
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Integer delFlag;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", parentId="+ parentId + ", name="+ name + ", orderNum="+ orderNum + ", delFlag="+ delFlag + "]";
    }
}