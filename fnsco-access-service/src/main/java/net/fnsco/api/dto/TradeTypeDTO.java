package net.fnsco.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.DTO;

/**
 * @desc 报表中按照支付渠道展示数据
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月1日 上午9:55:05
 */

public class TradeTypeDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 107573806789051245L;
    
    /**
     * 渠道方式、
     */
    private String payType;
    /**
     * 笔数
     */
    private Integer orderNum;
    /**
     * 营业额
     */
    private BigDecimal turnover;
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
     * orderNum
     *
     * @return  the orderNum
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getOrderNum() {
        return orderNum;
    }
    /**
     * orderNum
     *
     * @param   orderNum    the orderNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**
     * turnover
     *
     * @return  the turnover
     * @since   CodingExample Ver 1.0
    */
    
    public BigDecimal getTurnover() {
        return turnover;
    }
    /**
     * turnover
     *
     * @param   turnover    the turnover to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }
    
}
