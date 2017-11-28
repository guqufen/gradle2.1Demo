package net.fnsco.bigdata.api.trade;

import java.util.List;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.service.domain.trade.TradeDataLkl;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 拉卡拉流水统计
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年11月27日 下午3:59:57
 */

public interface LklTradeDataService {
	
	/**
     * queryTradeData:(这里用一句话描述这个方法的作用) web分页查询
     * @param merchantCore
     * @param currentPageNum
     * @param perPageSize
     * @return    设定文件
     * @return ResultPageDTO<TradeDataDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    ResultPageDTO<TradeDataLkl> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize);
    
    /**
     * 总金额查询
     * @param tradeDataDTO
     * @return
     */
    String queryTotalAmount(TradeDataDTO tradeDataDTO);
    
    /**
     * 查询符合条件的数据用于excel导出
     * @param tradeDataDTO
     * @return
     */
     List<TradeDataLkl> queryDataList(TradeDataDTO tradeDataDTO);
}
