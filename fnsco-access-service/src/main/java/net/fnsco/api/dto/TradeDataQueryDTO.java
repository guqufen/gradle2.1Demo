package net.fnsco.api.dto;

import java.util.List;

import net.fnsco.core.base.PageDTO;

public class TradeDataQueryDTO extends PageDTO {
    private Integer      userId;    //登录用户Id
    private String       innerCode; // 内部商务号
    private String       startDate; // 开始日期 2016-05-10
    private String       endDate;   //结束日期 2016-05-10
    private List<String> terminals; //终端号数组，所有选择的终端
    private String       tradeId;   //交易流水ID

    /**
     * tradeId
     *
     * @return  the tradeId
     * @since   CodingExample Ver 1.0
    */

    public String getTradeId() {
        return tradeId;
    }

    /**
     * tradeId
     *
     * @param   tradeId    the tradeId to set
     * @since   CodingExample Ver 1.0
     */

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
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
     * terminals
     *
     * @return  the terminals
     * @since   CodingExample Ver 1.0
    */

    public List<String> getTerminals() {
        return terminals;
    }

    /**
     * terminals
     *
     * @param   terminals    the terminals to set
     * @since   CodingExample Ver 1.0
     */

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }

}
