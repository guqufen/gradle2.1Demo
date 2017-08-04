package net.fnsco.order.api.dto;

import java.math.BigDecimal;
import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc 交易峰值数据
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月3日 下午5:26:02
 */

public class PeakTradeDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -1025930339284572163L;
    
    /**
     * 交易总笔数
     */
    private Integer orderNumTotal;
    
    /**
     * 交易总金额
     */
    private BigDecimal turnoverTotal;
    
    /**
     * 每个时间点的交易额
     */
    private List<TradeHourDTO> tradeHourdata;
    
    /**
     * 查询开始日期
     */
    private String startDate;
    
    /**
     * 查询结束日期
     */
    private String endDate;
    
    /**
     * startDate
     *
     * @return  the startDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getStartDate() {
        return startDate;
    }

    /**
     * startDate
     *
     * @param   startDate    the startDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * endDate
     *
     * @return  the endDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getEndDate() {
        return endDate;
    }

    /**
     * endDate
     *
     * @param   endDate    the endDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * tradeHourdata
     *
     * @return  the tradeHourdata
     * @since   CodingExample Ver 1.0
    */
    
    public List<TradeHourDTO> getTradeHourdata() {
        return tradeHourdata;
    }

    /**
     * tradeHourdata
     *
     * @param   tradeHourdata    the tradeHourdata to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeHourdata(List<TradeHourDTO> tradeHourdata) {
        this.tradeHourdata = tradeHourdata;
    }

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

}
