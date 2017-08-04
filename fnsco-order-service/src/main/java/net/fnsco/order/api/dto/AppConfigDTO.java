package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月2日 下午3:12:21
 */

public class AppConfigDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 6075033483631903359L;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 类型
     */
    private String type;
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
     * type
     *
     * @return  the type
     * @since   CodingExample Ver 1.0
    */
    
    public String getType() {
        return type;
    }
    /**
     * type
     *
     * @param   type    the type to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setType(String type) {
        this.type = type;
    }
    
}
