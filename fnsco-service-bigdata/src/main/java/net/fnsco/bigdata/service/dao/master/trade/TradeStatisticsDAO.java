package net.fnsco.bigdata.service.dao.master.trade;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.bigdata.service.domain.trade.TradeStatistics;
import net.fnsco.core.base.PageDTO;

/**
 * 
 * @desc
 * @author   tangliang
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年6月28日 下午3:42:26
 *
 */
public interface TradeStatisticsDAO {

    /**
     * 条件分页查询
     */
    List<TradeStatistics> queryPageList(PageDTO<TradeStatistics> pages);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(TradeStatistics record);
    /**
     * 通过innercode查询mer信息
     * @param innerCode
     * @return
     */
    TradeStatistics queryMerIdByInnerCode(@Param(value="innerCode") String innerCode);
}