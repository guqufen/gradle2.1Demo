package net.fnsco.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc 经营趋势DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月3日 下午4:33:43
 */

public class BusinessTrendDTO extends DTO {
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -2540647789402608406L;
    
    /**
     * 交易总笔数
     */
    private Integer orderNumTotal;
    
    /**
     * 交易总金额
     */
    private BigDecimal turnoverTotal;
    
    /**
     * 客单价
     */
    private BigDecimal orderPrice;
    
    /**
     * 按照天统计数据
     */
    private List<TradeDayDTO> tradeDayData;

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
     * orderPrice
     *
     * @return  the orderPrice
     * @since   CodingExample Ver 1.0
    */
    
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    /**
     * orderPrice
     *
     * @param   orderPrice    the orderPrice to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * tradeDayData
     *
     * @return  the tradeDayData
     * @since   CodingExample Ver 1.0
    */
    
    public List<TradeDayDTO> getTradeDayData() {
        return tradeDayData;
    }

    /**
     * tradeDayData
     *
     * @param   tradeDayData    the tradeDayData to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeDayData(List<TradeDayDTO> tradeDayData) {
        this.tradeDayData = tradeDayData;
    }
    
}
