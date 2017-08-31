package net.fnsco.order.api.report.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc  财务对账 APP接口返回总接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月30日 上午10:28:20
 */

public class FinanceReportDTO extends DTO{
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 1942680747794101635L;
    /**
     * 商家的innerCode
     */
    private String innerCode;
    /**
     * 查询开始时间
     */
    private String startDate;
    /**
     * 查询结束时间
     */
    private String endDate;
    
    /**
     * 总交易额
     */
    private String totalTurnover;
    
    /**
     * 订单数
     */
    private Integer totalOrderNum;
    
    /**
     * 结算额
     */
    private String totalSettlementAmount;
    
    /**
     * 按照月份结算详情
     */
    private List<FinanceMouthDTO> tradeMouthDatas;

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
     * totalTurnover
     *
     * @return  the totalTurnover
     * @since   CodingExample Ver 1.0
    */
    
    public String getTotalTurnover() {
        return totalTurnover;
    }

    /**
     * totalTurnover
     *
     * @param   totalTurnover    the totalTurnover to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTotalTurnover(String totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    /**
     * totalOrderNum
     *
     * @return  the totalOrderNum
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    /**
     * totalOrderNum
     *
     * @param   totalOrderNum    the totalOrderNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    /**
     * totalSettlementAmount
     *
     * @return  the totalSettlementAmount
     * @since   CodingExample Ver 1.0
    */
    
    public String getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

    /**
     * totalSettlementAmount
     *
     * @param   totalSettlementAmount    the totalSettlementAmount to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTotalSettlementAmount(String totalSettlementAmount) {
        this.totalSettlementAmount = totalSettlementAmount;
    }

    /**
     * tradeMouthDatas
     *
     * @return  the tradeMouthDatas
     * @since   CodingExample Ver 1.0
    */
    
    public List<FinanceMouthDTO> getTradeMouthDatas() {
        return tradeMouthDatas;
    }

    /**
     * tradeMouthDatas
     *
     * @param   tradeMouthDatas    the tradeMouthDatas to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeMouthDatas(List<FinanceMouthDTO> tradeMouthDatas) {
        this.tradeMouthDatas = tradeMouthDatas;
    }
    
}
