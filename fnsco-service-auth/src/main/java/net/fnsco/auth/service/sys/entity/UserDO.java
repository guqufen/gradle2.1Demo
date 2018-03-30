package net.fnsco.auth.service.sys.entity;

import java.util.Date;
import java.util.List;

public class UserDO {

    /**
     * 
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
   
	/**
     * 1 oem管理员/2 代理商用户/ 3 商户/ 4 其它用户
     */
    private Integer type;
    /**
     * 角色数组多选前后端传值
     */
    private List<Integer> roleList;
    



	/**
     * 1禁止 0正常-1删除
     */
    private Integer status;

   



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
    private Integer departId;
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


    private String modifyTimeStr;
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    public List<Integer> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Integer> roleList) {
		this.roleList = roleList;
	}
    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
    public String getModifyTimeStr() {
		return modifyTimeStr;
	}

	public void setModifyTimeStr(String modifyTimeStr) {
		this.modifyTimeStr = modifyTimeStr;
	}

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

    
    /**
	 * departId
	 *
	 * @return  the departId
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getDepartId() {
		return departId;
	}

	/**
	 * departId
	 *
	 * @param   departId    the departId to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setDepartId(Integer departId) {
		this.departId = departId;
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



    @Override
    public String toString() {
        return "[id="+ id + ", type="+ type + ", name="+ name + ", password="+ password + ", realName="+ realName + ", mobile="+ mobile + ", sex="+ sex + ", aliasName="+ aliasName + ", department="+ department + ", agentId="+ agentId + ", remark="+ remark + ", modifyTime="+ modifyTime + ", modifyUserId="+ modifyUserId + "]";
    }
}