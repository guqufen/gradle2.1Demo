package net.fnsco.risk.service.sys.entity;

import java.util.Date;

public class WebUserDO {

    /**
     * 
     */
    private Integer id;

    /**
     * 1 oem管理员/2 代理商用户/ 3 商户/ 4 其它用户
     */
    private Integer type;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 1男 2女
     */
    private Integer sex;

    /**
     * 花名
     */
    private String aliasName;

    /**
     * 部门
     */
    private String department;

    /**
     * 代理商编号
     */
    private Integer agentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     */
    private Date modifyTime;

    /**
     * 
     */
    private Integer modifyUserId;

    /**
     * 创建时间
     */
    private Date createrTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Integer modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", type="+ type + ", name="+ name + ", password="+ password + ", realName="+ realName + ", mobile="+ mobile + ", sex="+ sex + ", aliasName="+ aliasName + ", department="+ department + ", agentId="+ agentId + ", remark="+ remark + ", modifyTime="+ modifyTime + ", modifyUserId="+ modifyUserId + ", createrTime="+ createrTime + "]";
    }
}