package net.fnsco.web.controller.open.jo;

import net.fnsco.core.base.JO;

public class TradeJO extends JO {
    private String merCode;
    private String channelType;
    private String snCode;
    //分期数
    private Integer installmentNum;
    //支付总金额
    private String paymentAmount;

    /**
     * installmentNum
     *
     * @return  the installmentNum
     * @since   CodingExample Ver 1.0
    */

    public Integer getInstallmentNum() {
        return installmentNum;
    }

    /**
     * installmentNum
     *
     * @param   installmentNum    the installmentNum to set
     * @since   CodingExample Ver 1.0
     */

    public void setInstallmentNum(Integer installmentNum) {
        this.installmentNum = installmentNum;
    }

    /**
     * paymentAmount
     *
     * @return  the paymentAmount
     * @since   CodingExample Ver 1.0
    */

    public String getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * paymentAmount
     *
     * @param   paymentAmount    the paymentAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
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
