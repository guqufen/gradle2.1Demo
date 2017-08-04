package net.fnsco.order.service.dao.master.trade;

import java.util.List;

import net.fnsco.order.api.dto.TradeTypeDTO;
import net.fnsco.order.service.domain.trade.TradeByPayType;
/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午9:56:37
 */
public interface TradeByPayTypeDao {

    int insert(TradeByPayType record);

    int insertSelective(TradeByPayType record);
    /**
     * selectTradeDataByInnerCode:(这里用一句话描述这个方法的作用)按照innerCode和时间段查询数据
     *
     * @param record
     * @return    设定文件
     * @return TradeTypeDTO    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeTypeDTO> selectTradeDataByInnerCode(TradeByPayType record);
}