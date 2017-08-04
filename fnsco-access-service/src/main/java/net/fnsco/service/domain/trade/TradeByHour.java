package net.fnsco.service.domain.trade;
/**
 * @desc 统计天的营业额实体
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午9:48:03
 */
public class TradeByHour {
    private Integer id;

    private String tradeHour;

    private String tradeDate;

    private String innerCode;

    private Long turnover;

    private Integer orderNum;

    private Long orderPrice;
    
    private String startTradeDate;//开始时间
    private String endTradeDate;//结束时间
    private Integer userId;//用户ID
    private String roleId;//角色ID
    
    /**
     * startTradeDate
     *
     * @return  the startTradeDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getStartTradeDate() {
        return startTradeDate;
    }

    /**
     * startTradeDate
     *
     * @param   startTradeDate    the startTradeDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setStartTradeDate(String startTradeDate) {
        this.startTradeDate = startTradeDate;
    }

    /**
     * endTradeDate
     *
     * @return  the endTradeDate
     * @since   CodingExample Ver 1.0
    */
    
    public String getEndTradeDate() {
        return endTradeDate;
    }

    /**
     * endTradeDate
     *
     * @param   endTradeDate    the endTradeDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setEndTradeDate(String endTradeDate) {
        this.endTradeDate = endTradeDate;
    }

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
     * roleId
     *
     * @return  the roleId
     * @since   CodingExample Ver 1.0
    */
    
    public String getRoleId() {
        return roleId;
    }

    /**
     * roleId
     *
     * @param   roleId    the roleId to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeHour() {
        return tradeHour;
    }

    public void setTradeHour(String tradeHour) {
        this.tradeHour = tradeHour == null ? null : tradeHour.trim();
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public Long getTurnover() {
        return turnover;
    }

    public void setTurnover(Long turnover) {
        this.turnover = turnover;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }
}