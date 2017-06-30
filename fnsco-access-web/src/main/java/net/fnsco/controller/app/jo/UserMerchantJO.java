package net.fnsco.controller.app.jo;

import net.fnsco.core.base.JO;
/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年6月29日 下午2:02:42
 */
public class UserMerchantJO extends JO{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 5051870984537030099L;
    /**
     * app用户ID
     */
    private Integer userId;
    
    /**
     * 商户ID
     */
    private Integer merId;
    
    /**
     * merId
     *
     * @return  the merId
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getMerId() {
        return merId;
    }
    /**
     * merId
     *
     * @param   merId    the merId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setMerId(Integer merId) {
        this.merId = merId;
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
    
    
}
