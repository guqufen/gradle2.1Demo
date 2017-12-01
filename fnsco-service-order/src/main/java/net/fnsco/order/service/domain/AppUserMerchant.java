package net.fnsco.order.service.domain;

import java.util.Date;

public class AppUserMerchant {
    private Integer id;

    private String roleId;

    private String innerCode;

    private Integer appUserId;

    private Date modefyTime;
    
    private String entityInnerCode;//实体商户号
    

    /**
	 * entityInnerCode
	 *
	 * @return  the entityInnerCode
	 * @since   CodingExample Ver 1.0
	*/
	
	public String getEntityInnerCode() {
		return entityInnerCode;
	}

	/**
	 * entityInnerCode
	 *
	 * @param   entityInnerCode    the entityInnerCode to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setEntityInnerCode(String entityInnerCode) {
		this.entityInnerCode = entityInnerCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Date getModefyTime() {
        return modefyTime;
    }

    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
    }
}