package net.fnsco.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc  交易统计查询DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 下午3:19:28
 */

public class TradeReportDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 3726864269950799521L;
    
    /**
     * APP用户ID
     */
    private Integer userId;
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
     * 查询历史时间页码
     */
    private Integer pageNum;
    
    
    /**
     * pageNum
     *
     * @return  the pageNum
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * pageNum
     *
     * @param   pageNum    the pageNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
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
    
}
