package net.fnsco.order.service.dao.master.trade;

import java.util.List;

import net.fnsco.order.api.dto.TradeHourDTO;
import net.fnsco.order.service.domain.trade.TradeByHour;
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
    /**
     * deleteByCondition:(这里用一句话描述这个方法的作用)根据条件查询
     *
     * @param record
     * @return    设定文件
     * @return int    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    int deleteByCondition(TradeByHour record);
    /**
     * selectByCondition:(这里用一句话描述这个方法的作用)根据时间点查询
     *
     * @param record
     * @return    设定文件
     * @return List<TradeHourDTO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeHourDTO> selectByCondition(TradeByHour record);
}