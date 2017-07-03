package net.fnsco.controller.app.jo;

import net.fnsco.core.base.JO;

public class TradeDataJO extends JO {
    private String id;         //交易记录ID
    private String amount;     //交易金额
    private String tradeTime;  //交易时间
    private String status;     //交易状态（1成功 2失败）
    private String statusName;
    private String payType;
    private String payTypeName;

    /**
     * id
     *
     * @return  the id
     * @since   CodingExample Ver 1.0
    */

    public String getId() {
        return id;
    }

    /**
     * id
     *
     * @param   id    the id to set
     * @since   CodingExample Ver 1.0
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * tradeTime
     *
     * @return  the tradeTime
     * @since   CodingExample Ver 1.0
    */

    public String getTradeTime() {
        return tradeTime;
    }

    /**
     * tradeTime
     *
     * @param   tradeTime    the tradeTime to set
     * @since   CodingExample Ver 1.0
     */

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * amount
     *
     * @return  the amount
     * @since   CodingExample Ver 1.0
    */

    public String getAmount() {
        return amount;
    }

    /**
     * amount
     *
     * @param   amount    the amount to set
     * @since   CodingExample Ver 1.0
     */

    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * statusName
     *
     * @return  the statusName
     * @since   CodingExample Ver 1.0
    */

    public String getStatusName() {
        return statusName;
    }

    /**
     * statusName
     *
     * @param   statusName    the statusName to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * payType
     *
     * @return  the payType
     * @since   CodingExample Ver 1.0
    */

    public String getPayType() {
        return payType;
    }

    /**
     * payType
     *
     * @param   payType    the payType to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * status
     *
     * @return  the status
     * @since   CodingExample Ver 1.0
    */

    public String getStatus() {
        return status;
    }

    /**
     * status
     *
     * @param   status    the status to set
     * @since   CodingExample Ver 1.0
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * payTypeName
     *
     * @return  the payTypeName
     * @since   CodingExample Ver 1.0
    */

    public String getPayTypeName() {
        return payTypeName;
    }

    /**
     * payTypeName
     *
     * @param   payTypeName    the payTypeName to set
     * @since   CodingExample Ver 1.0
     */

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

}
