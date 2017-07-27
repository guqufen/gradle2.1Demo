package net.fnsco.service.dao.master.trade;

import net.fnsco.service.domain.trade.TradeByDay;
/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午9:45:15
 */
public interface TradeByDayDao {

    int insert(TradeByDay record);

    int insertSelective(TradeByDay record);
}