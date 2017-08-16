package net.fnsco.api.doc.service.vo;

import java.util.Date;
import java.util.Map;

import javax.management.relation.Role;

import com.google.common.collect.Maps;

/**
 * 
		* <p>Title: 用户信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月6日下午5:05:47</p>
 */
public class UserInfo {
    /** 用户id*/
    private Long              userId;

    /** 邮箱*/
    private String            email;

    /** 昵称*/
    private String            nickName;

    /** 头像*/
    private String            headUrl;

    /** 登陆token*/
    private String            token;

    /** 是否验证 */
    private boolean           valid;

    /** 是否锁定 */
    private boolean           locked;

    /** 用户角色 UserRole*/
    private String            role;

    /** 注册时间*/
    private Date              registDate;

    /** 新消息数目*/
    private int               newMsgCount;

    /** 具有权限的项目集合*/
    private Map<Long, String> projMap = Maps.newHashMap();

    /** 具有权限的文档集合*/
    private Map<Long, String> docMap  = Maps.newHashMap();

    /**
     * 
    		*@name 根据项目id获取对应的角色
    		*@Description  
    		*@CreateDate 2015年8月29日下午3:55:34
     */
    public String getRoleByProjId(Long projId) {
        return projMap.get(projId);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    /**
     * role
     *
     * @return  the role
     * @since   CodingExample Ver 1.0
    */

    public String getRole() {
        return role;
    }

    /**
     * role
     *
     * @param   role    the role to set
     * @since   CodingExample Ver 1.0
     */

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * projMap
     *
     * @return  the projMap
     * @since   CodingExample Ver 1.0
    */

    public Map<Long, String> getProjMap() {
        return projMap;
    }

    /**
     * projMap
     *
     * @param   projMap    the projMap to set
     * @since   CodingExample Ver 1.0
     */

    public void setProjMap(Map<Long, String> projMap) {
        this.projMap = projMap;
    }

    /**
     * docMap
     *
     * @return  the docMap
     * @since   CodingExample Ver 1.0
    */

    public Map<Long, String> getDocMap() {
        return docMap;
    }

    /**
     * docMap
     *
     * @param   docMap    the docMap to set
     * @since   CodingExample Ver 1.0
     */

    public void setDocMap(Map<Long, String> docMap) {
        this.docMap = docMap;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public int getNewMsgCount() {
        return newMsgCount;
    }

    public void setNewMsgCount(int newMsgCount) {
        this.newMsgCount = newMsgCount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
