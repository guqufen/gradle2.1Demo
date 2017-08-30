package net.fnsco.order.api.report.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月30日 上午11:39:21
 */

public class FinanceMouthDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = 4422660575236092607L;
    /**
     * 统计月份
     */
    private String tradeMonth;
    /**
     * 月份中每一天的数据
     */
    private List<FinanceDayDTO> tradeDayDatas;
    /**
     * tradeMonth
     *
     * @return  the tradeMonth
     * @since   CodingExample Ver 1.0
    */
    
    public String getTradeMonth() {
        return tradeMonth;
    }
    /**
     * tradeMonth
     *
     * @param   tradeMonth    the tradeMonth to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeMonth(String tradeMonth) {
        this.tradeMonth = tradeMonth;
    }
    /**
     * tradeDayDatas
     *
     * @return  the tradeDayDatas
     * @since   CodingExample Ver 1.0
    */
    
    public List<FinanceDayDTO> getTradeDayDatas() {
        return tradeDayDatas;
    }
    /**
     * tradeDayDatas
     *
     * @param   tradeDayDatas    the tradeDayDatas to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTradeDayDatas(List<FinanceDayDTO> tradeDayDatas) {
        this.tradeDayDatas = tradeDayDatas;
    }
    

}
