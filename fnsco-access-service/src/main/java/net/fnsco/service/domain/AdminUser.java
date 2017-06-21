package net.fnsco.service.domain;

import java.util.Date;

/**
 * @desc 后台管理用户实体
 * @author tangliang
 * @date 2017年6月20日 下午2:59:17
 */
public class AdminUser {
	
    private Integer id;

    private Integer innercode;

    private Integer type;

    private String name;

    private String password;

    private String realname;

    private String mobile;

    private Integer sex;

    private String aliasname;

    private String department;

    private String remark;

    private Date lastmodifiedtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInnercode() {
        return innercode;
    }

    public void setInnercode(Integer innercode) {
        this.innercode = innercode;
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
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAliasname() {
        return aliasname;
    }

    public void setAliasname(String aliasname) {
        this.aliasname = aliasname == null ? null : aliasname.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }
}