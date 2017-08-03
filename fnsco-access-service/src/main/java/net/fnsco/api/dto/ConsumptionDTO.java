package net.fnsco.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc 交易峰值DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月3日 下午2:44:59
 */

public class ConsumptionDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -6736095522796788562L;
    
    /**
     * 交易总笔数
     */
    private Integer orderNumTotal;
    
    /**
     * 交易总金额
     */
    private BigDecimal turnoverTotal;
    
    /**
     * 交易渠道数据集合
     */
    private List<TradeTypeDTO> tradeTypeData;

    /**
     * orderNumTotal
     *
     * @return  the orderNumTotal
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getOrderNumTotal() {
        return orderNumTotal;
    }

    /**
     * orderNumTotal
     *
     * @param   orderNumTotal    the orderNumTotal to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderNumTotal(Integer orderNumTotal) {
        this.orderNumTotal = orderNumTotal;
    }

    /**
     * turnoverTotal
     *
     * @return  the turnoverTotal
     * @since   CodingExample Ver 1.0
    */
    
    public BigDecimal getTurnoverTotal() {
        return turnoverTotal;
    }

    /**
     * turnoverTotal
     *
     * @param   turnoverTotal    the turnoverTotal to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnoverTotal(BigDecimal turnoverTotal) {
        this.turnoverTotal = turnoverTotal;
    }

    /**
     * tradeTypeData
     *
     * @return  the tradeTypeData
     * @since   CodingExample Ver 1.0
    */
    
    public List<TradeTypeDTO> getTradeTypeData() {
        return tradeTypeData;
    }

    /**
     * tradeTypeData
     *
     * @param   tradeTypeData    the tradeTypeData to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeTypeData(List<TradeTypeDTO> tradeTypeData) {
        this.tradeTypeData = tradeTypeData;
    }
    
}
