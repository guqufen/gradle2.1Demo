package net.fnsco.bigdata.api.trade;

import java.util.List;

import net.fnsco.bigdata.api.dto.TradeDataDTO;
import net.fnsco.bigdata.service.domain.trade.TradeData;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.base.ResultPageDTO;

public interface TradeDataService {

    boolean saveTradeData(TradeDataDTO tradeData);
    
    ResultDTO<String> batchSaveTradeData(TradeData tradeData);

    boolean saveTradeData(TradeData tradeData);

    TradeData queryByTradeId(String tradeId);
    
    String queryByCertifyId(String certifyid);

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
    ResultPageDTO<TradeData> queryTradeData(TradeDataDTO tradeDataDTO, int currentPageNum, int perPageSize);
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
    List<TradeData> queryDataList(TradeDataDTO tradeDataDTO);
    /**
     * 
     * queryByTerminal:(查询流水信息)
     *
     * @param merchantCore
     * @return   MerchantCore    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int selectCountByIRT(TradeData tradeData);
    
    /**
     * 通过渠道终端号商户号和订单号(放于原订单号字段)查找交易
     * @param record
     * @return
     */
    TradeData selectByCMT(TradeData record);
    
    int updateByPrimaryKeySelective(TradeData record);
}
