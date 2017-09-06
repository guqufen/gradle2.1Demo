package net.fnsco.bigdata.service.dao.master.trade;

import java.util.List;
import java.util.Map;

import net.fnsco.bigdata.service.domain.trade.TradeData;
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
public interface TradeDataDAO {

    int deleteByPrimaryKey(String id);

    int insert(TradeData record);

    int insertSelective(TradeData record);

    TradeData selectByPrimaryKey(String id);

    TradeData selectByMd5(String md5);

    int updateByPrimaryKeySelective(TradeData record);

    int updateByPrimaryKey(TradeData record);

    /**
     * 条件分页查询
     */
    List<TradeData> queryPageList(PageDTO<TradeData> pages);
    
    List<TradeData> queryByAllCondition(TradeData record);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(TradeData record);

    /**
     * queryByCondition:(这里用一句话描述这个方法的作用)
     *
     * @param record
     * @return    设定文件
     * @return List<TradeData>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeData> queryTempByCondition(TradeData record);

    /**
     * 
     * selectByIRT:(根据内部商务号终端号参数考)
     *
     * @param innerCode
     * @param referNo
     * @param termId
     * @return   TradeData    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TradeData selectByIRT(TradeData record);
    
    int selectCountByIRT(TradeData record);
    
    Map querySumByCondition(TradeData record);
}