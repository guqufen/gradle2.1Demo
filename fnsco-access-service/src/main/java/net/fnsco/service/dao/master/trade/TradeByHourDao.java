package net.fnsco.service.dao.master.trade;

import net.fnsco.service.domain.trade.TradeByHour;
/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午9:51:53
 */
public interface TradeByHourDao {

    int insert(TradeByHour record);

    int insertSelective(TradeByHour record);

}