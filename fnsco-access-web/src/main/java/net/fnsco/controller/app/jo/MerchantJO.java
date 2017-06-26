package net.fnsco.controller.app.jo;

import net.fnsco.core.base.JO;

public class MerchantJO extends JO {
    private String merCode;
    private String channelType;

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
