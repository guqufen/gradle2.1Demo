package net.fnsco.order.api.dto;

import java.util.Date;

import net.fnsco.core.base.DTO;

/**
 * @desc APP 用户界面管理DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月13日 下午1:32:46
 */

public class AppUserManageDTO extends DTO{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 5910492888779903687L;
    /**
     * 用户名称
     */
    private String  userName;
    
    private String mobile;
    
    private Date regTime;
    
    private String regTimeStr;
    private Date lastLoginTime;

    /**
     * 店铺名称集合字符串拼接
     */
    private String merNames;
    /**
     * userName
     *
     * @return  the userName
     * @since   CodingExample Ver 1.0
    */
    
    public String getUserName() {
        return userName;
    }
    
    public String getRegTimeStr() {
		return regTimeStr;
	}
	public void setRegTimeStr(String regTimeStr) {
		this.regTimeStr = regTimeStr;
	}
    /**
     * userName
     *
     * @param   userName    the userName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * mobile
     *
     * @return  the mobile
     * @since   CodingExample Ver 1.0
    */
    
    public String getMobile() {
        return mobile;
    }
    /**
     * mobile
     *
     * @param   mobile    the mobile to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * regTime
     *
     * @return  the regTime
     * @since   CodingExample Ver 1.0
    */
    
    public Date getRegTime() {
        return regTime;
    }
    /**
     * regTime
     *
     * @param   regTime    the regTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
    /**
     * merNames
     *
     * @return  the merNames
     * @since   CodingExample Ver 1.0
    */
    
    public String getMerNames() {
        return merNames;
    }
    /**
     * merNames
     *
     * @param   merNames    the merNames to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerNames(String merNames) {
        this.merNames = merNames;
    }
    
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
