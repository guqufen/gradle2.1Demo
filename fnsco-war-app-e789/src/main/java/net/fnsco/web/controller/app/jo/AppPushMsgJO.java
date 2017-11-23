package net.fnsco.web.controller.app.jo;

import java.util.List;

import net.fnsco.core.base.PageDTO;

/**
 * @desc 推送消息查询
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月12日 下午1:12:35
 */
public class AppPushMsgJO extends PageDTO<Object> {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    private static final long serialVersionUID = -1288599059443167295L;
    
    /**
     * app用户ID
     */
    private Integer userId;
    
    /**
     * 标识本次获取是否为已读取 true:是标识 false:否  默认为false
     */
    private boolean hasRead = false;
    
    /**
     * 手机类型
     */
    private Integer phoneType;
    
    /**
     * 信息IDs
     */
    private List<Integer> msgIds; //信息ID
    
    /**
     * msgIds
     *
     * @return  the msgIds
     * @since   CodingExample Ver 1.0
    */
    
    public List<Integer> getMsgIds() {
        return msgIds;
    }

    /**
     * msgIds
     *
     * @param   msgIds    the msgIds to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMsgIds(List<Integer> msgIds) {
        this.msgIds = msgIds;
    }

    /**
     * phoneType
     *
     * @return  the phoneType
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getPhoneType() {
        return phoneType;
    }

    /**
     * phoneType
     *
     * @param   phoneType    the phoneType to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPhoneType(Integer phoneType) {
        this.phoneType = phoneType;
    }

    /**
     * userId
     *
     * @return  the userId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getUserId() {
        return userId;
    }

    /**
     * userId
     *
     * @param   userId    the userId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * hasRead
     *
     * @return  the hasRead
     * @since   CodingExample Ver 1.0
    */
    
    public boolean isHasRead() {
        return hasRead;
    }

    /**
     * hasRead
     *
     * @param   hasRead    the hasRead to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
    
}
