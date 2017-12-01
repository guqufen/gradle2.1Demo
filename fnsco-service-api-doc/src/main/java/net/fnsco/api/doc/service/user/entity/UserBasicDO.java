package net.fnsco.api.doc.service.user.entity;

import java.util.Date;

public class UserBasicDO {

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
     * 手机号
     */
    private String phone;
    /**
     * 姓名
     */
    private String Name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否验证
     */
    private Integer valid;

    /**
     * 
     */
    private String role;

    /**
     * 是否锁定
     */
    private Integer locked;

    /**
     * 锁定时间
     */
    private Date lockedDate;

    /**
     * 注册ip
     */
    private String registerIp;



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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }



    @Override
    public String toString() {
        return "[id="+ id + ", createDate="+ createDate + ", modifyDate="+ modifyDate + ", phone="+ phone + ", email="+ email + ", password="+ password + ", valid="+ valid + ", role="+ role + ", locked="+ locked + ", lockedDate="+ lockedDate + ", registerIp="+ registerIp + "]";
    }
}