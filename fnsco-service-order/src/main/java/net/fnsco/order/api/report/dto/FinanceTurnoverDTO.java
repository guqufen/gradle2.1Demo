package net.fnsco.order.api.report.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月30日 下午4:19:51
 */

public class FinanceTurnoverDTO extends DTO {
    

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -7420741601548199865L;

    /**
     * 总交易额
     */
    private String turnover;
    
    /**
     * 订单数
     */
    private Integer orderNum;
    
    /**
     * 手续费
     */
    private String procedureFee;

    /**
     * turnover
     *
     * @return  the turnover
     * @since   CodingExample Ver 1.0
    */
    
    public String getTurnover() {
        return turnover;
    }

    /**
     * turnover
     *
     * @param   turnover    the turnover to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnover(String turnover) {
        this.turnover = turnover;
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
     * procedureFee
     *
     * @return  the procedureFee
     * @since   CodingExample Ver 1.0
    */
    
    public String getProcedureFee() {
        return procedureFee;
    }

    /**
     * procedureFee
     *
     * @param   procedureFee    the procedureFee to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setProcedureFee(String procedureFee) {
        this.procedureFee = procedureFee;
    }
    
}
