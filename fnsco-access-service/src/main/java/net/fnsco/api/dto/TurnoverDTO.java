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
     * 统计的营业额时间段
     */
    private String weeklyTime;
    
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
     * weeklyTime
     *
     * @return  the weeklyTime
     * @since   CodingExample Ver 1.0
    */
    
    public String getWeeklyTime() {
        return weeklyTime;
    }
    /**
     * weeklyTime
     *
     * @param   weeklyTime    the weeklyTime to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setWeeklyTime(String weeklyTime) {
        this.weeklyTime = weeklyTime;
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
