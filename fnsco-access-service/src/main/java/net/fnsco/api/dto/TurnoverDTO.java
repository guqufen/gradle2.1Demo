package net.fnsco.api.dto;

import java.math.BigDecimal;

import net.fnsco.core.base.JO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 下午3:28:40
 */

public class TurnoverDTO extends JO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 8018206410433137948L;
    /**
     * 统计的营业额开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    
    /**
     * 订单数
     */
    private Integer orderNum;
    
    /**
     * 营业额
     */
    private Long turnover;
    
    /**
     * 客单价
     */
    private BigDecimal orderPrice;
    
    /**
     * 是否是店主的周报
     */
    private boolean isWeekLy;
    
    /**
     * 营业额类型: 0:昨天 1:上周 2:本日 3:本周 4:店主周报
     */
    private Integer type;
    
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
     * type
     *
     * @return  the type
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getType() {
        return type;
    }
    /**
     * type
     *
     * @param   type    the type to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setType(Integer type) {
        this.type = type;
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
    
    public Long getTurnover() {
        return turnover;
    }
    /**
     * turnover
     *
     * @param   turnover    the turnover to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTurnover(Long turnover) {
        this.turnover = turnover;
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
     * isWeekLy
     *
     * @return  the isWeekLy
     * @since   CodingExample Ver 1.0
    */
    
    public boolean isWeekLy() {
        return isWeekLy;
    }
    /**
     * isWeekLy
     *
     * @param   isWeekLy    the isWeekLy to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setWeekLy(boolean isWeekLy) {
        this.isWeekLy = isWeekLy;
    }
    
}
