package net.fnsco.web.controller.e789.dto;

import net.fnsco.core.base.DTO;

public class TradeDTO extends DTO {
    //内部商务号
    private String innerCode;
    private String transTime;
    private String orderNo;
    private String singData;

    /**
     * innerCode
     *
     * @return  the innerCode
     * @since   CodingExample Ver 1.0
    */

    public String getInnerCode() {
        return innerCode;
    }

    /**
     * innerCode
     *
     * @param   innerCode    the innerCode to set
     * @since   CodingExample Ver 1.0
     */

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
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
     * orderNo
     *
     * @return  the orderNo
     * @since   CodingExample Ver 1.0
    */

    public String getOrderNo() {
        return orderNo;
    }

    /**
     * orderNo
     *
     * @param   orderNo    the orderNo to set
     * @since   CodingExample Ver 1.0
     */

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
