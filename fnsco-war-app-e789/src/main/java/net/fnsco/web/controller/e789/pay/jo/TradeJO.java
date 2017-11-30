package net.fnsco.web.controller.e789.pay.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class TradeJO extends JO {
    @ApiModelProperty(value = "用户名", name = "username", example = "xingguo")
    private String  merCode;
    private String  channelType;
    private String  snCode;
    //分期数
    private Integer installmentNum;
    //支付总金额
    private String  paymentAmount;
    //内部商务号
    private String  innerCode;
    private String  date;
    private String  orderNo;
    private Integer pageNum;
    private Integer pageSize;

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
     * date
     *
     * @return  the date
     * @since   CodingExample Ver 1.0
    */

    public String getDate() {
        return date;
    }

    /**
     * date
     *
     * @param   date    the date to set
     * @since   CodingExample Ver 1.0
     */

    public void setDate(String date) {
        this.date = date;
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
     * pageNum
     *
     * @return  the pageNum
     * @since   CodingExample Ver 1.0
    */

    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * pageNum
     *
     * @param   pageNum    the pageNum to set
     * @since   CodingExample Ver 1.0
     */

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * pageSize
     *
     * @return  the pageSize
     * @since   CodingExample Ver 1.0
    */

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * pageSize
     *
     * @param   pageSize    the pageSize to set
     * @since   CodingExample Ver 1.0
     */

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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
