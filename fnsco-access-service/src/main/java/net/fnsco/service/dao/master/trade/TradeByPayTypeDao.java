package net.fnsco.service.dao.master.trade;

import net.fnsco.service.domain.trade.TradeByPayType;
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

}