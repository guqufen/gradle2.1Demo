package net.fnsco.web.controller.open.jo;

import net.fnsco.core.base.JO;

public class MerchantJO extends JO {
    private String merCode;
    private String channelType;
    private String userId;     //登录用户Id
    private String snCode;
    private String posName;
    

    /**
     * posName
     *
     * @return  the posName
     * @since   CodingExample Ver 1.0
    */
    
    public String getPosName() {
        return posName;
    }

    /**
     * posName
     *
     * @param   posName    the posName to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPosName(String posName) {
        this.posName = posName;
    }

    /**
     * snCode
     *
     * @return  the snCode
     * @since   CodingExample Ver 1.0
    */
    
    public String getSnCode() {
        return snCode;
    }

    /**
     * snCode
     *
     * @param   snCode    the snCode to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    /**
     * userId
     *
     * @return  the userId
     * @since   CodingExample Ver 1.0
    */

    public String getUserId() {
        return userId;
    }

    /**
     * userId
     *
     * @param   userId    the userId to set
     * @since   CodingExample Ver 1.0
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the merCode
     */
    public String getMerCode() {
        return merCode;
    }

    /**
     * @param merCode the merCode to set
     */
    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    /**
     * @return the channelType
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * @param channelType the channelType to set
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

}
