package net.fnsco.trading.service.order.dto;

import net.fnsco.core.base.JO;

public class TradeJhfJO {
    private String commID;           //  商户Id
    private String thirdPayNo;       // 订单号
    private String payAmount;        //   支付金额
    private String periodNum;        // 分期数
    private String unionId;          // 客户ID
    private String transTime;        //   交易时间
    private String payNotifyUrl;     //   通知URL
    private String payCallBackUrl;   //  支付结束的回调URL
    private String payCallBackParams;//  支付成功后通知参数
    private String singData;         //    MD5签名

    /**
     * commID
     *
     * @return  the commID
     * @since   CodingExample Ver 1.0
    */

    public String getCommID() {
        return commID;
    }

    /**
     * commID
     *
     * @param   commID    the commID to set
     * @since   CodingExample Ver 1.0
     */

    public void setCommID(String commID) {
        this.commID = commID;
    }

    /**
     * thirdPayNo
     *
     * @return  the thirdPayNo
     * @since   CodingExample Ver 1.0
    */

    public String getThirdPayNo() {
        return thirdPayNo;
    }

    /**
     * thirdPayNo
     *
     * @param   thirdPayNo    the thirdPayNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setThirdPayNo(String thirdPayNo) {
        this.thirdPayNo = thirdPayNo;
    }

    /**
     * payAmount
     *
     * @return  the payAmount
     * @since   CodingExample Ver 1.0
    */

    public String getPayAmount() {
        return payAmount;
    }

    /**
     * payAmount
     *
     * @param   payAmount    the payAmount to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * periodNum
     *
     * @return  the periodNum
     * @since   CodingExample Ver 1.0
    */

    public String getPeriodNum() {
        return periodNum;
    }

    /**
     * periodNum
     *
     * @param   periodNum    the periodNum to set
     * @since   CodingExample Ver 1.0
     */

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    /**
     * unionId
     *
     * @return  the unionId
     * @since   CodingExample Ver 1.0
    */

    public String getUnionId() {
        return unionId;
    }

    /**
     * unionId
     *
     * @param   unionId    the unionId to set
     * @since   CodingExample Ver 1.0
     */

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * transTime
     *
     * @return  the transTime
     * @since   CodingExample Ver 1.0
    */

    public String getTransTime() {
        return transTime;
    }

    /**
     * transTime
     *
     * @param   transTime    the transTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    /**
     * payNotifyUrl
     *
     * @return  the payNotifyUrl
     * @since   CodingExample Ver 1.0
    */

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    /**
     * payNotifyUrl
     *
     * @param   payNotifyUrl    the payNotifyUrl to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    /**
     * payCallBackUrl
     *
     * @return  the payCallBackUrl
     * @since   CodingExample Ver 1.0
    */

    public String getPayCallBackUrl() {
        return payCallBackUrl;
    }

    /**
     * payCallBackUrl
     *
     * @param   payCallBackUrl    the payCallBackUrl to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayCallBackUrl(String payCallBackUrl) {
        this.payCallBackUrl = payCallBackUrl;
    }

    /**
     * payCallBackParams
     *
     * @return  the payCallBackParams
     * @since   CodingExample Ver 1.0
    */

    public String getPayCallBackParams() {
        return payCallBackParams;
    }

    /**
     * payCallBackParams
     *
     * @param   payCallBackParams    the payCallBackParams to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayCallBackParams(String payCallBackParams) {
        this.payCallBackParams = payCallBackParams;
    }

    /**
     * singData
     *
     * @return  the singData
     * @since   CodingExample Ver 1.0
    */

    public String getSingData() {
        return singData;
    }

    /**
     * singData
     *
     * @param   singData    the singData to set
     * @since   CodingExample Ver 1.0
     */

    public void setSingData(String singData) {
        this.singData = singData;
    }

}
