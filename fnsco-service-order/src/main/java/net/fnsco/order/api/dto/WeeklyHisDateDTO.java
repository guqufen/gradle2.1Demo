package net.fnsco.order.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月2日 上午9:19:37
 */

public class WeeklyHisDateDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -7770049977217288482L;
    /**
     * 当前页码
     */
    private Integer currentPageNum;
    /**
     * 总页码
     */
    private Integer totalPageNum;
    /**
     * 时间端集合
     */
    private List<DateDTO> hisDate;
    /**
     * currentPageNum
     *
     * @return  the currentPageNum
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getCurrentPageNum() {
        return currentPageNum;
    }
    /**
     * currentPageNum
     *
     * @param   currentPageNum    the currentPageNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setCurrentPageNum(Integer currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
    /**
     * totalPageNum
     *
     * @return  the totalPageNum
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getTotalPageNum() {
        return totalPageNum;
    }
    /**
     * totalPageNum
     *
     * @param   totalPageNum    the totalPageNum to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }
    /**
     * hisDate
     *
     * @return  the hisDate
     * @since   CodingExample Ver 1.0
    */
    
    public List<DateDTO> getHisDate() {
        return hisDate;
    }
    /**
     * hisDate
     *
     * @param   hisDate    the hisDate to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setHisDate(List<DateDTO> hisDate) {
        this.hisDate = hisDate;
    }
    
  
    
}
