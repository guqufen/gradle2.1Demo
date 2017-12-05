package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class GetQRUrlJO extends JO {
    /**
     * app用户ID
     */
    @ApiModelProperty(value = "APP用户id")
    private Integer userId;
    //分期数
    @ApiModelProperty(value = "分期数")
    private Integer installmentNum;
    //支付总金额
    @ApiModelProperty(value = "支付金额")
    private String  paymentAmount;

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

}
