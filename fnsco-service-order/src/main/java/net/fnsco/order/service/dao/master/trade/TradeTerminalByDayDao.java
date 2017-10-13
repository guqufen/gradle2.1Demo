package net.fnsco.order.service.dao.master.trade;

import java.util.List;

import net.fnsco.order.service.domain.trade.TradeTerminalByDay;
/**
 * @desc 按照终端号、天统计DAO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年10月13日 下午3:34:48
 */
public interface TradeTerminalByDayDao {

    int deleteByPrimaryKey(Integer id);

    int insert(TradeTerminalByDay record);

    int insertSelective(TradeTerminalByDay record);

    TradeTerminalByDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeTerminalByDay record);

    int updateByPrimaryKey(TradeTerminalByDay record);
    
    /**
     * deleteByCondition:(根据条件删除)
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年10月13日 下午3:51:40
     * @return int    DOM对象
     */
    int deleteByCondition(TradeTerminalByDay record);
    /**
     * insertBatch:(批量插入)
     * @param records
     * @return    设定文件
     * @author    tangliang
     * @date      2017年10月13日 下午3:56:59
     * @return int    DOM对象
     */
    int insertBatch(List<TradeTerminalByDay> records);
}