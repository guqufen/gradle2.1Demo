package net.fnsco.api.dto;

import net.fnsco.core.base.JO;

public class MerchantDTO extends JO {
    private String randomCode;
    private String channelType;
    private String userId;     //登录用户Id

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
     * randomCode
     *
     * @return  the randomCode
     * @since   CodingExample Ver 1.0
    */

    public String getRandomCode() {
        return randomCode;
    }

    /**
     * randomCode
     *
     * @param   randomCode    the randomCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
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
